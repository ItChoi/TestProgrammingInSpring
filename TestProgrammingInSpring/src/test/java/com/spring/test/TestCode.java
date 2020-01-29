package com.spring.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.springframework.util.StringUtils;

import lombok.Getter;

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
		Set<Integer> list = new HashSet<>();
	}
	
	@Test
	public void 자료구조_테스트() {
		List<String> list = new ArrayList<>();
		list.add("user1");
		list.add("user2");
		list.add("user3");
		list.add("user4");
		list.add("user5");
		제네릭_와일드카드_테스트(list);
		
		List<String> list1;
		List<String> list2 = new ArrayList<>();
		list1 = list2;
		
		자료구조_테스트_LIST(list1);
		list1.forEach(value -> System.out.println("TEST LIST: " + value));
	}
	
	private void 제네릭_와일드카드_테스트(List<?> list) {
	//private <T> void 제네릭_와일드카드_테스트(List<T> list) {
		list.forEach(value -> System.out.println("value: " + value));
	}
	
	private void 자료구조_테스트_LIST(List<String> list) {
		list.add("11");
		list.add("22");
		list.add("33");
		list.add("44");
	}
	
	@Test
	public void 시간_단위_테스트() {
		System.out.println(
			getDuration(
				LocalDateTime.of(2018, 1, 1, 12, 10, 0),
				LocalDateTime.of(2018, 1, 1, 13, 10, 0)
			)
		);
		
	}
	
	public Duration getDuration(LocalDateTime from, LocalDateTime to) {
		return Duration.between(from, to);
	}

	@Test
	public void List_삭제_테스트() {
		List<String> list = new ArrayList<>();
		list.add("asd");
		list.add("asd");
		System.out.println("length: " + list.size());
		list.remove(1);
		System.out.println("length: " + list.size());
	}
	
	@Test
	public void ENUM_사용_테스트() {
		Bingo123 bingo123 = BingoType123.findBingoConstructor("width");
		bingo123.lineBingo();
	}
	
}

@Getter
enum BingoType123 {
	WIDTH_BINGO("width", new WidthBingo123()),
	HEIGHT_BINGO("height", new HeightBingo123()),
	DIAGONAL_BINGO("diagonal", new DiagonalBingo123()),
	EMPTY("empty", null);
	
	private String code;
	private Bingo123 bingo;

	BingoType123(String code, Bingo123 bingo) {
		this.code = code;
		this.bingo = bingo;
	}

	public static Bingo123 findBingoConstructor(String code) {
		return  Arrays.stream(BingoType123.values())
					.filter(value -> code.equals(value.getCode()))
					.map(value -> value.getBingo())
					.findAny()
					.orElse(BingoType123.EMPTY.getBingo());
	}
	
	
	
	
}

interface Bingo123 {
	public void lineBingo();
}


// 가로 빙고
class WidthBingo123 implements Bingo123 {

	@Override
	public void lineBingo() {
		System.out.println("가로 빙고!");
	}
}

// 세로 빙고
class HeightBingo123 implements Bingo123 {

	@Override
	public void lineBingo() {
		System.out.println("세로 빙고!");
	}
}


// 대각선 빙고
class DiagonalBingo123 implements Bingo123 {

	@Override
	public void lineBingo() {
		System.out.println("대각선 빙고!");
	}
}

