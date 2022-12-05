package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	//동작 처리 이후에 페이지 이동
	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);
	
	//http://localhost:8080/web/doE
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr/*Model model*/) {
		logger.info("EEEEEEEEEEEEEEEEEE");
		
//		rttr.addAttribute("msg","ITWILL");//기존의 모델 객체와 동일
		//URL에 표시됨, 새로고침시 데이터 유지
		//기존의 이동방식 사용가능
		
		rttr.addFlashAttribute("msg","ITWILL");
		//전달 정보를 1회성으로 사용가능
		//URI에 표시안됨, 새로고침시 데이터 사라짐
		//redirect 이동시만 사용가능
		
		
//		model.addAttribute("msg","itwill");
		//return "/doF"; //리다이렉트x, 뷰페이지 호출o
//		return "forward:/doF"; //포워딩o
		return "redirect:/doF?msg=hello"; //리다이렉트 o
	}
	
	
	//http://localhost:8080/web/doF
	@RequestMapping("/doF")
	public String doF(@ModelAttribute("msg") String msg) {
		logger.info("FFFFFFFFFFFFFFFFF");
		logger.info("msg : "+msg);
		return "doF실행";
	}
}
