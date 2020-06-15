package com.javaex.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class AuthorInsertTest {

	public static void main(String[] args) {
		//0. import java.sql.*;
		//1. JDBC 드라이버 (Oracle) 로딩
		//2. Connection 얻어오기
		//3. SQL문 준비 / 바인딩 / 실행 / **중요**
		//4. 결과처리
		//5. 자원정리

		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null; //Select 사용문

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; //ip하고 포트번호 가르쳐주기
			conn = DriverManager.getConnection(url, "webdb", "webdb"); //주소,아이디,비밀번호 conn에 넣기
		    System.out.println("접속 성공");
		    
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO author VALUES (seq_author_id.NEXTVAL, ? , ?)"; //쿼리문 문자열 만들기 , ?는 일단 비워둔다.
			pstmt = conn.prepareStatement(query);//쿼리로 만들기
			
			pstmt.setString(1, "김영하"); // 첫번째 ? 는 이문열
			pstmt.setString(2, "알쓸신잡"); // 두번째 ?는 경북 영양
			
			int count = pstmt.executeUpdate(); // 실행문 executeUpdate()했을때 내보내기 **중요**
		    
			// 4.결과처리
			System.out.println(count + "건 처리되었습니다."); //count는 몇번 처리되었는지 확인하기 위한 변수
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

}
