### IoC 컨테이너와 빈

#### IoC 컨테이너 1 - 스프링 IoC 컨테이너와 빈
- Inversion of Control: 의존 관계 주입(Dependency Injection)라고도 하며, 어떤 객체가 사용하는 의존 객체를 직접 만들어 사용하는 것이 아니라, 주입 받아 사용하는 방법
    <br/>
    
- 스프링 IoC 컨테이너
    - BeanFactory
      - 스프링 IoC 컨테이너의 가장 최상위 interface
      - ex) getBean()
    - 애플리케이션 컴포넌트의 중앙 저장소
    - 빈 설정 소스로 부터 빈 정의를 읽어들이고, 빈을 구성하고 제공한다.
    <br/>

- Bean
    - 스프링 IoC 컨테이너가 관리하는 객체
      - @Controller, ... 등등의 애노테이션을 가지고 있는 객체들은 스프링 autuScan을 통해 IoC 컨테이너의 관리 대상이 된다.
    - 빈 등록 시 장점
      - 의존성 관리 
      - 스코프
        - Singleton으로 관리되어야 할 때 IoC 컨테이너에 만들어 사용
        - 프로토타입: 매번 다른 객체를 생성
      - 라이프 사이클 인터페이스
        - 스프링 IoC 컨테이너에 등록된 빈들에 국한된 장점!
        - 빈 객체 생성 전 / 후의 부가적인 작업 등을 할 수 있다. (ex: @PostConstruct)
    <br/>
    
- ApplicationContext
    - BeanFactory
    - 메시지 소스 처리 기능 (i18n)
    - 이벤트 발행 기능
    - 리소스 로딩 기능
    - ...        
    
    

#### IoC 컨테이너 1 - ApplicationContext
- 스프링 IoC 컨테이너의 역할
    - 빈 인스턴스 생성
    - 의존 관계 설정
    - 빈 제공
    <br/>
    
- ApplicationContext
    - ClassPathXmlApplicationContext
    - 
    <br/>
    
- 빈 설정
    - 빈 명세서
    - 빈에 대한 정의를 담고 있다
      - 이름
      - 클래스
      - 스코프
      - 생성자 아규먼트 (constructor)
      - 프로퍼트 (setter)
      - ...         
      
      
      
        