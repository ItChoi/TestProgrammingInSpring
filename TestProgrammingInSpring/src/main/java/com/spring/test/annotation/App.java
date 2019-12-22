package com.spring.test.annotation;

import java.util.Arrays;

public class App {
	public static void main(String[] args) {
		Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);
		System.out.println("=================");
		// 필드에 붙어있는 애노테이션 가져오기.
		System.out.println("====================");
		Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
			Arrays.stream(f.getAnnotations()).forEach(a -> {
				if (a instanceof MyAnnotation) {
					MyAnnotation myAnnotation = (MyAnnotation) a;
					System.out.println(myAnnotation.name1());
					System.out.println(myAnnotation.number());
				}
			});
		});	
	}
}
