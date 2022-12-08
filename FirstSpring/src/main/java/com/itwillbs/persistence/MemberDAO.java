package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {

	// 디비서버의 시간정보 조회
	public String getServerTime();
	
	// 회원가입
	public void insertMember(MemberVO vo);
	
	// 특정 회원정보 조회
	public MemberVO getMember(String userid);
	
	// 로그인 처리
	public MemberVO loginMember(String userid,String userpw);
	public MemberVO loginMember(MemberVO vo);
	
	//회원정보 수정
	public Integer updateMember(MemberVO uvo);
	
	//회원정보 삭제
	public void deleteMember(MemberVO dvo);
	
	//회원목록 조회(list - java.util)
	public List<MemberVO> getMemberList(String id);
	
}
