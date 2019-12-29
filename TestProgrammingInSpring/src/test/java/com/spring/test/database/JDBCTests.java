package com.spring.test.database;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class JDBCTests {
	
	public static final Logger log = LoggerFactory.getLogger(JDBCTests.class);
	
	static {
		/* 
		 * 클래스 'com.mysql.jdbc.Driver'로드 중 더 이상 사용되지 않습니다.
		 * 새로운 드라이버 클래스는`com.mysql.cj.jdbc.Driver '입니다. 
		 * 드라이버는 SPI를 통해 자동으로 등록되며 드라이버 클래스의 수동로드는 일반적으로 필요하지 않습니다.
		 * try { Class.forName("com.mysql.jdbc.Driver"); } catch(Exception e) {
		 * log.info("에러 메시지: {}", e); }
		 */
	}

	
	
	@Test
	void testConnection() {
		
		try {
			// 외부 properties 읽기
			// Properties properties = new Properties();
			// FileReader resources = new FileReader("/resources/properties/config.properties");
			// properties.load(resources);
			
			// 내부 properties 읽기
			Properties properties = new Properties();
			String resource = "properties/config.properties";
			// mybatis dependency를 추가해야 Resources.getResourceAsReader(resource) 사용 가능
			Reader reader = Resources.getResourceAsReader(resource);
			properties.load(reader);
			
			
			Connection con = DriverManager.getConnection(
					properties.getProperty("jdbc.url"),
					properties.getProperty("jdbc.username"),
					properties.getProperty("jdbc.password")
			);
			
		} catch(Exception e) {
			log.info("에러 메시지: {}", e);
		}
	}

}
