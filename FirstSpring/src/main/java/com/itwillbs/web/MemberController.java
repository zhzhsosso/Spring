package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

//

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
	public String mainGET(HttpSession session) throws Exception{
		mylog.info("mainGET() 호출");
		
		if(session.getAttribute("id") == null) {
			mylog.debug("아이디 정보 없음");
			return "redirect:/member/login";
		}
		//연결된 뷰페이지 호출
		return "/member/main";
	}
	
	//로그아웃
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logoutGET(HttpSession session) throws Exception{
		//세션초기화
		session.invalidate();
		//페이지 이동(로그인페이지)
		return "redirect:/member/login";
		
	}
	
	//회원 정보 조회
	@RequestMapping(value = "/info",method= RequestMethod.GET)
	public void infoGET(HttpSession session,Model model) throws Exception{
		mylog.debug("infoGET() 호출");
		
		//전달정보(x) ->세션정보(id) 필요
		String id= (String) session.getAttribute("id");		
		
		//db가서 회원정보 가져오기 -> 서비스계층
		MemberVO vo = service.getMember(id);	
		
		//db정보를 뷰페이지로 전달(request->Model)
		model.addAttribute("vo",vo);
		model.addAttribute(service.getMember(id));
		
		//view페이지 이동
		
	}
	
	//회원정보 수정(기존의 정보를 화면에 출력)
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void modifyGET(HttpSession session,Model model) throws Exception{
		mylog.debug("modifyGET()");
		//insertForm.jsp 참고해서 수정 뷰페이지 생성
		String id = (String) session.getAttribute("id");
		
		//서비스 - 회원정보 조회
		//model 객체 사용 정보 전달
		model.addAttribute("vo",service.getMember(id));
		//뷰페이지 이동
		
	}
	//회원정보 수정(수정된 정보를 db에 변경)
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPOST(MemberVO vo) throws Exception{
		//한글처리 => 필터
		//전달정보 저장 (폼-POST:수정정보)
		mylog.debug(vo+"");
		//서비스 - db회원정보 수정동작
		Integer result = service.updateMember(vo);
		String uri="";
		if(result == 1) {
			//수정 성공(메인페이지)
			uri="redirect:/member/main";
		}else {
			//수정 실패(수정페이지)
			uri="redirect:/member/modify";
		}
		return uri;

	}
	
	//회원정보 삭제 (삭제 비밀번호 입력)
	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public void removeGET() throws Exception{
		mylog.info("removeGET()");
		
		//연결된 뷰페이지 이동
	}
	
	
	//회원정보 삭제(데이터 삭제)
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String removePOST(MemberVO vo,HttpSession session) throws Exception{
		//전달된 파라미터값 저장
		
		//서비스 - DAO 회원탈퇴
		service.deleteMember(vo);
		
		//세션정보 초기화(로그인정보)
		session.invalidate();
		
		//페이지 이동
		
		return "redirect:/member/main";
	}
	
	//회원 전체 목록조회
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String listGET(HttpSession session, Model model) throws Exception{
		//관리자 로그인 제어
		String id = (String) session.getAttribute("id");
		if(id == null || !id.equals("admin")) {
			return "redirect:/member/login";
		}
		
		// 서비스 - DAO 회원목록 가져오기
		List<MemberVO> memberList = service.getMemberList(id);
		// Model 객체 사용 - 정보 전달
		model.addAttribute("memberList", memberList);
		//		model.addAttribute(memberList);
		// 뷰페이지 이동
		return "/member/list";	
	}
	
	
}
