package minimax.v3_alpha_beta.refer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import minimax.core.refer.Agent;
import minimax.core.refer.Node;

public class AlphaBetaSearchAlgo implements Agent {

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, -∞ , +∞)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		int v = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE);

		System.out.println("Value: " + v);
	}

	// function MAX-VALUE(state, alpha, beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- ∞
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s, alpha, beta))
	// if v ≥ beta then return v
	// alpha <- MAX(alpha, v)
	// return v

	public int maxValue(Node node, int alpha, int beta) {
//		System.out.println("current: " + node.getLabel() + " " + alpha + " " + beta);
		int v = 0;
		if (node.isTerminal())
			return node.getValue();
		v = Integer.MIN_VALUE;
		List<Node> children = node.getChildren();
		List<Node> tmp = new ArrayList<Node>();//
		tmp.addAll(children);
		Collections.sort(children, Node.LabelComparator);
		for (Node child : children) {
			v = Math.max(v, minValue(child, alpha, beta));
			tmp.remove(child);//
			if (v >= beta) {
				if (tmp.size() != 0) {
					System.out.println("Cut node: " + tmp);
				}
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		return v;
	}
	// function MIN-VALUE(state, alpha , beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- + ∞
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s, alpha , beta))
	// if v ≤ alpha then return v
	// beta <- MIN(beta ,v)
	// return v

	public int minValue(Node node, int alpha, int beta) {
//		System.out.println("current: " + node.getLabel() + " " + alpha + " " + beta);
		int v = 0;
		if (node.isTerminal())
			return node.getValue();
		v = Integer.MAX_VALUE;
		List<Node> children = node.getChildren();

		List<Node> tmp = new ArrayList<Node>();//
		tmp.addAll(children);
		Collections.sort(children, Node.LabelComparator);

		for (Node child : children) {
			v = Math.min(v, maxValue(child, alpha, beta));
			tmp.remove(child);//
			if (v <= alpha) {
				if (tmp.size() != 0) {
					System.out.println("Cut node: " + tmp);
				}
				return v;
			}
			beta = Math.min(beta, v);
		}
		return v;
	}


}
