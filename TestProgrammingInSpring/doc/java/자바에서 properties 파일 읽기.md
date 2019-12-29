### 자바에서 properties 파일 읽기
- properties 파일은 key=value 로 적어준다.
    - java에선 key를 getKey로 읽어온다.
    - ex) jdbc.type=mysql
    <br/>
    
- 주석은 #으로 사용

```java
try {
	// 외부 properties 읽기
	// Properties properties = new Properties();
	// FileReader resources = new FileReader("/resources/properties/config.properties");
	// properties.load(resources);
	
	// 내부 properties 읽기
	Properties properties = new Properties();
	String resource = "properties/config.properties";
	// mybatis dependency를 추가해야 Resources.getResourceAsReader(resource) 사용 가능
	Reader reader = Resources.getResourceAsReader(resource);
	properties.load(reader);
	
	
	Connection con = DriverManager.getConnection(
			properties.getProperty("jdbc.url"),
			properties.getProperty("jdbc.username"),
			properties.getProperty("jdbc.password")
	);
	
} catch(Exception e) {
	log.info("에러 메시지: {}", e);
}
```
### 참조
- [https://dydals5678.tistory.com/108](https://dydals5678.tistory.com/108 "properties 파일 읽기 참조")
- 