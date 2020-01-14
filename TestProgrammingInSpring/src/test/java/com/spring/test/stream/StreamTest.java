package com.spring.test.stream;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class StreamTest {
	
	@Test
	public void 스트림_테스트01() {
		Arrays.asList(1,2,3).forEach((value)-> {
			System.out.println("value: " + value);
		});
	}
	
	@Test
	public void 스트림_테스트02() {
		Arrays.asList(1,2,3)
			.stream()
				.map((i) -> i*i)
				.forEach(System.out::println);;
	}
	
	@Test
	public void 스트림_테스트03() {
		Arrays.asList(1,2,3,4,5,6)
			.stream()
				.map(i -> i +1)
				.limit(2)
				.forEach(System.out::println);
	}
	
	@Test
	public void 스트림_테스트04() {
		String test = "";
		Arrays.asList("일","이","삼")
			.stream()
				.filter(i -> i == "일")
				.forEach(System.out::println);
	}
	
}






















