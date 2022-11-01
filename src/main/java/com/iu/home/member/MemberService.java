package com.iu.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

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

	public MemberVO getLogin(MemberVO memberVO)throws Exception {
		return memberMapper.getLogin(memberVO);

	}

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


}
