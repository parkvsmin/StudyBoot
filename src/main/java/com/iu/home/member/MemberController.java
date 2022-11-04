package com.iu.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;



@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	//private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberService memberService;

	@GetMapping("add")
	public void setAdd(@ModelAttribute MemberVO memberVO)throws Exception {
		//MemberVO memberVO = new  MemberVO();
		//model.addAttribute(memberVO);
	}

	@PostMapping("add")
	public ModelAndView setAdd(@Valid MemberVO memberVO, BindingResult bindingResult, ModelAndView mv)throws Exception{
		//		if(bindingResult.hasErrors()) {
		//			//검승 실패하면 회원가입 jsp로 이동
		//			log.info("=====검증오류=====");
		//			mv.setViewName("member/add");
		//			return mv;
		//		}

		boolean check = memberService.getMemberError(memberVO, bindingResult);
		if(check) {
			mv.setViewName("member/add");

			List<FieldError> errors = bindingResult.getFieldErrors();			
			for(FieldError fieldError:errors) {	
				log.info("FieldError => {} ",fieldError);
				log.info("Field => {} ",fieldError.getField());
				log.info("Message => {}",fieldError.getRejectedValue());
				log.info("Default => {}",fieldError.getDefaultMessage());
				log.info("Code => {}",fieldError.getCode());
				mv.addObject(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return mv;
		}

		int result = memberService.setAdd(memberVO); 
		mv.setViewName("redirect:../");
		return mv;
	}

	@GetMapping("login")
	public void getLogin(@RequestParam(defaultValue = "false", required = false) boolean error, String message,Model model)throws Exception {
		if(error) {
			model.addAttribute("msg","ID 또는 PW를 확인하세요");
		}
		
		
		//Controller에서 받아서 jsp로 다시 보내도 됨	
	}
	
	@PostMapping("login")
	public String getLogin()throws Exception {
		log.info("=====Login Post=====");
		return "member/login";

	}

//	@PostMapping("login")
//	public String getLogin(MemberVO memberVO, HttpSession session)throws Exception {
//		memberVO = memberService.getLogin(memberVO);
//		session.setAttribute("member", memberVO);
//		return "redirect:../";
//
//	}

//	@GetMapping("logout")
//	public String getLogout(HttpSession session)throws Exception {
//		log.info("=====내가만든 로그아웃 메서드=====");
//		session.invalidate();
//		return "redirect:../";
//
//	}
	
	@GetMapping("mypage")
	public void getMyPage()throws Exception {
		
	}
	

	@GetMapping("idCheck")
	@ResponseBody
	public int getIdCheck(MemberVO memberVO)throws Exception {
		return memberService.getIdCheck(memberVO);
		//		int result=0;
		//		
		//		if(memberVO !=null) {
		//			result=1;
		//		}
		//		
		//		return result;

	}

}
