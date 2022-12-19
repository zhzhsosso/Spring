package com.itwillbs.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice : 컨트롤러에서 발생하는 모든 예외를 처리하는 객체

@ControllerAdvice
public class CommonExceptionAdvice {
	//Advice: 보저기능을 구현한 객체
	
	private static final Logger mylog = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	//http://localhost:8080/board/read
//	@ExceptionHandler(Exception.class)
//	public String commonTest1(Exception e) {
//		//Exception이 발생하면 이 메서드 실행함
//		mylog.debug("예외 발생!!!");
//		mylog.debug(e.getMessage());
//		
//		return "test1";
//	}
	
	
	//이게 젤 좋은방법!!!
//	@ExceptionHandler(Exception.class)
//	public String commonTest2(Exception e, Model model) {
//		//Exception이 발생하면 이 메서드 실행함
//		mylog.debug("예외 발생!!!");
//		mylog.debug(e.getMessage());
//		model.addAttribute("err", e.getMessage());
//		
//		return "test1";
//	}
	
	//요즘 잘안씀
	//기존의 객체는 컨트롤러가 아니기 때문에, model 객체 사용 x
	//=>model, view 정보를 한번에 전달가능한 객체 ModelAndView 정보 전달
	@ExceptionHandler(Exception.class)
	public ModelAndView commonTest3(Exception e, Model model) {
		//Exception이 발생하면 이 메서드 실행함
		mylog.debug("예외 발생!!!");
		mylog.debug(e.getMessage());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/test1");
		mav.addObject("err", e.getMessage());
		
		return mav;
	}
}
