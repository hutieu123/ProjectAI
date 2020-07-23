package minimax;

import model.Board;
import project.caro.config.ConfigGame;

public class Test {
	public static void main(String[] args) {
		Minimax minimax= new Minimax(1);
		Board board = new Board(15, 15, 5);
//		board.matrix=new int[][]   {//8
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//0
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1, 1,-1, -1,-1,-1,-1,-1,-1},//5
//			{-1,-1,-1,-1,-1,-1,-1,-1, 1, -1,-1,-1,-1,-1,-1},//6
//			{-1,-1,-1,-1,-1,-1,-1,-1, -1, -1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//10
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}//14
//								//7
//		};
		board.matrix[5][5]=1;
		Node node = new Node(board, ConfigGame.Target.O);
//		Node child= new Node(ConfigGame.Target.O, new Location(4,10));
//		node.getNeighbours().add(child);
//		child.setParent(node);
//		System.out.println(minimax.minimax(child, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false));
//		
//		System.out.println(child.heuristic());
////		node.getNeighbours().get(0).getStateBoard().printMatrix();
////		System.out.println(node.getNeighbours().get(0).value);
//		for(Node n:node.getNeighbours()) System.out.println(n.heuristic());
////		//minimax.initial.
		int[] l=minimax.findBestMove(board,  ConfigGame.Target.O);
		System.out.println(l[0]+":"+l[1]);
	}

}
