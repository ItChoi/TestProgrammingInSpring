### 오라클 에러 관련
#### 1. sql developer 툴 사용 시 에러 발생
- 에러 발생
    1. cmd에서 sql 계정 생성 후 데이터베이스 접근 권한 (CREATE SESSION)을 주고, sql developer에서 계정 정보를 적고 테스트 클릭 시 **Listener refused the connection with the  following error: ORA-12505, TNS:listener does not currently know of SID given in connect descriptor** 발생  
    
- 의심 요인
    1. sql developer 툴 접속 시 **Problem initializing the JavaFX runtime. This feature requires JavaFX.** 에러가 계속 뜨는데, 아마 open jdk를 경로로 지정하여 뜨는 에러 같은데 이 에러와 관련이 있을 가능성 알아보기. (NoClassDefFoundError, ClassNotFoundException)

- 시도한 방법
    1. developer 툴 문제인지 보기 위해 sysdba 계정으로 접속 하였으나 위와 같은 에러가 똑같이 발생. -> open jdk에서 미지원 하는 것 떄문인가?
    
- 원인
    1. ORA-12505 오류는 DriverManager Object를 이용하여 오라클과 연동 시 SID를 찾지 못하거나 인식을 못하는 경우 발생하는 에러이다. cmd에서 lsnrctl services를 입력하면 설치 과정 문제인지 알 수 있는데, 정상적으로 설치가 되어 있는 것 같았다. 문제는 open jdk8 문제가 아니라 sid가 xe가 아닌 orcl로 설정되어 있었다. 
- 해결
    1. SID가 xe로 되어 있었는데, orcl로 바꾸어 주니 됐다.  
    (\dbhome_1\NETWORK\ADMIN에  listener.ora / tnsnames.ora 파일 확인...)
    
