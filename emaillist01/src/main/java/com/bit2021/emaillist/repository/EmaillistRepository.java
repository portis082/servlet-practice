package com.bit2021.emaillist.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2021.emaillist.vo.EmaillistVo;

public class EmaillistRepository {
	public boolean insert(EmaillistVo vo) {
		boolean result = false;
		Connection connection = null;
		Statement stmt = null;

		try {
			// JDBC Driver
			Class.forName("org.mariadb.jdbc.Driver");

			// connection
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");

			// statement 객체 생성
			stmt = connection.createStatement();

			// sql 실행
			String sql =
					"INSERT INTO emaillist VALUES(null, '" +
							vo.getFirstName() + "', '"+
							vo.getLastName() +"', '"+
							vo.getEmail() +"')";
			int count = stmt.executeUpdate(sql);
			result = (count == 1);

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

	public List<EmaillistVo> findAll() {
		ArrayList<EmaillistVo> result = new ArrayList<>();
		
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
			String sql = "select no, first_name, last_name, email from emaillist order by no desc";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				
				EmaillistVo vo = new EmaillistVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
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
