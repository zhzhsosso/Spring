package com.itwillbs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{

	private static final Logger mylog = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardDAO dao;
	
	@Override
	public void insertBoard(BoardVO vo) throws Exception {
		mylog.debug("insertBoard(BoardVO vo) 호출 ->  DAO 동작 호출");
		
		dao.createBoard(vo);
		
		mylog.debug("글쓰기 완료 -> 컨트롤러 이동");
		
	}
	
}
