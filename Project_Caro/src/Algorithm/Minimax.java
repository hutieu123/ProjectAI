package Algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Model.ANode;
import Model.Agent;
import Model.Board;
import project.caro.config.ConfigGame;

public class Minimax implements Agent {
//	Node node;
	int depth;

	public Minimax(int depth) {
		this.depth = depth;
	}

	public long MiniMax(Node initial, int depth, boolean isMaximizngPlayer) {
		if (depth == 0 || initial.isNoChildrens()) {
			int[] label = initial.getLabel();
//			System.out.println(initial.getTarget());
//			initial.setValue(initial.getBoard().heuristicVer2(label[0], label[1], initial.getTarget()));
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

	

	public int[] findBestMove(Board initial, ConfigGame.Target target, int depth) {
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
