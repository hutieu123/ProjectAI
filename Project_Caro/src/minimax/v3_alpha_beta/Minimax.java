package minimax.v3_alpha_beta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.ANode;
import model.Agent;
import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.StatusMinimax;
import project.caro.config.ConfigGame.Target;

public class Minimax implements Agent {
	public Node initial=null;
	public int depth;
	private long alpha=Long.MIN_VALUE;
	private long beta=Long.MAX_VALUE;
	public Minimax(int depth) {
		this.depth=depth;
	}
	
	@Override
	public int[] findBestMove(Board board, Target target, int depth) {
		this.initial=new Node(board, target, true, 0);
		run(initial);
		Node goal = initial.findBestMove();
		if(goal!=null)
		return new int[] {goal.rowIndexBefore, goal.colIndexBefore};
		return null;
	}
	public Node findBestNode(Board board, Target target, int depth) {
		this.initial=new Node(board, target, true, 0);
		run(initial);
		Node goal = initial.findBestMove();
		return goal;
	}

	public void run(Node initial) {
		this.initial = initial;
		Node focus=this.initial.initLeaf(this.depth);
		if(focus.isMaxiumzing) {
			if(this.beta>focus.value)
			this.beta = focus.value;
//			System.out.println(beta);
		}else {
			if(this.alpha<focus.value)
			this.alpha=focus.value;
		}
		while(focus.depth!=0) {
			Node nodeSameDepth=focus.initNodeSameDepth(focus.depth, focus.rowIndexBefore, focus.colIndexBefore);
			while(nodeSameDepth!=null) {
				focus=nodeSameDepth.initLeaf(this.depth);
				if(focus.isMaxiumzing) {
					if(this.beta>focus.value)
					this.beta = focus.value;else {
						break;
					}
//					System.out.println(beta);
				}else {
					if(this.alpha<focus.value)
					this.alpha=focus.value;else {
						break;
					}
				}
				nodeSameDepth=focus.initNodeSameDepth(focus.depth, focus.rowIndexBefore, focus.colIndexBefore);
			}
			focus=focus.parent;
		}
	}
	private long minmax(Node n) {
		
		return 0;
	}
	


//	public static void main(String[] args) {
//		Board board = new Board(3, 3, 3);
//		board.matrix=new int[][] {
//			{1,2,1},
//			{2,2,1},
//			{-1,-1,-1}
//		};
//		Target turn= Target.X;
//		Minimax minimax= new Minimax(4);
//		System.out.println("Find Best Move: \n"+Arrays.toString(minimax.findBestMove(board, turn, 0)));
////		Node_v2 node=minimax.initial.getNeighbours().get(1);
////		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
////		node=minimax.initial.getNeighbours().get(2);
////		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
////		node=minimax.initial.getNeighbours().get(3);
////		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
//	}
	
}
