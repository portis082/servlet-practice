package com.bit2021.emaillist.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
			String url = "";
			connection = DriverManager.getConnection(url, "", "");
			
			// statement 객체 생성
			stmt = connection.createStatement();
			
			// sql 실행
			String sql =
					"";
		}
	}

}
