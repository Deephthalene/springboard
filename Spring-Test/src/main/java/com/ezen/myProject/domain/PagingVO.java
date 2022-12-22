package com.ezen.myProject.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingVO {
	
	private int pageNo; // 현재 화면에 출력된 페이지네이션 번호
	private int qty; // 한 페이지당 보여줄 게시글의 갯수
	
	public PagingVO() {
		this(1,10);
		
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}

	public int getPageStart() { // 시작 번호 값 구하는 메서드
		return (this.pageNo -1) * qty; //DB에서 값을 limit 첫 시작이 0
	}
}
