package com.iu.home;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;

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
	
	@GetMapping("/")
	public String home() throws Exception {
		log.info("====================");
		log.info("Message : {} ", message);
		log.info("default : {} ", app);
		log.info("====================");
	
		
		//List<QnaVO> ar = qnaMapper.getList(pager);
		
		//log.info("List : {}  size  {}",ar,ar.size());
		//System.out.println("List :"+ar +"size :"+ar.size());		
		return "index";
	}

}
