package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	//서버시간정보 조회
	public String getTime();
	
	//게시판 글쓰기
	public void createBoard(BoardVO vo) throws Exception;
	
	//게시판 목록(ALl)
	public List<BoardVO> getBoardListAll() throws Exception;
	
	//글 조회수 1증가
	public void updateViewcnt(Integer bno) throws Exception;
}
