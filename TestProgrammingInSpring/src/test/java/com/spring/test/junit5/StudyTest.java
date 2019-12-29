package com.spring.test.junit5;

import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 언더스코어를 빈 공백 문자로 치환
// @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
// @ExcludeTags("fast")
// @IncludeTags("slow")
// 클래스 마다 인스턴스를 생성 (멤버 변수 공유 가능), 
// 이 애노테이션을 사용하면 @BeforeAll, @AfterAll을 만들어 사용할 때
// 반드시 static으로 만들었어야 했는데, static을 없이 사용할 수 있게 된다. 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// 테스트 메서드 작동 순서 조작
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
	
	@Disabled
	// 반복할 횟수: 10
	// @RepeatedTest(10)
	// value: 반복할 횟수, name 반복되는 list들의 이름 지정
	@RepeatedTest(value=10, name="{displayName}, {currentRepetition}/{totalRepetitions}")
	@DisplayName("반복 테스트...")
	void repeatTest(RepetitionInfo repetitionInfo) {
		// RepetitionInfo: 인포를 통해 몇 번째 반복인지, 현재 몇번을 반복해야하는지 정보들을 알 수 있다.
		log.info("test repeated: {} / {}", repetitionInfo.getCurrentRepetition(), repetitionInfo.getTotalRepetitions());
	}

	@Disabled
	@DisplayName("스터뒤 만들기 테스트")
	// 반복적인 테스트마다 다른 값으로 하고 싶은 경우
	// JUnit5는 기본으로 제공, 4에서는 서드파티? 라이브러리 사용해야 가능
	// {0}에서 0은 파라미터 0번째(message)를 참조하는 것
	@ParameterizedTest(name = "{index} {displayName} message= {0}")
	// @ValueSource 사용하면 간단하게 파라미터들을 정의할 수 있다. 
	@ValueSource(strings={"날씨가", "많이", "추워", "그치?"})
	void parameterizedTest(String message) {
		// 위에 4개의 스트링 파라미터가 한 번씩 들어올수 있도록 String message를 정의
		log.info("message: {}", message);
	}
	
	
	
	@DisplayName("스터디 만들귀귀귀")
	@ParameterizedTest(name="{index} {displayName} message={0}")
	// @ValueSource(strings={"날씨가", "많이", "추워", "요!!"})
	@ValueSource(ints={10, 20, 40})
	// 빈 문자열을 하나 더 인자로 추가!
	// @EmptySource
	// null을 하나 더 인자로 추가!
	// @NullSource
	// null하고 빈 문자열을 하나씩 추가, null, empty 하나씩만 적용 된다. 
	// @NullAndEmptySource
	void parameterizedTest1(/* String message */ /* Integer limit */ @ConvertWith(StudyConverter.class) Study study) {
		// log.info(message);
		// log.info("{}", limit);
		// log.info("{}", study.getLimit());
		log.info("{}", study.getLimit());
	}
	
	// SimpleArgumentConverter는 다른 타입으로 변환을 하나의 arg에만 적용 
	static class StudyConverter extends SimpleArgumentConverter {

		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			// targetType이 Study인지 확인! (스터디로만 변환 가능)
			assertEquals(Study.class, targetType, "Can only convert to Study zzz");
			
			// 리턴 값을 받을 땐 해당 파라미터에 @ConvertWith로 알려줘야 한다.
			return new Study(Integer.parseInt(source.toString()));
		}
		
	}
	
	// @CsvSource: 여러 인자를 넘겨줄 수 있다. 기본 구분자는 콤마
	@DisplayName("스터뒤이잉 만들기")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
	void parameterizedTest2(/* Integer limit, String name */ /* ArgumentsAccessor argumentsAccessor */ @AggregateWith(StudyAggregator.class) Study study) {
		// Study study = new Study(limit, name);
		// log.info("{}", study);
		
		// ArgumentAccessor: 인자 값을 조합해서 하나로 만들어준다.
		// Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
		// log.info("{}", study.toString());
		
		// 위 작업을 더 줄이고 싶다면 costom한 aggregator을 만들어준다.
		log.info("{}", study);
	}
	
	
	// 만들기 위한 제약조건(or)
	// 1. 반드시 static inner class
	// 2. public 클래스 여야 한다.
	static class StudyAggregator implements ArgumentsAggregator {

		@Override
		public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
				throws ArgumentsAggregationException {
			// 리턴 값을 받을 땐 해당 파라미터에 @AggregateWith으로 알려줘야 한다.
			return new Study(accessor.getInteger(0), accessor.getString(1));
		}
		
	}
	
	
	int value = 1;
	
	@Order(1)
	@FastTest
	@DisplayName("스터디 만들기 인스턴스 공부1")
	void test_instance1() {
		System.out.println("1: " + this);
		System.out.println(value++);
		Study actual = new Study(1);
		System.out.println(value);
	}

	@Order(2)
	@FastTest
	@DisplayName("스터디 만들기 인스턴스 공부2")
	void test_instance2() {
		System.out.println("2: " + this);
		System.out.println(value++);
		Study actual = new Study(1);
		System.out.println(value);
	}
	
	
	
}
