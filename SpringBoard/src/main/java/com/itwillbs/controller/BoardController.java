package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String registPOST(BoardVO vo,RedirectAttributes rttr,@RequestParam("title") String title) throws Exception{
		
		
		mylog.debug("/board/regist(POST) 호출");
		mylog.debug("GET방식의 데이터 전달 -> DB저장 -> 페이지 이동");
		mylog.debug(title);
		
		//0. 한글처리(필터)
		
		//1. 전달된 정보 저장(title,content,writer)
		mylog.debug(vo.toString());
		
		//2. 서비스 -> DAO 접근 (mapper)
		service.insertBoard(vo);		
		mylog.debug("게시판 글쓰기 완료");
		
//		model.addAttribute("result","createOk");
		rttr.addFlashAttribute("result","createOK");
		
		//3. 페이지로 이동(list페이지)
		return "redirect:/board/list";
	}
	
	// http://localhost:8080/board/list?result=createOk
	// http://localhost:8080/board/list
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public void listGET(HttpSession session, Model model, @ModelAttribute("result") String result) throws Exception{
		mylog.debug("/board/list 호출 -> DB정보 가져와서 출력");
		
		//전달받은정보 없음
		mylog.debug("전달정보 :"+result);
		
		//세션객체 - 글 조회수 증가 체크정보
		session.setAttribute("updateCheck",true);
		
		
		//서비스-> DAO 게시판 리스트 가져오기
		List<BoardVO> boardList = service.getBoardListAll();
		//연결되어 있는 뷰페이지로 정보 전달 (Model 객체)
		model.addAttribute("boardList", boardList);
		
		//페이지 이동(board/list.jsp)
		
	}
	
	
	//@ModelAttribute : Model 객체에 저장 -> Model 객체에 저장 (1:n 관계)
	//@Requestparam : request.getParameter() 동작 (1:1관계)
//						-> 문자열, 숫자, 날짜 등 데이터 형변환 가능
	
	// http://localhost:8080/board/read?bno=1
	//게시판 본문보기
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void readGET(Model model,@RequestParam("bno") int bno, HttpSession session) throws Exception{
		//전달정보 (bno)
		mylog.debug("전달정보 (bno): " +bno);
		
		//세션객체
		boolean isUpdateCheck = (boolean) session.getAttribute("updateCheck");
		mylog.debug("조회수 증가 상태 : "+isUpdateCheck);
		
		if(isUpdateCheck) {//true
			//서비스 -> DAO 동작 호출
			//조회수 1증가 (list -> read 증가, 새로고침은 증가안함)
			service.updateViewcnt(bno);
			mylog.debug("조회수 1증가");
			
			// 상태 변경(true -> false)
			session.setAttribute("updateCheck", !isUpdateCheck);
		}
		//서비스 -> DAO (특정 글번호에 해당하는 정보 가져오기)
		BoardVO vo = service.getBoard(bno);
		
		
		//연결된 뷰페이지로 정보 전달(model)
		model.addAttribute("vo", vo);
		
//		model.addAttribute(service.getBoard(bno));
		//이렇게도 가능
		
		
	}
	
	//수정 GET
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(Model model,int bno) throws Exception{
		//파라미터 저장(bno)
		//서비스 - DAO(글 조회)
		//model 객체 사용 - view 페이지로 정보 전달
		model.addAttribute("vo",service.getBoard(bno));
		// /board/modify.jsp 페이지 이동
		
	}
	
	//수정 POST
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPOST(BoardVO vo,RedirectAttributes rttr) throws Exception{
		//전달된 정보(수정할 정보) 저장
		mylog.debug(vo+"");
		
		//서비스 - DAO : 정보 수정 메서드 호출
		int result = service.updateBoard(vo);
		
		if(result > 0) {
			//수정완료 - 정보 전달
			rttr.addFlashAttribute("result", "modOK");	
		}
		
		//페이지 이동(/board/list)
		return "redirect:/board/list";
	}
	
	//삭제하기
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String removePOST(int bno,RedirectAttributes rttr) throws Exception{
		//전달정보 저장(bno)
		
		//서비스 - DAO : 게시판글 삭제 메서드 호출
		service.deleteBoard(bno);
		
		
		//삭제완료 정보를 list페이지로 전달
		rttr.addFlashAttribute("result", "delOK");	
		
		
		//게시판 리스트로 이동(/board/list)
		return "redirect:/board/list";
	}
}
