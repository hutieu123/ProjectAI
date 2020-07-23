package minimax;

import java.util.*;

import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class Node {
	public int alpha = Integer.MIN_VALUE;
	public int beta = Integer.MAX_VALUE;
	private Board stateBoard;
	private List<Node> neighbours;
	private ConfigGame.Target nextTarget;
	
	private Node parent;

	private static Random rd = new Random();
	
	private Location justHit;
	public int value;
	public Location getJustHit() {
		return justHit;
	}
	public void setJustHit(Location justHit) {
		this.justHit = justHit;
	}


	private static int[] AScore = { 0, 4, 27, 256, 1458 };
	private static int[] DScore = { 0, 2, 19, 99, 769 };

	public Node(Board stateBoard, Target nextTarget) {
		this.stateBoard = stateBoard;
		this.neighbours = new ArrayList<Node>();
		this.nextTarget = nextTarget;
		this.parent = null;
	}
	public Node(Target player, Location justHit) {
		this.neighbours = new ArrayList<Node>();
		this.nextTarget = player;
		
		this.parent = null;
		this.justHit=justHit;
	}


	public Node getParent() {
		return parent;
	}


	public void setParent(Node parent) {
		this.parent = parent;
	}


	public Board getStateBoard() {
		Node tmp =this;
		Map<Location, ConfigGame.Target> map = new HashMap<Location, ConfigGame.Target>();
		while(tmp.stateBoard==null) {
			map.put(tmp.justHit, tmp.getTargetJustHit());
			tmp=tmp.parent;
		}
		Board tmpBoard=tmp.stateBoard.clone();
		for(Location key:map.keySet()) {
			tmpBoard.matrix[key.row][key.col]= map.get(key).VALUE;
		}
		return tmpBoard;
	}

	public ConfigGame.Target getNextTarget() {
		return nextTarget;
	}
	public ConfigGame.Target getTargetJustHit() {
		if(getNextTarget()==ConfigGame.Target.O)return ConfigGame.Target.X;
		return ConfigGame.Target.O;
	}

	public void setPlayer(ConfigGame.Target player) {
		this.nextTarget = player;
	}

	public void setNeighbours(List<Node> neighbours) {
		this.neighbours = neighbours;
	}

	public void addNeighbour(Node neighbourNode) {
		this.neighbours.add(neighbourNode);
	}

	public int heuristic() {
		if(this.justHit==null) {
//			System.out.println("?");
			return 0;
		}
		Board board=this.scoreBoard();
//		System.out.println( board.matrix[justHit.row][justHit.col]);
		return  board.matrix[justHit.row][justHit.col];
	}
	public List<Node> getNeighbours() {
		return this.neighbours;
		
	}
	public List<Node> initAddNeighbours() {
		List<Location> listPointCanHit = new ArrayList<>();
		// tao danh sach cac o trong
		Board board =this.getStateBoard();
		for (int i = 0; i < board.matrix.length; i++) {
			for (int j = 0; j < board.matrix[0].length; j++) {
				if (board.matrix[i][j] == -1) {
					Location p = new Location(i, j);
					listPointCanHit.add(p);
				}
			}
		}
		// tao tat ca Node con co the co
		while (!listPointCanHit.isEmpty()) {
			// chon ngau nhien o con trong
			int index = rd.nextInt(listPointCanHit.size());
			Location p = listPointCanHit.get(index);
			Node node = new Node(getTargetJustHit(), p);
			node.setParent(this);
			this.addNeighbour(node);// them vao danh sach con
			
			listPointCanHit.remove(index);
			}
		return this.neighbours;

	}
	public Board scoreBoard(){
		Board stateBoard = this.getStateBoard();
		stateBoard.matrix[justHit.row][justHit.col]=-1;
		Board cBoard =stateBoard.empty();
		
		int row, col;
		int ePC, eHuman;
		ConfigGame.Target justTarget =ConfigGame.Target.X;
		ConfigGame.Target nextTarget = ConfigGame.Target.O;
		// Duyet theo hang
		for (row = 0; row < cBoard.matrix.length; row++)
			for (col = 0; col < cBoard.matrix[row].length - 4; col++) {
				ePC = 0;
				eHuman = 0;
				for (int i = 0; i < 5; i++) {
					if (stateBoard.matrix[row][col + i] == nextTarget.VALUE) // neu quan do la cua human
						eHuman++;
					if (stateBoard.matrix[row][col + i] ==justTarget.VALUE) // neu quan do la cua pc
						ePC++;
				}
				// trong vong 5 o khong co quan dich
				if (eHuman * ePC == 0 && eHuman != ePC)
					for (int i = 0; i < 5; i++) {
						if (stateBoard.matrix[row][col + i] == -1) { // neu o chua danh
							if (eHuman == 0) // ePC khac 0
								if (this.getNextTarget().VALUE == ConfigGame.PLAYER_TARGET.VALUE)
									cBoard.matrix[row][col + i] += DScore[ePC]; // cho diem phong ngu
								else
									cBoard.matrix[row][col + i] += AScore[ePC];// cho diem tan cong
							if (ePC == 0) // eHuman khac 0
								if (this.getNextTarget().VALUE == ConfigGame.COMPUTER_TARGET.VALUE)
									cBoard.matrix[row][col + i] += DScore[eHuman];// cho diem phong ngu	
								else
									cBoard.matrix[row][col + i] += AScore[eHuman];// cho diem tan cong
							if (eHuman == 4 || ePC == 4)
								cBoard.matrix[row][col + i] *= 2;
						}
					}
			}

		// Duyet theo cot
		for (col = 0; col < cBoard.matrix.length; col++)
			for (row = 0; row < cBoard.matrix[col].length - 4; row++) {
				ePC = 0;
				eHuman = 0;
				for (int i = 0; i < 5; i++) {
					if (stateBoard.matrix[row + i][col ] == nextTarget.VALUE)
						eHuman++;
					if (stateBoard.matrix[row + i][col ] == justTarget.VALUE)
						ePC++;
				}
				if (eHuman * ePC == 0 && eHuman != ePC)
					for (int i = 0; i < 5; i++) {
						if (stateBoard.matrix[row + i][col ] == -1) // Neu o chua duoc danh
						{
							if (eHuman == 0)
								if (this.getNextTarget().VALUE == ConfigGame.PLAYER_TARGET.VALUE)
									cBoard.matrix[row + i][col] += DScore[ePC];
								else
									cBoard.matrix[row + i][col] += AScore[ePC];
							if (ePC == 0)
								if (this.getNextTarget().VALUE == ConfigGame.COMPUTER_TARGET.VALUE)
									cBoard.matrix[row + i][col] += DScore[eHuman];
								else
									cBoard.matrix[row + i][col] += AScore[eHuman];
							if (eHuman == 4 || ePC == 4)
								cBoard.matrix[row + i][col] *= 2;
						}

					}
			}

		// Duyet theo duong cheo xuong
		for (col = 0; col < cBoard.matrix.length - 4; col++)
			for (row = 0; row < cBoard.matrix[col].length - 4; row++) {
				ePC = 0;
				eHuman = 0;
				for (int i = 0; i < 5; i++) {
					if (stateBoard.matrix[row + i][col + i] == nextTarget.VALUE)
						eHuman++;
					if (stateBoard.matrix[row + i][col + i] == justTarget.VALUE)
						ePC++;
				}
				if (eHuman * ePC == 0 && eHuman != ePC)
					for (int i = 0; i < 5; i++) {
						if (stateBoard.matrix[row + i][col + i] == -1) // Neu o chua duoc danh
						{
							if (eHuman == 0)
								if (this.getNextTarget().VALUE == ConfigGame.PLAYER_TARGET.VALUE)
									cBoard.matrix[row + i][col + i] += DScore[ePC];
								else
									cBoard.matrix[row + i][col + i] += AScore[ePC];
							if (ePC == 0)
								if (this.getNextTarget().VALUE == ConfigGame.COMPUTER_TARGET.VALUE)
									cBoard.matrix[row + i][col + i] += DScore[eHuman];
								else
									cBoard.matrix[row + i][col + i] += AScore[eHuman];
							if (eHuman == 4 || ePC == 4)
								cBoard.matrix[row + i][col + i] *= 2;
						}

					}
			}

		// Duyet theo duong cheo len
		for (row = 4; row < cBoard.matrix.length; row++)
			for (col = 0; col < cBoard.matrix[row].length - 4; col++) {
				ePC = 0; // so quan PC
				eHuman = 0; // so quan Human
				for (int i = 0; i < 5; i++) {
					if (stateBoard.matrix[row - i][col + i] == nextTarget.VALUE) // neu la human
						eHuman++; // tang so quan human
					if (stateBoard.matrix[row - i][col + i] == justTarget.VALUE) // neu la PC
						ePC++; // tang so quan PC
				}
				if (eHuman * ePC == 0 && eHuman != ePC)
					for (int i = 0; i < 5; i++) {
						if (stateBoard.matrix[row - i][col + i] == -1) { // neu o chua duoc danh
							if (eHuman == 0)
								if (this.getNextTarget().VALUE == ConfigGame.PLAYER_TARGET.VALUE)
									cBoard.matrix[row - i][col + i] += DScore[ePC];
								else
									cBoard.matrix[row - i][col + i] += AScore[ePC];
							if (ePC == 0)
								if (this.getNextTarget().VALUE == ConfigGame.COMPUTER_TARGET.VALUE)
									cBoard.matrix[row - i][col + i] += DScore[eHuman];
								else
									cBoard.matrix[row - i][col + i] += AScore[eHuman];
							if (eHuman == 4 || ePC == 4)
								cBoard.matrix[row - i][col + i] *= 2;
						}

					}
			}

/////////////////////////////////////////////////////////////////////////	
//		if(getTargetTurnLateHit()!=ConfigGame.PLAYER_TARGET) {
//			int hp = this.parent.getStateBoard().getHeuristic()+2000;
//			for (int i = 0; i < this.scoreBoard.matrix.length; i++) {
//				for (int j = 0; j < this.scoreBoard.matrix[i].length; j++) {
//					this.scoreBoard.matrix[i][j]=-this.scoreBoard.matrix[i][j];
//				}
//			}
//		}
		//cBoard.printMatrix();
		return cBoard;
	}


	
}
