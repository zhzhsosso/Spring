package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	//수행해야하는 동작 선언
	
	//회원가입
	public void memberJoin(MemberVO vo);
	
	//로그인 체크
	public boolean memberLogin(MemberVO vo);
}
