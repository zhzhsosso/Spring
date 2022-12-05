package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

//@Repository : 스프링에서 해당파일을 DAO 파일로 인식시키는 용도

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	// 디비 연결정보 필요함 => 의존관계
	@Inject
	private SqlSession sqlSession;
	
	// mapper의 namespace정보 저장
	private static final String NAMESPACE
	        ="com.itwillbs.mapper.MemberMapper";
	
	
	@Override
	public String getServerTime() {
		// 디비연결
		// sql 작성 & pstmt
		// ??
		// sql 실행
		// 데이터처리 
		
		//sqlSession.selectOne(쿼리);
		String time 
		  = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		
		return time;
	}
	@Override
	public void insertMember(MemberVO vo) {
		//디비연결 - sql 작성 - 실행
		sqlSession.insert(NAMESPACE + ".createMember",vo);
		System.out.println(" DAOImpl : 회원가입 성공! ");		
	}
	
	
	
	@Override
	public MemberVO getMember(String userid) {
		//				"com.itwillbs.mapper.MemberMapper.[ID]"
 		MemberVO vo = sqlSession.selectOne(NAMESPACE + ".getMember",userid);
 		
 		System.out.println(" DAO : "+vo);
 		
		return vo;
	}
	
	@Override
	public MemberVO loginMember(String userid, String userpw) {
		
		//sqlSession.selectOne(statement,userid,userpw); (x)
		//sqlSession.selectOne(statement,vo); (o) vo 객체 생성해서 set호출 저장
		
		// VO객체 안에 전달된 정보를 한번에 전달 불가능한 경우
		// ->  관련없는 데이터를 1개 이상 전달하는 경우(join)
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		//paramMap.put("mapper에 매핑될 이름", 데이터);
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		MemberVO vo 
		      = sqlSession.selectOne(NAMESPACE+".loginMember",paramMap);
		
		return vo;
	}
	@Override
	public MemberVO loginMember(MemberVO vo) {
		
		return sqlSession.selectOne(NAMESPACE+".loginMember",vo);
	}
	
	
	
	
	

}
