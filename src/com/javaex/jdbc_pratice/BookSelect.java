package com.javaex.jdbc_pratice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelect {

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
			
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
			query += " SELECT book_id, ";
			query += "        title, ";
			query += "        pubs, ";
			query += "        to_char(book.pub_date, 'yy/mm/dd'), ";
			query += "        author_id ";
			query += " FROM book";
			
			pstmt = conn.prepareStatement(query);
			
			
			/*
			SELECT book_id,
                   title,
                   pubs,
                   pub_date,
                   author_id
            FROM book;
			 */
			
			rs = pstmt.executeQuery();

			// 4.결과처리
			
			while(rs.next()) {
			int bookId = rs.getInt(1);
			String title = rs.getString(2);
			String pubs = rs.getString(3);
			String pubDate = rs.getString(4);
			int authorId = rs.getInt(5);
			
			System.out.println(bookId +", " + title +", " + pubs +", " + pubDate +", " + authorId);
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
