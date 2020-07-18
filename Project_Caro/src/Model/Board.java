package Model;

import java.util.Random;

import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class Board {
	/*
	 * x : 1 o : 2 null : -1
	 */
	public int numWin;
	public int[][] matrix;
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	private Agent agent;

	

	public Board(int rows, int cols, int numWin) {
		this.numWin=numWin;
		this.matrix = new int[rows][cols];
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[i].length; j++) {
				this.matrix[i][j] = -1;
			}
		}
	}

	public boolean isValid(int rowIndex, int colIndex) {
		if(rowIndex>=this.matrix.length||colIndex>=this.matrix[0].length) {
			return false;
		}
		if (this.matrix[rowIndex][colIndex]==-1) {
			return true;
		}else
		return false;
	}

	public Board move(int rows, int cols, ConfigGame.Target target) {
		Board boardClone = this.clone();
		if (boardClone.matrix[rows][cols] == ConfigGame.Target.NOT_THING.VALUE) {
			boardClone.matrix[rows][cols] = target.VALUE;
			return boardClone;
		}
		return null;
	}



	public boolean isOver() {
		int numX = check(ConfigGame.Target.X);
		int numO = check(ConfigGame.Target.O);
		if (numX == this.numWin || numO == this.numWin)
			return true;
		return false;
	}

	protected Board clone() {
		Board boardClone = new Board(matrix.length, matrix[0].length, this.numWin);
		for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
			for (int colIndex = 0; colIndex < matrix[rowIndex].length; colIndex++) {
				boardClone.matrix[rowIndex][colIndex] = this.matrix[rowIndex][colIndex];
			}
		}
		return boardClone;
	}

	public int count(int[][] matrix, int[] location, int limitBottomNumInLine) {
		int rs = 1;
		int count = 1;
		final int loacation_row = location[0];
		final int loacation_col = location[1];
		// Đếm theo hàng ngang
		if(matrix[loacation_row][loacation_col]==-1) {
			return 0;
		}
//		if(ConfigGame.Target.NOT_THING.VALUE.equals(Integer.valueOf(matrix[loacation_row][loacation_col]))) {
//			return -1;
//		}
		//System.out.println(location[0]);
		for (int i = loacation_col + 1; i < matrix[loacation_row].length; i++) {
			if (matrix[loacation_row][loacation_col] == matrix[loacation_row][i]) {
				count++;
				if (count == limitBottomNumInLine) {
//					System.out.println(!checkCorrect(loacation_row, i + 1));
					if (!checkCorrect(loacation_row, loacation_col + 1 - 2) || !checkCorrect(loacation_row, i + 1)
							|| matrix[loacation_row][loacation_col - 1] == -1 || matrix[loacation_row][i + 1] == -1) {

					} else {
						if (matrix[loacation_row][loacation_col] != matrix[loacation_row][loacation_col - 1]
								&& matrix[loacation_row][loacation_col] != matrix[loacation_row][i + 1]) {
							break;
						}

					}
				}
				if (count >= rs) {
					rs = count;
				}
			} else {

				break;
			}

		}
		count = 1;
		// Đếm theo hàng dọc
		for (int i = loacation_row + 1; i < matrix.length; i++) {
			if (matrix[loacation_row][loacation_col] == matrix[i][loacation_col]) {
				count++;
				if (count == limitBottomNumInLine) {
					if (!checkCorrect(loacation_row + 1 + (-2), loacation_col) || !checkCorrect(i + 1, loacation_col)
							|| matrix[loacation_row + 1 + (-2)][loacation_col] == -1
							|| matrix[i + 1][loacation_col] == -1) {

					} else {
						if (matrix[loacation_row][loacation_col] != matrix[loacation_row + 1 + (-2)][loacation_col]
								&& matrix[loacation_row][loacation_col] != matrix[i + 1][loacation_col]) {
							break;
						}

					}
				}
				if (count >= rs) {
					rs = count;
				}
			} else {
				break;
			}

		}
		count = 1;
		// Đếm theo đường chéo ngược
		for (int i = 1; i < matrix.length - loacation_col && i < matrix.length - loacation_row; i++) {
			if (matrix[loacation_row][loacation_col] == matrix[loacation_row + i][loacation_col + i]) {

				count++;
				if (count == limitBottomNumInLine) {
					if (!checkCorrect(loacation_row + (-1), loacation_col + (-1))
							|| !checkCorrect(loacation_row + (i + 1), loacation_col + (i + 1))
							|| matrix[loacation_row + (-1)][loacation_col + (-1)] == -1
							|| matrix[loacation_row + (i + 1)][loacation_col + (i + 1)] == -1) {

					} else {
						if (matrix[loacation_row][loacation_col] != matrix[loacation_row + (-1)][loacation_col + (-1)]
								&& matrix[loacation_row][loacation_col] != matrix[loacation_row + (i + 1)][loacation_col
										+ (i + 1)]) {
							break;
						}

					}
				}
				if (count >= rs) {
					rs = count;
				}
			} else {
				break;
			}
		}
		count = 1;
		// Đếm theo chéo thuận
		for (int i = 1; i < matrix.length - loacation_col; i++) {
			if (!checkCorrect(loacation_row - i, loacation_col + i)) {

				break;
			}
			if (matrix[loacation_row][loacation_col] == matrix[loacation_row - i][loacation_col + i]) {
				count++;
				if (count == limitBottomNumInLine) {
					if (!checkCorrect(loacation_row + 1, loacation_col - 1)
							|| !checkCorrect(loacation_row - (i + 1), loacation_col + (i + 1))
							|| matrix[loacation_row + 1][loacation_col - 1] == -1
							|| matrix[loacation_row - (i + 1)][loacation_col + (i + 1)] == -1) {

					} else {
						if (matrix[loacation_row][loacation_col] != matrix[loacation_row + (-1)][loacation_col - (-1)]
								&& matrix[loacation_row][loacation_col] != matrix[loacation_row + (i + 1)][loacation_col
										- (i + 1)]) {
							break;
						}

					}
				}
				if (count >= rs) {
					rs = count;
				}
			} else {
				break;
			}
		}

		return rs;
	}

	public boolean checkCorrect(int rowIndex, int colIndex) {
		return rowIndex >= 0 && colIndex >= 0 && rowIndex < matrix.length && colIndex < matrix[rowIndex].length;
	}

	/*
	 * Check target win or no. If win return target, no return -1
	 */
	public int check(ConfigGame.Target target) {
		int[] location = new int[2];
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {

				if (matrix[i][j] == target.VALUE) {

					location[0] = i;
					location[1] = j;
//					System.out.println(i);
//					System.out.println(j);
					count = count(matrix, location, 3);

				}
				if (count >= numWin) {
					return count;
				} else
					count = 0;

			}
		}
		return -1;
	}
	public ConfigGame.Status getCurrentStatus(ConfigGame.Target target){
		int numX= check(ConfigGame.Target.X);
		int numO= check(ConfigGame.Target.O);
		boolean stalemate=true;
		LoopOne:
		for (int row_Index = 0; row_Index < matrix.length; row_Index++) {
			for (int col_Index = 0; col_Index < matrix[row_Index].length; col_Index++) {
				if(matrix[row_Index][col_Index]==ConfigGame.Target.NOT_THING.VALUE) {
					stalemate=false;
					break LoopOne;
				}
			}
		}
		if(stalemate) {
			return ConfigGame.Status.STALEMATE;
		}
		switch (target) {
		case X:
			if(numX!=-1) return ConfigGame.Status.X_WIN_GAME;
			if(numO!=-1) return ConfigGame.Status.X_LOSE_GAME;
			break;
		case O:
			if(numX!=-1) return ConfigGame.Status.O_LOSE_GAME;
			if(numO!=-1) return ConfigGame.Status.O_WIN_GAME;
			break;

		default:
			break;
		}
		
		return ConfigGame.Status.NOT_OVER;
	}
	public ConfigGame.StatusMinimax getCurrentStatusMinimax(ConfigGame.Target target){
		int numX= check(ConfigGame.Target.X);
		int numO= check(ConfigGame.Target.O);
		boolean stalemate=true;
		LoopOne:
		for (int row_Index = 0; row_Index < matrix.length; row_Index++) {
			for (int col_Index = 0; col_Index < matrix[row_Index].length; col_Index++) {
				if(matrix[row_Index][col_Index]==ConfigGame.Target.NOT_THING.VALUE) {
					stalemate=false;
					break LoopOne;
				}
			}
		}
		if(stalemate) {
			return ConfigGame.StatusMinimax.STALEMATE;
		}
		switch (target) {
		case X:
			if(numX!=-1) return ConfigGame.StatusMinimax.WIN_GAME;
			if(numO!=-1) return ConfigGame.StatusMinimax.LOSE_GAME;
			break;
		case O:
			if(numX!=-1) return ConfigGame.StatusMinimax.LOSE_GAME;
			if(numO!=-1) return ConfigGame.StatusMinimax.WIN_GAME;
			break;

		default:
			break;
		}
		
		return ConfigGame.StatusMinimax.NOT_OVER;
	}
	

}
