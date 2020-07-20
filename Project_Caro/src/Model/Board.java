package Model;

import java.util.Random;

import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;
import sun.swing.text.CountingPrintable;

public class Board {
	/*
	 * x : 1 o : 2 null : -1
	 */
	public static final long[] Attack = { 0, 90, 540, 2500, 15000, 12000, 95000 };
	public static final long[] Empty = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18 };
	public static final long[] Wall = { 15, 30, 45 };
	public static final long[] Defen = { 0, 30, 270, 1000, 7000, 6500, 50000 };
	public int numWin;
	public int[][] matrix;
	
		public Board(int rows, int cols, int numWin) {
		this.numWin = numWin;
		this.matrix = new int[rows][cols];
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[i].length; j++) {
				this.matrix[i][j] = ConfigGame.Target.NOT_THING.VALUE;
			}
		}
	}

	public void printBoard() {
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[i].length; j++) {
				System.out.print(" [" + this.matrix[i][j] + "] ");
			}
			System.out.println("");
		}
	}

	public int getNumWin() {
		return numWin;
	}

	public void setNumWin(int numWin) {
		this.numWin = numWin;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public boolean isValid(int rowIndex, int colIndex) {

		if (rowIndex >= this.matrix.length || colIndex >= this.matrix[0].length) {

			return false;

		}
		if (this.matrix[rowIndex][colIndex] == ConfigGame.Target.NOT_THING.VALUE) {
			return true;
		} else {

			return false;
		}

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
		// boardClone.matrix = this.matrix;
		return boardClone;
	}

	public int count(int[][] matrix, int[] location, int limitBottomNumInLine) {
		int rs = 1;
		int count = 1;
		final int loacation_row = location[0];
		final int loacation_col = location[1];
		// Đếm theo hàng ngang
		if (matrix[loacation_row][loacation_col] == -1) {
			return 0;
		}
		// if(ConfigGame.Target.NOT_THING.VALUE.equals(Integer.valueOf(matrix[loacation_row][loacation_col])))
		// {
		// return -1;
		// }
		// System.out.println(location[0]);
		for (int i = loacation_col + 1; i < matrix[loacation_row].length; i++) {
			if (matrix[loacation_row][loacation_col] == matrix[loacation_row][i]) {
				count++;
				if (count == limitBottomNumInLine) {
					// System.out.println(!checkCorrect(loacation_row, i + 1));
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
					// System.out.println(i);
					// System.out.println(j);
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

	public ConfigGame.Status getCurrentStatus(ConfigGame.Target target) {
		int numX = check(ConfigGame.Target.X);
		int numO = check(ConfigGame.Target.O);
		boolean stalemate = true;
		LoopOne: for (int row_Index = 0; row_Index < matrix.length; row_Index++) {
			for (int col_Index = 0; col_Index < matrix[row_Index].length; col_Index++) {
				if (matrix[row_Index][col_Index] == ConfigGame.Target.NOT_THING.VALUE) {
					stalemate = false;
					break LoopOne;
				}
			}
		}
		if (stalemate) {
			return ConfigGame.Status.STALEMATE;
		}
		switch (target) {
		case X:
			if (numX != -1)
				return ConfigGame.Status.X_WIN_GAME;
			if (numO != -1)
				return ConfigGame.Status.X_LOSE_GAME;
			break;
		case O:
			if (numX != -1)
				return ConfigGame.Status.O_LOSE_GAME;
			if (numO != -1)
				return ConfigGame.Status.O_WIN_GAME;
			break;

		default:
			break;
		}

		return ConfigGame.Status.NOT_OVER;
	}

	public ConfigGame.StatusMinimax getCurrentStatusMinimax(ConfigGame.Target target) {
		int numX = check(ConfigGame.Target.X);
		int numO = check(ConfigGame.Target.O);
		boolean stalemate = true;
		LoopOne: for (int row_Index = 0; row_Index < matrix.length; row_Index++) {
			for (int col_Index = 0; col_Index < matrix[row_Index].length; col_Index++) {
				if (matrix[row_Index][col_Index] == ConfigGame.Target.NOT_THING.VALUE) {
					stalemate = false;
					break LoopOne;
				}
			}
		}
		if (stalemate) {
			return ConfigGame.StatusMinimax.STALEMATE;
		}
		switch (target) {
		case X:
			if (numX != -1)
				return ConfigGame.StatusMinimax.WIN_GAME;
			if (numO != -1)
				return ConfigGame.StatusMinimax.LOSE_GAME;
			break;
		case O:
			if (numX != -1)
				return ConfigGame.StatusMinimax.LOSE_GAME;
			if (numO != -1)
				return ConfigGame.StatusMinimax.WIN_GAME;
			break;

		default:
			break;
		}

		return ConfigGame.StatusMinimax.NOT_OVER;
	}

	public int heuristic(ConfigGame.Target target) {
		int h = 0;
		for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
			for (int colIndex = 0; colIndex < matrix[rowIndex].length; colIndex++) {
				if (matrix[rowIndex][colIndex] == target.VALUE) {
					h += 50 * this.count(matrix, new int[] { rowIndex, colIndex }, 2);
				} else if (matrix[rowIndex][colIndex] == ConfigGame.Target.NOT_THING.VALUE)
					;
				else {
					h -= 49 * this.count(matrix, new int[] { rowIndex, colIndex }, 2);
				}
			}
		}
		return h;
	}

	public long heuristicVer2(int row, int col, ConfigGame.Target target) {

		long Attack = 0;
		long Defense = 0;

		Attack += scoreHorizontalAtk(row, col, target);
	
		Attack += scoreVerticalAtk(row, col, target);
	

		Attack += scorePrimaryDiagonalAtk(row, col, target);
		

		Attack += scoreReserveDiagonalAtk(row, col, target);
	

		Defense += scoreCountHorizontalDef(row, col, target);
		

		Defense += scoreCountVerticalDef(row, col, target);
	

		Defense += scoreCountPrimaryDiagonalDef(row, col, target);
	

		Defense += scoreCountReserveDiagonalDef(row, col, target);
		

		return Math.max(Attack, Defense);
	}

	public long scoreVerticalAtk(int row, int col, ConfigGame.Target target) {
		int scoreAtck = 0;
		int scoreDef = 0;
		int vacancy = ConfigGame.Target.NOT_THING.VALUE;
		int chessObject = 1;
		int chessEnemy = 0;
		int chessEmpty = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		all: for (int i = 1; i < numWin; i++) {
			if (checkCorrect(row + i, col) == true) {
				if (matrix[row + i][col] == object) {
					chessObject++;
				} else if (matrix[row + i][col] == enemy) {
					chessEnemy++;
					break;
				} else if (matrix[row + i][col] == vacancy) {
					chessEmpty++;
					for (int j = 1; j < (numWin - chessObject); j++) {
						if (checkCorrect(row + i + j, col)) {
							if (matrix[row + i + j][col] == object) {
								chessObject++;
								chessEmpty++;
							} else if (matrix[row + i + j][col] == vacancy) {
								chessEmpty++;
								break all;
							} else if (matrix[row + i + j][col] == enemy) {
								chessEnemy++;
								break all;
							}
						} else {
							chessEnemy++;
							break all;
						}
					}
					break all;
				}
			} else {
				chessEnemy += 2;
				break;
			}
		}
		all: for (int i = 1; i < numWin; i++) {
			if (checkCorrect(row - i, col) == true) {
				if (matrix[row - i][col] == object) {
					chessObject++;
				} else if (matrix[row - i][col] == enemy) {
					chessEnemy++;
					break;
				} else if (matrix[row - i][col] == vacancy) {
					chessEmpty++;
					for (int j = 1; j < (numWin - chessObject); j++) {
						if (checkCorrect(row - i - j, col)) {
							if (matrix[row - i - j][col] == object) {
								chessObject++;
								chessEmpty++;
							} else if (matrix[row - i - j][col] == vacancy) {
								chessEmpty++;
								break all;
							} else if (matrix[row - i - j][col] == enemy) {
								chessEnemy++;
								break all;
							}
						} else {
							chessEnemy++;
							break all;
						}
					}
					break all;
				}
			} else {
				chessEnemy += 2;
				break;
			}

		}
		if (chessEnemy >= 2 && chessObject < numWin) {
			return 0;
		} else {
			scoreAtck += Board.Attack[chessObject ];
			scoreDef += Board.Defen[chessEnemy ];
			scoreDef += Board.Empty[chessEmpty];
			return scoreAtck - scoreDef;
		}

	}

	public long scoreHorizontalAtk(int row, int col, ConfigGame.Target target) {
		long scoreAtck = 0;
		long scoreDef = 0;
		long vacancy = ConfigGame.Target.NOT_THING.VALUE;
		int chessObject = 1;
		int chessEnemy = 0;
		int chessEmpty = 0;
		long object = target.VALUE;
		long enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		all: for (int i = 1; i < numWin; i++) {
			if (checkCorrect(row, col + i)) {
				if (matrix[row][col + i] == object) {
					chessObject++;
				} else if (matrix[row][col + i] == enemy) {
					chessEnemy++;
					break;
				} else if (matrix[row][col + i] == ConfigGame.Target.NOT_THING.VALUE) {
					chessEmpty++;
					for (int j = 1; j < (numWin - chessObject); j++) {
						if (checkCorrect(row, col + i + j)) {
							if (matrix[row][col + i + j] == object) {
								chessEmpty++;
								chessObject++;
							} else if (matrix[row][col + i + j] == enemy) {
								chessEnemy++;
								break all;
							} else if (matrix[row][col + i + j] == ConfigGame.Target.NOT_THING.VALUE) {
								chessEmpty++;
								break all;
							}
						} else {
							chessEnemy++;
							break all;
						}
					}
					break all;
				}
			} else {
				chessEnemy += 2;
				break;
			}
		}
		all: for (int i = 1; i < numWin; i++) {
			if (checkCorrect(row, col - i)) {
				if (matrix[row][col - i] == object) {
					chessObject++;
				} else if (matrix[row][col - i] == enemy) {
					chessEnemy++;
					break;
				} else if (matrix[row][col - i] == vacancy) {
					chessEmpty++;
					for (int j = 1; j < (numWin - chessObject); j++) {
						if (checkCorrect(row, col - i - j)) {
							if (matrix[row][col - i - j] == object) {
								chessObject++;
								chessEmpty++;
							} else if (matrix[row][col - i - j] == enemy) {
								chessEnemy++;
								break all;
							} else if (matrix[row][col - i - j] == vacancy) {
								chessEmpty++;
								break all;
							}
						} else {
							chessEnemy++;
							break all;
						}
					}
					break all;
				}
			} else {
				chessEnemy += 2;
				break;
			}
		}
		if (chessEnemy >= 2 && chessObject < numWin) {
			return 0;
		} else {
			scoreAtck += Board.Attack[chessObject];
			scoreDef += Board.Defen[chessEnemy ];
			scoreDef += Board.Empty[chessEmpty];
			return scoreAtck - scoreDef;
		}
	}

	public long scorePrimaryDiagonalAtk(int row, int col, ConfigGame.Target target) {
		long scoreAtck = 0;
		long scoreDef = 0;
		int vacancy = ConfigGame.Target.NOT_THING.VALUE;
		int chessObject = 1;
		int chessEnemy = 0;
		int chessEmpty = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		all: for (int i = 1; i < numWin; i++) {
			if (checkCorrect(row + i, col + i)) {
				if (matrix[row + i][col + i] == object) {
					chessObject++;
				} else if (matrix[row + i][col + i] == enemy) {
					chessEnemy++;
					break;
				} else if (matrix[row + i][col + i] == vacancy) {
					chessEmpty++;
					for (int j = 1; j < (numWin - chessObject); j++) {
						if (checkCorrect(row + i + j, col + i + j)) {
							if (matrix[row + i + j][col + i + j] == object) {
								chessObject++;
								chessEmpty++;
							} else if (matrix[row + i + j][col + i + j] == enemy) {
								chessEnemy++;
								break all;
							} else if (matrix[row + i + j][col + i + j] == vacancy) {
								chessEmpty++;
								break all;
							}
						} else {
							chessEnemy++;
							break all;
						}
					}
					break all;
				}
			} else {
				chessEnemy += 2;
				break;
			}
		}
		all: for (int i = 1; i < numWin; i++) {
			if (checkCorrect(row - i, col - i)) {
				if (matrix[row - i][col - i] == object) {
					chessObject++;
				} else if (matrix[row - i][col - i] == enemy) {
					chessEnemy++;
					break;
				} else if (matrix[row - i][col - i] == vacancy) {
					chessEmpty++;
					for (int j = 1; j < (numWin - chessObject); j++) {
						if (checkCorrect(row - i - j, col - i - j)) {
							if (matrix[row - i - j][col - i - j] == object) {
								chessObject++;
								chessEmpty++;
							} else if (matrix[row - i - j][col - i - j] == enemy) {
								chessEnemy++;
								break all;
							} else if (matrix[row - i - j][col - i - j] == vacancy) {
								chessEmpty++;
								break all;
							}
						} else {
							chessEnemy++;
							break all;
						}
					}
					break all;
				}
			} else {
				chessEnemy += 2;
				break;
			}
		}
		if (chessEnemy >= 2 && chessObject < numWin) {
			return 0;
		} else {
			scoreAtck += Board.Attack[chessObject ];
			scoreDef += Board.Defen[chessEnemy ];
			scoreDef += Board.Empty[chessEmpty];
			return scoreAtck - scoreDef;
		}
	}

	public long scoreReserveDiagonalAtk(int row, int col, ConfigGame.Target target) {
		long scoreAtck = 0;
		long scoreDef = 0;
		int vacancy = ConfigGame.Target.NOT_THING.VALUE;
		int chessObject = 1;
		int chessEnemy = 0;
		int chessEmpty = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		all: for (int i = 1; i < numWin; i++) {
			if (checkCorrect(row + i, col - i)) {
				if (matrix[row + i][col - i] == object) {
					chessObject++;
				} else if (matrix[row + i][col - i] == enemy) {
					chessEnemy++;
					break;
				} else if (matrix[row + i][col - i] == vacancy) {
					chessEmpty++;
					for (int j = 1; j < (numWin - chessObject); j++) {
						if (checkCorrect(row + i + j, col - i - j)) {
							if (matrix[row + i + j][col - i - j] == object) {
								chessObject++;
								chessEmpty++;
							} else if (matrix[row + i + j][col - i - j] == enemy) {
								chessEnemy++;
								break all;
							} else if (matrix[row + i + j][col - i - j] == enemy) {
								chessEmpty++;
								break all;
							}
						} else {
							chessEnemy++;
							break;
						}
					}
					break all;
				}
			} else {
				chessEnemy += 2;
				break;
			}
		}
		all: for (int i = 1; i < numWin; i++) {
			if (checkCorrect(row - i, col + i)) {
				if (matrix[row - i][col + i] == object) {
					chessObject++;
				} else if (matrix[row - i][col + i] == enemy) {
					chessEnemy++;
					break;
				} else if (matrix[row - i][col + i] == vacancy) {
					chessEmpty++;

					for (int j = 1; j < (numWin - chessObject); j++) {
						if (checkCorrect(row - i - j, col + i + j)) {
							if (matrix[row - i - j][col + i + j] == object) {
								chessObject++;
								chessEmpty++;

							} else if (matrix[row - i - j][col + i + j] == enemy) {
								chessEnemy++;
								break all;
							} else if (matrix[row - i - j][col + i + j] == vacancy) {
								chessEmpty++;
								break all;
							}
						} else {
							chessEnemy++;
							break;
						}
					}
					break all;
				}
			} else {
				chessEnemy += 2;
				break;
			}
		}
		if (chessEnemy >= 2 && chessObject < numWin) {
			return 0;
		} else {
			scoreAtck += Board.Attack[chessObject ];
			scoreDef += Board.Defen[chessEnemy ];
			scoreDef += Board.Empty[chessEmpty];
			return scoreAtck - scoreDef;
		}
	}

	public long scoreCountVerticalDef(int row, int col, ConfigGame.Target target) {
		long scoreAtck = 0;
		long scoreDef = 0;
		int chessObject = 0;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		} else {
			enemy = 1;
		}
		for (int i = 1; i <= numWin; i++) {
			if (checkCorrect(row + i, col) == true) {
				if (matrix[row + i][col] == enemy) {
					chessEnemy++;
				} else if (matrix[row + i][col] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = 1; i <= numWin; i++) {
			if (checkCorrect(row - i, col) == true) {
				if (matrix[row - i][col] == enemy) {
					chessEnemy++;
				} else if (matrix[row - i][col] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (chessObject >= 2) {
			return 0;
		}
		if (chessEnemy < 3) {
			scoreDef = (long) (Board.Defen[chessEnemy] * (0.7));
			scoreDef -= Board.Defen[chessObject + 1];
		} else {
			scoreDef = (long) (Board.Defen[chessEnemy + 1]* (1.5));
			scoreDef -= Board.Defen[chessObject + 1];
		}

		return scoreDef;

	}

	public long scoreCountHorizontalDef(int row, int col, ConfigGame.Target target) {
		long scoreAtck = 0;
		long scoreDef = 0;
		int chessObject = 0;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		for (int i = 1; i <= numWin; i++) {
			if (checkCorrect(row, col + i) == true) {
				if (matrix[row][col + i] == enemy) {
					chessEnemy++;
				} else if (matrix[row][col + i] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}

		}

		for (int i = 1; i <= numWin; i++) {
			if (checkCorrect(row, col - i) == true) {
				if (matrix[row][col - i] == enemy) {
					chessEnemy++;
				} else if (matrix[row][col - i] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}

		}
		if (chessObject >= 2) {
			return 0;
		}
		if (chessEnemy < 3) {
			scoreDef = (long) (Board.Defen[chessEnemy] * (0.7));
			scoreDef -= Board.Defen[chessObject + 1];
		} else {
			scoreDef = (long) (Board.Defen[chessEnemy + 1]* (1.5));
			scoreDef -= Board.Defen[chessObject + 1];
		}
		return scoreDef;

	}

	public long scoreCountPrimaryDiagonalDef(int row, int col, ConfigGame.Target target) {
		long scoreAtck = 0;
		long scoreDef = 0;
		int chessObject = 0;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		for (int i = 1; i <= numWin; i++) {
			int proRow = row + i;
			int proCol = col + i;
			if (checkCorrect(proRow, proCol) == true) {
				if (matrix[proRow][proCol] == enemy) {
					chessEnemy++;
				} else if (matrix[proRow][proCol] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}

		}

		for (int i = 1; i < numWin; i++) {
			int proRow = row - i;
			int proCol = col - i;
			if (checkCorrect(proRow, proCol) == true) {
				if (matrix[proRow][proCol] == enemy) {
					chessEnemy++;
				} else if (matrix[proRow][proCol] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (chessObject == 2) {
			return 0;
		}
		if (chessEnemy < 3) {
			scoreDef = (long) (Board.Defen[chessEnemy] * (0.7));
			scoreDef -= Board.Defen[chessObject + 1];
		} else {
			scoreDef = (long) (Board.Defen[chessEnemy + 1]* (1.5));
			scoreDef -= Board.Defen[chessObject + 1];
		}

		return scoreDef;

	}

	public long scoreCountReserveDiagonalDef(int row, int col, ConfigGame.Target target) {
		long scoreAtck = 0;
		long scoreDef = 0;
		int chessObject = 0;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		for (int i = 1; i <= numWin; i++) {
			int proRow = row + i;
			int proCol = col - i;
			if (checkCorrect(proRow, proCol) == true) {
				if (matrix[proRow][proCol] == enemy) {
					chessEnemy++;
				} else if (matrix[proRow][proCol] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i <= numWin; i++) {
			int proRow = row - i;
			int proCol = col + i;
			if (checkCorrect(proRow, proCol) == true) {
				if (matrix[proRow][proCol] == enemy) {
					chessEnemy++;
				} else if (matrix[proRow][proCol] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (chessObject == 2) {
			return 0;
		}
		if (chessEnemy < 3) {
			scoreDef = (long) (Board.Defen[chessEnemy] * (0.7));
			scoreDef -= Board.Defen[chessObject + 1];
		} else {
			scoreDef = (long) (Board.Defen[chessEnemy + 1]* (1.5));
			scoreDef -= Board.Defen[chessObject + 1];
		}

		return scoreDef;

	}

}
