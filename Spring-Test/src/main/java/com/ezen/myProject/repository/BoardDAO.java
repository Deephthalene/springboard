package com.ezen.myProject.repository;

import java.util.List;

import com.ezen.myProject.domain.BoardVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> selectBoardList();

	BoardVO selectBoardOne(int bno);

	void readCount(int bno);

}
