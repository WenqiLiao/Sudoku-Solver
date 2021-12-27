package Soduke;

import java.io.*;
import java.util.*;

public class Sudoku {

	static int[][] board;
	static int rowNum = 0;
	static int colNum = 0;

	public static void main(String[] args) {

		board = new int[][] { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
				{ 0, 9, 8, 0, 0, 0, 0, 6, 0 }, { 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
				{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, { 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
				{ 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

		solveSudoku(board);

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.print("\n");
		}

	}

	public static void solveSudoku(int[][] board) {
		ArrayList<int[]> toFill = new ArrayList<int[]>();
		rowNum = board.length;
		colNum = board[0].length;

		// Find out all coordinates that need to be filled
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (board[i][j] == 0) {
					int[] fillSec = { i, j };
					toFill.add(fillSec);
				}
			}
		}
		// System.out.println(toFill.size());

		checkSolution(toFill, 0);

		/*
		 * 
		 * //Find solution for all coordinates that need to be filled for (int i = 0; i
		 * < toFill.size(); i++) { int[] cdnt = toFill.get(i); int row = cdnt[0]; int
		 * col = cdnt[1]; int sol = checkSolution(row, col); board[row][col] = sol; }
		 * 
		 */

	}

	private static boolean checkSolution(ArrayList<int[]> toFill, int loc) {
		// after find the last number
		if (loc == toFill.size()) {
			return true;
		}
		// get the position now we need to solve
		int[] current = toFill.get(loc);
		int row = current[0];
		int col = current[1];

		for (int num = 1; num <= 9; num++) {
			if (checkRow(row, col, num) && checkCol(row, col, num) && checkSub(row, col, num)) {
				board[row][col] = num;

				// we need to know whether all next ones can get right answer
				boolean allChecked = checkSolution(toFill, loc + 1);

				if (allChecked) {
					return true;
				} else {
					board[row][col] = 0;
				}
			}
		}
		return false;
	}

	/*
	 * 
	 * //Find solution for each coordinate private static int checkSolution(int row,
	 * int col) { int result = 0; result = recCheckSol(row, col, 1); return result;
	 * }
	 * 
	 */

	/*
	 * 
	 * //Recursion to find solution private static int recCheckSol(int row, int col,
	 * int num) { //base case if (num == 9) { return num; } if (checkRow(row, col,
	 * num) && checkCol(row, col, num) && checkSub(row, col, num)) { return num; }
	 * else { return recCheckSol(row, col, num+1); }
	 * 
	 * }
	 * 
	 */

	// Check whether there is repetition in the row
	private static boolean checkRow(int row, int col, int num) {
		for (int i = 0; i < colNum; i++) {
			if (board[row][i] == num) {
				return false;
			}
		}
		return true;
	}

	// Check whether there is repetition in the col
	private static boolean checkCol(int row, int col, int num) {
		for (int i = 0; i < rowNum; i++) {
			if (board[i][col] == num) {
				return false;
			}
		}
		return true;
	}

	// Check whether their is repetition in the sub
	private static boolean checkSub(int row, int col, int num) {
		int subRow = row / 3;
		int subCol = col / 3;
		for (int i = subRow * 3; i < subRow * 3 + 3; i++) {
			for (int j = subCol * 3; j < subCol * 3 + 3; j++) {
				if (board[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}

}
