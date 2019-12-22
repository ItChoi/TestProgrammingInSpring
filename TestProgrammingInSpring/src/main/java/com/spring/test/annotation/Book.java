package com.spring.test.annotation;

// @MyAnnotation에서 name1과 number의 기본 값을 줬기 때문에 값을 안넣어줘도 된다.
// 그러나 만약 기본 값 없이 name1, number의 값을 안준다면 컴파일 에러가 발생(애노테이션 필드명과 동일하게 네이밍)
@MyAnnotation(name1 = "sanghyun", number = 100)
public class Book {
	
	@MyAnnotation
	private String a = "a";
	
	private static String B = "BOOK";

	private static final String C = "BOOK";
	
	@AnotherAnnotation
	public String d = "d";
	
	protected String e = "e";
	
	public Book() { 
		
	}
	
	public Book(String a, String d, String e) {
		this.a = a;
		this.d = d;
		this.e = e;
		
	}
	
	private void f() {
		System.out.println("F");
	}
	
	public void g() {
		System.out.println("g");
	}
	
	public int h() {
		return 100;
	}
	
	
	
	
}
