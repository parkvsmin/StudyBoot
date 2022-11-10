package com.iu.home.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Value("${my.social.kakao.admin}")
	private String adminKey;

	public int setAdd(MemberVO memberVO)throws Exception {
		int result = memberMapper.setAdd(memberVO);

		if(result<1) {
			throw new Exception();
		}

		result = memberMapper.setMemberRole(memberVO);

		if(result<1) {
			throw new Exception();
		}

		return result;

	}
    
	//로그인 처리는 Security에서
//	public MemberVO getLogin(MemberVO memberVO)throws Exception {
//		return memberMapper.getLogin(memberVO);
//
//	}

	public Integer getIdCheck(MemberVO memberVO)throws Exception {
		return memberMapper.getIdCheck(memberVO);
	}

	//사용자 정의 검증 메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception {
		boolean check=false;
		//check=false : 검증성공(error 없음)
		//check=true : 검증실패(error 있음)

		//1. annotation 검증
		check = bindingResult.hasErrors();
		//2. password가 일치하는지 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check=true;
			//에러메세지
			//bindingResult.rejectValue("멤버변수명(path)", "properties의Key(코드)");
			bindingResult.rejectValue("passwordCheck", "member.password.notEqual");
		}
		
		//3. id가 중복 인지 검증
		if(!memberVO.getId().equals(memberVO.getIdCheck())) {
			check=true;
			bindingResult.rejectValue("idCheck", "member.id.equal");
		}
		return check;

	}
	
	public int setDelete(MemberVO memberVO) throws Exception {
		//1. WebClient 생성
		WebClient webClient = WebClient.builder()
									   .baseUrl("https://kapi.kakao.com/")
									   .build();
		
		//2. parameter
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("target_id_type", "user_id");
		map.add("target_id", memberVO.getId());
		
		Mono<String> res = webClient.post()
				 .uri("v1/user/unlink")
				 .body(BodyInserters.fromFormData(map))
				 .header("Authorization","KakaoAK "+adminKey)
				 .header("Content-Type: application/x-www-form-urlencoded")
				 .retrieve()
				 .bodyToMono(String.class);
		
		log.info("WebClintResult -> {}",res.block());
				 return 1;
	}
	
	public int setDelete2(MemberVO memberVO) throws Exception {
		int result =0;
		RestTemplate restTemplate = new RestTemplate();
		//Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//application/x-www-form-urlencoded
		headers.add("Authorization", "KakaoAK "+adminKey);
		
		//parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_Id");
		params.add("target_id", memberVO.getId());
		
		//요청객체
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);
		
		//전송 후 결과 처리
		ResponseEntity<String> res =restTemplate.postForEntity("https://kapi.kakao.com/v1/user/unlink", req, String.class);
		
		log.info("res => {}",res.getBody());
		
		if(res.getBody()!=null) {
			result=1;
		}
		return result;
	}


}
