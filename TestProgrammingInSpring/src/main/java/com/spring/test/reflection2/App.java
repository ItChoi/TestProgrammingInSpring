package com.spring.test.reflection2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
	public static void main(String[] args) {
		try {
			Class<?> bookClass = Class.forName("com.spring.test.reflection2.Book");
			
			// 비권장 방법
			bookClass.newInstance();
			
			// 권장 방법 - constructor 사용
			// Constructor<?> constructor = bookClass.getConstructor(null);
			// 생성자 중 String 파라미터를 가지고 있는 생성자를 가져올 시 String.class 추가 
			Constructor<?> constructor = bookClass.getConstructor(String.class);
			// Object obj = constructor.newInstance();
			// Book book = (Book) constructor.newInstance();
			// 파라미터 개수에 맞춰서 arguments 를 맞춰줘야 한다.
			Book book = (Book) constructor.newInstance("myBook");
			System.out.println(book);
			
			// 필드 값 가져오기.
			Field a = Book.class.getDeclaredField("A");
			// static 변수기에 Object 자리에는 null을....
			System.out.println(a.get(null));
			a.set(null, "AAAA");
			System.out.println(a.get(null));
			
			Field b = Book.class.getDeclaredField("B");
			// 특정 인스턴스 해당 필드기에, null로는 못 가져오고(아예 없는 상태),
			// B 필드는 private 이기 때문에 접근 권한을 줘야 한다.
			b.setAccessible(true);
			System.out.println(b.get(book));
			b.set(book, "AAAA");
			System.out.println(b.get(book));
			
			// 메서드 사용, 매개 변수가 있으면 매개 변수 타입도 지정해줘야 한다.
			Method c = Book.class.getDeclaredMethod("sum", int.class, int.class);
			// private method라서 접근 권한 설정
			c.setAccessible(true);
			// invoke: 실행할 때 invoke 쓰면 된다.
			int invoke = (int) c.invoke(book, 1, 2);
			System.out.println("invoke: " + invoke);
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (InstantiationException e) {
			System.out.println(e);
		} catch (IllegalAccessException e) {
			System.out.println(e);
		} catch (NoSuchMethodException e) {
			System.out.println(e);
		} catch (SecurityException e) {
			System.out.println(e);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		} catch (InvocationTargetException e) {
			System.out.println(e);
		} catch (NoSuchFieldException e) {
			System.out.println(e);
		}
		
	}
}
