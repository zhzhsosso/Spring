package com.itwillbs.service;


import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	
	//게시판 글쓰기
	public void insertBoard(BoardVO vo) throws Exception;
	
	//게시판 글목록(All)
	public List<BoardVO> getBoardListAll() throws Exception;
	
	//글 조회수 1증가
	public void updateViewcnt(int bno) throws Exception;
	
	//글번호 정보 조회
	public BoardVO getBoard(Integer bno) throws Exception;
	
	//글 수정
	public Integer updateBoard(BoardVO vo) throws Exception;
	
	//글 삭제
	public void deleteBoard(int bno) throws Exception;
}
