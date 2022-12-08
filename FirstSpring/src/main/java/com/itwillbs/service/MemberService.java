package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	//수행해야하는 동작 선언
	
	//회원가입
	public void memberJoin(MemberVO vo);
	
	//로그인 체크
	public boolean memberLogin(MemberVO vo);
	
	//특정 회원정보 조회
	public MemberVO getMember(String id);
	
	//회원정보 수정
	public Integer updateMember(MemberVO uvo);
	
	//회원정보 삭제
	public void deleteMember(MemberVO dvo);
	
	//회원목록 조회
	public List<MemberVO> getMemberList(String id);
}
