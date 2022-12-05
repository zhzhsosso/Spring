package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// @Controller : 해당 클래스가 컨트롤러역활 수행

@Controller
@RequestMapping("/test/*")
public class SampleController1 {
	
	// 로그출력 객체
	private static final Logger logger
	     = LoggerFactory.getLogger(SampleController1.class);
	
	// * 메서드의 리턴타입이 void 경우
	
	 // http://localhost:8080/web/doA
	// http://localhost:8080/web/test/doA
	 @RequestMapping(value = "/doA")
	 public void doA() {
		 logger.info("/doA 호출 -> doA() 실행");
		 
	 }
	
	 // /doB 주소 호출되는 doB()메서드
	 // http://localhost:8080/web/doB
	 //@RequestMapping("/doB")
	 //@RequestMapping(value = "/doB",method = RequestMethod.GET)
	 //@PostMapping
	 @GetMapping(value = "/doB")
	 public void doB() {
		 logger.info(" doB() 실행!!!!!! ");
	 }
	

}
