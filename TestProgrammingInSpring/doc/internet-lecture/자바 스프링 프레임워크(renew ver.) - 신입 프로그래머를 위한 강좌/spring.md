### 스프링 개요
- 스프링 프레임워크
    - 자바 기반 언어
    - 주요 기능
      - DI
        - 주입 기능, 기능을 만들어 필요할 때 사용
      - AOP
        - 공통 부분을 뽑아내어 사용한다. 주요 기능만 집중하여 개발 가능하도록...
      - MVC
      - JDBC
- 스프렝 프레임워크 모듈
    - spring-core: 스프링 핵임 DI / IoC 제공
    - spring-aop: AOP 구현 기능 제공
    - spring-jdbc: DB를 쉽게 다룰 수 있도록 기능 제공
    - spring-tx: 스프링에서 제공하는 트랜잭션 관련 기능 제공
    - spring-webmvc: mvc 구현 기능 제공
- 스프링 컨테이너(IoC)
    - 스프링에서 객체를 생성하고 조립하는 컨테이너로 컨테이너를 통해 생성된 객체를 빈이라고 부른다.
    - xml 문서로 생성된 객체들을 IoC에 담고 있다. 개발자는 IoC에 담겨 있는 객체를 필요할 때 마다 가져와서 사용 가능



### 개발 환경 구축
- jdk -> jre -> api -> jvm
- javac.exe, java.exe를 다른 디렉토리에서도 실행할 수 있도록 환경 변수 Path 설정에 bin 경로 등록    
    - 코딩 프로그램이 다른 경로에 있더라도 javac, java를 이용하여 컴파일러 및 자바 어플리케이션을 실행할 수 있다.
    - java.exe -> JVM 구동 명령
    - javac.exe -> 컴파일러    



### 스프링 프로젝트 생성
- pom.xml
    - 스프링은 모듈로 구성되어 있는데, 모듈 하나 하나 단위가 프로젝트인데, 이를 즉 artifact id에 속한다.스프링 버전에해당하는 것이 Group Id
    - Group Id: 전체 큰 프로젝트를 감싸고 있는 이름
    - Artifact Id: 현재 만들고 있는 프로젝트 아이디
    - version: 
    
- 폴더 및 pom.xml의 이해
    - pom.xml 파일은 메이븐 설정파일로 메이븐은 라이브러리를 연결해주고 빌드를 위한 플랫폼이다.
    
### 첨 해보는 스프링 플젝
- 스프링은 컨테이너 안에 객체들을 다 모아둔다.
- 객체를 메모리 로딩을 하긴 하는데, 메모리가 생성될 때 스프링 컨테이너를 만들고, 거기에 필요한 객체를 생성하고, 필요할 때 빼와서 사용한다. 
- xml에서 bean 정의를 하면 객체가 생성된다. 즉 메모리가 스프링 컨테이너에 로딩된다.
- 스프링 컨테이너 접근 방법
    - GenericXmlApplicationContext ctx = new GenericApplicationContext("classpath:applicationContext.xml");
    - ctx.getBean("id", ClassType.class);
    
### 또 다른 플젝 생성 방식
- IDE에 의존하지 않고 직접 만들어보기.
    1. 폴더(java,resources)와 파일(pom.xml) 만들기
      - 프로젝트 명 폴더 만들고 src폴더를 만들어준다
      - src 폴더와 같은 경로에 pom.xml을 만들어준다.
      - src 폴더 하위 경로에 main을 만들어준다.
      - main 하위 폴더에 java 및 resources만들어준다.
    2. 이클립스 import 하기     
      - import ~
      
      
### DI (Dependency Injection)
- DI란?
    - 스프링 뿐만 아니라 OOP에서 사용하는 방법론
    <br/>

### 다양한 의존 객체 주입
- 생성자를 이용한 의존 객체 주입
- setter를 이용한 의존 객체 주입
- List타입 의존 객체 주입
- Map타입 의존 객체 주입


### 스프링 설정 파일 분리
- 스프링 설정 파일 분리
    - application.xml을 app1.xml, app2.xml, app3.xml로 분리하기.
    - GenericXmlApplicationContext ctx = new GenericApplicationContext({"classpath:app1.xml", "classpath:app2.xml", "classpath:app3.xml"});
    - appImport.xml에 <import resource="classpath:app1.xml"/> 사용해서 가져올 수도 있다.
    <br/>
    
- 빈(Bean)의 범위
    - 스프링 컨테이너에서 getBean("")을 통해 가져오는 객체는 동일한 객체다. 빈 객체시 디폴트가 싱글톤이다.(싱글톤)
    - 프로토 타입 범위: 개발자가 별도의 설정을 해주어야 하고, Bean 객체 정의 시 scope를 통해 해주면 된다.
 
 
### 의존 객체 자동 주입
- 의존 객체 자동 주입이란?
    - 스프링 설정 파일에서 객체 주입 시 xml태그들에 의존하지 않고 스프링 컨테이너가 알아서 자동으루 필요한 의존 대상 객체를 찾아서 삽입
    <br/>
     
- @Autowired
    - 주입하려고 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다.
    - @Autowired Service service -> Service 타입을 찾아 주입.
    - 생성자에 사용시 그냥 붙이면 된다. 단, 프로퍼티나 메소드에 사용 시 디폴트 생성자를 반드시 명시해주어야 한다.
    <br/>

- @Resource
    - 주입하려고 하는 객체의 이름이 일치하는 객체를 자동으로 주입한다.
    - 생성자에 생성 못한다. 프로퍼티 또는 메소드에서만 사용 가능
    - 기본 생성자가 반드시 있어야 한다.
    <br/>
        
- @Autowired 사용 시 
    - xml 파일에 &lt context:annotation-config /&gt 추가, 애노테이션 사용하겠다는 태그
      - namespaces에서 context 추가



### 의존 객체 선택
- 의존 객체 선택
    - 동일한 객체가 2개 이상인 경우 자동 주입 대상 객체를 판단하지 못해 Exception 발생
    - @Autowired
      - @Qualifier("이름"), <qualifier value="이름" />
    - @Inject
      - @Named(value="이름"), xml에서 id로 찾기 때문에 별도의 태그는 필요하지 않음.
    <br/>
    
- 의존 객체 자동 주입 체크
    - @Autowired(required = false): 해당 Bean 객체가 없을 시 Exception 발생하지 않도록 설정. 의존 객체가 있으면 주입, 없으면 안한다. 거의 쓰이지는 않는다.
    <br/> 

- @Inject
    - required 속성을 지원하지 않는다.

### 생명주기
- 스프링 컨테이너 생명주기
    - 해당 xml을 GenericApplicationContext를 이용해 가져오면, 스프링 컨테이너 초기화(생성)
    - getBean을 통해 이용
    - close()를 이용해 스프링 컨테이너 종료 (스프링 컨테이너 및 Bean 소멸, 소멸: 메모리에서 날라간다)
    - 그래서 스프링 컨테이너와 Bean의 생명은 같다고 볼 수 있다.
    <br/>
    
- Bean 객체 생명주기
    - Bean 객체 생명 주기는 스프링 컨테이너의 생명주기와 같다.
    - Bean 객체 생성 시점 호출
      - 인터페이스 활용
          - interface InitializingBean - afterPropertiesSet() {}
      - Bean 태그 속성 활용
          - &lt bean init-method="메소드 이름" destory-method="메소드 이름" /%gt
    - Bean 객체 소멸 시점 호출
        - interface DisposableBean - destory() {}
    <br/>
    
- init-method, destory-method 속성
    
    
    
### 어노테이션을 이용한 스프링 설정 - 1
- XML파일을 Java 파일로 변경하기
    - 스프링 컨테이너를 생성할 수 있는 파일이라고 명시 
      - @Configuration
    - Bean 객체 만들기
      - @Bean public StudenDao studenDao() { return new StudentDao(); }
    - &lt context:annotation-config /&gt 대체
      - AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Class.class);
      
    
### 어노테이션을 이용한 스프링 설정 - 2
- Java 파일 분리
    - AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Class1.class, Class2.class, ...);
    <br/>
- @Import 애노테이션
    - @Configuration @Import({ Class1.class, Class2.class }) public class Class0 { }
    
    
    
    
### 웹 프로그래밍 설계 모델
- 웹 프로그래밍을 구축하기 위한 설계 모델
    - Model1
      - 브라우저 -> Was(웹 어플리케이션 서버) (jsp, service & dao) -> DB
      - 유지보수가 힘들수 있다.
    - Model2
      - 브라우저 -> Was (view - controller - service - dao) -> model -> DB
      - MVC를 기본으로 한다.
    <br/>
- 스프링 MVC 프레임워크 설계 구조
    1. 브라우저 요청
    2. DispatcherServlet
      - 요청을 받아 HandlerMapping 던진다.
    3. HandlerMapping
      - 요청한 알맞은 Controller를 선택해준다.
    4. HandlerAdapter
      - 다시 DispatcherServlet에 와서 HandlerAdapter에 요청 하는데, 알맞은 컨트롤러에 요청을 처리할 메소드를 찾아준다. 그리고 Model이라는 데이터를 가져와서 DispatcherServlet에 돌아간다.
    5. Controller
    6. ViewResolver
      - DispatcherServlet은 다음 ViewResolver를  찾고, 적합한 jsp 페이지를 찾아준다.
    7. View
      - 찾은 jsp를 응답을 생성(데이터 넣어준다)해주고 브라우저에 응답.
    8. 브라우저 응답
    <br/>
- DispatcherServlet 설정
    - web.xml에 서블릿을 매핑
      - 서블릿 등록, 초기 파라미터 등록 등등의 작업
      - DispatcherServlet을 등록하고, 모든 루트에 매핑되도록 설정
      - DispatcherServlet가 만들어질 때servlet-context.xml 스프링 설정 파일까지 설정해서 스프링 컨테이너가 만들어져야 한다.
      - 스프링 컨테이너가 만들어지면 그 안에는 HandlerMapping, HandlerAdapter, ViewResolver는 자동으로 생성된다. 다 스프링 프레임워크 패키지에 있는 파일들이다.
      - 만약 DispatcherServlet을 등록할때 스프링 설정 파일을 초기 파라미터로 주지 않으면, 자동으로 스프링 프레임워크가 스프링 설정 파일을 생성한다.  
        - 초기 파라미터를 이용하여 servlet-context.xml을 이용하여 스프링 컨테이너를 생성
        - 초기 파라미터로 스프링 설정 파일을 지정하지 않은 경우 서블릿 별칭을 이용하여 스프링 컨테이너 생성하여  HandlerMapping, HandlerAdapter, ViewResolver는 자동으로 만들어 준다. ex) appServlet-context.xml
      
      
```java
<servlet>
	<servlet-name>appServlet</servlet-name> <!-- 서블릿 별칭 -->
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class> <!-- 서블릿명 (패키지 이름을 포함한 전체 서블릿명) -->
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
	<servlet-name>appServlet</servlet-name> <!-- 서블릿 별칭 -->
	<url-pattern>/</url-pattern> <!-- /맵핑명 -->
</servlet-mapping>
```
      
- Controller 객체 - @Controller
    - @Controller는 개발자가 직접 만들어야 한다.
      - 스프링 설정 파일에 &lt annotation-driven /&gt 태그를 넣어준다. (@Controller를 사용하기 위해서는 반드시 사용해야 한다.)
      - 태그를 넣어주면, 스프링 컨테이너를 사용하기 위한 여러 가지 클래스들이 Bean 객체로 스프링 설정 파일 존재를 하게 된다.
    <br/>
     
- Controller 객체 - @RequestMapping
    - @RequestMapping는 클라이언트가 요청한 메소드를 찾아야 한다.
    <br/>
    
- Controller 객체 - Model 타입의 파라미터
    - Controller에서 작업을 끝내고 받은 데이터를 model로 담아 ditpatcherServlet에 보내준다.
    <br/>
     
- View 객체
    - DispatcherServlet에 전달된 Model 데이터는 View에서 가공되어 클라이언트에게 응답
    - ViewResolver이 해당 Path와 확장자를 통해 파일을 찾아 사용자에게  응답해줄 view를 만들어준다.
    <br/>
    
    
    
### 스프링 MVC 웹 서비스 - 1
- 웹 서버 (Tomcat) 다운로드
    - 웹 서버와 웹 컨테이너를 제공
    <br/>
- 웹 서버 (Tomcat)와 이클립스 연동
    - 서버 톰캣을 더블 클릭 후 Server Locations를 use Tomcat installation (takes control of Tomcat installation으로 체크해준다.
    - Server Options에 Publish module contexts to separate XML files 체크 해준다.
- 이클립스에 STS 설치
- STS를 이용한 웹 프로젝트 생성
- 스프링 MVC 프레임워크를 이용한 웹 프로젝트 분석



### 스프링 MVC 웹 서비스 - 2
- 프로젝트 전체 구조
    - servlet-context.xml: 스프링 설정 파일 (스프링 컨테이너 생성)
    - web.xml: 웹 설정 파일
    - pom.xml: 메이븐 설정 파일
    <br/>
    
- web.xml
    - 웹 애플리케이션에서 최초 사용자 요청 시 가장 먼저 DispatcherServlet이 요청을 받는다.
    - 따라서 서블릿으로 가장 먼저 등록하고 매핑을 모든 루트 /로 해준다.
    <br/>

- DispatcherServlet
- servlet-context.xml
    - &lt resources mapping="/resources/**/ location="/resources/" /&gt
      - mapping은 폴더 resources안에 html, css, js 등 자원
      - location은 resources 위치?
- Controller
- View      
    
    
    
### STS를 이용하지 않은 웹 프로젝트
- 스프링 MVC 웹 애플리케이션 제작을 위한 폴더 생성
- pom.xml 및 이클립스 import
- web.xml 작성
- 스프링 설정 파일(servlet-context.xml) 작성
- root-context.xml 작성
- 컨트롤러와 뷰 작성
- 실행
    
   
    
### Service & Dao 객체 구현
- 웹 어플리케이션 준비
- 한글 처리
    - web.xml에서 한글 처리 filter 추가, 모든 경로
- 서비스 객체 구현
    - Service 객체 (class MemberService)를 만들어 놓고 위에 @Service를 붙이면 자동으로 스프링 컨테이너에 담기게 되고, Autowired로 사용할 수 있다. 아마 context:component-scan이 있어야 할듯.  
- DAO 객체 구현    
    
   
    
### Controller 객체 구현 - 1
- 웹 어플리케이션 준비
- @RequestMapping을 이용한 URL맵핑
- 요청 파라미터

    
    
### Controller 객체 구현 - 2
- @ModelAttribute
    - 컨트롤러에서 뷰를 호출할 때, 컨트롤러에서 @ModelAttribute("serverTime")이란 메소드를 만들어 놓으면, 다른 뷰 호출 시 공통으로 저걸 사용할 수 있게 된다.
- 커맨드 객체 프로퍼티 데이터 타입
    - List<Phones> phone -> jsp (phone[0].phone1)
- Model & ModelAndView
    
    
    
### 세션, 쿠키
- 세션과 쿠키
    - 세션이 보안 측면에서 강하다. 그러나 쿠키도 보안 측면에서 중요하지 않은 경우 사용하기도 한다.
    - ConnectionList Protocal: 웹 서비스는 HTTP 프로토콜을 기반으로 하는데, 이는 클라이언트와 서버의 관계를 유지하지 않는다. 즉, 요청을 받고 응답을 해주면 연결이 끊어진다. 그 이유는 서버에 요청하는 클라이언트가 여러 명이기 때문에, 부하를 방지하기 위해 자원을 효율적으로 관리하기 위함. 따라서 요청 시 마다 서버와 매번 새로운 연결이 생성된다.
    - 로그인, 상품 주문, 장바구니 등의 기능을 구현하기 어렵다.
    - ConnectionList Protocal의 불편함을 해결하기 위해, 클라이언트와 서버의 연결 상태를 유지하기 위해세션과 쿠키를 이용한다.
      - 세션: 서버에서 연결 정보를 관리한다.
      - 쿠키: 클라이언트에서 연결 정보를 관리한다.
    <br/>
    
- HttpServletRequest를 이용한 세션 사용
    - HttpSession request.getSession() -> session.setAttribute("member", member); -> session.getAttribute("member"); 
    <br/>
     
- HttpSession을 이용한 세션 사용
    - HttpServletRequest, HttpSession 차이점은 거의 없으며 세션 객체를 얻는 방법의 차이만 있을 뿐
    - 파라미터로 HttpSession으로 바로 받아 사용 (조금 더 간단)
    <br/>
     
- 세션 삭제
    - session.invalidate();
    <br/>
- 세션 주요 메소드 및 플로어
    - getId(): 세션 ID 반환
    - setAttribute(): 세션 객체에 속성을 지정
    - getAttribute(): 세션 객체에 지정된 속성 반환
    - removeAttribute(): 세션 객체에 저장된 속성 제거
    - setMaxInactiveInterval(): 세션 객체의 유지 시간 설정
    - getMaxInactiveInterval(): 세션 객체의 유지 시간 반환
    - invalidate(): 세션 객체의 모든 정보 삭제
    <br/>
    
- 쿠키
    - 사용자 로컬 컴퓨터에 정보가 남아있다.
    - HttpServletResponse에 쿠키를 담고 있다.
    - 파라미터로, @CookieValue(value="gender", required=false)
      - value에 명시한 쿠키를 가져온다. 없으면 Exception이 나기에 required 속성으로 제어



### 리다이렉트, 인터셉트
- 리다이렉트 (redirect)
    - 컨트롤러에서 뷰를 분기하는 방법
    - ex) return "redirect:/";
    <br/>
    
- 인터셉트 (interceptor)
    - 컨트롤러 실행 전 / 후에 특정 작업 수행
    - 리다에릭트를 사용해야 하는 경우가 많은 경우 HandlerInterceptor를 이용 할 수 있다.
    - HandlerInterceptor 인터페이스 / 구현체는 HandlerInterceptorAdapter고, 밑에 메소드를 사용하려면 이것을 상속받아야 한다.
      - preHandle(): 컨트롤러가 작동하기 전 작업 (가장 많이 쓰임 - redirect 대체)
      - postHandle(): 컨트롤러 작동 후 작업
      - afterCompletion(): 컨트롤러와 뷰 작업 후 작업
      - servlet-context.xml에서 인터셉너 태그 추가 필요
      


### Database
- 오라클 다운로드
- 오라클 설치
- 계정 생성
    - 오라클 다운로드 시 system 아이디로 만들었고, cmd에서 sqlplus 통해 접속하여 아이디 및 패스워드 입력 
    - create user scott identified by tiger; 를 통해 scott 계정 생성
    - grant를 통해 connect(접속 권한), resource(자원 활용 권한)
    - grant connect, resource to scott;
- SQL developer 



### JDBC
- 기본 SQL
- JDBC
    - 드라이버 로딩
    - DB 연결
    - SQL 작성 및 전송
    - 자원 해제
   
   
    
### JDBC Template
- JDBC의 단점을 보완한 JDBC Template
    - JdbcTemplate(드라이버 로딩, DB 연결, 자원 해제) 
    - SQL 작성 및 전송
    <br/>
    
- DataSource 클래스
    - 데이터베이스 연결과 관련된 정보를 가지고 있는 DataSource는 스프링 또는 c3p0에 제공하는 클래스를 이용할 수 있다.
    - 스프링: org.springframeowkr.jdbc.datasource.DriverManagerDataSource
    - c3p0: com.mchange.v2.c3p0.DriverManagerDataSource
    <br/>
    
    - 스프링 jdbc 디펜던시 추가 , c3p0도 디펜던시 추가 필요
    
    
    
### 커넥션 풀
- 데이터베이스 연결을 미리 준비해 놓고 사용하는 방법
    <br/>
- c3p0 모듈의 ComboPooledDataSource

```java
dataSource = new DriverManagerDataSource();
dataSource.setDriverClass(driver);
dataSource.setJdbcUrl(url);
dataSource.setUser(userId);
dataSource.setPassword(userpw);
```
    
- 스프링 설정파일을 이용한 DataSource 설정

```java
<beans:bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<beans:property name="driverClass" value="oracle.jdbc.dirver.OracleDriver" />
	<beans:property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe" />
	<beans:property name="user" value="scott" />
	<beans:property name="password" value="tiger" />
	<beans:property name="maxPoolSize" value="200" />
	<beans:property name="checkoutTimeout" value="60000" />
	<beans:property name="maxIdleTime" value="1800" />
	<beans:property name="idleConnectionTestPeriod" value="600" />
</beans>
```
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    






