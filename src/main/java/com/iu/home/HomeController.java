package com.iu.home;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.iu.home.board.qna.PostVO;
import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;
import com.iu.home.member.MemberVO;
import com.iu.home.util.TestInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Controller
public class HomeController {
	
	@Value("${my.client_id}")
	private String clientId;
	
	//@Value("${my.message.hi}")
	private String message;
	//@Value("${my.default}")
	private String app;
	
	//private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@GetMapping("/arrow")
	public void arrow() {
		//Lamda식(JS Arrow Function)
		TestInterface t = (m)->System.out.println(m);
		t.info("test");
		
		TestInterface t2 = new TestInterface() {
			
			@Override
			public void info(String message) {
				// TODO Auto-generated method stub
				System.out.println(message);
				
			}
		};
		t2.info("test");
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String member() {
		return "Member Role";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "Manager Role";
	}
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "Admin Role";
	}
	
	
	@GetMapping("/address")
	@ResponseBody
	public String address()throws Exception {
		//kakao 지도 요청
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK"+clientId);
		
		MultiValueMap<String, String>params = new LinkedMultiValueMap<>();
		params.add("query", "전북 삼성동 180");
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String,String>>(params,headers);
		ResponseEntity<String> res = restTemplate.getForEntity("https://dapi.kakao.com/v2/local/search/address.json", String.class, req);
		
		return res.getBody();
	}
	
	@GetMapping("/web")
	public String webClientTest() {
		WebClient webClient = WebClient.builder()
				   .baseUrl("https://jsonplaceholder.typicode.com/")
				   .build();
		Flux<PostVO> res = webClient.get()
				.uri("posts")
				.retrieve()
				.bodyToFlux(PostVO.class);


		PostVO postVO = res.blockFirst();
		
//		public void (PostVO postVO) {}
//		a.test(postVO)
		
		res.subscribe((s)->{
			PostVO pvo = s;
			log.info("ID => {}",s.getId());
		});
		
		log.info("Result => {}",postVO);
							 
		return "";
	}
	
	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
//		headers.add("key","value");
		
		//2. parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("key", "value");
		//3. 요청 정보 객체 (1,2번을 모음)
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		//4 전송 후 결과
		List<PostVO> posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class, request);
		//PostVO postVO = response.getBody();
		log.info("PostVO => {}",posts);
		
		log.info("====================");
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			log.info("Key => {} ",key);
		}
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("Obj => {} ",obj);
		
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(context != null) {
			log.info("Context => {} ", context);
		}
		
		log.info("Message : {} ", message);
		log.info("default : {} ", app);
		log.info("====================");
	
		
		//List<QnaVO> ar = qnaMapper.getList(pager);
		
		//log.info("List : {}  size  {}",ar,ar.size());
		//System.out.println("List :"+ar +"size :"+ar.size());		
		return "index";
	}

}
