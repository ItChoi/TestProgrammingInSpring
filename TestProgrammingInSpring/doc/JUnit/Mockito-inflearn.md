### Mockito

#### Mockito 소개
- Mock: 진짜 객체와 비슷하게 동작하지만, 프로그래머가 직접 그 객체의 행동을 관리하는 객체.
- Mockito: Mock 객체를 쉽게 만들고 관리하고 검증할 수 있는 방법 제공
- 테스트를 작성하는 자바 개발자 50% + 사용하는 Mock 프레임워크
    - [https://www.jetbrains.com/lp/devecosystem-2019/java/](https://www.jetbrains.com/lp/devecosystem-2019/java/)
- 최신 버전 3.1.0
- [https://site.mockito.org/](https://site.mockito.org/)
- 대체제: EashMock, JMock

- 애플리케이션이 DB 사용, 외부 APi 또는 자사의 플랫폼 APi를 호출한다고 가정하면(복잡한 상황) 외부 APi가 동작하거나 사용하면서 사용할 수 없기 떄문에, 예측을 통해 Mock을 만들어 객체가 어떻게 동작해야되는지 Mockito로 코딩해놓고, 그 다음 서비스가, 동작한다는 가정하에 이후 서비스를 테스트 
- Mockito 사용 시 Unit 테스트 논의를 하게 되는데,  
참조: [htpps://martinfowler.com/bliki/UnitTest.html](htpps://martinfowler.com/bliki/UnitTest.html)
- 이미 구현되어 있는 클래스는 Mocking할 필요 없지만, 외부 서비스는 Mocking하는 것이 좋은 거 같다. 외부서비스가 테스트 환경이 구축되어 있는 상황이라면, 서버 기반으로 테스트를 하면 되고, 아니라면 Mocking을 하여 실제 응답과 비슷하게 응답이 나오도록 코딩하고 테스트 진행!!
- 인터페이스만 존재하고 구현체가 없는 경우에 Mocking하기 좋은 상황.

#### Mockito 시작하기
- 스프링 부트 2.2 프로젝트 생성 시 spring-boot-starter-test에서 자동으로 Mockito추가해 준다. 그게 아니라면 의존성 직접 추가

```java
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
<!-- Mockito Core -->
<!-- 기본 제공 기능 -->
<dependency>
	<groupId>org.mockito</groupId>
	<artifactId>mockito-core</artifactId>
	<version>3.1.0</version>
	<scope>test</scope>
</dependency>

<!-- Mockito Jupiter -->
<!-- JUnit Test에서 Mockito를 연결하여 테스트할 수 있다. -->
<dependency>
	<groupId>org.mockito</groupId>
	<artifactId>mockito-junit-jupiter</artifactId>
	<version>3.1.0</version>
	<scope>test</scope>
</dependency>

```

- Mock 활용 테스트 쉽게 작성 팁
    - Mock 만드는 방법 .   
    - Mock이 어떻게 동작해야 하는지 관리하는 방법
    - Mock의 행동을 검증하는 방법
    
- Mockito 레퍼런스
    -[https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html) 


#### Mock 객체 만들기
- Mockito.mock() 메소드로 만드는 방법

```java
// MemberService memberService = Mockito.mock(MemberService.class);
// static import 사용시 mock 메서드만 사용 가능
MemberService memberService = mock(MemberService.class);
StudyRepository studyRepository = mock(StudyRepository.class);
```

- @Mock 애노테이션으로 만드는 방법
    - JUnit5 extension으로 MockitoExtension을 사용해야 한다.
    - 필드
    - 메소드 매개변수
    
```java
// @Mock 애노테이션 처리를 해줄 Extension을 등록해야한다. 
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

	@Mock
	MemberService memberService;
	
	@Mock StudyRepository studyRepository;

	@Test
	void testMethod1() {
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNoNull(studyService);
	}

}

```
            
- Mock 객체를 여러 테스트에 걸쳐서 사용한다면 애노테이션이 편한데, Mock 객체를 하나의 메서드에서만 만들어서 사용 가능하다.

```java
// @Mock 애노테이션 처리를 해줄 Extension을 등록해야한다. 
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
	
	@Test
	void testMethod1(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNoNull(studyService);
	}

}
```


### Mock 객체 Stubbing
- Stubbing: Mock 객체의 행동을 조작

- 모든 Mock 객체의 행동
    - Null을 리턴한다. (Optional 타입은 Option.empty 리턴)
    - Primitive 타입은 기본 Primitive 값.
    - 콜렉션은 비어있는 콜렉션.
    - Void 메소드는 예외를 던지지 않고 아무런 일도 발생하지 않는다.
    <br/>
    
- Mock 객체를 조작
    - 특정한 매개변수를 받은 경우 특정한 값을 리턴하거나 예외를 던지도록 만들 수 있다.
      - How about some stubbing?
      - Argument matchers
    - Void 메소드 특정 매개변수를 받거나 호출된 경우 예외를 발생
      - Subbing void methods with exceptions
    - 메소드가 동일한 매개변수로 여러 번 호출될 때 각기 다르게 행동하도록 조작 가능
      - Stubbing consecutive calls
    <br/>
    
```java
// @Mock 애노테이션 처리를 해줄 Extension을 등록해야한다. 
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
	
	@Test
	void testMethod1(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNoNull(studyService);
		
		Member member = new Member();
		member.setId(1L);
		member.setEmail("sanghyun@test.com");

		// memberService.findById(1L)이 호출되면 (id가 1L) 위에 정의한 객체를 리턴한다.
		// Mockito.when(memberService.findById(1L)).thenReturn(Optional.of(member));
		
		// memberService.findById 파라미터로 2L로 설정하면, 호출을 1L로 할 경우 리턴을 하지 않게 된다.
		// 파라미터를 ArgumentMatchers로 해서 조금 더 범용적으로 사용 가능하도록 설정할 수 있다. (파라미터 아무거나 사용 가능)
		Mockito.when(memberService.findById(ArgumentMatchers.any())).thenReturn(Optional.of(member));
		
		Study study new Study(10, "java");
		// studyService.createNewStudy();
		
		Optional<Member> findById = memberService.findById(1L);
		assertEquals("sanghyun@test.com", findById.get().getEmail());

		// id가 1로 해서 호출되면 예외를 던진다.		
		Mockito.when(memberService.findById(1L)).thenThrow(new RuntimeException());
		
		// void 메소드 경우
		// 1의 값으로 멤버 서비스의 validate가 호출되면 예외를 던진다.
		Mockito.doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
		
		assertThrows(IllegalArgumentException.class, () -> {
			memberService.validate(1L);
		});
		
		memberService.validate(2L);
		
		
	}
	
	
	
	@Test
	void testMethod2(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNoNull(studyService);
		
		Member member = new Member();
		member.setId(1L);
		member.setEmail("sanghyun@test.com");

		// 첫 호출, 제대로 나오고
		// 둘 호출, 익셉션 발생
		// 셋 호출, 
		Mockito.when(memberService.findById(ArgumentMatchers.any()))
			.thenReturn(Optional.of(member))
			.thenThrow(new RuntimeException())
			.thenReturn(Optional.empty());
			
			// 제대로 나옴
			Optional<Member> byId = memberService.findById(1L);
			assertEquals("sanghyun@test.com", byId.get().getEmail());
			
			// 익셉션 발생
			assertThrows(RuntimeException.class, () -> {
				memberService.findById(2L);
			});
			
			assertEquals(Optional.empty(), memberService.findById(3L));
		
	}
}

class Study {
	
	public Study createNewStudy(Long memberId, Study study) {
		Optional<Member> member = memberService.findById(memberId);
		study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("에러"));
		return repository.save(study);
	}
}
```



### Mock 객체 확인
- Mock 객체가 어떻게 사용이 됐는지 확인할 수 있다.
    - 특정 메소드가 특정 매개변수로 몇 번 호출 됐는지, 최소 한 번은 호출 됐는지, 호출되지 않았는지
      - Verifying exact number of invocations
    - 어떤 순서로 호출했는지
      - Verification in order
    - 특정 시간 이내에 호출됐는지
      - Verification with timeout
    - 특정 시점 이후에 아무 일도 벌어지지 않았는지
      - Finding redundant invocations
      
```java
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
	
	@Mock MemberService memberService;
	@Mock StudyRepository studyRepository;
	
	@Test
	void createNewStudy() {
		StudyService sutdyService = new StudyService(memberservice, studyRepository);
		assertNotNull(studyService);
		
		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@test.com");
		
		Study study = new Study(10, "테스트");
		
		Mockito.when(memberService.findById(1L)).thenReturn(Optional.of(member));
		Mockito.when(studyRepository.save(study)).thenReturn(study);
		
		studyService.createNewStudy(1L, study);
		
		assertEquals(member, study.getOwner());
		
		// 멤버서비스에서 딱 한번 노티파이가 스터디라는 매개변수를 가지고 호출이 됐어야 한다. - 아닐 시 에러 발생
		// Mockito.verify(객체, times(매개변수)).notify(study);
		// - 매개변수 자리에는 숫자가 들어 간다. 1일 시 - 호출 1번
		// - 호출안되는걸 원한다면 Mockito.never()를 넣어주면 된다.
		Mockito.verify(memberService, times(1)).notify(study);
		// Mockito.verify(memberService, times(1)).notify(ArgumentMatchers.any());
		
		// 순서 확인 - 스터디 먼저 호출 후 멤버가 호출되는지 검증
		// InOrder inOrder = Mockito.inOrder(memberService);
		// inOrder.verify(memberService).notify(study);
		// inOrder.verify(memberService).notify(member);
		
		InOrder inOrder = Mockito.inOrder(memberService);
		inOrder.verify(memberService).notify(study);
		// 위 스터디 호출 후 멤버서비스의 어떠한 호출이 이루어지면 안된다.
		Mockito.verifyNoMoreInteractions(membersService);
		inOrder.verify(memberService).notify(member);
		
	}
	
}
```


#### Mockito BDD 스타일 API
- BDD: 애플리케이션이 어떻게 "행동"해야 하는지에 대한 공통된 이해를 구성하는 방법으로, TDD에서 창안함.
- 행동에 대한 스펙
    - Title
    - Narrative
      - As a / I want / so that
    - Acceptance criteria
      - Given / When / Then
    <br/>
    
- Mockito는 BDDMockito라는 클래스를 통해 BDD 스타일의 API를 제공한다.
- When -> Given
- Verify -> Then
- 참조
    - [https://javadoc.io/static/org.mockito/mockito-core/3.2.0/org/mockito/BDDMockito.html](https://javadoc.io/static/org.mockito/mockito-core/3.2.0/org/mockito/BDDMockito.html)
    - [https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#BDD_behavior_verification](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#BDD_behavior_verification)
    

```java
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
	
	@Mock MemberService memberService;
	@Mock StudyRepository studyRepository;
	
	@Test
	void createNewStudy() {
		// Given
		StudyService sutdyService = new StudyService(memberservice, studyRepository);
		assertNotNull(studyService);
		
		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@test.com");
		
		Study study = new Study(10, "테스트");
		
		Mockito.when(memberService.findById(1L)).thenReturn(Optional.of(member));
		Mockito.when(studyRepository.save(study)).thenReturn(study);
		
		// BDD 방식 Given
		BDDMockito.given(memberService.findById(1L))
			.willReturn(Optional.of(member));
		BDDMockito.given(studyRepository.save(study)).willReturn(study);
		
		
		// When
		studyService.createNewStudy(1L, study);
		
		// Then
		assertEquals(member, study.getOwner());
		Mockito.verify(memberService, times(1)).notify(study);
		Mockito.verifyNoMoreInteractions(membersService);

		// BDD 방식 Verify
		BDDMockito.then(memberService).should(times(1)).notify(study);
		BDDMockito.then(memberService).shouldHaveNoMoreInteractions();
		
	}
	
}
```    
     
