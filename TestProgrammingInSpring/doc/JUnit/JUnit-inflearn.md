### 테스트하는 다양한 방법
- JUnit5
     - JUnit Platform: junit 작성 테스트 코드를 실행해주는 런쳐(콘솔, 메인메서드, IDE 지원)
     - JUnit Jupiter: 플랫폼이 제공하는 테스트 엔진 APi의 구현체 인데, 5의 구현체
     - JUnit Vintage: '' 3, 4의 구현체
     

#### JUnit5 시작
- 인텔리 기준 해당 클래스의 테스트 코드 만들기 단축키: Ctrl + Shift + T 또는 Shift 두번 누르고 Create Test 검색
    <br/>
- 이클립스의 경우 마우스로 new -> JUnit test case로 알맞는 버전의 테스트 클래스를 만들 수 있다.
    - pom.xml 디펜던시로 추가해도 된다.
    ```java
    <dependency>
    	<groupId>org.junit.jupiter</groupId>
    	<artifactId>junit-jupiter-engine</artifactId>
    	<version>5.5.2</version>
    	<scope>test</scope>
    </dependency>
    ```
    - 나는 JUnit test case 선택 후 JUnit5 클릭하고 클래스를 만드니 JUnit5 외부 라이브러리가 등록되어 사용했다.
    <br/>
- 스프링 부트 2.2부터는 스프링 starter가 제공 하는 기본 JUnit 의존성이 5로 바뀌었다. 이전은 JUnit4로 만들어져있다.
    <br/>
- JUnit5부터는 클래스나 테스트 메서드가 public일 필요가 없어졌다. 4에서는 둘 다 반드시 public이었어야 했는데 제약이 사라짐.
    - 아마도 4, 5 모두 리플렉션을 사용해서 private도 접근 가능하니.. 쓸 필요가 없었나? 
    <br/>
- JUnit5 애노테이션
    - @BeforeAll
      - 테스트 클래스 안에 있는 여러 테스트가 실행되기 전 딱 한 번만 호출이 된다.
      - 반드시 static 사용
      - private은 안되고 default는 가능
      - return type 사용 불가
      <br/>
    - @AfterAll
      - 모든 테스트가 실행된 이후에 딱 한번만 실행
      - 위 조건과 같다.
      <br/>
      
    - @BeforeEach
      - 모든 테스트를 실행할 때 각각의 테스트 실행 전과 실행 후에 두 번 호출된다.
      - static일 필요 없다.
      <br/>
      
    - @AfterEach
      - 위와 동일
      <br/>
    
    - @Disabled
      - 테스트 중 실행하고 싶지 않은 곳에 마킹하면 그 테스트는 실행되지 않는다.
      <br/>
      
### JUnit5 테스트 이름 표기
- @DisplayNameGeneration
    - JUnit 탭에 테스트 메소드 이름 표기 관련 애노테이션
    - 클래스, 메소드 사용 가능
    - 클래스에 사용하면 모든 메소드에 적용 가능
    
    ```java
    // 언더스코어를 빈 공백 문자로 치환
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class StudyTest {
		@Test
		void create1_new_study_again() {
			System.out.println("create1");
		}
	}
    ```
    

- @DisplayName
    - 메소드 표기 이름을 만들어준다. 한글, 이모티콘도 가능
    - ex) @Test@DisplayName("스터디 만들기")
    <br/>     

- 참조
    - [https://junit.org/junit5/docs/current/user-guide/#writing-tests-display-names](https://junit.org/junit5/docs/current/user-guide/#writing-tests-display-names "JUnit5 테스트")      
    
    
### JUnit5 - Assertion
- assertEquals(expected, actual): 실제 값이 기대한 값과 같은지 확인
    - 인자1에 기대하는 값
    - 인자2에 예상값
    - 인자3에 에러 발생 시 메시지  new Supplier() { override get } 및 이걸 줄여 람다식으로 가능
      - 람다식의 경우 실패했을 경우 메시지 연산을 하지만, 아닐 경우 성공이든 실패든 하기 때문에 비용을 줄일 수 있다.
    <br/>
    
- assertNotNull(actual): 값이 null인지 확인
    <br/>
    
- assertTrue(boolean): 조건이 참인지 확인
    <br/>
    
- assertAll(executables...): 모든 확인 구문 확인
    - 에러 2개의 코드 시 첫 번째 만나는 에러가 먼저 발생하여 두 번째 에러는 실행을 안하는데, 이걸로 돌리면 모든 에러 메시지가 뜬다.
    ```java
    assertAll(
			() -> assertNotNull(study),
			() -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디 처음 상태는 " + StudyStatus.DRAFT + " 이다."),
			() -> assertTrue(study.getLimit() > 0, "스터디 참석인원은 0보다 커야 한다.")
		);
    ```

- assertThrows(expectedType, executable): 예외 발생 확인

    ```java
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
		assertEquals("limit은 0보다 커야 함.", exception.getMessage());
    ```

- assertTimeout(duration, executable): 특정 시간 안에 실행되는지 확인
    - new Study(10)객체가 10초안에 생성되지 않으면 예외 발생
      - assertTimeout(Duration.ofSeconds(10), () -> new Study(10));

    ```java
    // 단점 코드블럭이 실행 끝나는 거 까지 기다리고 시간을 비교한다.
	assertTimeout(Duration.ofMillis(100), () -> {
		new Study(10);
		Thread.sleep(300);
	});
	
	// 시간이 초과하면 테스트를 바로 종료시킨다. 
	// 주의: 테스트 코드 블럭은 별도의 스레드에서 실행하기 떄문에, 스레드 로컬을 사용하는 코드가 있다면
	// 예상치 못한 결과가 나올 수 있다. 가령, 트랜잭션 처리는 스레드 로컬을 기본 전략으로 하는데,
	// 스레드 로컬은 다른 스레드에서 공유가 불가능! - 조정은 가능 
	// 테스트에서 스프링 트랜잭션이 제대로 동작안할 수 가 있어서 rollback이 안되고 DB에 반영될 수 있다.
	assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
		new Study(10);
		Thread.sleep(300);
	});
    ```
    

### JUnit5 - 조건에 따라 테스트 실행하기
- 테스트 코드를 특정한 OS, Java Version, 환경 변수, 시스템 변수에 따라 실행 여부를 결정해야 할 때 사용
- org.junit.jupiter.api.Assumptions.*
    - assumeTrue(조건)
      - assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));
    - assumingThat(조건, 테스트)
      ```java
     	assumingThat("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> {
			// 코드 실행
		});
		
		assumingThat("DEV".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> {
			// 코드 실행
		});
      ```
 
- @Enabled__ 와 @Disabled__
    - OnOS: 특정 OS
    - OnJre: 특정 자바 버전
    - IfSystemProperty: 시스템 변수...
    - IfEnvironmentVariable: 환경 변수..
    - if
    <br/>
    - ex: @EnabledOnOS(OS.MAC) OS가 Mac인경우에 메서드 실행!
    - ex: @DisabledOnOS(OS.MAC) OS가 Mac인경우에 메서드 실행하지 않음!
    - ex: @EnabledOnOS({OS.MAC, OS.LINUX}) OS가 Mac이건 Linux경우에 메서드 실행!
    - ex: @EnabledIfEnvironmentVariable(named="TEST_ENV", matches="LOCAL") 일 경우 실행
        
        
### JUnit5 - 태깅과 필터링
- 테스트 그룹을 만들고 원하는 테스트 그룹만 테스트를 실행할 수 있는 기능
- @Tag
    - 테스트 메소드 태그를 추가할 수 있다..
    - 하나의 테스트 메소드에 여러 태그를 사용할 수 있다.
    - 인텔리제이에서는 Edit Configuration에 JUnit 탭에서 Test Kind에 Class가 아닌 Tags로 선택하고 밑에 @Tag Name을 적어주면 해당하는 것만 실행
    <br/>
    
    - 문제발생 문제발생!
      - 이클립스에서는 pom.xml dependency에서  <includeTags>fast</includeTags> 추가하는 방식...?
      - 나는 이클립스에서 왜 밑에처럼 해도 안될까... JUnit 5를 외부 라이브러리로 받아 사용해서 그런가?
      - 클래스에  @ExcludeTags("fast") @IncludeTags("slow") 해줘도 안된다... 흐음
    - 해결함! 해결함!
      - 해결함 이클립스: 우클릭 Run Configuration에 가서 Test 탭에 include and exclude tags: 에 Configure 버튼 클릭 후 추가 및 삭제!!
    
    ```java
    <!-- build툴 및 ci에서 테스트를 돌리기 위함 -->
    <profiles>
    	<!-- 특정 profile마다 각기 다른 설정을 할 수 있다.-->
    	<profile>
    		<id>default</id>
    		<activation>
    			<activeByDefault>true</activeByDefault>
    		</activation>
    		<build>
    			<plugins>
    				<plugin>
	    				<artifactId>maven-surefire-plugin</artifactId>
	    				<configuration>
	    					<groups>fast</groups>
	    				</configuration>
    				</plugin>
    			</plugins>
    		</build>
    	</profile>
    	
    	<!-- ci서버로 빌드 하려면 터미널에 ./mvnw test -P ci -->
    	<profile>
    		<id>ci</id>
    		<build>
    			<plugins>
    				<plugin>
	    				<artifactId>maven-surefire-plugin</artifactId>
	    				<!-- 설정 안하면 테스트 다 실행 -->
	    				<configuration>
	    					<groups>fast | slow</groups> <!-- fast | slow 테스트 그룹 실행 -->
	    				</configuration>
    				</plugin>
    			</plugins>
    		</build>
    	</profile>
    </profiles>
    ```
    


#### JUnit5 - 커스텀 태그
- JUnit5 애노테이션을 좋바하여 커스텀 태그를 만들 수 있다.
- 간단하지만 꽤 유용하다.
- JUnuit5 애노테이션은 메타 에노태이션(@Test, @Tag, @DisplayName, 등등)을 사용할 수 있는데, 따라서 composed 애노테이션을 만들어서 사용할 수 있다. 즉, 커스텀 애노테이션을 만들 때 애노테이션 위에 메타 애노테이션을 사용하면 그게 마치 우리가 만든 애노테이션을 사용하더라도, 동일한 기능이 적용 된다.

```java
// 이 애노테이션을 사용할 때 이 애노테이션 정보를 런타임까지 유지..
@Retention(RetentionPolicy.RUNTIME)

// 두 개의 메타 애노테이션을 사용
@Test
@Tag("fast")
// FastTest라는 Composed(여러 개의 다른 애노테이션을 조합해서 만든) 새로운 애노테이션을 만듬!
public @interface FastTest {

}

class StudyTest {
	// @FastTest를 사용하면 @FastTest에 설정한 메타 애노테이션 정보를 간단히 사용 가능
	// 만약 @Tag("fast")로 한다면 안에 있는 문자열은 Type Safe 하지 않다. 즉 오타가 나서 원하는대로 동작하지 않을 수 있다. 따라서 커스터마이징한 태그를 만들어 사용할 수 있다.
	@FastTest 
	void customizingTag1() {
		log.info("test fast customizingTag");
	}
}
```



#### JUnit5 - 테스트 반복하기.
- @RepeatedTest
    - 반복 횟수와 반복 테스트 이름 설정 가능
      - {displayName}
      - {currentRepetition}
      - {totalRepetitions}
    - RepetitionInfo 타입의 인자를 받을 수 있다.
    
    ```java
    // 반복할 횟수: 10
	// @RepeatedTest(10)
	// value: 반복할 횟수, name 반복되는 list들의 이름 지정
	@RepeatedTest(value=10, name="{displayName}, {currentRepetition}/{totalRepetitions}")
	@DisplayName("반복 테스트...")
	void repeatTest(RepetitionInfo repetitionInfo) {
		// RepetitionInfo: 인포를 통해 몇 번째 반복인지, 현재 몇번을 반복해야하는지 정보들을 알 수 있다.
		log.info("test repeated: {} / {}", repetitionInfo.getCurrentRepetition(), repetitionInfo.getTotalRepetitions());
	}
    ```

    
- ParameterizedTest
    - 테스트에 다른 매개 변수를 대입해가며 반복 실행한다.
      - {displayName}
      - {index}
      - {arguments}
      - {0}, {1}, ...
      
      ```java
      @DisplayName("스터뒤 만들기 테스트")
      // 반복적인 테스트마다 다른 값으로 하고 싶은 경우
      // JUnit5는 기본으로 제공, 4에서는 서드파티? 라이브러리 사용해야 가능
      {0}에서 0은 파라미터 0번째(message)를 참조하는 것
      @ParameterizedTest(name = "{index} {displayName} message= {0}")
      // @ValueSource 사용하면 간단하게 파라미터들을 정의할 수 있다.
      @ValueSource(strings = {"날씨가", "많이", "추워", "그치?"})
      void parameterizedTest(String message) {
      	// 위에 4개의 스트링 파라미터가 한 번씩 들어올수 있도록 String message를 정의
      	log.info("message: {}", message);
      }
      ```

- 인자 값들의 소스
    - @ValueSource
    - @NullSource, @EmptySource, @NullAndEmptySource
    - @EnumSource
    - @MethodSource
    - @CvsSource
    - @CvsFileSource
    - @ArgumentSource
    <br/>
    
- 인자 값 타입 변환
    - 암묵적 타입 변환
      - [공식 홈 참조](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests "참조")
      <br/>
    - 명시적 타입 변환
      - SimpleArgumentConverter 상속받은 구현체 제공
      - @ConvertWith
      <br/>
      
    - 인자 값 조합
      - ArgumentAccessor: 인자 값을 조합해서 하나로 만들어준다.
      - 커스텀 Accessor
        - ArgumentsAggregator 인터페이스 구현
        - @AggregateWith

        
```java
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



public class Study {
	private StudyStatus status = StudyStatus.DRAFT;
	//private StudyStatus status;
	
	private int limit;
	
	private String name;
	
	public Study(int limit, String name) {
		this.limit = limit;
		this.name = name;
	}

	public Study(int limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit은 0보다 커야 함.");
		}
		this.limit = limit;
	}
	
	public StudyStatus getStatus() {
		return this.status;
	}

	public int getLimit() {
		return limit;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Study [status=" + status + ", limit=" + limit + ", name=" + name + "]";
	}
}
```

        
### JUnit5 - 테스트 인스턴스
- JUnit은 테스트 메소드마다 테스트 인스턴스를 새로 만든다.
    - 이거쉬 기본전략
    - 독립적 실행으로 인해 예쌍치 못한 부작용 방지
    - 이 전략은 JUnit5에서 변경 가능
    <br/>
    
- @TestInstance (Lifecycle.PER_CLASS)
    - 테스트 클래스당 인스턴스 하나만 만들어 사용
    - 경우에 따라 공유 상태를 @BeforeEach 또는 @AfterEach에 초기화 할 필요가 생김
    - @BeforeAll과 @AfterAll을 인스턴스 메소드 또는 인터페이스에 정의한 default 메소드로 정의 가능
    <br/>
    
- JUnit5의 테스트 메서드를 실행하려면 테스트 클래스의 인스턴스를 만들어야 하는데, 기본 전략은 클래스 안 테스트 메서드 마다 인스턴스를 생성한다. 즉, 멤버 변수(int val = 1;)를 테스트 메소드 두 곳에서 수정을 시켜도 처음에 1을 가져온다.(공유 X) - 테스트 마다 의존성을 줄이기 위함, 전체 테스트를 할 경우 테스트 순서는 ASC(오름차순)이 아니다. 내부적으로 순서가 있고 그 원리에 의해 작동하긴 한다.
    <br/>

```java
// 클래스 마다 인스턴스를 생성 (멤버 변수 공유 가능), 
// 이 애노테이션을 사용하면 @BeforeAll, @AfterAll을 만들어 사용할 때
// 반드시 static으로 만들었어야 했는데, static을 없이 사용할 수 있게 된다.
// 인스턴스를 클래스당 하나를 사용할 경우 유용한 경우가 있다. 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudyTest { 
int value = 1;
	@FastTest
	@DisplayName("스터디 만들기 인스턴스 공부1")
	void test_instance1() {
		System.out.println(this);
		System.out.println(value++);
		Study actual = new Study(1);
		System.out.println(value);
	}
	
	@FastTest
	@DisplayName("스터디 만들기 인스턴스 공부2")
	void test_instance2() {
		System.out.println(this);
		System.out.println(value++);
		Study actual = new Study(1);
		System.out.println(value);
	}
}
```
    
    
### JUnit5 - 테스트 순서
- 내부적으로 테스트 메서드 실행 순서는 있지만, 내부 순서가 개발자 입장에서는 분명하지 않다. 따라서 작성 순서에 의존하면 안된다.
- 경우에 따라 특정 순서대로 테스트를 실행하고 싶을 땐, @TestInstance(TestInstance.Lifecycle.PER_CLASS)와 함께 @TestMethodOrder를 사용할 수 있다.
    - MethodOrderer 구현체 설정
    - 기본 구현체
      - Alphanumeric
      - OrderAnnotation
      - Random
      <br/>
      

```java
// 클래스 마다 인스턴스를 생성 (멤버 변수 공유 가능), 
// 이 애노테이션을 사용하면 @BeforeAll, @AfterAll을 만들어 사용할 때
// 반드시 static으로 만들었어야 했는데, static을 없이 사용할 수 있게 된다.
// 내부 순서를 위해 이 애노테이션이 있어야 순서를 보장하는 것은 아니다. 즉, @TestMethodOrder로만 순서가 보장이 된다.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// 테스트 메서드 작동 순서 조작
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {
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
```



### JUnit5 - junit-platform.properties
- JUnit 설정 파일로, 클래스패스 루트 (src/test/resources/)에 넣어두면 적용된다.
- 테스트 인스턴스 라이프사이클 설정
    - junit.jupiter.testinstance.lifecycle.default = per_class
- 확장팩 자동 감지 기능
    - 자동 감지 기능 default는 false다.
    - junit.jupiter.extensions.autodetection.enabled = true
- @Disabled 무시하고 실행하기
    - junit.jupiter.conditions.deactivate = org.junit.*DisabledCondition
- 테스트 이름 표기 전략 설정
    - junit.jupiter.displayname.generator.default = \org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores
        
        

### JUnit5 - 확장 모델
- JUnit4의 확장 모델은 @RunWith(Runner), TestRule, MethodRole
- JUnit5의 확장 모델은 단 하나, Extension
- 확장 팩 등록 방법
    - 선언적인 등록 @ExtendWith
    - 프로그래밍 등록 @RegisterExtension
    - 자동 등록 자바 ServiceLoader 이용
- 확장팩 만드는 방법    
    - 테스트 실행 조건
    - 테스트 인스턴스 팩토리
    - 테스트 인스턴스 후.처리기
    - 테스트 매개변수 리졸버
    - 테스트 라이프사이클 콜백
    - 예외 처리
    - ...
- 참조
    - [https://junit.org/junit5/docs/current/user-guide/#extensions](https://junit.org/junit5/docs/current/user-guide/#extensions "참조1")
    <br/><br/><br/>
    
    
- @SlowTest 애노테이션이 붙어있지 않지만, 작업이 오래 걸리는 경우 @SlowTest를 사용하라고 권장하게 만들기

```java
// ************** 방법 (1) - 선언적인 등록 @ExtendWith **************
// BeforeTestExecutionCallback, AfterTestExecutionCallback 구현하기.
public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
	// 1초
	private static final long THRESHOLD = 1000L;
	
	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		// ExtensionContext - store 인터페이스가 있는데, 데이터를 넣고 뺴는 용도
		ExtensionContext.Store store = getStore(context);
		store.put("START_TIME", System.currentTimeMillis());
	}
	
	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		Method requiredTestMethod = context.getRequiredTestMethod();
		
		SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);
		
		String testMethodName = context.getRequiredTestMethod().getName();
		
		// ExtensionContext - store 인터페이스가 있는데, 데이터를 넣고 뺴는 용도
		ExtensionContext.Store store = getStore(context);
		// START_TIME을 지움과 동시에 값을 long 타입으로 받으면 현재 시간을 가지고 올 수 있다.
		long startTime = store.remove("START_TIME", long.class);
		// 현재시간에서 뺴면 얼마나 걸렸는지 구한다.
		long duration = System.currentTimeMillis() - startTime;
		
		// 작업 시간이 1초를 넘기면 @SlowTest를 붙이라고 권장.
		if (duration > THRESHOLD && annotation == null) {
			System.out.printf("Please consider mark method [%s] with @SlowTest.\n", testMethodName);
		}
	}
	
	private ExtensionContext.Store getStore(ExtensionContext context) {
		// 테스트 클래스 이름 + 메소드 이름 조합한 네임스페이스
		String testClassName = context.getRequiredTestClass().getName();
		String testMethodName = context.getRequiredTestMethod().getName();
		
		return context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
	}
}



// 위에 만든 Extension 사용!
// 먼저 위에 Extension 클래스를 사용하겠다고 선언
@ExtendWith(FindSlowTestExtension.class)
class StudyTest {

	// 1초 이상이 걸리기 때문에 FindSlowTestExtension 클래스의 로직을 타서 @SlowTest 애노테이션을 붙이라고 권장한다.
	@Test
	@DisplayName("스터디 만들기 Extension 공부")
	void test_extension() throws InterruptedException {
		Thread.sleep(1005L);
	}
	
}





// ************** 방법 (2) - 프로그래밍 등록 @RegisterExtension **************
// 이 방법이 있는 이유는 애노테이션으로 등록하면 인스턴스(FindSlowTestExtension)를 커스터마이징을 할 수가 없다.
// 즉 FindSlowTestExtension는 기본 default 생성자로 만드는 것인데, 위 THRESHOLD라는 제한 시간을 값을 받는 생성자를 만들 경우, 값을 받을 방법이 없다.
// 

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

	private long THRESHOLD;
	
	public FindSlowTestExtension(long tHRESHOLD) {
		super();
		THRESHOLD = tHRESHOLD;
	}
	
}




class StudyTest {
	// 필드에 정의
	// static으로 정의해야 한다.
	// 코딩으로 설정을 해야 한다면 이런 방식으로
	@RegisterExtension
	static FindSlowTestExtension FindSlowTestExtension = new FindSlowTestExtension(1000L);
	
	@Test
	@DisplayName("스터디 만들기 Extension 공부")
	void test_extension() throws InterruptedException {
		Thread.sleep(1005L);
	}
}



// ************** 방법 (2) - 자동 등록 자바 ServiceLoader 이용 **************
// 이 방법은 넘어갔음.. 명시적 선언이 더 좋다고 함. 잘 알고 쓰지 않으면 예상치 못한 예외 발생 확률도 있다
```


### JUnit5 - JUnit4 마이그레이션
- junit-vintage-engine을 의존성으로 추가하면, JUnit5의 junit-platform으로 JUnit 3과 4로 작성된 테스트를 실행할 수 있다.
    - @Rule은 기본적으로 지원하지 않지만, junit-jupiter-migrationsupport 모듈이 제공하는 @EnableRuleMigrationSupport를 사용하면 다음 타입의 Rule을 지원한다.
      - ExternalResource
      - Verifier
      - ExpectedException
      <br/>
      
JUnit4 | JUnit5
---------|---------
@Category(Class) | @Tag(String)
@RunWith, @Rule, @ClassRule | @ExtendWith, @RegisterExtension
@Ignore | @Displayed
@Before, @After, @BeforeClass, @AfterClass | @BeforeEach, @AfterEach, @BeforeAll, @AfterAll      

      

        
        