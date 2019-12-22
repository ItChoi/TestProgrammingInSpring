package com.spring.test.annotation;

import java.util.Arrays;

public class MyBook extends Book implements MyInterface {

	
	public static void main(String[] args) {
		// Book에 붙은 애노테이션 가져오기.
		// 먼저 @MyAnnotation에 @Inheried를 주어야 가능함.
		Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);
		
		// 해당 클래스에만(MyBook) 붙은 애노테이션 정보만 가져오고 싶다면
		System.out.println("====================");
		Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);
		
		// 필드에 붙어있는 애노테이션 가져오기.
		System.out.println("====================");
		Arrays.stream(MyBook.class.getDeclaredFields()).forEach(f -> {
			
		});
	}

	
	
}
