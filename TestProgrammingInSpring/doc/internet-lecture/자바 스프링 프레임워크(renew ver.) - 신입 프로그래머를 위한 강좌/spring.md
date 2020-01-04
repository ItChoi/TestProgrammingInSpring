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
    
## 또 다른 플젝 생성 방식
- IDE에 의존하지 않고 직접 만들어보기.
    1. 폴더(java,resources)와 파일(pom.xml) 만들기
      - 프로젝트 명 폴더 만들고 src폴더를 만들어준다
      - src 폴더와 같은 경로에 pom.xml을 만들어준다.
      - src 폴더 하위 경로에 main을 만들어준다.
      - main 하위 폴더에 java 및 resources만들어준다.
    2. 이클립스 import 하기     
      - import ~
      
      
## DI (Dependency Injection)
- DI란?
    - 스프링 뿐만 아니라 OOP에서 사용하는 방법론
    
    <br/>
- 스프링 DI 설정 방법
    - ㅁㄴㅇㅁㄴㅇ


