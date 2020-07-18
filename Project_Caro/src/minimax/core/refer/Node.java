package minimax.core.refer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private String label;
	private int value;
	private List<Node> children = new ArrayList<Node>();

	public Node(String label) {
		super();
		this.label = label;
	}

	public Node(String label, int value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public List<Node> getChildren() {
		return children;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void addChild(Node that) {
		this.children.add(that);
	}

	// check whether this node is terminal or not
	public boolean isTerminal() {
		return this.children.size() == 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node))
			return false;
		Node that = (Node) obj;
		return this.getLabel().equals(that.getLabel());
	}

	@Override
	public String toString() {
		return this.label;
	}

	// Defined comparator which is used for sorting children by alphabetical order
	public static Comparator<Node> LabelComparator = new Comparator<Node>() {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.getLabel().compareTo(o2.getLabel());
		}
	};
}
