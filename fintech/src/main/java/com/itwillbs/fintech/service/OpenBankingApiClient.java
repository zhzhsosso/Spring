package com.itwillbs.fintech.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.itwillbs.fintech.vo.AccountSearchRequestVO;
import com.itwillbs.fintech.vo.AccountSearchResponseVO;
import com.itwillbs.fintech.vo.RequestTokenVO;
import com.itwillbs.fintech.vo.ResponseTokenVO;
import com.itwillbs.fintech.vo.UserInfoRequestVO;
import com.itwillbs.fintech.vo.UserInfoResponseVO;

@Service
public class OpenBankingApiClient {
	private String client_id="be6aa2c4-8b34-4401-9c54-49643df0e522";
	private String client_secret="b1a25933-1cb9-4ef0-ba24-74020c8fc412";
	private String redirect_uri="http://localhost:8080/fintech/callback";
	private String grant_type="authorization_code";
	
	//http 헤더 정보 관리 클래스
	private HttpHeaders httpHeaders;
	//REST방식 API요청
	private RestTemplate restTemplate;

	// httpheader에 토큰값 담아가기 위해서 헤더에 토큰추가하는 메서드 정의
		public HttpHeaders setHeaderAccessToken(String access_token) {
			httpHeaders.add("Authorization", "Bearer " + access_token);
			
			return httpHeaders;
		}
	
	//토큰발급 요청 메서드
	public ResponseTokenVO requestToken(RequestTokenVO requestTokenVO) {
//		return openBankingApiClient.requestToken(requestTokenVO);
		
		// http header 지정
		httpHeaders = new HttpHeaders();
		httpHeaders.add("Context-Type","application/x-www-form-urlencoded; charset=UTF-8");
		
		//requestTokenVo code => 저장
		requestTokenVO.setClient_id(client_id);
		requestTokenVO.setClient_secret(client_secret);
		requestTokenVO.setRedirect_uri(redirect_uri);
		requestTokenVO.setGrant_type(grant_type);
		//application/x..~~ 객체저장 불가
		//파라미터 저장해서 들고감
		MultiValueMap<String, String> parameters= new LinkedMultiValueMap<String, String>();
		parameters.add("code", requestTokenVO.getCode());
		parameters.add("client_id", requestTokenVO.getClient_id());
		parameters.add("client_secret",	requestTokenVO.getClient_secret());
		parameters.add("redirect_uri", requestTokenVO.getRedirect_uri());
		parameters.add("grant_type", requestTokenVO.getGrant_type());
		
//		httpHeaders,parameters 담아서 감 => HttpEntity	
		HttpEntity<MultiValueMap<String, String>> param = 
				new HttpEntity<MultiValueMap<String,String>>(parameters,httpHeaders);
		
		
		String requestUrl = "https://testapi.openbanking.or.kr/oauth/2.0/token";
		
		restTemplate = new RestTemplate();
		
		return restTemplate.exchange(requestUrl,
				HttpMethod.POST,param,ResponseTokenVO.class).getBody();
	}
	
	// 사용자 정보 조회
		public UserInfoResponseVO findUser(UserInfoRequestVO userInfoRequestVO) {
			
			// httpheader 지정
			httpHeaders = new HttpHeaders();
			// restTemplate? REST-API 제공하는..... 그거..........
			restTemplate = new RestTemplate();
			
			String requestUrl = "https://testapi.openbanking.or.kr/v2.0/user/me";
					
//			httpHeaders(token)담아서 감 => HttpEntity
			HttpEntity<String> param = new HttpEntity<String>(setHeaderAccessToken(userInfoRequestVO.getAccess_token()));
					
			// user_seq_no 파라미터
			// http://testapi.openbanking.or.kr/v.2.0/user/me?user_seq_no=11111
			UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
					.queryParam("user_seq_no", userInfoRequestVO.getUser_seq_no()).build();
			
			return restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET, param, UserInfoResponseVO.class).getBody();
		}

		
	// 등록 계좌 조회
	public AccountSearchResponseVO findAccount(AccountSearchRequestVO accountSearchRequestVO) {
		// httpheader 지정
		httpHeaders = new HttpHeaders();
		// restTemplate? REST-API 제공하는..... 그거..........
		restTemplate = new RestTemplate();
		
		String requestUrl = "https://testapi.openbanking.or.kr/v2.0/account/list";
		
//			httpHeaders(token)담아서 감 => HttpEntity
		HttpEntity<String> param = new HttpEntity<String>(setHeaderAccessToken(accountSearchRequestVO.getAccess_token()));
		
		// user_seq_no 파라미터
		UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
				.queryParam("user_seq_no", accountSearchRequestVO.getUser_seq_no())
				.queryParam("include_cancel_yn", accountSearchRequestVO.getInclude_cancel_yn())
				.queryParam("sort_order", accountSearchRequestVO.getSort_order()).build();
				
				
		return restTemplate.exchange(uriBuilder.toString(),
				HttpMethod.GET, param, AccountSearchResponseVO.class).getBody();
	}
}
