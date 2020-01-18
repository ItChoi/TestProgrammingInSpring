package com.spring.test.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class LambdaTest {

	@Test
	void 람다_사용_테스트01() {
		TestInterface ti = (aa, bb) -> {
			System.out.println("test: " + (aa + bb));
		};
		
		ti.sumValue(1, 23);
	}
	
	@Test
	public void 람다_사용_테스트02() {
		List<String> list = new ArrayList<>();
		
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		
		list.forEach((val) -> {
			// System.out.println(val);
		});
		
		// 타입을 int가 아닌 Integer로 해야 forEach했을 때 값이 제대로 나온다.
		// int[] array1 = new int[5];
		Integer[] array1 = {1,2,3,4,5};
		
		// 배열을 스트림 객체로 변경
		/**
		 * wrapper -> 스트림 객체 변환과 primitive -> 스트림 객체 변환 시
		 * Stream 객체의 동작이 다르다
		 *  
		 */
		Stream intStream = Stream.of(array1);
		
		intStream.forEach(abc -> System.out.println("test: " + abc));
		
		
		Stream.of(array1).forEach((a) -> {
			System.out.println("tt: " + a);
		});
		
		
		int[][] array2 = new int[5][5];
		array2[0][0] = 123;
		
	}
	
	
	@Test
	public void 람다_스트림_2차원배열_테스트() {
		Integer[][] array = new Integer[5][5];
		array[0][1] = 123;
		
		Stream.of(array).forEach((val1) -> {
			// 주소 값이 나온다
			// System.out.println("val1: " + val1);
			Stream.of(val1).forEach((val2) -> {
				System.out.println("val2: " + val2);
			});
			
		});
		
		
	}

}

@FunctionalInterface
interface TestInterface {
	public void sumValue(int a, int b);
}
