### git ignore 설정
- 목록
    - git ignore란?
    - git ignore 적합한 파일
    - git ignore 설정 방식

##### 1. git ignore란?
- Git을 사용할 경우 제외할 파일들의 목록을 지정하여 나열한 파일이다.
- Git 사용 시 관리가 필요없는 파일들을 git이 track하지 않도록 .ignore로 설정한다.

##### 2. git ignore 적합한 파일
- 프로젝트 설정 파일(DB 정보, ...)
- 로그 파일
- 컴파일 파일(.class)
- 다운받은 외부 패키지(maven, gradle, bundle, composer ...) 
- **개인키(ssh) !주의** 

##### 3. git ignore 설정 방식
- 항상 최상위 Directory에 존재해야 한다. (.git 파일이 존재하는 곳에 .ignore를 만들어준다.)
- 버전관리가 되고 있는 파일을 .gitignore 파일에 입력한다고 해서 Git이 버전 관리에서 제외하지는 않는다. 따라서 버전 관리 대상으로 되어있는 파일을 제외하기 위해서는 수동으로 파일들을 버전 관리에서 제외시켜야 한다.
    - ex) git 관리 대상의 파일 삭제 후 .ignore 관리
      - git rm -r --cached .    // 현재 Repository의 cache 모두 삭제
      - git rm -r --cached [File Name)    // [File Name] 파일을 원격 저장소에서 삭제. (local에서는 삭제하지 않는다.)
      - git commit -m "remove track file..."
- ignore 목록에 있어도 강제로 추가해야 할 경우 -f(force) 옵션을 사용
    - ex) git add -f application.properties
- ignore 설정 정보는 하위 폴더에도 영향을 미친다. 특정 하위 폴더에서 다른 ignore 정책을 사용하기 위해서는 특정 하위 폴더에 .gitignore를 만들어 상위 정책과 다르게 적용할 수 있다. 
    - ex) !application.properties // !는 이전 패턴 무효화를 의미, 만약 파일 명에 !가 들어갈 경우 \\! 사용
       
#### git ignore reference
- [ignore list](github/gitignore "GitHub에서 제공하는 대부분 언어의 ignore목록")
- [https://gmlwjd9405.github.io/2017/10/06/make-gitignore-file.html](https://gmlwjd9405.github.io/2017/10/06/make-gitignore-file.html "ignore 참조 사이트 1")
- [https://www.lesstif.com/pages/viewpage.action?pageId=54952369](https://www.lesstif.com/pages/viewpage.action?pageId=54952369 "ignore 참조 사이트2)

