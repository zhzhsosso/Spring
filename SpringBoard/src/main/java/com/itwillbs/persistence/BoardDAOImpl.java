package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	
	private static final Logger mylog = LoggerFactory.getLogger(BoardDAOImpl.class);
	
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

	@Override
	public void createBoard(BoardVO vo) throws Exception {
		mylog.debug("createBoard(BoardVO vo) -> mapper동작 호출");
		
		sqlSession.insert(NAMESPACE+".create",vo);
		
		mylog.debug("작성 완료!");
	}
	
	

}
