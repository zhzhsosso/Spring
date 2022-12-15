package com.itwillbs.persistence;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	//서버시간정보 조회
	public String getTime();
	
	//게시판 글쓰기
	public void createBoard(BoardVO vo) throws Exception;
	
}
