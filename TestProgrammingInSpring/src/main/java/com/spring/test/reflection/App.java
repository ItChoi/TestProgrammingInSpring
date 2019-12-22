package com.spring.test.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {
	public static void main(String[] args) {
		// 클래스 인스턴스를 가져오는 방법
		// (1)
		Class<Book> bookClass = Book.class;
		
		// (2)
		Book book = new Book();
		book.getClass();
		Class<? extends Book> aClass = book.getClass();
		
		try {
			// (3)
			Class<?> aClass1 = Class.forName("com.spring.test.reflection.Book");
		} catch (ClassNotFoundException e) {
			
		}
		
		// 인스턴스 접근 후 참조 정보
		Field[] fields = bookClass.getFields();
		
		// public한 필드만 리턴.
		Arrays.stream(bookClass.getFields()).forEach(System.out::println);
		System.out.println("============================================");
		// 접근지시자 무시하고 가져오고 싶다면
		Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);
		System.out.println("============================================");
		Book book1 = new Book();
		Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
			try {
				// 접근 불가능 필드에 접근하기 위해
				f.setAccessible(true);
				
				System.out.printf("%s %s", f, f.get(book1));
				f.get(book1);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		System.out.println("============================================");
		// Object로 부터 상속 받은 것들도 다 가져온다.
		Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
		System.out.println("============================================");
		Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);
		// 슈퍼 클래스
		System.out.println("============================================");
		System.out.println(MyBook.class.getSuperclass());
		// 인터페이스
		System.out.println("============================================");
		Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
		
		System.out.println("============================================");
		Arrays.stream(MyBook.class.getFields()).forEach(f -> {
			// static, 접근 제어 지시자 등을 알아볼 수 있다.
			int modifiers = f.getModifiers();
			System.out.println(Modifier.isPrivate(modifiers));
			System.out.println(Modifier.isPublic(modifiers));
			System.out.println(Modifier.isStatic(modifiers));
		});
		System.out.println("============================================");
		Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
			int modifiers = f.getModifiers();
			System.out.println(Modifier.isPrivate(modifiers));
			System.out.println(Modifier.isPublic(modifiers));
			System.out.println(Modifier.isStatic(modifiers));
		});
		
	}
}
