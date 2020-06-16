package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// 필드

	// 생성자
	public BookDao() {

	}
	// g/s

	// 일반메소드

	// ********** 작가 추가 **********
	public void BookInsert(BookVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null; //Select 사용문

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ip하고 포트번호 가르쳐주기
			conn = DriverManager.getConnection(url, "webdb", "webdb"); // 주소,아이디,비밀번호 conn에 넣기
			System.out.println("추가하기/접속 성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO book VALUES (seq_book_id.NEXTVAL, ? , ? , ? ,?)"; // 쿼리문 문자열 만들기 , ?는 일단 비워둔다.
			pstmt = conn.prepareStatement(query);// 쿼리로 만들기

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPub_date());
			pstmt.setInt(4, vo.getAuthor_id());

			int count = pstmt.executeUpdate(); // 실행문 executeUpdate()했을때 내보내기 **중요**

			// 4.결과처리
			System.out.println(count + "건 추가되었습니다."); // count는 몇번 처리되었는지 확인하기 위한 변수
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
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

	// ********** 작가 수정 **********
	public void BookUpdate(BookVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ip하고 포트번호 가르쳐주기
			conn = DriverManager.getConnection(url, "webdb", "webdb"); // 주소,아이디,비밀번호 conn에 넣기
			System.out.println("수정하기/접속 성공");

			// 3. SQL문 준비 / 바인딩 / 실행 (update author set author_desc ='서울특별시' where
			// author_name ='강풀';
			String query = " Update book b ";
			query += "		 set 	title=?, ";
			query += " 				pubs=? ,";
			query += " 				pub_date=? ,";
			query += " 				author_id=? ";
			query += " 		 where 	book_id=? ";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPub_date());
			pstmt.setInt(4, vo.getAuthor_id());
			pstmt.setInt(5, vo.getBook_id());

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
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

	// ********** 작가 삭제 **********
	public void BookDelete(BookVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null; //Select 사용문

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ip하고 포트번호 가르쳐주기
			conn = DriverManager.getConnection(url, "webdb", "webdb"); // 주소,아이디,비밀번호 conn에 넣기
			System.out.println("삭제하기/접속 성공");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " delete from book ";
			query += "		 where book_id=? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, vo.getBook_id());

			int count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 삭제되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
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

	// ********** 작가 리스트 **********
	public List<BookVo> getBookList() {
		// 리스트 준비
		List<BookVo> booklist = new ArrayList<BookVo>();

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
			System.out.println("리스트/접속성공");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "select 	book_id,";
			query += "				title,";
			query += "				pubs,";
			query += "				to_char(pub_date,'YYYY-DD-MM'),";
			query += "				author_id";
			query += "		from	book";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			// 4.결과처리
			System.out.println("--------------------------------");
			while (rs.next()) {// next() /한번 반복하고 가로 한줄 내려가기 next하다가 값이 없으면 자동으로 false로 변경됨
				int bookid = rs.getInt("book_id");// 첫번째 칸에 숫자
				String booktitle = rs.getString("title"); // 두번째 칸에 문자열
				String bookpubs = rs.getString("pubs");
				String bookdate = rs.getString("to_char(pub_date,'YYYY-DD-MM')");
				int bookid2 = rs.getInt("author_id");

				// 리스트에 담기
				BookVo bookVo = new BookVo(bookid, booktitle, bookpubs, bookdate, bookid2);
				booklist.add(bookVo); // 데이터를 리스트에 담아두기
			}
			System.out.println("--------------------------------");
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
		return booklist; // 리스트에 담겨있는 데이터를 모두 리턴
	}

}
