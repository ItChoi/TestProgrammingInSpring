package com.spring.test.refactoring.bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;


/**
 * - 빙고 게임 만들어보기
 * 1. 2인 이상 플레이
 * 2. 빙고판 width x height
 * 3. 입력 숫자가 빙고판에 숫자와 일치하면 0으로 변경
 * 4. 가로/세로/대각선 줄 채울 시 빙고 (0으로 가로, 세로, 대각선 빙고 체크) 
 * 5. 빙고 3줄 시 게임 승리
 */
public class Main2 {
	public static void main(String[] args) {
		int bingoLength = 5;
		int endGameCondition = 3;
		int attendanceNumber = 2;
		
		List<String> userNames = new ArrayList<>();
		userNames.add("최상현");
		userNames.add("아무개");
		
		BingoBoard bingoBoard = new BingoBoard(bingoLength, endGameCondition, attendanceNumber, userNames);
		bingoBoard.initBingoGame();
		bingoBoard.logUserBingoBoardList();
		bingoBoard.tellBingoNumber();
		bingoBoard.logUserBingoBoardList();
	}
}

@Getter
class BingoBoard {
	private final int bingoLength;
	private final int totalNumber;
	private final int endBingo;
	private final int attendanceNumber;
	private final List<String> userNames;
	private Map<String, User> attendanceList = new HashMap<>();
	
	public BingoBoard(int bingoLength, int endBingo, int attendanceNumber, List<String> userNames) {
		this.bingoLength = bingoLength;
		this.endBingo = endBingo;
		this.totalNumber = this.bingoLength * this.bingoLength;
		this.attendanceNumber = attendanceNumber;
		this.userNames = validateUserNames(userNames);
	}
	
	private List<String> validateUserNames(List<String> userNames) {
		if (attendanceNumber > userNames.size()) {
			for (int i = userNames.size(); i < attendanceNumber; i++) {
				userNames.add("anonymity" + i);
			}
		}
		
		if (attendanceNumber < userNames.size()) {
			for (int i = attendanceNumber; i < userNames.size(); i++) {
				userNames.remove(i);
			}
			
		}
		
		return userNames;
		
	}
	
	public void removeBingoNumber(int bingoNumber) {
		userNames.forEach(userName -> {
			User user = attendanceList.get(userName);
			
			int[][] userCheckBingo = user.getUserCheckBingo();
			
			for (int i = 0; i < userCheckBingo.length; i++) {
				for (int j = 0; j < userCheckBingo[i].length; j++) {
					if (bingoNumber == userCheckBingo[i][j]) {
						userCheckBingo[i][j] = 0;
						user.checkBingo(i, j);
					}
				}
			}
		});
		
	}
	
	public void logUserBingoBoardList() {
		userNames.forEach(userName -> {
			User user = attendanceList.get(userName);
			int[][] userCheckBingo = user.getUserCheckBingo();
			
			System.out.println("닉네임: " + user.getUserName());
			for (int i = 0; i < userCheckBingo.length; i++) {
				for (int j = 0; j < userCheckBingo[i].length; j++) {
					System.out.printf("%4d", userCheckBingo[i][j]);
				}
				System.out.println();
			}
			
			System.out.println();
		});
		
	}
	
	public void initBingoGame() {
		this.userNames.forEach(userName -> {
			User user = new User(userName, this);
			user.writerBingoByNumber();
		});
	}
	
	public void tellBingoNumber() {
		Scanner inputNumber = new Scanner(System.in);
		boolean endGame = false;
		
		do {
			int bingoNumber = inputNumber.nextInt();
			
			if (bingoNumber <= 0) {
				continue;
			}
			
			removeBingoNumber(bingoNumber);
			logUserBingoBoardList();
			
			for (int i = 0; i < userNames.size(); i++) {
				User user = attendanceList.get(userNames.get(i));
				
				if (user.getBingoCount() == endBingo) {
					endGame = true;
					System.out.println("==========================================");
					System.out.println("우승자는: " + user.getUserName());
					System.out.println("==========================================");
					break;
				}
			}
			
			
		} while(!endGame);
		
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
		userBingoBoard = new int[bingoBoard.getBingoLength()][bingoBoard.getBingoLength()];
	}
	
	public void writerBingoByNumber() {
		for (int i = 0; i < userBingoBoard.length; i++) {
			for (int j = 0; j < userBingoBoard[i].length; j++) {
				userBingoBoard[i][j] = inputRandomValue(userBingoBoard, i, j);
			}
		}
		
		userCheckBingo = Arrays.copyOf(userBingoBoard, userBingoBoard.length);
		bingoBoard.getAttendanceList().put(userName, this);
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
		
		for (int i = 0; i <= wLength; i++) {
			
			 for (int j = 0; j <= (i == wLength ? hLength : userBingoBoard[i].length-1); j++) { 
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
	
	public void checkBingo(int index1, int index2) {
		boolean widthBingo = true;
		boolean heightBingo = true;
		boolean diagonalBingo1 = true;
		boolean diagonalBingo2 = false;
		
		int k = userCheckBingo.length - 1;
		for (int j = 0; j < userCheckBingo.length; j++) {
			
			if (userCheckBingo[index1][j] != 0 && widthBingo) {
				widthBingo = false;
			}
			
			if (userCheckBingo[j][index2] != 0 && heightBingo) {
				heightBingo = false;
			}
			
			if (diagonalBingo1) {
				if (index1 != index2) {
					diagonalBingo1 = false;					
				}
				
				if (userCheckBingo[k][k] != 0) {
					diagonalBingo1 = false;
				}
			}
			
			if (!diagonalBingo2) {
				if (j == index1 && k == index2) {
					int m = userCheckBingo.length - 1;
					diagonalBingo2 = true;
					
					for (int l = 0; l < userCheckBingo.length; l++) {
						if (userCheckBingo[l][m] != 0) {
							diagonalBingo2 = false;
						}
						
						--m;
					}
				
				}
			}
			
			--k;
		}
			
		if (widthBingo) {
			lineBingo(this, BingoType.WIDTH_BINGO.getBingoType());
		}
		
		if (heightBingo) {
			lineBingo(this, BingoType.HEIGHT_BINGO.getBingoType());
		}
		
		if (diagonalBingo1) {
			lineBingo(this, BingoType.DIAGONAL_BINGO.getBingoType());
		}
		
		if (diagonalBingo2) {
			lineBingo(this, BingoType.DIAGONAL_BINGO.getBingoType());
		}
		
	}
	
	private void lineBingo(User user, String bingoType) {
		BingoType.findBingoConstructor(bingoType).lineBingo(user);;
	}
	
}

@Getter
enum BingoType {
	WIDTH_BINGO("widthBingo", new WidthBingo()),
	HEIGHT_BINGO("heightBingo", new WidthBingo()),
	DIAGONAL_BINGO("diagonalBingo", new WidthBingo()),
	EMPTY("emptyBingo", null);
	
	private BingoType(String bingoType, Bingo bingoConstructor) {
		this.bingoType = bingoType;
		this.bingoConstructor = bingoConstructor;
	}
	
	private String bingoType;
	private Bingo bingoConstructor;
	
	public static Bingo findBingoConstructor(String code) {
		return  Arrays.stream(BingoType.values())
					.filter(value -> code.equals(value.getBingoType()))
					.map(value -> value.getBingoConstructor())
					.findAny()
					.orElse(BingoType.EMPTY.getBingoConstructor());
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
		System.out.println(user.getUserName() + ": 가로 빙고! 현재 빙고: " + user.getBingoCount());
	}
}

// 세로 빙고
class HeightBingo implements Bingo {

	@Override
	public void lineBingo(User user) {
		user.setBingoCount(user.getBingoCount() + 1);
		System.out.println(user.getUserName() + ": 세로 빙고! 현재 빙고: " + user.getBingoCount());
	}
}


// 대각선 빙고
class DiagonalBingo implements Bingo {

	@Override
	public void lineBingo(User user) {
		user.setBingoCount(user.getBingoCount() + 1);
		System.out.println(user.getUserName() + ": 대각선빙고! 현재 빙고: " + user.getBingoCount());
	}
}
