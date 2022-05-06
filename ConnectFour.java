package com.codebind;
import java.util.*;

public class ConnectFour {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int[] places = {12,12,12,12,12,12,12};

		char[][] board = {
				{' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' '},
		};
//		printBoard(board);
		
		System.out.println("Please enter what column you would like your peice to go into: (1-7)");
		
		charToString(board);
		
		char winner = ' ';
		
		while (winner == ' ') {
			
			bestSpot(board, places);
			
			winner = gameOver(board);
			
			if (winner != ' ') {
				resault(winner);
				return;
			}
			
			// --------------------------
			
//			int place = -1;
//			boolean carryOn = false;
//			while (!carryOn) {
//				place = in.nextInt();
//				carryOn = isValid(place-1, places, true);
//			}
//			move(board, 'X', place, places, true);
//			
//			winner = gameOver(board);
//			
//			if (winner != ' ') {
//				resault(winner);
//				return;
//			}
			
			int place = -1;
			boolean carryOn = false;
			while (!carryOn) {
				place = in.nextInt();
				carryOn = isValid(place-1, places, true);
			}
			move(board, 'O', place, places, true);
			
			winner = gameOver(board);
			
			if (winner != ' ') {
				resault(winner);
				return;
			}
		}
	}
	
	public static void resault(char winner) {
		if (winner == 'T') {
			System.out.println("IT IS A DRAW");
		} else {
			System.out.println("THE WINNER IS " + winner);
		}
	}
	
	public static boolean isValid(int place, int[] places, boolean print) {
		if (place > 6 || place < 0) {
			System.out.println("Please choose another column that is available: (1-7)");
			return false;
		}
		
		int counter = places[place];
		
		if (print && counter == -2) {
			System.out.println("Please choose another column that is available: (1-7)");
			return false;
		}
		
		return counter != -2 && counter != -1;
	}
	
	public static void move(char[][] board, char who, int placeY, int[] places, boolean print) {
		int placeX = placeY * 2;
		placeX -= 2;
		board[places[placeY-1]][placeX] = who;
		places[placeY-1] -= 2;
		if (print) {
			printBoard(board);
		}
	}
	
	public static void remove(char[][] board, int place, int[] places) {
		int placeX = place * 2;
		placeX -= 2;
		board[places[place-1]+2][placeX] = ' ';
		places[place-1] += 2;
//		printBoard(board);
	}
	
	public static void printBoard(char[][] board) {
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board.length; b++) {
				System.out.print(board[a][b]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static char gameOver(char[][] board) {
		char winner = ' ';
		
		//horizontally
		for(int a = 0; a < board.length; a+= 2) {
			for (int b = 0; b < 7; b+= 2) {
				if (equal4(board[a][b], board[a][b+2], board[a][b+4], board[a][b+6])) {
					winner = board[a][b];
				}
			}
		}
		
		//vertically
		for(int a = 0; a < board.length; a+= 2) {
			for (int b = 0; b < 7; b+= 2) {
				if (equal4(board[b][a], board[b+2][a], board[b+4][a], board[b+6][a])) {
					winner = board[b][a];
				}
			}
		}
		
		//diogonally
		
		for (int y = 0; y < 7; y+= 2) {
			for (int x = 0; x < 7; x+= 2) {
				if (equal4(board[y][x], board[y+2][x+2], board[y+4][x+4], board[y+6][x+6])) {
					winner = board[y][x];
				}
			}
		}
		
		for (int y = 0; y < 7; y+= 2) {
			for (int x = 0; x < 7; x+= 2) {
				if (equal4(board[y+6][x], board[y+4][x+2], board[y+2][x+4], board[y][x+6])) {
					winner = board[y+6][x];
				}
			}
		}
		
		
		//tie
		
		if (winner == ' ') {
			int counter = 0;
			
			for (int a = 0; a < board.length; a++) {
				for (int b = 0; b < board.length; b++) {
					if (board[b][a] == ' ') {
						counter++;
					}
				}
			}
			
			if (counter == 0) {
				winner = 'T';
			}
		}
		
		return winner;
	}
	
	public static boolean equal4(char a, char b, char c, char d) {
		return a == b && b == c && c == d && d != ' ';
	}
	
	public static int points(char[][] board) {
		int points = 0;
		
		for(int a = 0; a < board.length; a+= 2) {
			for (int b = 0; b < 9; b+= 2) {
				if (equal3(board[a][b], board[a][b+2], board[a][b+4])) {
					if (b >= 2 && board[a][b-2] == ' ') {
						if (board[b][a] == 'X') {
							points+= 2;
						} else {
							points--;
						}
					} 
					if (b <= 6 && board[a][b+6] == ' ') {
						if (board[b][a] == 'X') {
							points+= 2;
						} else {
							points--;
						}
					}
				}
			}
		}
		
		for(int a = 0; a < board.length; a+= 2) {
			for (int b = 0; b < 9; b+= 2) {
				if (equal3(board[b][a], board[b+2][a], board[b+4][a])) {
					if (b >= 2 && board[b-2][a] == ' ') {
						if (board[b][a] == 'X') {
							points+= 2;
						} else {
							points--;
						}
					} 
					if (b <= 6 && board[b+6][a] == ' ') {
						if (board[b][a] == 'X') {
							points+= 2;
						} else {
							points--;
						}
					}
				}
			}
		}
		
		for (int y = 0; y < 9; y+= 2) {
			for (int x = 0; x < 9; x+= 2) {
				if (equal3(board[y][x], board[y+2][x+2], board[y+4][x+4])) {
					if (x >= 2 && y >= 2 && board[y-2][x-2] == ' ') {
						if (board[y][x] == ' ') {
							if (board[y][x] == 'X') {
								points+= 2;
							} else {
								points--;
							}
						}
					}
					if (x <= 6 && y <= 6 && board[y+6][x+6] == ' ') {
						if (board[y][x] == 'X') {
							points+= 2;
						} else {
							points--;
						}
					}
				}
			}
		}
		
		for (int y = 0; y < 9; y+= 2) {
			for (int x = 0; x < 9; x+= 2) {
				if (equal3(board[y+4][x], board[y+2][x+2], board[y][x+4])) {
					if (x <= 6 && y >= 2 && board[y-2][x+6] == ' ') {
						if (board[y][x] == ' ') {
							if (board[y][x] == 'X') {
								points+= 2;
							} else {
								points--;
							}
						}
					}
					if (x >= 2 && y <= 6 && board[y+6][x-2] == ' ') {
						if (board[y+2][x+2] == 'X') {
							points+= 2;
						} else {
							points--;
						}
					}
				}
			}
		}
		
		return points;
	}
	
	public static boolean equal3(char a, char b, char c) {
		return a == b && b == c && c != ' ';
	}
	
	public static String charToString(char[][] board) {
		String str = "";
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board.length; b++) {
				str += board[a][b];
			}
		}
		return str;
	}
	
	public static void bestSpot(char[][] board, int[] places) {
		int move = -1;
		int score;
		int bestScore = -100000;
		long start = System.currentTimeMillis();
		HashMap<String, Integer> index = new HashMap<>();
//		int index = 0;
		String[] str = new String[32000000];
		
		for (int a = 1; a < 8; a++) {
			if (isValid(a-1, places, false)) {
				move(board, 'X', a, places, false);
				
				
//				String str = charToString(board);
//				if (index.containsKey(str)) {
//					score = index.get(str);
//				} else {
//					score = minimax(board, places, false, 0, -100000, 100000, index);
//					index.put(str, score);
//				}
				
				score = minimax(board, places, false, 0, -100000, 100000, index);
				
				remove(board, a, places);
				if (bestScore < score) {
					bestScore = score;
					move = a;
				}
			}
		}
		move(board, 'X', move, places, true);
		System.out.println(System.currentTimeMillis() - start);
	}
	
	public static int minimax(char[][] board, int[] places, boolean isMaximising, int depth, int alpha, int beta, HashMap<String, Integer> index) {
		char winner = gameOver(board);
		if (winner != ' ') {
			return scores(winner);
		}
		if (depth > 8) {
			return points(board)-(depth/2);
			
		}
		
		int score, bestScore;
		
		if (isMaximising) {
			bestScore = -10000;
			for (int a = 1; a < 8; a++) {
				if (isValid(a-1, places, false)) { 
					move(board, 'X', a, places, false);
					
//					String str = charToString(board);
//					if (index.containsKey(str)) {
//						score = index.get(str);
////						System.out.println("used?");
//					} else {
//						score = minimax(board, places, false, depth+1, alpha, beta, index);
//						index.put(str, score);
////						System.out.println("not used?");
//					}
					
					score = minimax(board, places, false, depth+1, alpha, beta, index);
					
					remove(board, a, places);
					if (bestScore < score) {
						bestScore = score;
					}
					if (alpha < score) {
						alpha = score;
					}
					if (beta <= alpha) {
						return 700;
					}
				}
			}
		} else {
			bestScore = 10000;
			for (int a = 1; a < 8; a++) {
				if (isValid(a-1, places, false)) {
					move(board, 'O', a, places, false);
					
//					String str = charToString(board);
//					if (index.containsKey(str)) {
//						score = index.get(str);
//					} else {
//						score = minimax(board, places, true, depth+1, alpha, beta, index);
//						index.put(str, score);
//						
//					}
					
					score = minimax(board, places, true, depth+1, alpha, beta, index);
					
					remove(board, a, places);
					if (bestScore > score) {
						bestScore = score;
					}
					if (beta > score) {
						beta = score;
					}
					if (beta <= alpha) {
						return -700;
					}
				}
			}
		}
		return bestScore;
	}
		
	public static int scores(char winner) {
		int[] scores = {-100, 0, 100};
		switch(winner) {
			case 'X': 
				return scores[2];
			case 'O':
				return scores[0];
			default:
				return scores[1];
		}
	}
}




