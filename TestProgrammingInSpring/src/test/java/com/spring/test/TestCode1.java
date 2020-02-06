package com.spring.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class TestCode1 {

	@Test
	public void Collection_TEST() {
		List<String> a =  new ArrayList<>();
		
		a.add("asdasd1");
		a.add("asdasd2");
		a.add("asdasd3");
		
		System.out.println("result: " + Collections.unmodifiableCollection(a));
		System.out.println("result: " + Arrays.asList(a));
	}
}
