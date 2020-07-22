package minimax.v3_alpha_beta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ANode;
import model.Agent;
import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.StatusMinimax;
import project.caro.config.ConfigGame.Target;

public class Minimax implements Agent {
	public Node initial=null;
	public int depth;
	public Minimax(int depth) {
		this.depth=depth;
	}
	
	@Override
	public int[] findBestMove(Board board, Target target, int depth) {
		boolean clean=true;
		Loop1:
		for (int i = 0; i < board.matrix.length; i++) {
			for (int j = 0; j < board.matrix[i].length; j++) {
				if(board.matrix[i][j]!=ConfigGame.Target.NOT_THING.VALUE) {
					clean=false;
					break Loop1;
				}
			}
		}
		if(clean)return new int[] {board.matrix.length/2, board.matrix[0].length/2};
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
		if(focus.parent.value==null) {
			focus.parent.value=focus.value;
		}
		while(focus.depth!=0) {
			Node nodeSameDepth=focus.initNodeSameDepth(focus.depth, focus.rowIndexBefore, focus.colIndexBefore);
			while(nodeSameDepth!=null) {
				if(nodeSameDepth.value==null) {
					focus=nodeSameDepth.initLeaf(this.depth);
					
				}else {
					focus = nodeSameDepth;
					System.out.println("Run");
				}
				if(focus.isMaxiumzing) {
					if(focus.parent.value==null) {
						focus.parent.value=focus.value;
					}
					if(focus.parent.value>focus.value) {
						focus.parent.value=focus.value;
					}
					else {
//						System.out.println("Cut");
						break;
					}
				}else {
					if(focus.parent.value==null) {
						focus.parent.value=focus.value;
					}
					if(focus.parent.value<focus.value) {
						focus.parent.value=focus.value;
					}
					else {
//						System.out.println("Cut");
						break;
					}
				}
				if(nodeSameDepth.value==null) {
					System.out.println("null");
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
