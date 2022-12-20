package com.itwillbs.persistence;

import java.util.List;

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
		
		mylog.debug(" createBoard(BoardVO vo) -> mapper 동작 호출 ");
		
		sqlSession.insert(NAMESPACE + ".create", vo);
		
		mylog.debug(" 회원가입 완료! -> 서비스 ");
		
	}

	@Override
	public List<BoardVO> getBoardListAll() throws Exception {

		mylog.debug(" getBoardListAll() -> sqlSession-mapper 호출 ");
		
		List<BoardVO> boardList = sqlSession.selectList(NAMESPACE +".listAll");
		//mylog.debug(boardList+"");
		mylog.debug(" 게시판 글 개수 : "+boardList.size()+"");
		
		return boardList;
	}

	@Override
	public void updateViewcnt(Integer bno) throws Exception {
		mylog.debug("updateViewcnt(Integer bno) -> sqlSession객체 ");
		
		sqlSession.update(NAMESPACE+".updateViewcnt",bno);
	}

	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		mylog.debug("getBoard(Integer bno) 호출");
		
		BoardVO vo = sqlSession.selectOne(NAMESPACE + ".getBoard",bno);
		return vo;
	}

	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		mylog.debug(" updateBoard(BoardVO vo) ");
		return sqlSession.update(NAMESPACE + ".updateBoard", vo);
	}

	@Override
	public void deleteBoard(Integer bno) throws Exception {
		sqlSession.delete(NAMESPACE + ".deleteBoard", bno);
	}
	
	
	
	

}
