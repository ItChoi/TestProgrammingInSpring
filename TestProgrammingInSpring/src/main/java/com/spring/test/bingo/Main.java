package com.spring.test.bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;


// TODO:: 빙고 게임 만들어보기
/**
 * 1. 2명이서 플레이
 * 2. 빙고판 width x height
 * 3. 입력 숫자가 빙고판에 숫자와 일치하면 0으로 변경
 * 4. 가로/세로/대각선 줄 채울 시 빙고 (0으로 가로, 세로, 대각선) 
 * 5. 빙고 3줄 시 게임 승리
 */
public class Main {
	public static void main(String[] args) {
		int width = 5;
		int height = 5;
		
		BingoBoard bingoBoard = new BingoBoard(width, height, 3);
		
		User userInfo1 = new User(bingoBoard);
		User userInfo2 = new User(bingoBoard);
		
		userInfo1.writerBingoByNumber();
		userInfo2.writerBingoByNumber();
		
		
		/**
		 * 1. 유저 턴제로 번호 부르면 둘 다 있는 번호 체크
		 * 2. 가로, 세로, 대각선 빙고 체크
		 *   - 빙고판, 체크판, 체크판은 x 용도로 쓰자 
		 * 3. 유저 3개 빙고 먼저하면 승리
		 *  
		 */
		
		// checkDuplicationNumber
		bingoBoard.getCheckBingoInfos().forEach((array) -> {
			System.out.println("userInfo");
			Arrays.stream(array).forEach((info) -> {
				Arrays.stream(info).forEach((index) -> {
					System.out.printf("%4d", index);
				});
				
				System.out.println();
				
			});
			System.out.println();
		});
		
		Scanner inputNumber = new Scanner(System.in);
		
		while (userInfo1.getBingoCount() == bingoBoard.getEndBingo() ||
				  userInfo2.getBingoCount() == bingoBoard.getEndBingo()) {
			  
			int bingoNumber = inputNumber.nextInt();
			bingoBoard.removeBingoNumber(bingoNumber);
		  
		}
		 
		
		bingoBoard.getCheckBingoInfos().forEach((array) -> {
			System.out.println("userInfo");
			Arrays.stream(array).forEach((info) -> {
				Arrays.stream(info).forEach((index) -> {
					System.out.printf("%4d", index);
				});
				
				System.out.println();
				
			});
			System.out.println();
		});
		
	}
	
}

@Getter
class BingoBoard {
	private final int width;
	private final int height;
	private final int totalNumber;
	
	private final int endBingo;
	
	private List<int[][]> checkBingoInfos = new ArrayList<>();
	// private List<Integer[][]> checkBingoInfos = new ArrayList<>();
	
	// TODO 참석자로 변경하여 진행하기.
	private List<User> attendanceList; 
	
	public BingoBoard() {
		this.width = 5;
		this.height = 5;
		this.totalNumber = this.width * this.height;
		this.endBingo = 3;
	}
	
	public BingoBoard(int width, int height, int endBingo) {
		this.width = width;
		this.height = height;
		this.endBingo = endBingo;
		this.totalNumber = this.width * this.height;
	}
	
	public void removeBingoNumber(int bingoNumber) {
		AtomicInteger increment1 = new AtomicInteger();
		AtomicInteger increment2 = new AtomicInteger();
		AtomicInteger increment3 = new AtomicInteger();
		
		checkBingoInfos.forEach((array) -> {
			int index1;
			index1 = increment1.getAndIncrement();
			increment2.set(0);
			
			
			Arrays.stream(array).forEach((info) -> {
				increment3.set(0);
				int index2;
				index2 = increment2.getAndIncrement();
				
				Arrays.stream(info).forEach((index) -> {
					int index3;
					index3 = increment3.getAndIncrement();
					if (bingoNumber == index) {
						checkBingoInfos.get(index1)[index2][index3] = 0;
					}
				});
				
			});
		});
		
	}
	
}

@Getter
class User {
	private BingoBoard bingoBoard;
	
	// 모든 유저의 빙고판
	
	private int[][] userBingoBoard;
	private int[][] userCheckBingo;
	
	private int bingoCount;
	
	
	public User(BingoBoard bingoBoard) {
		this.bingoBoard = bingoBoard;
		userBingoBoard = new int[bingoBoard.getWidth()][bingoBoard.getHeight()];
	}
	
	public void writerBingoByNumber() {
		for (int i = 0; i < userBingoBoard.length; i++) {
			for (int j = 0; j < userBingoBoard[0].length; j++) {
				userBingoBoard[i][j] = inputRandomValue(userBingoBoard, i, j);
			}
		}
		
		userCheckBingo = Arrays.copyOf(userBingoBoard, userBingoBoard.length);
		bingoBoard.getCheckBingoInfos().add(userCheckBingo);
		// bingoBoard.getCheckBingoInfos().add(Arrays.stream(userCheckBingo).toArray(Integer[][]::new));
	}
	
	private int inputRandomValue(int[][] userBingoBoard, int ii, int jj) {
		boolean duplicationNumber;
		int number = 0;
		
		do {
			duplicationNumber = false;
			number = (int) (Math.random() * (bingoBoard.getTotalNumber() * 2)) + 1;
			
			duplicationNumber = checkDuplicationNumber(userBingoBoard, ii, jj, number);
			
		} while(duplicationNumber);
		
		return number;
	}
	
	public boolean checkDuplicationNumber(int[][] userBingoBoard, int wLength, int hLength, int checkValue) {
		boolean existNumber = false;
		
		int i = 0; 
		for (; i < wLength; i++) { 
			int j = 0;
		 
			 for (; j <= (i == wLength ? hLength : userBingoBoard[i].length-1); j++) { 
				 if (checkValue == userBingoBoard[i][j]) { 
					 existNumber = true; 
					 break; 
				 }
			 }
		 
			 if (existNumber) { 
				 break; 
			 }
		 }
		
		return existNumber;
	}
	
}


interface Bingo {
	public void lineBingo(User user);
}


// 가로 빙고
class WidthBingo implements Bingo {

	@Override
	public void lineBingo(User user) {
		
	}
	
}

// 세로 빙고
class HeightBingo implements Bingo {

	@Override
	public void lineBingo(User user) {
		
	}
	
}


// 대각선 빙고
class DiagonalBingo implements Bingo {

	@Override
	public void lineBingo(User user) {
		
	}
	
}
