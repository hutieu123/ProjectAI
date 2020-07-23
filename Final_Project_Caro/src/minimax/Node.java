package minimax;

import java.util.*;

import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class Node {
	private Board stateBoard;
	private List<Node> neighbours;
	private ConfigGame.Target player;
	private Board scoreBoard;
	private Node parent;

	private List<Location> listPointCanHit = new ArrayList<>();
	private static Random rd = new Random();
	private Map<Node, Location> mapPoints;
	
	private static int[] AScore = { 0, 4, 27, 256, 1458 };
	private static int[] DScore = { 0, 2, 19, 99, 769 };

	public Node(Board stateBoard, Target player) {
		this.stateBoard = stateBoard;
		this.neighbours = new ArrayList<Node>();
		this.player = player;
		this.mapPoints = new HashMap<>();
		this.scoreBoard = scoreBoard();
		this.parent = null;
	}


	public Node getParent() {
		return parent;
	}


	public void setParent(Node parent) {
		this.parent = parent;
	}


	public Board getStateBoard() {
		return stateBoard;
	}

	public void setStateBoard(Board stateBoard) {
		this.stateBoard = stateBoard;
	}

	public ConfigGame.Target getPlayer() {
		return player;
	}

	public void setPlayer(ConfigGame.Target player) {
		this.player = player;
	}

	public void setNeighbours(List<Node> neighbours) {
		this.neighbours = neighbours;
	}

	public void addNeighbour(Node neighbourNode) {
		this.neighbours.add(neighbourNode);
	}

	public int heuristic(Board board, Location point, int player) {
		int heuristic = 0;
		
		return heuristic = scoreBoard.matrix[point.row][point.col];
	}

	public List<Node> getNeighbours() {
		// tao danh sach cac o trong
		for (int i = 0; i < stateBoard.matrix.length; i++) {
			for (int j = 0; j < stateBoard.matrix[0].length; j++) {
				if (stateBoard.matrix[i][j] == -1) {
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
			ConfigGame.Target nextPlayer;
			
			if(player == ConfigGame.Target.O)nextPlayer = ConfigGame.Target.X;
			else nextPlayer = ConfigGame.Target.O;
			
			int heuristic = this.heuristic(stateBoard, p, nextPlayer.VALUE);// tim heuristic
			Board newState = stateBoard.move(p.row, p.col, nextPlayer);// tao trang thai moi
			
			newState.setHeuristic(heuristic);
			Node node = new Node(newState, nextPlayer);
			node.setParent(this);
			this.addNeighbour(node);// them vao danh sach con
			this.getMapPoints().put(node, p);
			listPointCanHit.remove(index);
			}
		return neighbours;

	}
	public Board scoreBoard(){
		Board cBoard = this.getStateBoard().empty();
		int nextPlayer;
		
		if(this.getPlayer() == ConfigGame.Target.O)nextPlayer = ConfigGame.Target.X.VALUE;
		else nextPlayer = ConfigGame.Target.O.VALUE;
		
		int row, col;
		int ePC, eHuman;
		// Duyet theo hang
		for (row = 0; row < cBoard.matrix.length; row++)
			for (col = 0; col < cBoard.matrix[row].length - 4; col++) {
				ePC = 0;
				eHuman = 0;
				for (int i = 0; i < 5; i++) {
					if (this.getStateBoard().matrix[row][col + i] == 1) // neu quan do la cua human
						eHuman++;
					if (this.getStateBoard().matrix[row][col + i] == 2) // neu quan do la cua pc
						ePC++;
				}
				// trong vong 5 o khong co quan dich
				if (eHuman * ePC == 0 && eHuman != ePC)
					for (int i = 0; i < 5; i++) {
						if (this.getStateBoard().matrix[row][col + i] == -1) { // neu o chua danh
							if (eHuman == 0) // ePC khac 0
								if (nextPlayer == ConfigGame.PLAYER_TARGET.VALUE)
									cBoard.matrix[row][col + i] += DScore[ePC]; // cho diem phong ngu
								else
									cBoard.matrix[row][col + i] += AScore[ePC];// cho diem tan cong
							if (ePC == 0) // eHuman khac 0
								if (nextPlayer == ConfigGame.COMPUTER_TARGET.VALUE)
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
					if (this.getStateBoard().matrix[row + i][col ] == 1)
						eHuman++;
					if (this.getStateBoard().matrix[row + i][col ] == 2)
						ePC++;
				}
				if (eHuman * ePC == 0 && eHuman != ePC)
					for (int i = 0; i < 5; i++) {
						if (this.getStateBoard().matrix[row + i][col ] == -1) // Neu o chua duoc danh
						{
							if (eHuman == 0)
								if (nextPlayer == ConfigGame.PLAYER_TARGET.VALUE)
									cBoard.matrix[row + i][col] += DScore[ePC];
								else
									cBoard.matrix[row + i][col] += AScore[ePC];
							if (ePC == 0)
								if (nextPlayer == ConfigGame.COMPUTER_TARGET.VALUE)
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
					if (this.getStateBoard().matrix[row + i][col + i] == 1)
						eHuman++;
					if (this.getStateBoard().matrix[row + i][col + i] == 2)
						ePC++;
				}
				if (eHuman * ePC == 0 && eHuman != ePC)
					for (int i = 0; i < 5; i++) {
						if (this.getStateBoard().matrix[row + i][col + i] == -1) // Neu o chua duoc danh
						{
							if (eHuman == 0)
								if (nextPlayer == ConfigGame.PLAYER_TARGET.VALUE)
									cBoard.matrix[row + i][col + i] += DScore[ePC];
								else
									cBoard.matrix[row + i][col + i] += AScore[ePC];
							if (ePC == 0)
								if (nextPlayer == ConfigGame.COMPUTER_TARGET.VALUE)
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
					if (this.getStateBoard().matrix[row - i][col + i] == 1) // neu la human
						eHuman++; // tang so quan human
					if (this.getStateBoard().matrix[row - i][col + i] == 2) // neu la PC
						ePC++; // tang so quan PC
				}
				if (eHuman * ePC == 0 && eHuman != ePC)
					for (int i = 0; i < 5; i++) {
						if (this.getStateBoard().matrix[row - i][col + i] == -1) { // neu o chua duoc danh
							if (eHuman == 0)
								if (nextPlayer == ConfigGame.PLAYER_TARGET.VALUE)
									cBoard.matrix[row - i][col + i] += DScore[ePC];
								else
									cBoard.matrix[row - i][col + i] += AScore[ePC];
							if (ePC == 0)
								if (nextPlayer == ConfigGame.COMPUTER_TARGET.VALUE)
									cBoard.matrix[row - i][col + i] += DScore[eHuman];
								else
									cBoard.matrix[row - i][col + i] += AScore[eHuman];
							if (eHuman == 4 || ePC == 4)
								cBoard.matrix[row - i][col + i] *= 2;
						}

					}
			}

/////////////////////////////////////////////////////////////////////////	
		return cBoard;
	}


	public Map<Node, Location> getMapPoints() {
		return mapPoints;
	}

}
