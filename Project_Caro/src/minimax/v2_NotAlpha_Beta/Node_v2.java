package minimax.v2_NotAlpha_Beta;

import java.util.ArrayList;
import java.util.List;

import Model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.StatusMinimax;
import project.caro.config.ConfigGame.Target;

public class Node_v2 {
	public Board board;
	public int value;
	ConfigGame.Target target;
	List<Node_v2> neighbours;
	boolean isMaxiumzing;
	int rowIndexBefore=-1;
	int colIndexBefore=-1;
	//isMaxiumzing = true when turn on target move
	public Node_v2(Board board, ConfigGame.Target target, boolean isMaxiumzing) {
		this.target=target;
		this.board=board;
		this.isMaxiumzing=isMaxiumzing;
	}
	public Node_v2(Board boardTryMove, Target target, boolean isMaxiumzing, int row_Index, int col_Index) {
		this.target=target;
		this.board=boardTryMove;
		this.isMaxiumzing=isMaxiumzing;
		this.colIndexBefore=col_Index;
		this.rowIndexBefore=row_Index;
		this.value=Math.abs(this.board.matrix.length/2-col_Index)*4+
		Math.abs(this.board.matrix.length/2-row_Index)*4;
		if(!isMaxiumzing) {
			this.value=-this.value;
		}
	}
	public List<Node_v2> getNeighbours(){
		return this.neighbours;
	}
	public void initNeighbours() {
		StatusMinimax status = this.board.getCurrentStatusMinimax(target);
		switch (status) {
		case WIN_GAME:
		case LOSE_GAME:
		case STALEMATE:
			break;
		case NOT_OVER:
			this.neighbours=new ArrayList<Node_v2>();
			for (int row_Index = 0; row_Index < board.matrix.length; row_Index++) {
				for (int col_Index = 0; col_Index < board.matrix[row_Index].length; col_Index++) {
					if(board.matrix[row_Index][col_Index]==ConfigGame.Target.NOT_THING.VALUE) {
						Target t = null;
						if(isMaxiumzing) {
							t= target;
						}else {
							t=(this.target==Target.O)?Target.X:Target.O;
						}
						Board boardTryMove = board.move(row_Index, col_Index, t);
						if(boardTryMove!=null) {
							this.neighbours.add(new Node_v2(boardTryMove, target, !isMaxiumzing,row_Index, col_Index));
						}
					}
				}
			}
			
			break;
			default:
			break;
		}
	}
	@Override
	public String toString() {
		return board.toString()+"Value: "+this.value;
	}
	public Node_v2 findBestMove() {
		Node_v2 bestMove = null;
		if(this.neighbours!=null)
		for(Node_v2 n: this.neighbours) {
			if(bestMove==null) {
				bestMove=n;
				continue;
			}
			if(bestMove.value<n.value) {
				bestMove=n;
			}
		}
		return bestMove;
	}
	

}
