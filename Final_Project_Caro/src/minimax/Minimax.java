package minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Agent;
import model.Board;
import project.caro.config.ConfigGame;

public class Minimax implements Agent{
	
	private int depth;
	public Minimax(int depth){
		
		this.depth=depth;
	}
	public int minimax(Node node,  int alpha, int beta, int depth, boolean isMaxPlayer) {
		if (depth == 0 || node.getStateBoard().isOver()) {
			return node.value=node.heuristic();
		}
		List<Node> listNeighbours = node.initAddNeighbours();
		if(isMaxPlayer){
			int v = Integer.MIN_VALUE;
			for (Node i : listNeighbours) {
				int value = minimax(i, alpha, beta, depth-1, false);
				if(v < value){
					v = value;
					
					
				}
				if (v >= beta || i.getStateBoard().isOver()) {
					//Cut
					
					
					return v;
				}
				alpha = Math.max(alpha, v);
			}
			
			return v;
		}else{
			int bestVal = Integer.MAX_VALUE;
			for (Node i : listNeighbours) {
				int value = minimax(i, alpha, beta, depth-1, true);
				if(bestVal > value){
					bestVal = value;
					
					
				}
				if (bestVal <= alpha || i.getStateBoard().isOver()) {
					//node.value=bestVal;
					//Cut
					return bestVal;
				}
				beta = Math.min(beta, bestVal);
			}
//			node.value=bestVal;
			return bestVal;
		}
	}
	public int alpha_beta(Board state, int alpha, int beta, int depth, ConfigGame.Target target) {
		int result = 0;
		this.initial=new Node(state, target);
		if (target == ConfigGame.COMPUTER_TARGET) {
			result = minimax(this.initial, alpha, beta, depth, true);
		}else{
			result = minimax(this.initial, alpha, beta, depth, false);
		}
		return result;
	}
	Node initial;
	public int[] findBestMove(Board initial, ConfigGame.Target target) {
		int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;
		int bestVal = alpha_beta(initial,alpha, beta, this.depth,target);
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
		return result;
	}
	

}
