package com.itwillbs.service;


import com.itwillbs.domain.BoardVO;

public interface BoardService {
	
	//게시판 글쓰기
	public void insertBoard(BoardVO vo) throws Exception;
	
}
