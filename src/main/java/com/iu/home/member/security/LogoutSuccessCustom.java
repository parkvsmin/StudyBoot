package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.iu.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutSuccessCustom implements LogoutSuccessHandler {
	
	@Value("${my.client_id}")
	private String clientId;
	@Value("${my.logout_redirect_uri}")
	private String redirectUri;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		MemberVO memberVO = (MemberVO)authentication.getPrincipal(); //memberVO
		String social = memberVO.getSocial();
		if(social != null) {
			if(social.equals("kakao")) {
//				RestTemplate restTemplate = new RestTemplate();
//				ResponseEntity<String> res = restTemplate.getForEntity("https://developers.kakao.com/logout", String.class, request);
//				log.info("response => {} ",res);
//				response.sendRedirect("/");
					//https://developers.kakao.com/logout
					response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=" + clientId + "&logout_redirect_uri=" + redirectUri);
		
			}else if(social.equals("google")) {

			}else {

			}
		}else {
			log.info("=====LogoutSucessHandler 성공시에만 실행=====");
			response.sendRedirect("/");
			
		}

	}

}
