package com.javaex.book01;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BookDao bookdao = new BookDao();
		
		//추가하기
		
		BookVo v01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookdao.BookInsert(v01);
		
		BookVo v02 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookdao.BookInsert(v02);
		
		//수정하기 (수정할 북 번호 치고 변경내용 적기) 2번내용변경
		BookVo v03 = new BookVo(2,"수정-삼국지", "수정-민음사", "2002-03-01", 1);
		
		bookdao.BookUpdate(v03);
		
		//삭제하기
		BookVo v04 = new BookVo(2);
		bookdao.BookDelete(v04);
		
		
		// 리스트
		List<BookVo> bookList = bookdao.getBookList();
		// 출력
		for (BookVo vo : bookList) {
			System.out.println(vo.getBook_id() + "\t" + vo.getTitle()+ "\t" + vo.getPubs()+"\t"+vo.getPub_date()+"\t"+vo.getAuthor_id());
		}
	}

}