//package com.iu.home.aop.test;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//@Aspect
//public class Card {
//	
//	@Before("execution(* com.iu.home.aop.test.Transport.airplane())")
//	public void before() {
//		log.info("-----Before------");
//	}
//	
//	@After("execution(* com.iu.home.aop.test.Transport.get*())")
//	public void after() {
//		log.info("-----After------");
//	}
//	
//	@AfterThrowing("execution(* com.iu.home.aop.test.Transport.take*())")
//	public Object cardCheck(ProceedingJoinPoint joinPoint)throws Throwable {
//		log.info("-----삐빅 승차 입니다-----");
//		Object obj = joinPoint.proceed();
//		log.info("-----삐빅 하차 입니다-----");
//		return obj;
//	}
//	
//
//}
