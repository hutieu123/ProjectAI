package Model;

import java.util.ArrayList;
import java.util.List;

import project.caro.config.ConfigGame;

public class Node {
	private Board board;
	private int value;
	private List<Node> childrens;

	public Node(Board board, int value, List<Node> childrens) {

		this.board = board;
		this.value = value;
		this.childrens = childrens;
	}

	public Node() {
		super();

		childrens = new ArrayList<Node>();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Node> getChildrens(ConfigGame.Target target) {
		List<Node> childrens = new ArrayList<Node>();
		for (int i = 0; i < board.matrix.length; i++) {
			for (int j = 0; j < board.matrix.length; j++) {
				Node node = new Node();
				Board board = node.getBoard();
				if (board.isValid(i, j) == true) {
					board.move(i, j, target);
					node.setBoard(board);
					node.setValue(board.heuristic(target));
					childrens.add(node);
				}

			}
		}
		return childrens;
	}

	public void setChildrens(List<Node> childrens) {
		this.childrens = childrens;
	}

}
