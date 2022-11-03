package com.iu.home;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;
import com.iu.home.member.MemberVO;

@Controller
public class HomeController {
	
	//@Value("${my.message.hi}")
	private String message;
	//@Value("${my.default}")
	private String app;
	
	//private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QnaMapper qnaMapper;
	
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
	
	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
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
