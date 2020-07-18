package minimax.v2_NotAlpha_Beta;

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
	public Minimax(int depth) {
		this.depth=depth;
	}
	public void minimax(Node initial, boolean isMaximizingPlayer, ConfigGame.Target target, int depth) {
		StatusMinimax status = initial.board.getCurrentStatusMinimax(target);
		switch (status) {
		case WIN_GAME:
			initial.value=1000-depth;
			break;
		case LOSE_GAME:
			initial.value=-1000+depth;
			break;
		case NOT_OVER:
			if(depth==this.depth&&!initial.board.isOver()) {
				initial.value+=initial.heuristic(target);
				break;
			}
			initial.initNeighbours();
			List<Node> neighbours = initial.getNeighbours();
			if(neighbours!=null) {
				for(Node n: neighbours) {
					minimax(n, !isMaximizingPlayer, target, depth+1);
				}
				
			}
			//
			if(isMaximizingPlayer) {
				int max= Integer.MIN_VALUE;
				for(Node n: neighbours) {
					if(max<n.value) {
						max=n.value;
					}
				}
				initial.value+=max;
			}else {
				int min= Integer.MAX_VALUE;
				for(Node n: neighbours) {
					if(min>n.value) {
						min=n.value;
					}
				}
				initial.value+=min;
			}
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public int[] findBestMove(Board board, Target target, int depth) {
		this.initial=new Node(board, target, true);
		minimax(initial, true, target, depth);
		Node goal = initial.findBestMove();
		if(goal!=null)
		return new int[] {goal.rowIndexBefore, goal.colIndexBefore};
		return null;
	}
	

	public static void main(String[] args) {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{1,2,1},
			{2,2,1},
			{-1,-1,-1}
		};
		Target turn= Target.X;
		Minimax minimax= new Minimax(4);
		System.out.println("Find Best Move: \n"+Arrays.toString(minimax.findBestMove(board, turn, 0)));
//		Node_v2 node=minimax.initial.getNeighbours().get(1);
//		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
//		node=minimax.initial.getNeighbours().get(2);
//		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
//		node=minimax.initial.getNeighbours().get(3);
//		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
	}
	
}
