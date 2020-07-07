package minimax;

import java.util.List;

import Model.Agent;
import Model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.StatusMinimax;
import project.caro.config.ConfigGame.Target;

public class Minimax implements Agent {
	public Minimax() {
	}
	public static void minimax(Node initial, boolean isMaximizingPlayer, ConfigGame.Target target, int depth) {
		StatusMinimax status = initial.board.getCurrentStatusMinimax(target);
//		System.out.println(initial);
//		System.out.println(status);
		switch (status) {
		case WIN_GAME:
			initial.value=10-depth;
			break;
		case LOSE_GAME:
			initial.value=-10+depth;
			break;
		case NOT_OVER:
			initial.initNeighbours();
			List<Node> neighbours = initial.getNeighbours();
			if(neighbours!=null) {
				for(Node n: neighbours) {
					minimax(n, !isMaximizingPlayer, target, depth+1);
				}
				
			}
			if(isMaximizingPlayer) {
				int max= Integer.MIN_VALUE;
				for(Node n: neighbours) {
					if(max<n.value) {
						max=n.value;
					}
				}
				initial.value=max;
			}else {
				int min= Integer.MAX_VALUE;
				for(Node n: neighbours) {
					if(min>n.value) {
						min=n.value;
					}
				}
				initial.value=min;
			}
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public int[] findBestMove(Board board, Target target, int depth) {
		Node initial = new Node(board, Target.X, true);
		minimax(initial, true, Target.X, depth);
		Node goal = initial.findBestMove();
		if(goal!=null)
		return new int[] {goal.rowIndexBefore, goal.colIndexBefore};
		return null;
	}
	
	@Override
	public List<Model.Node> MinMax() {
		return null;
	}
	@Override
	public void AlphaBeta() {
		
	}
	@Override
	public int heuristic() {
		return 0;
	}
	public static void main(String[] args) {
		Board board = new Board(3, 3);
		board.matrix[0][0]=1;
		board.matrix[0][2]=1;
		board.matrix[1][2]=1;
		board.matrix[0][1]=2;
		board.matrix[1][1]=2;
//		board.matrix[1][0]=2;
		Target turn= Target.O;
		Node initial = new Node(board, turn, true);
		minimax(initial, true, turn,0);
		System.out.println("Find Best Move: \n"+initial.findBestMove());
		System.out.println(initial.getNeighbours().get(1));
	}
}
