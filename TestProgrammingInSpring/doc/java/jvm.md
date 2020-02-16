### JVM (Java Virtual Machine)
- 자바 가상 머신으로 자바 바이트 코드를 실행할 수 있는 주체
- CPU와 운영체제에 종속되지 않고 실행 가능
- OS 위에서 동작하는 프로세스로, 자바 코드를 컴파일해서 얻은 바이트 코드를 해당 운영체제가 이해할 수 있는 기계어로 바꿔 실행시켜주는 역할
    - .java -> .class(바이트코드) -> 기계어
      1. java compile (javac.exe)
      2. JVM이 기계어로 전환
          - JVM 안에서 Class Loader, Execution Engine, Garbage Collector는 각각 Runtime Data Area와 양방향 교류? 한다.
          - Class Loader
              - 클래스파일(바이트 코드)들을 엮어 JVM이 OS로부터 할당받은 Runtime Data Area 메모리 영역으로 적재하는 역할을 Class Loader가 한다. (자바 애플리케이션이 실행중일 때 작업 수행)
          - Execution Engine
              - Class Loader에 의해 메모리에 적재된 바이트 코드들을 기계어로 변경해 명령어 단위로 실행하는 역할
              - 인터프리터 방식과 JIT(Just-In-Time) 컴파일러 방식이 있다. 
                - 인터프리터: 순서대로 명령어를 하나 하나 실행
                - JIt: 적절한 시간에 전체 바이트 코드를 네이티브 코드로 변경하여 실행 (성능 높이는 방식)
          - Garbage Collector
              - Heap 메모리 영역에 생성(쩍재)된 객체들 중에 참조되지 않는 객체들을 탐색 후 제거
              - GC가 언제 수행되는 지 알 수 없다.
              - GC가 수행되는 동안 GC 수행 쓰레드 외에 다른 모든 쓰레드 일시정지 (치명적 문제 가능성 - 이건 나중에 더 알아보기)
          - Runtime Data Area
              - JVM의 메모리 영역으로 자바 애플리케이션을 실행할 때 사용되는 데이터들을 적재하는 영역
              - 이 영역은 크게 Method Area, Heap Area, Stack Area, PC Register, Native Method Stack로 나눈다.
                1. Method Area
                    - 클래스 멤버 변수의 이름
                    - 데이터 타입
                    - 필드 정보
                    - 메소드 이름
                    - 리턴 타입
                    - 파라미터
                    - 접근 제어자
                    - 상수
                    - static 변수
                    - 등등등
                2. Heap Area
                    - new로 생성된 객체와 배열이 생성되는 영역
                    - Method Area에 로드된 클래스만 생성 가능, GC의 관리 영역
                3. Stack Area
                    - 지역 변수
                    - 파라미터
                    - 리턴 값
                    - 연산에 사용되는 임시 값
                    - 등등등
                4. PC Register
                    - 쓰레드가 생성될 때 마다 생성되는 영역
                    - 현재 쓰레드가 실행되는 부분의 주소와 명령을 저장하고 있는 영역
                    - 쓰레드를 돌아가면서 수행 가능하게 만든다.
                5. Native Method Stack
                    - 자바 외 언어로 작성된 네이티브 코드를 위한 메모리 영역 (C, C++, ...)
                <br/>
                
          - Heap & Garbage Collector
             - 쓰레드 생성 기준으로 메소드 영역과 힙 영역을 모든 쓰레드가 공유한다.
             - 나머지 영역은 각각의 쓰레드마다 생성되고 공유되지 않는다.
             - Heap 영역은 5개의 영역이 있다. (효율적인 GC를 위함)
                - eden
                - survivor1
                - survivor2
                - old
                - permanent
             - GC는 Minor GC와 Major GC로 나뉜다.
                - Minor GC: New 영역에서 일어나는 GC
                   - 최초 객체 생성 시 eden
                   - eden이 가득 차면 첫 번째 GC 발생
                   - survivor1 영역에 eden영역의 메모리를 그대로 복사, 그 외 영역 객체 제거
                   - eden 영역, survivor1 둘 다 가득찰 시 eden에서 survivor1에 참조되고 있는 객체인지 검사
                   - 참조 되고 있는 객체 survivor2에 복사, 나머지 영역 객체 제거
                   - 일정 횟수 이상 참조되고 있는 객체들은 survivor2 -> Old 영역 이동
                   - survivor2영역까지 꽉 차기 전 계속 해서 Old로 비운다.
                - Major GC (Full GC) : Old 영역에서 일어나는 GC
                   - Manor GC보다 시간이 많이 걸리고, 
                   - Old 영역에 있는 모든 객체들이 참조되고 있는 지 검사
                   - 참조되지 않은 객체를 모아 한 번에 제거
                   - Heap 메모리 영역에 빈 공간을 없애기 위해 재구성 (메모리 정리)
                      - GC를 제외한 모든 쓰레드 중지 -> 메모리 이동 중 다른 쓰레드가 그 메모리를 사용하면 안되기 떄문에
                   
                   
      
