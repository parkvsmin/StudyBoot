package com.iu.home.member;

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
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("=====Social Login 정보=====");
		log.info("Name => {} ",oAuth2User.getName());
		log.info("Attr => {} ",oAuth2User.getAttributes());
		log.info("Auth => {} ",oAuth2User.getAuthorities());
		return null;
	}

}
