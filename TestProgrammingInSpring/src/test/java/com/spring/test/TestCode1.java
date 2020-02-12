package com.spring.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestCode1 {

	@Test
	public void Collection_TEST() {
		int[] arr = {1, 1, 3, 3, 0, 1, 1};
		// int[] arr = {4, 4, 4, 3, 3};
		int temp;
		
		
		for (int i = 0; i < arr.length; i++) {
			
			
			temp = arr[i];
		}
		
		int[] answer = {};
		
		
		for (int b : answer) {
			System.out.println("resutl: " + b);
		}
		
	}
	
	@Test
	public void 자료구조_테스트() {
		List<String> list1 = new LinkedList<>();
		List<String> list2 = new ArrayList<>();
		
		list1.add("setset");
		System.out.println(list1.get(0));
		
		
	}
}
