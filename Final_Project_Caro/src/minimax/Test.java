package minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import project.caro.config.*;

public class Test {

	public static void main(String[] args) {
		Board board = new Board(15, 15, 5);
		board.matrix=new int[][]   {//8
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//0
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1,-1,-1,-1,-1},//5
			{-1,-1,-1,-1,-1,-1,-1,-1, 1, 1,-1,-1,-1,-1,-1},//6
			{-1,-1,-1,-1,-1,-1,-1,-1, 2, 1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//10
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}//14
								//7
		};
		Node node = new Node(board, ConfigGame.Target.X);
		
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				Point point = new Point(i,j);
				System.out.print("  ("+i+","+j+"):"+node.heuristic(board, point, 2));
			}
			System.out.println();
		}
		
//		node.getNeighbours();	//	kiem tra map point
//		for (Integer i : node.mapPoints.keySet()) {
//			Point p = node.mapPoints.get(i);
//			System.out.print("("+p.x+","+p.y+"):" +i+"  ");
//		}
		
		
//		int count=0; //kiem tra bang con co day du khong
//		for (Node i : node.getNeighbours()) {
//			if(i.getStateBoard().getHeuristic() == 46 )
//			System.out.print((count+1)+"():" +i.getStateBoard().getHeuristic()+"  ");
//			if(count%14==0)System.out.println();
//			count++;
//		}System.out.println(count+":"+(15*15-2));
	
		Minimax mim = new Minimax(0);//kiem tra minimax va ham findBestMove
		node.getNeighbours();
		System.out.println(mim.minimax(node,-99999999,999999999, 1, true));
		int e[] = mim.findBestMove(board, ConfigGame.COMPUTER_TARGET);
		System.out.println(e[0]+","+e[1]);
		
		
//		for (int i = 0; i < 15; i++) {
//			for (int j = 0; j < 15; j++) {
//				System.out.print(node.getNeighbours().get(0).getStateBoard().matrix[i][j]+"  ");
//			}System.out.println();
//		}
	}

}
