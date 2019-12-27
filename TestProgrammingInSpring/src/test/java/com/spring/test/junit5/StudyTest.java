package com.spring.test.junit5;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 언더스코어를 빈 공백 문자로 치환
// @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExcludeTags("fast")
@IncludeTags("slow")
class StudyTest {
	
	private static final Logger log = LoggerFactory.getLogger(Study.class);

	@Test
	@Disabled
	@DisplayName("스터디 만들기")
	void create_new_study() {
		/*
		 * IllegalArgumentException exception =
		 * assertThrows(IllegalArgumentException.class, () -> new Study(-10));
		 * assertEquals("limit은 0보다 커야 함.", exception.getMessage());
		 */
		// Study study = new Study(-10);
		/*
		 * assertNotNull(study); assertEquals(StudyStatus.DRAFT, study.getStatus(),
		 * "스터디를 첨 만들면 상태 값이 DRAFT여야 한다."); assertTrue(study.getLimit() > 0,
		 * "스터디 최대 참석 가능 인원은 0보다 커야 한다.");
		 */
		
		/*
		 * assertAll( () -> assertNotNull(study), () -> assertEquals(StudyStatus.DRAFT,
		 * study.getStatus(), () -> "스터디 처음 상태는 " + StudyStatus.DRAFT + " 이다."), () ->
		 * assertTrue(study.getLimit() > 0, "스터디 참석인원은 0보다 커야 한다.") );
		 */
		
		// 단점 코드블럭이 실행 끝나는 거 까지 기다리고 시간을 비교한다.
		/*
		 * assertTimeout(Duration.ofMillis(100), () -> { new Study(10);
		 * Thread.sleep(300); });
		 */
		
		// 시간이 초과하면 테스트를 바로 종료시킨다. 
		// 주의: 테스트 코드 블럭은 별도의 스레드에서 실행하기 떄문에, 스레드 로컬을 사용하는 코드가 있다면
		// 예상치 못한 결과가 나올 수 있다. 가령, 트랜잭션 처리는 스레드 로컬을 기본 전략으로 하는데,
		// 스레드 로컬은 다른 스레드에서 공유가 불가능! - 조정은 가능 
		// 테스트에서 스프링 트랜잭션이 제대로 동작안할 수 가 있어서 rollback이 안되고 DB에 반영될 수 있다.
		/*
		 * assertTimeoutPreemptively(Duration.ofMillis(100), () -> { new Study(10);
		 * Thread.sleep(300); });
		 */

		/*
		 * String test_env = System.getenv("TEST_ENV"); System.out.println(test_env);
		 * 
		 * assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));
		 * 
		 * assumingThat("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> { //
		 * 코드 실행 });
		 * 
		 * assumingThat("DEV".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> { // 코드
		 * 실행 });
		 */
	}

	/*
	 * @Test
	 * 
	 * @Disabled void create1_new_study_again() { System.out.println("create1"); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("테스트1")
	 * 
	 * @Tag("fast") void test1() { System.out.println("test1"); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("테스트3")
	 * 
	 * @Tag("fast") void test3() { System.out.println("test3"); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("테스트2")
	 * 
	 * @Tag("slow") void test2() { System.out.println("test2"); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("테스트4")
	 * 
	 * @Tag("slow") void test4() { System.out.println("test4"); }
	 */
	
	// 테스트 클래스 안에 있는 여러 테스트가 실행되기 전 딱 한 번만 호출이 된다.
	// 반드시 static 사용해야 하고, private은 안되는데, default는 되고 return type이 있으면 안된다.
	/*
	 * @BeforeAll static void beforeAll() { System.out.println("before all"); }
	 * 
	 * @AfterAll static void afterAll() { System.out.println("after all"); }
	 * 
	 * @BeforeEach void beforeEach() { System.out.println("Before each"); }
	 * 
	 * @AfterEach void afterEach() { System.out.println("After each"); }
	 */
 
	// @FastTest를 사용하면 @FastTest에 설정한 메타 애노테이션 정보를 간단히 사용 가능  
	/*
	 * @FastTest void customizingTag1() { log.info("test fast customizingTag"); }
	 * 
	 * @SlowTest void customizingTag2() { log.info("test slow customizingTag"); }
	 */
	
	// 반복할 횟수: 10
	// @RepeatedTest(10)
	// value: 반복할 횟수, name 반복되는 list들의 이름 지정
	@RepeatedTest(value=10, name="{displayName}, {currentRepetition}/{totalRepetitions}")
	@DisplayName("반복 테스트...")
	void repeatTest(RepetitionInfo repetitionInfo) {
		// RepetitionInfo: 인포를 통해 몇 번째 반복인지, 현재 몇번을 반복해야하는지 정보들을 알 수 있다.
		log.info("test repeated: {} / {}", repetitionInfo.getCurrentRepetition(), repetitionInfo.getTotalRepetitions());
	}

	@DisplayName("스터뒤 만들기 테스트")
	// 반복적인 테스트마다 다른 값으로 하고 싶은 경우
	// JUnit5는 기본으로 제공, 4에서는 서드파티? 라이브러리 사용해야 가능
	@ParameterizedTest(name = "{index} {displayName} message= {0}")
	// @ValueSource 사용하면 간단하게 파라미터들을 정의할 수 있다. 
	@ValueSource(strings = {"날씨가", "많이", "추워", "그치?"})
	void parameterizedTest(String message) {
		// 위에 4개의 스트링 파라미터가 한 번씩 들어올수 있도록 String message를 정의
		log.info("message: {}", message);
	}
	
	
}
