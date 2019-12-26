package com.spring.test.junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

// 언더스코어를 빈 공백 문자로 치환
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	@Test
	@DisplayName("스터디 만들기")
	void create_new_study() {
		Study study = new Study(-10);
		/*
		 * assertNotNull(study); assertEquals(StudyStatus.DRAFT, study.getStatus(),
		 * "스터디를 첨 만들면 상태 값이 DRAFT여야 한다."); assertTrue(study.getLimit() > 0,
		 * "스터디 최대 참석 가능 인원은 0보다 커야 한다.");
		 */
		
		assertAll(
			() -> assertNotNull(study),
			() -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디 처음 상태는 " + StudyStatus.DRAFT + " 이다."),
			() -> assertTrue(study.getLimit() > 0, "스터디 참석인원은 0보다 커야 한다.")
		);
	}

	@Test
	@Disabled
	void create1_new_study_again() {
		System.out.println("create1");
	}
	
	// 테스트 클래스 안에 있는 여러 테스트가 실행되기 전 딱 한 번만 호출이 된다.
	// 반드시 static 사용해야 하고, private은 안되는데, default는 되고 return type이 있으면 안된다.
	@BeforeAll
	static void beforeAll() {
		System.out.println("before all");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("after all");
	}
  
	@BeforeEach
	void beforeEach() {
		System.out.println("Before each");
	}
  
	@AfterEach
	void afterEach() {
		System.out.println("After each");
	}
 
	
	
	
}
