package minimax;

import java.util.*;

import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class Node {
	
	private Board stateBoard;
	private List<Node> neighbours;
	private ConfigGame.Target nextTarget;
	
	private Node parent;

	private static Random rd = new Random();
	
	private Location justHit;
	public long value;
	public Location getJustHit() {
		return justHit;
	}
	public void setJustHit(Location justHit) {
		this.justHit = justHit;
	}


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
	

	
}
