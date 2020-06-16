package com.javaex.author02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		// Dao,Vo를 이용한 프로그램
		AuthorDao authorDao = new AuthorDao();

		// 등록
		AuthorVo v01 = new AuthorVo("이문열" , "경북 영양");
		authorDao.AuthorInsert(v01);
		
		AuthorVo v02 = new AuthorVo("박경리" , "경남 통영");
		authorDao.AuthorInsert(v02);
		
		// 수정
		AuthorVo v03 = new AuthorVo(2, "수정-박경리", "수정-경남 통영");
		authorDao.AuthorUpdate(v03);
		
		// 삭제
		AuthorVo v04 = new AuthorVo(2);
		authorDao.AuthorDelete(v04);

		// 리스트
		List<AuthorVo> authorList = authorDao.getAuthorList();
		// 출력
		for (AuthorVo vo : authorList) {
			System.out.println(vo.getAuthor_id() + "\t" + vo.getAuthor_name() + "\t" + vo.getAuthor_desc());
		}

	}

}
