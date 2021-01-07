package com.bit2021.guestbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2021.guestbook.vo.GuestbookVo;

public class GuestbookRepository {
	public boolean insert(GuestbookVo vo){
		boolean result = false;
		Connection connection = null;
		Statement stmt = null;
		
		try {
			// JDBC driver
			Class.forName("org.mariadb.jdbc.Driver");
			
			// connection
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			// statement
			stmt = connection.createStatement();
			
			// sql실행
			String sql = "insert into guestbook value(null, '" +
					vo.getName() + "', '" + 
					vo.getPassword() + "', '" + 
					vo.getMessage() + "', now())";
			int count = stmt.executeUpdate(sql);
			
			result = (count == 1);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<GuestbookVo> findAll() {
		ArrayList<GuestbookVo> result = new ArrayList<>();
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// JDBC Driver
			Class.forName("org.mariadb.jdbc.Driver");

			// connection
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");

			// statement 객체 생성
			stmt = connection.createStatement();

			// sql 실행
			String sql = "select no, name, message, date_format(reg_date, '%Y-%m-%d %h:%i:%s') from guestbook order by no desc";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String message = rs.getString(3);
				String regDate = rs.getString(4);
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setMessage(message);
				vo.setRegDate(regDate);;
				
				result.add(vo);
			}

		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch(SQLException e) {
			System.out.println("에러 " + e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
