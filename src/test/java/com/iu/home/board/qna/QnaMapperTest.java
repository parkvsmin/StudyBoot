//package com.iu.home.board.qna;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.iu.home.util.Pager;
//
//
//
//@SpringBootTest
////상속받지 않고 SpringBoot Test를 작성
////@Rollback(true)
//@Transactional
//class QnaMapperTest {
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
//	@Value("${my.default}")
//	private String app;
//	
//	@Autowired
//	private QnaMapper qnaMapper;
//
//	private QnaVO qnaVO;
//	
//	@BeforeAll
//	static void beforeAll() {
//		System.out.println("전체Test실행 전");
//	}
//
//	@AfterAll
//	static void afterAll() {
//		System.out.println("전체Test실행 후");
//	}
//
//	@BeforeEach
//	void beforeEach() throws Exception {
//		System.out.println("Test메서드 실행 전");
//
//		for(int i = 0; i<100; i++) {            
//			qnaVO = new QnaVO();
//			qnaVO.setNum(null);   
//			qnaVO.setWriter("작성자"+i);
//			qnaVO.setTitle("제목"+i);
//			qnaVO.setContents("내용"+i);
//			qnaVO.setRegDate(null);
//			qnaVO.setHit(1L);
//			qnaVO.setRef(1L);
//			qnaVO.setStep(1L);
//			qnaVO.setDepth(1L);
//
//			int result  = qnaMapper.setAdd(qnaVO);
//			if(result > 0) {
//				log.info("성공");
//			}else {
//				log.info("실패");
//			}
//		}
//
//	}
//
//	@AfterEach
//	void afterEach() {
//		System.out.println("Test메서드 실행 후");
//	}
//
//	@Test
//	void Test2() {
//		log.info("Test2 Case");
//	}
//
//	//@Test
//	void test(Pager pager) throws Exception{
//		List<QnaVO> ar = qnaMapper.getList(pager);
//		log.info("list {}", ar);
//		assertNotEquals(0, ar.size());
//	}
//
//	//@Test
//	void qnaAdd()throws Exception{
//		QnaVO qnaVO = new QnaVO();
//		qnaVO.setNum(5L);
//		qnaVO.setWriter("작성자");
//		qnaVO.setTitle("제목");
//		qnaVO.setContents("내용");
//		qnaVO.setHit(1L);
//		qnaVO.setRegDate(null);
//		qnaVO.setRef(1L);
//		qnaVO.setStep(1L);
//		qnaVO.setDepth(1L);
//
//		int result  = qnaMapper.setAdd(qnaVO);
//
//		if(result > 0) {
//			log.info("성공");
//		}else {
//			log.info("실패");
//		}
//	}
//
//}