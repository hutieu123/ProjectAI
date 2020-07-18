package Algorithm;

import java.util.ArrayList;
import java.util.List;

import Model.ANode;
import Model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class Node extends ANode {
	
	private int[] label;
	private long value;
	private Node parent;
	private List<Node> childrens;
	private ConfigGame.Target target;
	
	public Node(Board board, long value, List<Node> childrens,ConfigGame.Target target) {
		super(board);
		this.value = value;
		this.childrens = childrens;
		this.target=target;
	}

	public Node() {
		super();
		childrens = new ArrayList<Node>();
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public long getValue() {
		return value;
	}

	public void computeValue(Target target) {
		this.value = this.heuristic(target);
	}

	public ConfigGame.Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public void addChild(Node childNode) {
		this.childrens.add(childNode);
	}

	public boolean isNoChildrens() {
		if (this.getChildrens().size() == 0) {
			return true;

		} else {
			return false;
		}

	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int[] getLabel() {
		return label;
	}

	public void setLabel(int[] label) {
		this.label = label;
	}

	public void setChildrens() {
		for (int i = 0; i < board.matrix.length; i++) {
			for (int j = 0; j < board.matrix.length; j++) {
				if (board.isValid(i, j) == true) {
					Node node = new Node();
					node.setTarget(target);
					board.move(i, j, target);
					node.setBoard(board);
					int[] label = new int[2];
					label[0] = i;
					label[1] = j;
					node.setLabel(label);
					addChild(node);
				}

			}
		}

	}

	public List<Node> getChildrens() {
		return this.childrens;
	}

	@Override
	public long heuristic(ConfigGame.Target target){
		int rs = 0;
		long Attack = 0;
		long Defense = 0;
//		Attack += scoreCountHorizontalAtk(row, col, target);
//		Attack += scoreCountVerticalAtk(row, col, target);
//		Attack += scoreCountPrimaryDiagonalAtk(row, col, target);
//		Attack += scoreCountReserveDiagonalAtk(row, col, target);
//		Defense += scoreCountHorizontalDef(row, col, target);
//		Defense += scoreCountVerticalDef(row, col, target);
//		Defense += scoreCountPrimaryDiagonalDef(row, col, target);
//		Defense += scoreCountReserveDiagonalDef(row, col, target);
//		System.out.println(Math.max(Attack, Defense));
		return Math.max(Attack, Defense);
	}
	
	private int scoreCountVerticalAtk(int row, int col, ConfigGame.Target target) {
		int scoreAtck = 0;
		int scoreDef = 0;
		int chessObject = 1;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		for (int i = row + 1; i < this.board.matrix.length; i++) {
			if (this.board.checkCorrect(i, col) == true) {
				if (this.board.matrix[i][col] == object && chessObject < this.board.numWin) {
					chessObject++;
				} else if (this.board.matrix[i][col] == enemy) {
					chessEnemy++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}

		}

		for (int i = row - 1; i < this.board.matrix.length; i--) {
			if (this.board.checkCorrect(i, col) == true) {
				if (this.board.matrix[i][col] == object && chessObject < this.board.numWin) {
					chessObject++;
				} else if (this.board.matrix[i][col] == enemy) {
					chessEnemy++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		if (chessEnemy == 2) {
			return 0;
		} else {
			scoreAtck = this.Attack[chessObject];
			scoreDef = this.Defen[chessEnemy+3];
		
			return scoreAtck - scoreDef;
		}

	}
	private static final int[] Attack = { 0, 3, 30, 200, 1500, 12000, 95000 };
	private static final int[] Defen = { 0, 1, 10, 150, 700, 6500, 50000 };
	
	private int scoreCountVerticalDef(int row, int col, ConfigGame.Target target) {
		int scoreAtck = 0;
		int scoreDef = 0;
		int chessObject = 1;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		for (int i = row + 1; i < this.board.matrix.length; i++) {
			if (this.board.checkCorrect(i, col) == true) {
				if (this.board.matrix[i][col] == enemy && chessEnemy < this.board.numWin) {
					chessEnemy++;
				} else if (this.board.matrix[i][col] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (chessObject < 2) {
			if (chessEnemy < this.board.numWin) {
				for (int i = row - 1; i < this.board.matrix.length; i--) {
					if (this.board.checkCorrect(i, col) == true) {
						if (this.board.matrix[i][col] == enemy && chessEnemy < this.board.numWin) {
							chessEnemy++;
						} else if (this.board.matrix[i][col] == object) {
							chessObject++;
							break;
						} else {
							break;
						}
					} else {
						break;
					}
				}
			}
		}
		if (chessObject == 2) {
			return 0;
		} else {
			scoreAtck = this.Attack[chessEnemy];
			scoreDef = this.Defen[chessObject];
			return scoreAtck - scoreDef;
		}

	}

	private int scoreCountHorizontalAtk(int row, int col, ConfigGame.Target target) {
		int scoreAtck = 0;
		int scoreDef = 0;
		int chessObject = 1;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}

		for (int i = col + 1; i < this.board.matrix.length; i++) {
			if (this.board.checkCorrect(row, i) == true) {
				if (this.board.matrix[row][i] == object && chessObject < this.board.numWin) {
					chessObject++;
				} else if (this.board.matrix[row][i] == enemy) {
					chessEnemy++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}

		}

		for (int i = col - 1; i < this.board.matrix.length; i--) {
			if (this.board.checkCorrect(row, i) == true) {
				if (this.board.matrix[row][i] == object && chessObject < this.board.numWin) {
					chessObject++;
				} else if (this.board.matrix[row][i] == enemy) {
					chessEnemy++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}

		}

		if (chessEnemy == 2) {
			return 0;
		} else {

			scoreDef = this.Defen[chessEnemy+1];
			return scoreDef;

		}

	}

	private int scoreCountHorizontalDef(int row, int col, ConfigGame.Target target) {
		int scoreAtck = 0;
		int scoreDef = 0;
		int chessObject = 1;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		for (int i = col + 1; i < this.board.matrix.length; i++) {
			if (this.board.checkCorrect(row, i) == true) {
				if (this.board.matrix[row][i] == enemy && chessEnemy < this.board.numWin) {
					chessEnemy++;
				} else if (this.board.matrix[row][i] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}

		}

		for (int i = col - 1; i < this.board.matrix.length; i--) {
			if (this.board.checkCorrect(row, i) == true) {
				if (this.board.matrix[row][i] == enemy && chessEnemy < this.board.numWin) {
					chessEnemy++;
				} else if (this.board.matrix[row][i] == enemy) {
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
		} else {
			scoreDef = this.Defen[chessEnemy+1];
			return scoreDef;

		}

	}

	private int scoreCountPrimaryDiagonalAtk(int row, int col, ConfigGame.Target target) {
		int scoreAtck = 0;
		int scoreDef = 0;
		int chessObject = 1;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		for (int i = 1; i <= (this.board.numWin - 1); i++) {
			int proRow = row + i;
			int proCol = col + i;
			if (this.board.checkCorrect(proRow, proCol) == true) {
				if (this.board.matrix[proRow][proCol] == object && chessObject < this.board.numWin) {
					chessObject++;
				} else if (this.board.matrix[proRow][proCol] == enemy) {
					chessEnemy++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}

		}

		for (int i = 1; i < (this.board.numWin - 1); i++) {
			int proRow = row - i;
			int proCol = col - i;
			if (this.board.checkCorrect(proRow, proCol) == true) {
				if (this.board.matrix[proRow][proCol] == object && chessObject < this.board.numWin) {
					chessObject++;
				} else if (this.board.matrix[proRow][proCol] == enemy) {
					chessEnemy++;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		if (chessEnemy == 2) {
			return 0;
		} else {
			scoreAtck = this.Attack[chessObject];
			scoreDef = this.Defen[chessEnemy + 2];
			return scoreAtck - scoreDef;

		}

	}

	private int scoreCountPrimaryDiagonalDef(int row, int col, ConfigGame.Target target) {
		int scoreAtck = 0;
		int scoreDef = 0;
		int chessObject = 1;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		for (int i = 1; i <= (this.board.numWin - 1); i++) {
			int proRow = row + i;
			int proCol = col + i;
			if (this.board.checkCorrect(proRow, proCol) == true) {
				if (this.board.matrix[proRow][proCol] == enemy && chessEnemy < this.board.numWin) {
					chessEnemy++;
				} else if (this.board.matrix[proRow][proCol] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}

		}
		if (chessObject < 2) {
			if (chessEnemy < this.board.numWin) {
				for (int i = 1; i < (this.board.numWin - 1); i++) {
					int proRow = row - i;
					int proCol = col - i;
					if (this.board.checkCorrect(proRow, proCol) == true) {
						if (this.board.matrix[proRow][proCol] == enemy && chessEnemy < this.board.numWin) {
							chessEnemy++;
						} else if (this.board.matrix[proRow][proCol] == object) {
							chessObject++;
						} else {
							break;
						}
					} else {
						break;
					}
				}
			}
		}

		if (chessObject == 2) {
			return 0;
		} else {
			scoreDef = this.Defen[chessEnemy+1];
			return scoreDef;

		}
	}

	private int scoreCountReserveDiagonalAtk(int row, int col, ConfigGame.Target target) {
		int scoreAtck = 0;
		int scoreDef = 0;
		int chessObject = 1;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		for (int i = 1; i <= (this.board.numWin - 1); i++) {
			int proRow = row + i;
			int proCol = col - i;
			if (this.board.checkCorrect(proRow, proCol) == true) {
				if (this.board.matrix[proRow][proCol] == object && chessObject < this.board.numWin) {
					chessObject++;
				} else if (this.board.matrix[proRow][proCol] == enemy) {
					chessEnemy++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = 1; i <= (this.board.numWin - 1); i++) {
			int proRow = row - i;
			int proCol = col + i;
			if (this.board.checkCorrect(proRow, proCol) == true) {
				if (this.board.matrix[proRow][proCol] == object && chessObject < this.board.numWin) {
					chessObject++;
				} else if (this.board.matrix[proRow][proCol] == enemy) {
					chessEnemy++;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		if (chessEnemy == 2) {
			return 0;
		} else {
			scoreAtck = this.Attack[chessObject];
			scoreDef = this.Defen[chessEnemy + 2];
			return scoreAtck - scoreDef;

		}
	}

	private int scoreCountReserveDiagonalDef(int row, int col, ConfigGame.Target target) {
		int scoreAtck = 0;
		int scoreDef = 0;
		int chessObject = 1;
		int chessEnemy = 0;
		int object = target.VALUE;
		int enemy = 0;
		if (object == 1) {
			enemy = 2;
		}
		if (object == 2) {
			enemy = 1;
		}
		for (int i = 1; i <= (this.board.numWin - 1); i++) {
			int proRow = row + i;
			int proCol = col - i;
			if (this.board.checkCorrect(proRow, proCol) == true) {
				if (this.board.matrix[proRow][proCol] == enemy && chessEnemy < this.board.numWin) {
					chessEnemy++;
				} else if (this.board.matrix[proRow][proCol] == object) {
					chessObject++;
					break;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = 1; i <= (this.board.numWin - 1); i++) {
			int proRow = row - i;
			int proCol = col + i;
			if (this.board.checkCorrect(proRow, proCol) == true) {
				if (this.board.matrix[proRow][proCol] == enemy && chessEnemy < this.board.numWin) {
					chessEnemy++;
				} else if (this.board.matrix[proRow][proCol] == object) {
					chessObject++;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		if (chessObject == 2) {
			return 0;
		} else {
			scoreDef = this.Defen[chessEnemy+1];
			return scoreDef;
		}
	}

}
