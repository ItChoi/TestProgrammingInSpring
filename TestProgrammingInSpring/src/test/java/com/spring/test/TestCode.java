package com.spring.test;

import java.util.Arrays;

import org.junit.Test;
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
		int number = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = checkDuplicationNumber(array, i, j);
				System.out.printf("%3d", array[i][j]);
			}
			System.out.println();
		}
		
	}
	
	private int checkDuplicationNumber(int[][] userBingoBoard, int ii, int jj) {
		boolean duplicationNumber;
		int number = 0;
		
		do {
			int j = 0;
			int i = 0;
			
			duplicationNumber = false;
			number = (int) (Math.random() * (25)) + 1;
			
			for (; i <= ii; i++) {
				for (; j <= (i == ii ? jj : userBingoBoard[i].length-1); j++) {
					if (number == userBingoBoard[i][j]) {
						duplicationNumber = true;
						//System.out.println("@@중복:" + number + "@@");
						break;
					}
					
				}
				
				if (duplicationNumber) {
					break;
				}
				
			}
			
			/*
			 * if (userBingoBoard[0].length > j && !duplicationNumber) {
			 * userBingoBoard[ii][jj] = number; }
			 */
			
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
	
	
}

