package Algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Model.Board;
import project.caro.config.ConfigGame;

public class Agent {
	Node node;
	int depth;

	public Agent(int depth) {
		this.depth = depth;
	}

	public long MiniMax(Node initial, int depth, boolean isMaximizngPlayer) {
		if (depth == 0 || initial.isNoChildrens()) {
			int[] label = initial.getLabel();
//			System.out.println(initial.getTarget());
			initial.setValue(initial.getBoard().heuristicVer2(label[0], label[1], initial.getTarget()));
			return initial.getValue();
		}
		if (isMaximizngPlayer) {
			long maxEva = -999999999;
			initial.setTarget(ConfigGame.COMPUTER_TARGET);
			initial.setChildrens();
			for (Node child : initial.getChildrens()) {
				long eva = MiniMax(child, depth - 1, false);
				maxEva = Math.max(maxEva, eva);
				return maxEva;
			}
		} else {
			long minEva = 999999999;
			initial.setTarget(ConfigGame.PLAYER_TARGET);
			initial.setChildrens();			
			for (Node child : initial.getChildrens()) {
				long eva = MiniMax(child, depth - 1, true);
				minEva = Math.min(minEva, eva);
				return minEva;
			}

		}

		return 0;
	}

	public Node minimaxlevel1(Board initial, int depth, ConfigGame.Target target) {
		Node nodeinit = new Node();
		nodeinit.setBoard(initial);
		nodeinit.setTarget(target);
		nodeinit.setChildrens();
		long bestvalue = 0;
		List<Node> childrens = nodeinit.getChildrens();
		for (int i = 0; i < childrens.size(); i++) {
			Node pro = childrens.get(i);
			int[] label = pro.getLabel();
			pro.setValue(pro.getBoard().heuristicVer2(label[0], label[1], ConfigGame.COMPUTER_TARGET));
			childrens.set(i, pro);

		}
		for (int i = 0; i < childrens.size(); i++) {
			if (childrens.get(i).getValue() >= bestvalue) {
				bestvalue = childrens.get(i).getValue();
				nodeinit = childrens.get(i);
			}
		}

		return nodeinit;
	}

	public void setHeuristic() {

	}

	public void AlphaBeta() {

	}

	public int[] findBestMove(Board initial, int depth, ConfigGame.Target target) {
		int[] bestLabel = new int[2];
		long bestvalue = -999999999;
		for (int i = 0; i < initial.matrix.length; i++) {
			for (int j = 0; j < initial.matrix.length; j++) {
				if (initial.isValid(i, j) == true) {
					Node node = new Node();
					node.setTarget(target);
					initial.move(i, j, target);
					node.setBoard(initial);
					int[] label = new int[2];
					label[0] = i;
					label[1] = j;
					node.setLabel(label);
					long value = MiniMax(node, 4, true);
//					System.out.println(value);
					if (bestvalue < value) {
						bestvalue = value;
						bestLabel = node.getLabel();
					}

				}

			}
		}

		return bestLabel;
	}

	
}
