package com.spring.test;

import java.util.Arrays;

import org.junit.Test;

public class TestCode {

	@Test
	public void test() {
		System.out.println("asdasdasd");
		
		Class<TestCode> class1 = TestCode.class;
		
		Arrays.stream(class1.getFields()).forEach(System.out::println);
		
	}
	
}
