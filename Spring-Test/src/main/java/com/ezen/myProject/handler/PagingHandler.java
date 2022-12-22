package com.ezen.myProject.handler;

import com.ezen.myProject.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingHandler {

	private int startPage; // 현재 화면에서 보여줄 시작 페이지네이션 번호
	private int endPage; // 현재 화면에서 보여줄 마지막 페이지네이션 번호
	private boolean prev, next; //이전페이지가 있는지 없는지 여부를 true, false로 나타냄
	// 총 10개씩 보이게
	// 예시 : 1 2 3 4 5 6 7 8 9 10 Next startPage : 1, endPage : 10
	// prev 11 12 13 14 15 16 17 18 19 20 Next startPage : 11, endPage : 20
	// prev 21 22 startPage : 21, endPage : 22
	
	private int totalCount; //전체 게시글 수
	private PagingVO pgvo; 
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		this.endPage = (int)(Math.ceil(pgvo.getPageNo() / (pgvo.getQty()*1.0)))*pgvo.getQty(); //1부터 10까지는 10의값이 되도록 설정
		this.startPage = endPage - 9;
		
		int realEndPage = (int)Math.ceil((totalCount*1.0) / pgvo.getQty()); // 실제 끝 값
		if(realEndPage < this.endPage){
			this.endPage = realEndPage;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
}
