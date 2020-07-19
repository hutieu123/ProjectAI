package minimax.v3_alpha_beta;

import java.util.ArrayList;
import java.util.List;

import model.ANode;
import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.StatusMinimax;
import project.caro.config.ConfigGame.Target;

public class Node extends ANode {
	public long value;
	public Node parent;
	ConfigGame.Target target;
	List<Node> neighbours = new ArrayList<Node>();
	boolean isMaxiumzing;
	int rowIndexBefore=-1;
	int colIndexBefore=-1;
	int depth;
	//isMaxiumzing = true when turn on target move
	public Node(Board board, ConfigGame.Target target, boolean isMaxiumzing, int depth) {
		this.target=target;
		this.board=board;
		this.isMaxiumzing=isMaxiumzing;
		this.depth=depth;
	}
	public Node(Board boardTryMove, Target target, boolean isMaxiumzing, int row_Index, int col_Index, int depth) {
		this.target=target;
		this.board=boardTryMove;
		this.isMaxiumzing=isMaxiumzing;
		this.colIndexBefore=col_Index;
		this.rowIndexBefore=row_Index;
		this.depth=depth;
		this.value=Math.abs(this.board.matrix.length/2-col_Index)*4+
		Math.abs(this.board.matrix.length/2-row_Index)*4;
		if(!isMaxiumzing) {
			this.value=-this.value;
		}
	}
	public List<Node> getNeighbours(){
		return this.neighbours;
	}
	
	@Override
	public String toString() {
		return board.toString()+"Value: "+this.value;
	}
	public Node findBestMove() {
		Node bestMove = null;
		if(this.neighbours!=null)
		for(Node n: this.neighbours) {
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
	@Override
	public long heuristic(Target target) {
		int h=0;
		for (int rowIndex = 0; rowIndex < board.matrix.length; rowIndex++) {
			for (int colIndex = 0; colIndex <  board.matrix[rowIndex].length; colIndex++) {
				if( board.matrix[rowIndex][colIndex]==target.VALUE) {
					h+=50* board.count( board.matrix, new int[] {rowIndex,colIndex}, 2);
				}else
				if( board.matrix[rowIndex][colIndex]==ConfigGame.Target.NOT_THING.VALUE){}else {
					h-=49* board.count( board.matrix, new int[] {rowIndex,colIndex}, 2);
				}
			}
		}
		return h;
	}
	public Node initLeaf(int depthM) {
		StatusMinimax status = this.board.getCurrentStatusMinimax(target);
		switch (status) {
		case WIN_GAME:
			this.value=1000-depth;
			return this;
		case LOSE_GAME:
			this.value=-1000+depth;
			return this;
		case NOT_OVER:
			if(depthM==this.depth&&!this.board.isOver()) {
				this.value+=this.heuristic(target);
				return this;
			}
			int row_Index=-1;
			int col_Index=-1;
			Loop1:
			for (int i = 0; i < this.board.matrix.length; i++) {
				for (int j = 0; j < this.board.matrix[i].length; j++) {
					if(this.board.matrix[i][j]==ConfigGame.Target.NOT_THING.VALUE) {
						row_Index=i;
						col_Index=j;
						break Loop1;
					}
				}
			}
			Target t = null;
			if(isMaxiumzing) {
				t= target;
			}else {
				t=(this.target==Target.O)?Target.X:Target.O;
			}
			Board boardTryMove = board.move(row_Index, col_Index, t);
			if(boardTryMove!=null) {
				Node n=new Node(boardTryMove, target, !isMaxiumzing,row_Index, col_Index, this.depth+1);
				this.neighbours.add(n);
				n.parent=this;
//				System.out.println(n);
				return n.initLeaf(depthM);
			}
			break;
		default:
			break;
		}
		return null;
	}
	public int[] nextLocation(int depth) {
		Node n= this;
		while(n.depth<depth) {
			n =n.getNeighbours().get(n.getNeighbours().size()-1);
		}
		if(n.colIndexBefore==-1||n.rowIndexBefore==-1) {
			return new int[] {0,0};
		}
		int i=-1;
		int j=-1;
		if(n.colIndexBefore==n.board.matrix[n.rowIndexBefore].length-1) {
			j=0;
			if(n.rowIndexBefore==n.board.matrix.length-1) {
				return null;
			}else {
				i=n.rowIndexBefore+1;
			}
		}else if(n.colIndexBefore<n.board.matrix[n.rowIndexBefore].length-1){
			j=n.colIndexBefore+1;
			i=n.rowIndexBefore;
			
		}
		return new int[] {i,j};
	}
	public int[] nextLocation(int depth, int rowIndex, int colIndex) {
		Node n= this;
		while(n.depth<depth) {
			n =n.getNeighbours().get(n.getNeighbours().size()-1);
		}
		if(colIndex==-1||rowIndex==-1) {
			return new int[] {0,0};
		}
		int i=-1;
		int j=-1;
		if(colIndex==n.board.matrix[rowIndex].length-1) {
			j=0;
			if(rowIndex==n.board.matrix.length-1) {
				return null;
			}else {
				i=rowIndex+1;
			}
		}else if(colIndex<n.board.matrix[rowIndex].length-1){
			j=colIndex+1;
			i=rowIndex;
		}
		if(i==n.board.matrix.length||j==n.board.matrix[i].length)
			return null;
		return new int[] {i,j};
	}
	public Node initNodeSameDepth(int depth, int rowIndex, int colIndex) {
		Node n= this;
		while(n.depth<depth) {
			n =n.getNeighbours().get(n.getNeighbours().size()-1);
		}
		int[] l=this.nextLocation(depth, rowIndex, colIndex);
		if(l==null) {
			return null;
		}
		while(!n.board.isValid(l[0], l[1])) {
			l=n.nextLocation(depth, l[0]	, l[1]);
			if(l==null) {
				return null;
			}
		}
		Node childSameDepth=new Node(n.board.clone(), n.target, n.isMaxiumzing, l[0],l[1], n.depth);
		childSameDepth.board.matrix[n.rowIndexBefore][n.colIndexBefore]=ConfigGame.Target.NOT_THING.VALUE;
		
		Target t =null;
		if(!childSameDepth.isMaxiumzing) {
			t=childSameDepth.target;
		}else t=(childSameDepth.target==ConfigGame.Target.O)?ConfigGame.Target.X:ConfigGame.Target.O;
			childSameDepth.board.matrix[l[0]][l[1]]=t.VALUE;
		childSameDepth.parent=n.parent;
		n.parent.getNeighbours().add(childSameDepth);
		return childSameDepth;
	}
	public boolean isLeaf() {
		StatusMinimax status = this.board.getCurrentStatusMinimax(target);
		switch (status) {
		case WIN_GAME:
			return true;
		case LOSE_GAME:
			return true;
		case NOT_OVER:
			if(ConfigGame.DEPTH==this.depth&&!this.board.isOver()) {
				return true;
			}
			break;
		default:
			
			break;
		}
		return false;
	}
	

}
