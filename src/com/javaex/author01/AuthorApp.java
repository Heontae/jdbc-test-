package com.javaex.author01;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		// Dao를 이용한 프로그램
		AuthorDao authorDao = new AuthorDao();

		// 등록
		authorDao.AuthorInsert("패션왕", "중앙북스(books)");
		
		// 수정
		authorDao.AuthorUpdate(1, "우리들의 일그러진 영웅", "다림");
		
		// 삭제
		authorDao.AuthorDelete(2);

		// 리스트
		List<AuthorVo> authorList = authorDao.getAuthorList();
		// 출력
		for (AuthorVo vo : authorList) {
			System.out.println(vo.getAuthor_id() + "\t" + vo.getAuthor_name() + "\t" + vo.getAuthor_desc());
		}

	}

}
