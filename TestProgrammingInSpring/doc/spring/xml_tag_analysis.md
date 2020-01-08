### xml 설정에서 사용하는 태그들을 학습해보자.  
#### mvc:annotation-driven
1. Spring MVC가 HandlerMapping과 HandlerAdapter를 Bean으로 등록한다.
2. bean 생성을 위해 context:component-scan 사용 시 mvc:annotation-driven를 사용하지 않아도 MVC 애플리케이션은 작동한다.


#### context:component-scan
1. 특정 패키지 안의 클래스들을 스캔하고, Annotation을 확인 후 Bean을 생성한다.
    - @Controller, @Service, @Component, @Repository
2. @Autowired와 @Qualifier 인식 가능
3. context:component-scan 사용 시 context:annotation-config 선언할 필요가 없다.


#### context:annotation-config
1. ApplicationContext 안에 이미 등록된 Bean들의 Annotation 활성화
2. IoC Container 안에 Bean 들에 대하여 @Autowired와 @Qualifier 인식 가능
3. component-scan도 같은 일을 하는데, context:annotation-config는 Bean 등록 작업을 하지는 않는다.


#### tx:annotation-driven
1. 등록된 Bean 중에 @Transactional이 붙은 클래스 / 인터페이스 / 메소드를 찾아 트랜잭션 어드바이스 적용


#### mvc:resource mapping
1. 정적 데이터 파일들의 위치를 매핑해준다.
