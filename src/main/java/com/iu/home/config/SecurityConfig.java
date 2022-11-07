package com.iu.home.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.iu.home.member.MemberSecurityService;
import com.iu.home.member.MemberSocialService;
import com.iu.home.member.security.LoginFail;
import com.iu.home.member.security.LoginSuccess;
import com.iu.home.member.security.LogoutCustom;
import com.iu.home.member.security.LogoutSuccessCustom;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private LoginSuccess loginSuccess;
	@Autowired
	private LoginFail loginFail;
	@Autowired
	private LogoutCustom logoutCustom;
	@Autowired
	private LogoutSuccessCustom logoutSuccessCustom;
	@Autowired
	private MemberSecurityService memberSecurityService;
	@Autowired
	private MemberSocialService memberSocialService;
	
	@Bean
	//public을 선언하면 default로 바꾸라는 메세지 출력
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야하는 URL 패턴 등록 (보안상관X)
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon/**")
				.antMatchers("/resources/**");
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception {
		httpSecurity
				.cors()
					.configurationSource(this.corsConfigurationSource()) 
					.and()
					.csrf()
					.disable()
				.authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/admin").hasRole("ADMIN")
					.antMatchers("/manager").hasAnyRole("ADMIN","MANAGER")
					.antMatchers("/qna/list").permitAll()
					//.antMatchers("/qna/**").hasRole("MEMBER")
					.anyRequest().permitAll()
					.and()
					
			.formLogin() // 로그인 폼 인증 설정
				.loginPage("/member/login") // 내장된 로그인폼을 사용하지 않고 개발자가 만든 폼요청 URL
				//.loginProcessingUrl("login") // 로그인을 요청할 form 태그의 action의 주소 지정
				.passwordParameter("password") // 패스워드에 파라미터는 password이지만 개발자가 다른 파라미터 이름을 사용할 떄
				.usernameParameter("id") // 아이디에 파라미터는 username이지만 개발자가 다른 파라미터 이름을 사용할 떄
				//.defaultSuccessUrl("/") // 인증에 성공할 경우 요청할 URL
				.successHandler(loginSuccess)
				//.failureUrl("/member/login?error=true&message=loginFail") //인증에 실패했을 경우 요청할 URL
				.failureHandler(loginFail)
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")
				//.logoutSuccessUrl("/")
				.logoutSuccessHandler(logoutSuccessCustom)
				.addLogoutHandler(logoutCustom)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
				.and()
			.rememberMe()
				.rememberMeParameter("rememberMe") 
				.tokenValiditySeconds(300)       
				.key("rememberMe")  
				.userDetailsService(memberSecurityService)
				.authenticationSuccessHandler(loginSuccess)
				.and()
			.oauth2Login() // Social Login 설정
				.userInfoEndpoint()
				.userService(memberSocialService);
		
		return httpSecurity.build();
	}
	

	//평문(Clear Text)을 암호화 시켜주는 객체생성
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//@Bean
		public CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500", "http://192.168.1.20:5500", "http://192.168.1.2:5500", "*"));//List<Integer>
			configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
			
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			
			
			return source;
		}
	

}
