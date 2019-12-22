### 오라클 계정 생성 및 권한 부여
- sqlplus 접근 -> 오라클 설치 시 패스워드를 입력한 계정(system)?으로 접속하여 계정 생성 및 권한을 부여해보자.<br/><br/>

- 계정 생성
    - create USER '유저id' IDENTIFIED BY '비밀번호';
    <br/>
- 계정 삭제
    - DROP USER userName CASCADE;    
    
- ROLE 생성 및 권한
    - ROLE 생성
      - CREATE ROLE admin  
    - ROLE에 권한 부여
      - GRANT create session, create table TO admin
    - ROLE 부여
      - GRANT admin to test1, test2;
    - ROLE 권한 회수
      - REVOKE admin FROM test1;
    <br/>
    - 테이블 DML 권한 부여
      - 방법 (1)
          - GRANT SELECT ON tableName TO userId;
          - GRANT INSERT ON tableName TO userId;
          - GRANT DELETE ON tableName TO userId;
          - GRANT UPDATE ON tableName TO userId;
      - 방법 (2)	
          - GRANT SELECT, INSERT, DELETE, UPDATE, MERGE ON tableName TO userId;
     <br/>
     
     - 시스템 권한 종류
       - CREATE USER: 사용자 생성 권한
       - DROP USER: 사용자 삭제 권한
       - DROP ANY TABLE: 테이블 삭제 권한
       - SELECT ANY TABLE: 모든 유저 테이블 조회 권한
       - CREATE ANY TABLE: 모든 유저 테이블 생성 권한
       - CREATE SESSION: DB 접속 권한
       - CREATE(ALTER, DROP) TABLE: 테이블 생성 권한
       - CREATE(DROP) VIEW: 뷰 생성 권한
       - CREATE(ALTER, DROP) PROCEDURE USER: 프로시저 생성 권한
       - CREATE(ALTER, DROP) SEQUENCE: 시퀀스 생성 권한
       - CREATE(ALTER, DROP) SYNONYM: 동의어 생성 권한
       - CREATE(ALTER, DROP) TRIGGER: 트리거 생성 권한
       - SYSDBA: DB 관리 최고 권한
       - SYSOPER: DB 관리 권한
       <br/> 
     - 객체 권한 종류
       - ALTER: 객체 변경 권한
       - COMMENT: 객체 COMMENT 가능 권한
       - DELETE: 객체 데이터 삭제 권한
       - GRANT: 객체에 GRANT 가능 권한
       - INDEX: 인덱스 생성 권한
       - INSERT: 데이터 삽입 권한
       - RENAME: 이름 변경 권한
       - SELECT: 데이터 조회 권한
       - UPDATE: 데이터 갱신 권한
       - REFERENCE: 데이터 참조 권한
       - EXECUTE: 프로시저, 함수, 패키지 실행 권한
     <br/>
     
- ROLE
    - 사용자에게 특정 행위를 허용할 수 있는 권한 (권한 부여 및 회수 - 동일 명령어 사용)
    - CREATE ROLE 권한을 가진 유저에 의해서 생성 가능
    - DBA가 권한 하나하나를 유저마다 지정하는 것이 아니라, 역할에 맞는 권한을 생성하여 효율적으로 권한을 부여
    - 오라클 설치 시 기본적으로 CONNECT, RESOURCE, DBA 권한 제공
    <br/>

- 권한 확인
    - 시스템 권한 확인
      - SELECT * FROM DBA_SYS_PRIVS;
    - ROLE 확인
      - SELECT * FROM DBA_ROLE_PRIVS;
    - 롤에 부여된 시스템 권한 확인
      - SELECT * FROM DBA_SYS_PRIVS WHERE GRANTEE = 'roleName';
    - 테이블 확인
      - 테이블도 소유자와 사용 권한이 있는 것 같다. 본인 소유의 테이블은  모든 작업이 가능하지만, 타 테이블의 경우 권한 확인이 필요한 것으로 보인다.
      - SELECT * FROM DBA_SYS_PRIVS
      

      