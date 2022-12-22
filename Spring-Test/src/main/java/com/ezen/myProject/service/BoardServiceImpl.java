package com.ezen.myProject.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	@Inject
	private BoardDAO bdao;
	@Override
	public int register(BoardVO bvo) {
		log.info(">>>> board register check 2");
		return bdao.insertBoard(bvo);
	}
	@Override
	public List<BoardVO> getList() {
		log.info(">>>> board list check 2");
		return bdao.selectBoardList();
	}
	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>>> board detail check 2");
		bdao.readCount(bno);
		return bdao.selectBoardOne(bno);
	}
}
