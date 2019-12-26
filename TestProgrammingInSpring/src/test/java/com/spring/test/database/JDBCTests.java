package com.spring.test.database;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class JDBCTests {
	
	public static final Logger log = LoggerFactory.getLogger(JDBCTests.class);
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			log.info("에러 메시지: {}", e);
		}
	}

	@Test
	void testConnection() {
		try {
			Connection con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:orcl",
						"asdasd",
						"asdasd"
					);
			
		} catch(Exception e) {
			log.info("에러 메시지: {}", e);
		}
	}

}
