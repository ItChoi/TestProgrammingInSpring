### 도커와 테스트

# TODO::: 이 이후로 테스트 설명이 자세하지 않아 알아듣기 힘들다. 테스트 경력을 쌓고 나중에 강의 다시 듣기

#### TestContainers 소개
- 테스트에서 도커 컨테이너를 실행할 수 있는 라이브러리
    - https://www.testcontainers.org/
    - 테스트 실행시 DB를 설정하거나 별도의 프로그램 또는 스크립트를 실행할 필요 없다.
    - 보다 Production에 가까운 테스트를 만들 수 있다.
    - 테스트가 느려진다.
    <br/>
    

#### TestContainers 설치
- Testcontainers JUnit5 지원 모듈 설치
    
```java
<!-- https://www.testcontainers.org/test_framework_integration/junit_5/ -->
<dependency>
	<groupId>org.testcontainers</groupId>
	<artifactId>junit-jupiter</artifactId>
	<version>1.12.4</version>
	<scope>test</scope>
</dependency>
```

- @Testcontainers
    - JUnit5 확장팩으로 테스트 클래스에 @Container를 사용한 필드를 찾아서 컨테이너 라이프사이클 관련 메소드를 실행해준다.
    <br/>
    
- @Container
    - 인스턴스 필드에 사용하면 모든 테스트 마다 컨테이너를 재시작하고, 스태틱 필드에 사용하면 클래스 내부 모든 테스트에서 동일한 컨테이너를 재사용한다.
    <br/>

- 여러 모듈을 제공하는데, 각 모듈은 별도 설치 필요
    - Postgresql 모듈 설치
    - https://www.testcontainers.org/modules/databases/
    - https://www.testcontainers.org/modules/databases/postgres/
    
    ```java
    <dependency>
		<groupId>org.testcontainers</groupId>
		<artifactId>postgresql</artifactId>
		<version>1.12.4</version>
		<scope>test</scope>
	</dependency>
    ```
    
    
#### Testcontainer, 기능 살펴 보기
- 컨테이너 만들기
    - New GenericContainer(String imageName)
    <br/>

- 네트워크
    - withExposedPorts(int...)
    - getMapperedPort(int)
    <br/>
    
- 환경 변수 설정
    - withEnv(key, value)
    <br/>
    
- 명령어 실행
    - withCommand(String cmd...)
    <br/>
    
- 사용할 준비가 됐는지 확인하기
    - watingFor(Wait)
    - Wait.forHttp(String url)
    - Wait.forLogMessage(String message)
    <br/>
    
- 로그 살펴보기
    - getLogs()
    - followOutput()
            
    
