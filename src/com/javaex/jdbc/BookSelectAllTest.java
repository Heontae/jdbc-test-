package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAllTest {

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
			
			String query = "select 		b.book_id , ";
			query += "					b.title , ";
			query += "					b.pubs , ";
			query += "					to_char(pub_date,'YYYY-MM-DD') , ";
			query += "					a.author_id ,";
			query += "					a.author_name ,";
			query += "					author_desc";
			query += "		from		book   b , ";
			query += "					author a ";
			query += "		where		b.author_id = a.author_id";

			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			System.out.println("-----------------------------------------------------------------");
			while (rs.next()) {// next() /한번 반복하고 가로 한줄 내려가기 next하다가 값이 없으면 자동으로 false로 변경됨
				int bookid = rs.getInt("book_id");// 첫번째 칸에 숫자
				String booktitle = rs.getString("title"); // 두번째 칸에 문자열
				String bookpubs = rs.getString("pubs");
				Date bookdate = rs.getDate("to_char(pub_date,'YYYY-MM-DD')");
				int bookid2 = rs.getInt("author_id");
				String authorname = rs.getString("author_name");
				String authordesc = rs.getString("author_desc");

				System.out.println(bookid + "\t" + booktitle + "\t" + bookpubs + "\t" + bookdate + "\t" + bookid2 + "\t"
						+ authorname + "\t" + authordesc);
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
