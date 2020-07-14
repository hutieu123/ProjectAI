package minimax.v2_NotAlpha_Beta;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import Model.Board;
import project.caro.config.ConfigGame.Target;

public class TestMinimax_v2 {
	@Test
	public void Test5x5() {
		Board board = new Board(5, 5, 3);
		Target turn= Target.X;
		Minimax_v2 minimax= new Minimax_v2(2);
		int[] move =minimax.findBestMove(board, turn, 0);
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {2,2}, minimax.findBestMove(board, turn, 0));
	}
	@Test
	public void Test4x4() {
		Board board = new Board(4, 4, 3);
		Target turn= Target.X;
		Minimax_v2 minimax= new Minimax_v2(2);
		int[] move =minimax.findBestMove(board, turn, 0);
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {2,2}, minimax.findBestMove(board, turn, 0));
	}
//	@Test
//	public void Test7x7() {
//		Board board = new Board(7, 7, 5);
//		board.matrix=new int[][]   {//8
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//0
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//5
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//10
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
//			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}//14
//			
//		};
//		Target turn= Target.X;
////		System.out.println(board.check(turn));
//		Minimax_v2 minimax= new Minimax_v2();
//		int[] move =minimax.findBestMove(board, turn, 0);
//		System.out.println(Arrays.toString(move));
////		assertArrayEquals(new int[] {2,1}, minimax.findBestMove(board, turn, 0));
//	}
	@Test
	public void Test3x3() {
		Board board = new Board(3, 3, 3);
		Target turn= Target.X;
		Minimax_v2 minimax= new Minimax_v2(2);
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node_v2 node = null;
		for(int i=0; i<9;i++) {
			node=minimax.initial.getNeighbours().get(i);
//			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
		}
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {1,1}, move);
	}
	@Test
	public void Test3x3_2() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{-1,-1,2},
			{-1,1,2},
			{1,-1,-1}
		};
		Target turn= Target.X;
		Minimax_v2 minimax= new Minimax_v2(4);
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node_v2 node = null;
		for(int i=0; i<5;i++) {
			node=minimax.initial.getNeighbours().get(i);
//			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
			
		}
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {2,2}, move);
	}

}
