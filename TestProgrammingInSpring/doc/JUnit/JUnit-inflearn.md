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
    <br/>

- assertTimeout(duration, executable): 특정 시간 안에 실행되는지 확인