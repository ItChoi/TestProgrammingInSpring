package com.spring.test.bingo;

import java.util.Arrays;
import java.util.Scanner;

import lombok.Getter;


// TODO:: 빙고 게임 만들어보기
/**
 * 1. 2명이서 플레이
 * 2. 빙고판 width x height
 * 3. 가로/세로/대각선 줄 채울 시 빙고
 * 4. 빙고 3줄 시 게임 승리
 */
public class Main {
	public static void main(String[] args) {
		Scanner user1 = new Scanner(System.in);
		Scanner user2 = new Scanner(System.in);
		
		BingoBoard bingoBoard = new BingoBoard(5, 5, 3);
		
		User userInfo1 = new User(bingoBoard);
		User userInfo2 = new User(bingoBoard);
		
		userInfo1.writerBingoByNumber();
		userInfo2.writerBingoByNumber();
		
		for (int i = 0; i < userInfo1.getUserBingoBoard().length; i++) {
			for (int j = 0; j < userInfo1.getUserBingoBoard()[0].length; j++) {
				System.out.print(userInfo1.getUserBingoBoard()[i][j] + " ");
			}
			System.out.println();
		}
		
		
		
		while (userInfo1.getAllBingoCount() == bingoBoard.getEndBingo() ||
				userInfo2.getAllBingoCount() == bingoBoard.getEndBingo()) {
			
			// checkDuplicationNumber(user1, userInfo1.getBingoBoard()); 
			
		}
		
		
		

		
	}
	
}

@Getter
class BingoBoard {
	private final int width;
	private final int height;
	private final int totalNumber;
	
	private final int endBingo;
	
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
	
}


@Getter
class User {
	private BingoBoard bingoBoard;
	
	private int[][] userBingoBoard;
	private int[][] checkBingo;
	
	private int allBingoCount;
	
	
	public User(BingoBoard bingoBoard) {
		this.bingoBoard = bingoBoard;
		userBingoBoard = new int[bingoBoard.getWidth()][bingoBoard.getHeight()];
	}
	
	public void writerBingoByNumber() {
		for (int i = 0; i < userBingoBoard.length; i++) {
			for (int j = 0; j < userBingoBoard[0].length; j++) {
				userBingoBoard[i][j] = checkDuplicationNumber(userBingoBoard, i, j);
			}
		}
		
		checkBingo = Arrays.copyOf(userBingoBoard, userBingoBoard.length);
	}
	
	private int checkDuplicationNumber(int[][] userBingoBoard, int ii, int jj) {
		boolean duplicationNumber;
		int number = 0;
		
		do {
			int i = 0;
			int j = 0;
			duplicationNumber = false;
			number = (int) (Math.random() * (bingoBoard.getTotalNumber() * 2)) + 1;
			
			for (; i < ii; i++) {
				for (; j < jj; j++) {
					if (number == userBingoBoard[i][j]) {
						duplicationNumber = true;
						break;
					}
					
				}
				
				if (duplicationNumber) {
					break;
				}
				
			}
			
			if (userBingoBoard[0].length > j) {
				userBingoBoard[ii][jj] = number;
			}
			
		} while(duplicationNumber);
		
		return number;
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
