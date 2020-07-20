package Algorithm;

import java.util.ArrayList;
import java.util.List;

import Model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class Node {
	private Board board;
	private int[] label;
	private long value;
	private Node parent;
	private List<Node> childrens;
	private ConfigGame.Target target;
	
	public Node(Board board, long value, List<Node> childrens,ConfigGame.Target target) {
		this.board = board;
		this.value = value;
		this.childrens = childrens;
		this.target=target;
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

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
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
					Board newmove= board.move(i, j, target);
					node.setBoard(newmove);
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

	public void setTarget2(Target target2) {
		this.target=target2;
		
	}

	
	

}
