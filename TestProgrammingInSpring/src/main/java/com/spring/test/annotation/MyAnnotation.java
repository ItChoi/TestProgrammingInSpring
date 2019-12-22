package com.spring.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 애노테이션 

// 기본적으로 애노테이션은 주석이나 마찬가지... 기능은 몇 가지 더 있지만 주석과 같은 취급 소스, 클래스도 남지만 바이트 코드 로딩 시 메모리 상에는 남지 않는다. 
// 그러나 런타임까지도 이 정보를 유지하고 싶다면 애노테이션 추가 
@Retention(RetentionPolicy.RUNTIME)

// 애노테이션 사용 가능 위치 제한 (타입과 필드에 제한)
@Target({ElementType.TYPE, ElementType.FIELD})

// class Book extends MyBook { } 일 때 MyBook에 붙은 애노테이션 정보도 가져올 수 있도록 설정하기 위함
@Inherited

public @interface MyAnnotation {

	// String name(); 
	
	// 디폴트 값 넣기.
	String name1() default "sanghyun";
	int number() default 100;
	
	// 어떤 값을 value로 줬을 시 명시하지 않고 그냥 값만 넣어줘도 된다. ex) @MyAnnotation("sanghyun") public class Book { }
	// String value() default "sanghyun"; // 값을 하나만 넣어줬을 때 value로 네이밍하면 유용하다.
}
