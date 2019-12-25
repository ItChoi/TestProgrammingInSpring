### 자바 리플렉션

#### 1. 스프링 DI (Dependency Injection) 동작 방식
- Service 클래스를 만들어 애노테이션 Service를 추가하고, Repository클래스를 만들어 repository 애노테이션을 붙여 준 다음에 메인에서 @Autowired를 선언하여 사용할 때 왜 null이 아니고 사용이 가능할까? 고민해볼 필요가 있다.

#### 2. 클래스 정보 조회
- 클래스 로딩이 끝나면 heap에 넣어준다.
- 클래스 로딩만 해도 인스턴스가 만들어진다.
- 클래스를 조회하는 3가지 방법  

```java
Class<Board> class = Board.class;
Board board = new Board(); Class<? extends Board> boardClass2 = board.getClass();
Class<?> boardClass2 = Class.forName("com.test.Board");
```
- Class를 통해 해당 클래스의 변수 및 생성자, 메소드를 가져올 수 있다. (상속도 포함) 접근제어 지시자(private, public, ...)에 따라 접근을 하지 못하는 경우도 있는데, 이럴 때는 setAccessible(true)를 주면 접근 가능하다.

#### 3. 애노테이션과 리플렉션
- 자바에서 애노테이션 만들 때 public @interface AnnotationClass { }
- 기본적으로 애노테이션은 주석이나 마찬가지... 기능은 몇 가지 더 있지만 주석과 같은 취급 소스, 클래스도 남지만 바이트 코드 로딩 시 메모리 상에는 남지 않는다.그러나 런타임까지도 이 정보를 유지하고 싶다면 @Retention(RetentionPolicy.CLASS) 추가

- 애노테이션 위치 제한 가능하다 @Target(ElementType.TYPE, ElementType.FIELD) 타입과 필드만 가능, 생성자에 붙일 시 컴파일 에러가 난다.
- 애노테이션에 default 값 설정 가능
- 상속 가능하도록 애노테이션 설정 @Inherited
- javap란?
    - 간단히 설명하면, 클래스 파일의 역어셈블하는 실행파일이다. (역컴파일러와는 다르다.) 
      - 역컴파일: 클래스 파일을 원래 소스로 변환하는 것
      - 역어셈블: 클래스 파일의 내부 기본 구조와 역어셈블코드(JVM의 바이너리 코드)만을 나오게 한다. 실제 동작되는 바이트 코드를 확인하고 싶을 때 JDK에 내장되어 있는 javap라는 역어셈블러를 사용한다.
        - -l: 클래스 내 모든 라인 번호와 지역변수 테이블 출력
        - -public, protected, private: 접근 제어 지시자로 선언된 클래스와 멤버 출력 
        - -p: 모든 클래스와 멤버 출력
        - -c: 역어셈블된 코드 출력
        - -verbose: 스택 사이즈 출력
        - -v: 애노테이션 정보 
    	
#### 4. 리플렉션 API: 클래스 정보 수정 또는 실행
- Class 인스턴스 만들기: Class.newInstance()는 권장하지 않기에 Constructor.newInstance(param) 생성자로 인스턴스 만들기!
- 필드 값 접근 및 설정
    - 인스턴스 필요
    - Field.set(obj, val), Field.get(obj)
- 메소드 실행
    - method.invoke(obj, param);    
    
#### DI (Dependecny Injection)
##### 나만의 DI 프레임워크 만들기    
- @Inject 애노테이션 지원 예정
- 우리가 만든 IoC 컨테이너 설치 후 사용해보기.
    - 프로젝트 경로 이동 후 mvn install (안되서 살펴보니, maven 따로 설치가 필요하고, 환경 변수 등록 후 재접속이 필요하다.)
    - mvn install 후 로컬 경로: C:\Users\itcho\.m2\repository\com\spring\test\1.0.0-BUILD-SNAPSHOT에 jar나 war파일로 들어간다.
    - 그러고나면 다른 메이븐 프로젝트에서 참조할 수 있다.
    - 새 프로젝트를 만든 후
      1. 새 프로젝트 pom.xml의 dependency를 추가한다.
      ```java
      <dependency>
      	<groupId>com.spring</groupId>
      	<artifactId>test</artifactId>
      	<version>1.0.0-BUILD-SNAPSHOT</version>
      </dependency>
      ```
      
#### 리플렉션 정리 및 활용      
- 리플렉션 사용 시 주의점
    - 지나친 사용은 성능 이슈 야기
    - 컴파일 타임에 확인되지 않고 런타임 시 발생하는 문제 만들 가능성
    - 접근 지시자 무시 가능성
    <br/>
- 스프링
    - 의존성 주입
    - mvc 뷰에서 넘어온 데이터를 객체에 바인딩 할 때
    
- 하이버네이트 사용 시
    - @Entity 클래스에 Setter가 없다면 리플렉션 사용
    <br/>

- JUnit
          

