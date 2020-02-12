### 면접 기술 질문
 
#### 개발 상식
1. 객체 지향 프로그래밍이란?
     - 쉽게 말하면, 현실 세계를 프로그래밍으로 옮겨와 객체화시켜 프로그래밍 하는 것을 말합니다. 근데, 현실 세계의 사물과는 차이점이 있습니다. 객체지향 관점에서는 모든 것이 능동적이고 자율적인 존재로 무생물 역시 스스로 행동하고 책임지는 자율적인 존재로 취급됩니다. (가방)
     - 객체지향 설계 원칙
       - SRP (Single Responsibility Principle) : 단일 책임 원칙
         - 클래스는 단 하나의 책임을 가져야 하며, 클래스를 변경하는 이유는 단 하나의 이유이어야 한다.
       - OCP (Open-Closed Principle) : 개방-폐쇄 원칙
         - 확장에는 열려 있어야 하고 변경에는 닫혀 있어야 한다.
       - LSP (Liskov Substitution Principle) : 리스코프 치환 원칙
         - 상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입 사용 프로그램은 정상 동작해야 한다.
       - ISP (Interface Segregation Principle) : 인터페이스 분리 원칙
         - 인터페이스는 그 인터페이스를 사용하는 클라이언트를 기준으로 분리 해야 한다.
       - DIP (Dependency Inversion Principle) : 의존 역전 원칙
         - 고수준 모듈은 저수준 모듈의 구현에 의존해서는 안된다.
         
2. Restfull API (REpresentatinal State Transfer, Application Programming Interface)
    - API 설계 중심에 자원이 있고, Http Method를 통해 자원을 처리하도록 설계
    - Restful하게 API를 디자인 한다는 것은 무엇을 의미하는가?
       - 리소스와 행위(Http Method)를 명시적이고 직관적으로 분리
       - Message는 Header와 Body를 명확하게 분리해서 사용
         - header: API 버전 정보, 응답 받고자 하는 MIME 타입 등
         - body: Entity에 대한 내용
    - API 버전을 관리한다.
       - 특정 API 변경 시 반드시 하위호환성을 보장해야 한다.
    - 서버와 클라이언트가 같은 방식을 사용해서 요청한다.
       - 브라우저는 form-data로 보내고 서버는 json으로 분리하는 것 보단, 클 & 서버를 form-data로 보내든 json으로 보내든 통일하여 보낸다.
    - 장점
       - Open API를 제공하기 쉽다.
       - 멀티플랫폼 지원 및 연동 용이
       - 원하는 타입으로 데이터를 주고 받을 수 있다.
       - 기존 웹 인프라(HTTP)를 그대로 사용 가능
    - 단점
       - 분산 환경에 부적합
       - HTTP 통신 모델에 대해서만 지원

3. 웹 프로그래밍 설계 모델
- Model1
    - 브라우저 -> Was (jsp, servlet & dao) -> DB
    - 유지보수가 힘들 수 있다.
- Model2
    - 브라우저 -> Was (view - controller - service - dao) -> model -> DB
    - MVC를 기본으로 한다.
- 스프링 MVC 프레임워크 설계 구조
    - 브라우저 요청
    - DispatcherServlet -> HandlerMapping(Controller)
    - HandlerAdapter -> 해당 컨트롤러 Method를 찾아준다. -> model
    - DispatcherServlet -> ViewResolver -> jsp
    - 브라우저 응답
- DispatcherServlet 설정
    - web.xml에 서블릿 등록 후 모든 루트에 매핑되도록 설정
    - DispatcherServlet이 만들어 질 때 스프링 설정 파일(serlvet-context.xml)까지 설정해서 스프링 컨테이너가 만들어져야한다.
    - 스프링 컨테이너가 만들어지면 그 안에는 HandlerMapping, HandlerAdapter, ViewResolver는 자동으로 생성된다.
    - 만약 DispatcherServlet을 등록할 때 스프링 설정 파일(servlet-context.xml) 초기 파라미터로 주지 않으면, 자동으로 스프링 프레임워크가 스프링 설정 파일을 생성한다.
    
    
#### 자료구조
1. Collection
    - List (ArrayList, LinkedList, Vector)
       - 순서를 유지하고 저장
       - 중복 저장 가능  
       - 객체를 인덱스로 관리 
       - 객체를 저장하는 것이 아니라, 해당 인덱스에 객체의 주소를 참조하여 저장
       - ArrayList
         - 순차 추가/삭제, 조회가 빠르다
         - 저장 용량이 초과되도 자동으로 저장용량 늘어난다. (초기 10)
         - 객체 삽입/삭제 자주 있을 시 비효율
       - LinkedList
         - 순차 추가/삭제, 검색 느리고, 중간 추가/삭제가 빠르다.
       - Vector
         - ArrayList와 같은 구조
         - Vector는 동기화(Syncronized) 메소드로 구성되어 Multi Thread가 동시에 이 메소드를 실행할 수 없다. (Thread safe)
    - Set
       - 순서를 유지하지 않고 저장
       - 중복 저장 안된다.
       - HashSet
          - 순서 없이 저장
          - 동일 객체 중복 저장 x
       - TreeSet
          - 이진 트리(binary tree)기반 Set Collection
          - 2개의 자식 노드를 참조하기 위한 2개의 변수로 구성
          - 객체 저장 시 자동 정렬, 부모 값과 비교하여 낮은 것은 왼쪽 자식 노드, 높은 것은 오른쪽 자식 노드에 저장
          -   
    - Queue
       - FIFO (First In First Out) 구조
2. Map
    - Map (HashMap, HashTable, TreeMap, Properties)
       - 키와 값의 쌍으로 저장
       - Key는 중복 불가 (기존 값 사라지고 새로운 값 대체)
       - HashMap
          - 키 값은 주로 String, Object도 가능
       - HashTable
          - HashMap 동일 구조
          - thread safe
       - Properties
          - HashTable 하위 클래스
       - TreeMap
          - 이즌 트리 기반으로 한 Map Collection
          - TreeSet과 같은 구조지만 키와 값이 저장된 Map entity를 저장하는 것
          
#### 네트워크
1. HTTP의 GET과 POST 비교
    - GET
       - 데이터가 HTTP Request Message의 Header 부분의 url에 담겨 전송, 쿼리 스트링을 이용해 request를 같이 보낸다. 
       - url에 담겨 전송하기에 데이터 크기가 제한적
       - 보안이 필요한 데이터는 부적절
       - 브라우저에 Caching 가능.. 따라서 POST 요청이 필요한 데이터를 GET으로 요청 시 Caching 되어 있던 데이터가 응답될 가능성이 있다.
    - POST
       - Request가 HTTP Message의 Body 부분에 데이터가 담겨 전송
       - 보안 면에서 GET방식 보다 낫지만, 암호화를 하지 않는 이상 비슷...
2. TCP 3-way-handshake
    - 연결
       - 클라이언트는 서버에 접속 요청 패킷 전송
       - 서버는 클라이언트의 요청을 받아 클라이언트에게 요청을 수락한다는 패킷 발송
       - 클라이언트는 서버의 수락 응답 패킷을 받아 다시 ACK를 서버로 보내면 연결 성립
    - 연결 해제
       - 클라이언트가 서버에게 연결 종료 플래그 전송
       - 서버는 플래그를 전송 받고 확인 메시지로 ACK를 보낸다.
          - 전송 중인 데이터 모두 발송 시 연결 종료되었다고 클라이언트에게 플래스 전송
       - 클라이언트는 플래스를 받고, 알겠다고 메시지를 보낸다.
       - 메시지를 받은 서버는 소켓 연결을 닫는다.
       - 클라이언트는 서버에서 받지 못한 데이터를 대비하여 일정 시간 세션을 남겨놓고 패킷을 기다리는 과정을 거친다.
3. TCP와 UDP 비교
    - UDP (User Datagram Protocal)
       - 비연결형 프로토콜
       - 흐름 제어, 오류 제어, 손상 세그먼트 수신에 대한 재전송을 하지 않는다. (사용자 프로세스 몫)
    - TCP (Transmission Control Protocal)
       - 송신자, 수신자 모두 소켓을 생섬함으로써 이루어진다.
       - 3-way-handshake를 통해 진행된다.
       - 전이중, 점대점 방식
          - 전이중: 전송이 양방향으로 동시에 일어날 수 있음
          - 점대점: 각 연결이 정확히 2개의 종단점을 가지고 있음
4. HTTP와 HTTPS
    - HTTP
       - 평문 통신으로 도청 가능성이 있다.
       - 통신 상대를 확인하지 않기에 위장 가능
       - 변조 가능
       - SSL을 조합한 HTTP를 HTTPS라고 부른다.
       - TCP와 직접 통신
    - HTTPS
       - HTTP 통신 소켓 부분을 SSL(Secure Socket Layer) 이나 TLS(Transport Layer Security)라는 프로토콜로 대체
       - TCP 직접 통신이 아닌, HTTP가 SSL과 통신하고 SSL이 TCP와 통신하게 된다.
       - HTTPS의 SSL에서는 공통키 암호화, 공개키 암호화 방식을 혼합한 암호 시스템 사용
       - 모든 웹 페이지에서 HTTPS를 사용하지 않는 이유
          - 평문 통신에 비해 암호화 통신은 CPU나 메모리 등의 리소스가 많이 필요, 서버 한 대당 처리 가능 Request 수가 줄어 든다.
    - TODO::: 내용이 좋다 더 보자.
5. 운영체제
    - 프로세스
       - 실행 중인 프로그램 자체와 프로그램이 실행되는 주변 환경(파일, 데이터, 메모리 영역, 주소 공간)을 포함
       - 디스크로부터 메모리에 적재되어 CPU의 할당을 받을 수 있는 것
       - OS로부터 주소 공간, 파일, 메모리등을 할당받음
       - 프로세스 실행 중 동적으로 할당되는 메모리인 힙을 포함한다.
    - 스레드
       - 프로세스의 실행 단위
       - 프로세스 내에서 동작되는 여러 실행 흐름
       - 프로세스 내의 주소 공간이나 자원 공유 가능
    - 멀티 스레드
    - 스케줄러
       - 프로세스를 스케줄링 하기 위한 Queue에는 세 가지 종류가 존재
          - Job Queue: 현재 시스템 내에 있는 모든 프로세스의 집합
          - Ready Queue: 현재 메모리 내에 있으면서 실행되기를 기다리는 프로세스의 집합
          - Device Queue: Device I/O 작업을 대기하고 있는 프로세스의 집합


### 참조
- [https://github.com/JaeYeopHan/Interview_Question_for_Beginner](https://github.com/JaeYeopHan/Interview_Question_for_Beginner)