package minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import minimax.v4_heuristic_notalpha.*;
import model.Agent;
import model.Board;
import project.caro.config.ConfigGame;

public class Minimax implements Agent{
	private Node lastNode;
	private int depth;
	public Minimax(int depth){
		this.lastNode = null;
		this.depth=depth;
	}
	public int minimax(Node node,  int alpha, int beta, int depth, boolean isMaxPlayer) {
		List<Node> listNeighbours = node.getNeighbours();
		if (depth == 0 || node.getStateBoard().isOver()) {
			return node.getStateBoard().getHeuristic();
		}
		if(isMaxPlayer){
			int bestVal = Integer.MIN_VALUE;
			for (Node i : listNeighbours) {
				int value = minimax(i, alpha, beta, depth-1, false);
				if(bestVal < value){
					bestVal = value;
					setLastNode(i);
				}
				if (bestVal >= beta || i.getStateBoard().isOver()) {
					return bestVal;
				}
				alpha = Math.max(alpha, bestVal);
			}
			return bestVal;
		}else{
			int bestVal = Integer.MAX_VALUE;
			for (Node i : listNeighbours) {
				int value = minimax(i, alpha, beta, depth-1, true);
				if(bestVal > value){
					bestVal = value;
					setLastNode(i);
				}
				if (bestVal <= alpha || i.getStateBoard().isOver()) {
					return bestVal;
				}
				beta = Math.min(beta, bestVal);
			}
			return bestVal;
		}
	}
	public int alpha_beta(Board state, int alpha, int beta, int depth, ConfigGame.Target target) {
		int result = 0;
		
		ConfigGame.Target nextPlayer;
		if(target == ConfigGame.Target.O)nextPlayer = ConfigGame.Target.X;
		else nextPlayer = ConfigGame.Target.O;
		
		Node node = new Node(state, nextPlayer);
		
		if (target == ConfigGame.COMPUTER_TARGET) {
			result = minimax(node, alpha, beta, depth, true);
		}else{
			result = minimax(node, alpha, beta, depth, false);
		}
		return result;
	}
	public int[] findBestMove(Board initial, ConfigGame.Target target) {
		int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;
		int bestVal = alpha_beta(initial,alpha, beta, depth,target);
		ConfigGame.Target nextPlayer;
		if(target == ConfigGame.Target.O)nextPlayer = ConfigGame.Target.X;
		else nextPlayer = ConfigGame.Target.O;
		
		Node node = new Node(initial, nextPlayer);
		node.getNeighbours();
		Location p = null;
		for (Node n : node.getMapPoints().keySet()) {
			if(n.getStateBoard().getHeuristic() == bestVal)
				p = node.getMapPoints().get(n);
		} 
		int[] result = {p.row,p.col};
		return result;
	}
	
	public Node getLastNode() {
		return lastNode;
	}
	public void setLastNode(Node lastNode) {
		this.lastNode = lastNode;
	}
	

}
