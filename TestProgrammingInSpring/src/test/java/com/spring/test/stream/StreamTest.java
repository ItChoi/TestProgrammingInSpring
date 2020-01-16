package com.spring.test.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	@Test
	public void 테스트_코드() {
		int[] a = {1,5,2,5,4,3};
		Arrays.sort(a);
		
		Object aaa = Collectors.toList();
		Arrays.stream(a).forEach(System.out::println);
	}
	
	@Test
	public void Arrays_테스트() {
		int[] a = {1,5,2,5,4,3};
		
		System.out.println(Arrays.binarySearch(a, 22));
	}
	
	@Test
	public void 스트림_테스트05() {
		String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		
		Stream<String> stream = Arrays.stream(arr, 1, 3);
		
		stream.forEach(System.out::println);
	}
	
	
}






















