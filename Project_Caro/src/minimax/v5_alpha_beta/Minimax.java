package minimax.v5_alpha_beta;

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
		computeNode(initial);
		Node goal = initial.findBestMove();
		System.out.println("Initial: "+goal.value);
		if(goal!=null)
		return new int[] {goal.rowIndexBefore, goal.colIndexBefore};
		return null;
	}
	public Node findBestNode(Board board, Target target, int depth) {
		this.initial=new Node(board, target, true, 0);
		computeNode(initial);
		Node goal = initial.findBestMove();
		return goal;
	}
	public Node computeNode(Node initial) {
		Node leaf=initial.initLeaf(this.depth);
		if(leaf.depth==initial.depth) {
			return leaf;
		}
//		leaf.board.printMatrix();
		leaf.parent.value = minmax(leaf.parent, leaf);
		Node tmp = leaf.parent;
		while(tmp.depth!=initial.depth) {
			tmp.parent.value =tmp.value;
			alphaBeta(tmp.parent, tmp.initNodeSameDepthSameParent(tmp.rowIndexBefore, tmp.colIndexBefore));
			tmp=tmp.parent;
		}
		return initial;
	}
	private void alphaBeta(Node parent, Node initNodeSameDepthSameParent) {
		while(initNodeSameDepthSameParent!=null) {
			if(initNodeSameDepthSameParent.board.isOver()) {
				Node leaf= computeNode(initNodeSameDepthSameParent);
				if(parent.isMaxiumzing) {
					if(parent.value<leaf.value) {
						parent.value=leaf.value;
					}else break;
				}
				else {
					if(parent.value>leaf.value) {
						parent.value=leaf.value;
					}else break;
				}
			}
			Target t =null;
			if(initNodeSameDepthSameParent.isMaxiumzing) {
				t=initNodeSameDepthSameParent.target;
			}else t=(initNodeSameDepthSameParent.target==ConfigGame.Target.O)?ConfigGame.Target.X:ConfigGame.Target.O;
			int[] lB=initNodeSameDepthSameParent.nextLocation(-1, -1);
			while(lB!=null){
				if(!initNodeSameDepthSameParent.board.isValid(lB[0], lB[1])) {
					lB= initNodeSameDepthSameParent.nextLocation(lB[0], lB[1]);
					continue;
				}
				Board boardTry=initNodeSameDepthSameParent.board.move(lB[0], lB[1], t);
//				if(boardTry==null) {
//					
//				}
				Node leaf = new Node(boardTry, t, !initNodeSameDepthSameParent.isMaxiumzing, lB[0],lB[1], initNodeSameDepthSameParent.depth+1);
				
				leaf.parent=initNodeSameDepthSameParent;
				initNodeSameDepthSameParent.getNeighbours().add(leaf);
				leaf=computeNode(leaf);
				if(parent.isMaxiumzing) {
					if(parent.value<leaf.value) {
						parent.value=leaf.value;
					}else break;
				}
				else {
					if(parent.value>leaf.value) {
						parent.value=leaf.value;
					}else break;
				}
			}
			initNodeSameDepthSameParent=initNodeSameDepthSameParent.initNodeSameDepthSameParent(initNodeSameDepthSameParent.rowIndexBefore, initNodeSameDepthSameParent.colIndexBefore);
			
		}
	}
	private void mi(Node parent, Node leaf){
		
	}

	private long minmax(Node leafParent, Node leaf) {
		long rs=0;
		rs=computeNode(leaf).value;
		if(leafParent.isMaxiumzing) {
			while((leaf=leaf.initNodeSameDepthSameParent(leaf.rowIndexBefore, leaf.colIndexBefore))!=null) {
				if(rs<computeNode(leaf).value) {
					rs=leaf.value;
				}
			}
		}else {
			while((leaf=leaf.initNodeSameDepthSameParent(leaf.rowIndexBefore, leaf.colIndexBefore))!=null) {
				if(rs>=computeNode(leaf).value) {
					rs=leaf.value;
				}
			}
		}
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
