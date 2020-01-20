package com.spring.test.bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.Setter;


// TODO:: 빙고 게임 만들어보기
/**
 * 1. 2인 이상 플레이
 * 2. 빙고판 width x height
 * 3. 입력 숫자가 빙고판에 숫자와 일치하면 0으로 변경
 * 4. 가로/세로/대각선 줄 채울 시 빙고 (0으로 가로, 세로, 대각선) 
 * 5. 빙고 3줄 시 게임 승리
 */
public class Main {
	public static void main(String[] args) {
		int width = 5;
		int height = 5;
		int endBingo = 3;
		
		BingoBoard bingoBoard = new BingoBoard(width, height, endBingo);
		
		User userInfo1 = new User("user1", bingoBoard);
		User userInfo2 = new User("user2", bingoBoard);
		
		userInfo1.writerBingoByNumber();
		userInfo2.writerBingoByNumber();
		
		bingoBoard.logUserBingoBoardList();
		
		Scanner inputNumber = new Scanner(System.in);
		
		boolean endGame = false;
		do {
			int bingoNumber = inputNumber.nextInt();
			bingoBoard.removeBingoNumber(bingoNumber);
			
			for (User user :  bingoBoard.getAttendanceList()) {
				if (user.getBingoCount() == endBingo) {
					endGame = true;
					break;
				}
			}
			
		} while(!endGame);
		
		 
		
		System.out.println("\n\n\n=====================result=====================\n");
		bingoBoard.logUserBingoBoardList();
		
	}
	
}

@Getter
class BingoBoard {
	private final int width;
	private final int height;
	private final int totalNumber;
	private final int endBingo;
	private List<User> attendanceList = new ArrayList<>(); 
	
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
		
		attendanceList.stream().forEach((user) -> {
			increment1.set(0);
			
			Arrays.stream(user.getUserCheckBingo()).forEach((info) -> {
				increment2.set(0);
				int index1;
				index1 = increment1.getAndIncrement();
				
				Arrays.stream(info).forEach((index) -> {
					int index2;
					index2 = increment2.getAndIncrement();
					if (bingoNumber == index) {
						user.getUserCheckBingo()[index1][index2] = 0;
						// TODO:: 빙고인지 체크
					}
				});
				
			});
		});
	}
	
	private void checkBingoKind() {
		
	}
	
	public void logUserBingoBoardList() {
		attendanceList.stream().forEach((user) -> {
			System.out.println("userInfo");
			Arrays.stream(user.getUserCheckBingo()).forEach((array) -> {
				Arrays.stream(array).forEach((info) -> {
					System.out.printf("%4d", info);
					
				});
				System.out.println();
			});
			System.out.println();
		});
	}
	
}

@Getter
@Setter
class User {
	private BingoBoard bingoBoard;
	private Bingo bingo;
	
	private int[][] userBingoBoard;
	private int[][] userCheckBingo;
	
	private String userName;
	private int bingoCount;
	
	public User(String userName, BingoBoard bingoBoard) {
		this.userName = userName;
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
		bingoBoard.getAttendanceList().add(this);
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
		user.setBingoCount(user.getBingoCount() + 1);
		System.out.println(user.getUserName() + ": 가로 빙고 + 1");
	}
	
}

// 세로 빙고
class HeightBingo implements Bingo {

	@Override
	public void lineBingo(User user) {
		user.setBingoCount(user.getBingoCount() + 1);
		System.out.println(user.getUserName() + ": 세로 빙고 + 1");
	}
	
}


// 대각선 빙고
class DiagonalBingo implements Bingo {

	@Override
	public void lineBingo(User user) {
		user.setBingoCount(user.getBingoCount() + 1);
		System.out.println(user.getUserName() + ": 대각선빙고 + 1");
	}
	
}
