package com.spring.test.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class JavaOptionalTest {

	@Test
	public void 옵셔널_테스트() {
		List<String> list = new ArrayList<>();
		list.add("asd1");
		list.add("asd2");
		list.add("asd3");
				
		System.out.println("list: " + list);
		System.out.println("list size: " + list.size());
		
		System.out.println("result1: " + Optional.of(list).map(List::size).orElseThrow(RuntimeException::new));
		System.out.println("result2: " + Optional.of(list).filter(l -> l.size() == 0).orElseThrow(() -> new RuntimeException("asdasd")));
		
		
	}
	
}
