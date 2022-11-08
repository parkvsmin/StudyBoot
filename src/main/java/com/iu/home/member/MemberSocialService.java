package com.iu.home.member;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSocialService extends DefaultOAuth2UserService {

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		log.info("=====Social Login 시도=====");
		log.info("UserRequest => {}", userRequest);
		log.info("AccessTokern => {}",userRequest.getAccessToken());
		log.info("ClientReg => {} ",userRequest.getClientRegistration());
		log.info(" => {} ",userRequest.getAdditionalParameters());


		String social = userRequest.getClientRegistration().getRegistrationId();
		log.info("Social => {} ",social);

		OAuth2User oAuth2User = this.socialJoinCheck(userRequest);

		return oAuth2User;

	}

	private OAuth2User socialJoinCheck(OAuth2UserRequest userRequest) {
		//회원가입 유무 판단 
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("=====Social Login 정보=====");
		log.info("Name => {} ",oAuth2User.getName());
		log.info("Attr => {} ",oAuth2User.getAttributes());
		log.info("Auth => {} ",oAuth2User.getAuthorities());
		
		Map<String, Object> map = oAuth2User.getAttributes();
		
		//Key들을 꺼내기
		Iterator<String> keys = map.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			log.info("Key => {} ",key);
			
		}
		
		LinkedHashMap<String, String> lm = oAuth2User.getAttribute("properties");
		Map<String, Object> ka = oAuth2User.getAttribute("kakao_account");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(oAuth2User.getName());
		
//		//password가 없으므로 비워두거나 랜던함 값으로 생성
//		memberVO.setPassword(null);
		memberVO.setName(lm.get("nickname"));
		memberVO.setEmail(ka.get("email").toString());
		
		memberVO.setSocial(userRequest.getClientRegistration().getRegistrationId());
		memberVO.setAttributes(oAuth2User.getAttributes());
		
		//Role 임의로 작성
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_NUMBER");
		
		memberVO.setRoleVOs(list);
		
		return memberVO;
	}

}
