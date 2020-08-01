package minimax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import heuristic.AHeuristic;
import heuristic.Heuristic;
import model.Agent;
import model.Board;
import project.caro.config.ConfigGame;

public class Minimax implements Agent{
	private AHeuristic heuristic;
	private int depth;
	public Minimax(int depth){
		this.depth=depth;
	}
	public long minimax(Node node,  long alpha, long beta, int depth, boolean isMaxPlayer, ConfigGame.Target target) {
		if (depth == 0 || node.getStateBoard().isOver()) {
			return node.value=new Heuristic(node.getStateBoard(), target).heuristic();
		}
		List<Node> listNeighbours = node.initAddNeighbours();
		if(isMaxPlayer){
			long v = Long.MIN_VALUE;
			for (Node i : listNeighbours) {
				long value = minimax(i, alpha, beta, depth-1, false, target);
				if(v < value){
					v = value;
					
				}
				if (v >= beta || i.getStateBoard().isOver()) {
					//Cut
					
					
					return node.value=v;
				}
				alpha = Math.max(alpha, v);
			}
			
			return node.value=v;
		}else{
			long bestVal = Long.MAX_VALUE;
			for (Node i : listNeighbours) {
				long value = minimax(i, alpha, beta, depth-1, true, target);
				if(bestVal > value){
					bestVal = value;
					
					
				}
				if (bestVal <= alpha || i.getStateBoard().isOver()) {
					//node.value=bestVal;
					//Cut
					return node.value=bestVal;
				}
				beta = Math.min(beta, bestVal);
			}
//			node.value=bestVal;
			return node.value=bestVal;
		}
	}
	public long alpha_beta(Board state, long alpha, long beta, int depth, ConfigGame.Target target) {
		long result = 0;
		this.initial=new Node(state, target);
		if (target == ConfigGame.COMPUTER_TARGET) {
			result = minimax(this.initial, alpha, beta, depth, true, target);
		}else{
			result = minimax(this.initial, alpha, beta, depth, false, target);
		}
		return result;
	}
	Node initial;
	public int[] findBestMove(Board initial, ConfigGame.Target target) {
		long alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;
		long bestVal = alpha_beta(initial,alpha, beta, this.depth,target);
		this.initial.getNeighbours();
		Location p = null;
		int countCanHitEqualScore=0;
		for (Node n : this.initial.getNeighbours()) {
			if(n.value == bestVal) {
				p = n.getJustHit();
				countCanHitEqualScore++;
			}
		} 
		if(countCanHitEqualScore>1) {
			System.out.println("Can hit "+countCanHitEqualScore+" location");
		}
		int[] result = {p.row,p.col};
		System.out.println("Best value: "+bestVal);
		System.out.println(Arrays.toString(result));
		return result;
	}
	

}
