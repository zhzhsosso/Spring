package com.itwillbs.fintech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.fintech.service.OpenBankingService;
import com.itwillbs.fintech.vo.AccountSearchRequestVO;
import com.itwillbs.fintech.vo.AccountSearchResponseVO;
import com.itwillbs.fintech.vo.RequestTokenVO;
import com.itwillbs.fintech.vo.ResponseTokenVO;
import com.itwillbs.fintech.vo.UserInfoRequestVO;
import com.itwillbs.fintech.vo.UserInfoResponseVO;

@Controller
public class OpenBankingController {
	
	//객체생성
	@Autowired
	private OpenBankingService openBankingService;

		@RequestMapping(value="/callback", method= RequestMethod.GET)
		public String getToken(RequestTokenVO requestTokenVO, Model model) throws Exception{
			//사용자인증 API (3-legged) => 응답결과 출력
			//<authorization_code> 사용자인증 성공 시 반환되는 코드
			System.out.println("code : " +requestTokenVO.getCode());
			//login inquiry transfer Access Token 권한 범위 (다중 scope 가능)
			System.out.println("scope : "+requestTokenVO.getScope());
			//client_info <client_info> 요청 시 이용기관이 세팅한 client_info값
			System.out.println("client_info : "+requestTokenVO.getClient_info());
			//주 1) <state> 요청 시 이용기관이 세팅한 state값을
			System.out.println("state : "+requestTokenVO.getState());
			
			
			//토큰발급=> 처리 메서드 호출 리턴 받기
			ResponseTokenVO responseTokenVO = openBankingService.requestToken(requestTokenVO);
			System.out.println("토큰"+responseTokenVO.getAccess_token());
			
			//정보를 들고 jsp 이동
			model.addAttribute("responseTokenVO", responseTokenVO);
			
			return "bank_main";
		}
		
		// 사용자 정보 조회
		@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
		public String getUserInfo(UserInfoRequestVO userInfoRequestVO, Model model) throws Exception{
			
			//토큰발급 => 처리 메서드 호출 리턴 받기
			UserInfoResponseVO userInfo = openBankingService.findUser(userInfoRequestVO);
			
			//정보를 들고 bank_main.jsp 이동
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("access_token", userInfoRequestVO.getAccess_token());
			
			return "account/user_info";
		}

		// 등록계좌 조회
		@RequestMapping(value = "/accountList", method = RequestMethod.GET)
		public String getAccountList(AccountSearchRequestVO accountSearchRequestVO, Model model) {
			
			//토큰발급 => 처리 메서드 호출 리턴 받기
			AccountSearchResponseVO accountList = openBankingService.findAccount(accountSearchRequestVO);
			
			//정보를 들고 bank_main.jsp 이동
			model.addAttribute("accountList", accountList);
			model.addAttribute("access_token", accountSearchRequestVO.getAccess_token());
			
			return "account/list";
		}

}
