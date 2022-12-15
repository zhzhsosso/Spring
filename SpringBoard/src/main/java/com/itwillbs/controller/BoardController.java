package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger mylog = LoggerFactory.getLogger(BoardController.class);
	
	//서비스 객체 - 주입
	@Inject
	private BoardService service;
	
	// http://localhost:8080/board/regist
	
	//게시판 글쓰기 GET
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public void registGET() throws Exception {
		mylog.debug("/board/regist(GET) 호출 -> 페이지 이동");
	}
	
	//게시판 글쓰기 POST
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String registPOST(BoardVO vo) throws Exception{
		mylog.debug("/board/regist(POST) 호출");
		mylog.debug("GET방식의 데이터 전달 -> DB저장 -> 페이지 이동");
		
		//0. 한글처리(필터)
		
		//1. 전달된 정보 저장(title,content,writer)
		mylog.debug(vo.toString());
		
		//2. 서비스 -> DAO 접근 (mapper)
		service.insertBoard(vo);		
		mylog.debug("게시판 글쓰기 완료");
		
		//3. 페이지로 이동(list페이지)
		return "redirect:/board/list";
	}
	
}
