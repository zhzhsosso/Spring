package com.itwillbs.web;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.persistence.MemberDAOImpl;
import com.itwillbs.service.MemberService;

/*
 * 컨트롤러별 공통 URI 
 * 각 주소(URI)별 호출방식 결정(GET/POST)
 * GET - 페이지 조회, 정보 입력페이지
 * POSt - 개발자가 작업을 수행하는 페이지
 * 메서드 실행결과 처리 및 이동페이지 설정
 * 예외처리
 */



@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger mylog = LoggerFactory.getLogger(MemberController.class);
	
	//서비스 객체 주입
	@Inject
	private MemberService service;
	// http://localhost:8080/web/insertForm(x)
	//톰캣 path 설정후
	// http://localhost:8080/member/insert (o)
	//회원가입(insert)
	@RequestMapping(value="insert", method= RequestMethod.GET)
	public String joinGET() throws Exception{
		mylog.info("/member/insertForm->정보입력창(view)이동");
		//페이지 이동(컨트롤러 주소 포함)
		return "/member/insertForm";
	
	}
	//회원가입-처리(insert)
	@RequestMapping(value="insert",method=RequestMethod.POST)
	public String joinPOST(MemberVO vo) throws Exception{
		mylog.info("/member/insertPro -> DB 정보저장");
		//한글처리 (필터)
		
		//전달정보 저장
		mylog.info(vo+"");
		mylog.info(vo.toString());
		
		//DB 저장 -> DAO 객체 생성 - 회원가입메서드 호출
//		MemberDAO dao = new MemberDAOImpl();
//		dao.insertMember(vo);
		//=> 서비스를 통한 DAO 호출
		service.memberJoin(vo);
		
		//로그인페이지 이동
		return "redirect:/member/login";
		
	}
	
	//로그인()
	@GetMapping(value="/login")
	public void loginGET() throws Exception{
			mylog.debug("loginGET()호출");
			//연결된 뷰페이지 구현
			
	}
	
	//로그인()
	@PostMapping(value="/login")
	public String loginPOST(MemberVO vo,HttpSession session/*, @RequestParam("userid") String userid*/) throws Exception{
			mylog.debug("loginPOST()호출");
			
			//전달정보 저장(userid,userpw)
			mylog.debug("로그인정보: "+vo);
			
			//서비스 - DAO(로그인체크)
			boolean loginStatus = service.memberLogin(vo);
			mylog.debug("로그인 상태 : "+loginStatus);
			
			//로그인 여부에 따라서 페이지이동
			//성공 - main페이지
			//실패 - login페이지
			String resultURI="";
			if(loginStatus) {
//				return "redirect:/member/main";
				resultURI = "redirect:/member/main";
				session.setAttribute("id", vo.getUserid());
			}else {
//				return "redirect:/member/login";
				resultURI = "redirect:/member/main";
			}
			
			return resultURI;
			
			
	}
	//메인페이지
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public void mainGET() throws Exception{
		mylog.info("mainGET() 호출");
		//연결된 뷰페이지 호출
	}
	
	//로그아웃
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logoutGET(HttpSession session) throws Exception{
		//세션초기화
		session.invalidate();
		//페이지 이동(로그인페이지)
		return "redirect:/member/login";
		
	}
}
