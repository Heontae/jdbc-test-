package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsertTest2 {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		// 3. SQL문 준비 / 바인딩 / 실행 / **중요**
		// 4. 결과처리
		// 5. 자원정리
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null; //Select 사용문

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ip하고 포트번호 가르쳐주기
			conn = DriverManager.getConnection(url, "webdb", "webdb"); // 주소,아이디,비밀번호 conn에 넣기
			System.out.println("접속 성공");

			conn.setAutoCommit(false); //자동으로 커밋하는것을 false로 바꿔서 자동커밋 끄기

			// 3. SQL문 준비 / 바인딩 / 실행
			
			// -값 정보 등록
			String query = "INSERT INTO book VALUES (seq_book_id.NEXTVAL,? ,? ,? ,?)"; // 쿼리문 문자열 만들기 , ?는 일단 비워둔다.
			pstmt = conn.prepareStatement(query);// 쿼리로 만들기
			pstmt.setString(1, "-삼국지");
			pstmt.setString(2, "-민음사");
			pstmt.setString(3, "2002-03-01");
			pstmt.setInt(4, 5);

			pstmt.executeUpdate();
			// --------------------------------------------------------------------------
			// +값 정보등록 
			
			pstmt.setString(1, "+삼국지");
			pstmt.setString(2, "+민음사");
			pstmt.setString(3, "2002-03-01");
			pstmt.setInt(4, 1000); //강제로 오류발생시키기

			pstmt.executeUpdate(); // 쿼리문 실행 **중요**
			
			// 4.결과처리
			conn.commit();
			System.out.println("커밋 완료!!");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
			
			try {
				System.out.println("롤백처리되었습니다.");
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {

			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

	}

}
