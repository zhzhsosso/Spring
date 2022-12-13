package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	// 디비연결 -> bean 주입(root-context.xml)
	@Inject
	private SqlSession sqlSession;
	
	// mapper NAMESPACE 정보
	private static final String NAMESPACE
	             = "com.itwillbs.mapper.BoardMapper";
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(NAMESPACE + ".getTime");
	}

}
