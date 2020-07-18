package Model;

import project.caro.config.ConfigGame;

public abstract class ANode {
	public Board board;
	public Board getBoard() {
		return board;
	}
	public ANode() {
	}
	public ANode(Board board) {
		super();
		this.board = board;
	}
	public abstract long heuristic(ConfigGame.Target target);
	
	
}
