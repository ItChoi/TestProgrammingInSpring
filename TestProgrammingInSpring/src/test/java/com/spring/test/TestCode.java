package com.spring.test;

import java.util.Arrays;

import org.junit.Test;

import lombok.Data;
import lombok.Getter;

public class TestCode {

	@Test
	public void test() {
		System.out.println("asdasdasd");
		
		Class<TestCode> class1 = TestCode.class;
		
		Arrays.stream(class1.getFields()).forEach(System.out::println);
		
	}
	
	@Test
	public void lombokeTest() {
		LombokTest t = new LombokTest();
		System.out.println(t.getVariable());
		t.setVariable(12321);
		System.out.println(t.getVariable());
		
	}
	
}

@Data
class LombokTest {
	private int variable = 5;
	
}
