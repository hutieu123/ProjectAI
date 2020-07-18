package minimax.v2_NotAlpha_Beta.refer;

import java.util.Collections;
import java.util.List;

import minimax.core.refer.Agent;
import minimax.core.refer.Node;

public class MiniMaxSearchAlgo implements Agent {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		int v = maxValue(node);

		System.out.println("Value: " + v);
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- -∞
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MIN_VALUE;
		List<Node> children = node.getChildren();
		Collections.sort(children, Node.LabelComparator);
		for (Node child : children) {
			v = Math.max(v, minValue(child));
		}
		return v;
	}
	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- +∞
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v

	public int minValue(Node node) {
		if (node.isTerminal())
			return node.getValue();
		int v = Integer.MAX_VALUE;
		List<Node> children = node.getChildren();
		Collections.sort(children, Node.LabelComparator);
		for (Node child : children) {
			v = Math.min(v, maxValue(child));
		}
		return v;
	}
}
