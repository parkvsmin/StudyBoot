package com.iu.home.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberVO implements UserDetails{
	
	@NotBlank(message = "ID는 꼭 필요해 !!!")
	private String id;
	@NotBlank(message = "중복된 ID 입니다 !!!")
	private String idCheck;
	@NotBlank
//	@Size(max=10, min=8)
	private String password;
	private String passwordCheck;
	@NotBlank
	private String name;
	@Email
	@NotBlank
	private String email;
	@Range(max=150, min=0)
	private int age;
	@Past()
	private Date birth;
	private boolean enabled;
	
	private List<RoleVO> roleVOs;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO : roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
			
		}
		
		return authorities;
	}
	
//	@Override
//	public String getPassword() {
//		return this.getPassword;
//	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		// ID 반환
		return this.id;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		// 계정의 만료 여부
		// true : 만료 안됨
		// false : 만료됨 (로그인 불가)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		// 계정의 잠김 여부
		// true : 계정이 잠기지 않음
		// false : 계정이 잠김 (로그인 불가)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		// 비밀번호 만료 여부
		// true : 만료 안됨
		// false : 만료됨 (로그인 불가)
		return true;
	}
	
	//isEnabled
	//계정 사용 여부
	// true : 계정 활성화(계정 사용 가능)
	// false : 계정 비활성화(계정 사용 불가 로그인 불가)
	public boolean isEnabled() {
		return true;
	}
}
