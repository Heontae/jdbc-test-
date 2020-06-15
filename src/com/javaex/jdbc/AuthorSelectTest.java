package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelectTest {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			
			String query = "select 	a.author_id,";
			query += "				a.author_name,";
			query += "				a.author_desc";
			query += "		from	author a";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			// 4.결과처리
			System.out.println("--------------------------------");
			System.out.println("아이디 \t 이름 \t 제목");
			System.out.println("--------------------------------");
			while (rs.next()) {// next() /한번 반복하고 가로 한줄 내려가기 next하다가 값이 없으면 자동으로 false로 변경됨
				int authorid = rs.getInt("author_id");// 첫번째 칸에 숫자
				String authorname = rs.getString("author_name"); // 두번째 칸에 문자열
				String authordesc = rs.getString("author_desc");

				System.out.println(authorid + "\t" + authorname + "\t" + authordesc);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
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
