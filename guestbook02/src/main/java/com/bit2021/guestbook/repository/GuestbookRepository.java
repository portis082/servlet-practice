package com.bit2021.guestbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit2021.guestbook.vo.GuestbookVo;

public class GuestbookRepository {
	public boolean delete(Long no, String password) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			//연결하기
			connection = getConnection();
			
			// sql 준비
			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = connection.prepareStatement(sql);
			
			// 바인딩
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			
			// sql 실행
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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
	public boolean insert(GuestbookVo vo){
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			//연결하기
			connection = getConnection();
			
			// sql 준비
			String sql = "insert into guestbook value(null, ?, ?, ?, now())";
			pstmt = connection.prepareStatement(sql);
			
			// 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			// sql 실행
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 연결하기
			connection = getConnection();
			
			// sql 준비
			String sql = "select no, name, message, date_format(reg_date, '%Y-%m-%d %h:%i:%s') from guestbook order by no desc";
			pstmt = connection.prepareStatement(sql);
			
			// 바인딩
			
			// sql 실행
			rs = pstmt.executeQuery();
			
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

		} catch(SQLException e) {
			System.out.println("에러 " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			// JDBC Driver
			Class.forName("org.mariadb.jdbc.Driver");

			// connection
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}
		
		return connection;
	}
}
