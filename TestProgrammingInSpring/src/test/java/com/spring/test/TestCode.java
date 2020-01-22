package com.spring.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class TestCode {

	@Test
	public void test() {
		System.out.println("asdasdasd");
		
		Class<TestCode> class1 = TestCode.class;
		
		Arrays.stream(class1.getFields()).forEach(System.out::println);
		
	}
	
	@Test
	public void arrayContainsTest() {
		String[][] array = new String[5][5];
		int result = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// array[i][j] = (int) (Math.random() * 25) + 1;
				array[i][j] = String.valueOf(result);
				result++;
				System.out.print(array[i][j] + " ");
			}
			System.out.println(array[0][0].contains("1"));
		}
		
		// System.out.println("test result: " + );
		
		String asd = StringUtils.collectionToCommaDelimitedString(Arrays.asList("123", "456", "789"));
		System.out.println("asd: " + asd.contentEquals("123"));
		
	}
	
	@Test
	public void 배열_중복_테스트() {
		int[][] array = new int[5][5];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = checkDuplicationNumber(array, i, j);
				System.out.printf("%8d", array[i][j]);
			}
			System.out.println();
		}
		
	}
	
	private int checkDuplicationNumber(int[][] userBingoBoard, int ii, int jj) {
		boolean duplicationNumber;
		int number = 0;
		
		do {
			duplicationNumber = false;
			number = (int) (Math.random() * (25)) + 1;
			
			for (int i = 0; i <= ii; i++) {
				
				for (int j = 0; j <= (i == ii ? jj : userBingoBoard[i].length-1); j++) {
					if (number == userBingoBoard[i][j]) {
						duplicationNumber = true;
						break;
					}
					
				}
				
				if (duplicationNumber) {
					break;
				}
			}
		} while(duplicationNumber);
		
		return number;
	}
	
	@Test
	public void 배열_FOR_확인() {
		int[][] array = new int[5][5];
		
		System.out.println("test1: " + array[0][0]);
		System.out.println("test2: " + array[0][1]);
		test(array, 1);
		System.out.println("test3: " + array[0][0]);
		System.out.println("test4: " + array[0][1]);
		test(array, 2);
		System.out.println("test5: " + array[0][0]);
		System.out.println("test6: " + array[0][1]);
		
	}
	
	private void test(int[][] array, int i) {
		if (i == 1) {
			array[0][0] = 123123;
		} else {
			array[0][1] = 123123;
		}
	}
	
	
	@Test
	public void Integer_테스트() {
		Integer a = 2;
		
		int b = 55;

		// 같: 0, 작: -1, 크: 1
		int aaa = a.compareTo(b);
		
		System.out.println("aaa: " + aaa);
	}
	
	@Test
	public void Integer_형변환() {
		int[] a = {1,2,3,4};
		Integer[] b = Arrays.stream(a).boxed().toArray(Integer[]::new);
		
		int[][] aa = {{1,2,3,4}, {5,6,7,8}};
		Integer[][] bb = Arrays.stream(aa).toArray(Integer[][]::new);
		
		// List<int[][]> list new ArrayList<>();
	}
	
	@Test
	public void 람다_인덱스_테스트() {
		String[] array = { "G", "e", "e", "k", "s" }; 
		  
		// 방법 1
        AtomicInteger index = new AtomicInteger();   
        Arrays
	        .stream(array)              
	        .map(str -> index.getAndIncrement() + " -> " + str)  // 0부터 시작한다.
	        .forEach(System.out::println);

        
        AtomicInteger index1 = new AtomicInteger();
        int a; 
        a = index1.getAndIncrement();
        System.out.println("a: " + a);
        a = index1.getAndIncrement();
        System.out.println("aa: " + a);
        index1.set(0);
        a = index1.getAndIncrement();
        System.out.println("aaa: " + a);
        
	}
	
	@Test
	public void 연산_테스트() {
		int[] a = {-1,2,3,4};
		int k = a.length - 1;
		System.out.println("test: " + a[k--]);
		System.out.println("test: " + a[k--]);
		System.out.println("test: " + a[0]);
	}
	
}
