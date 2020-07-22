package minimax.v1_3x3;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import model.Board;
import project.caro.config.ConfigGame.Target;

public class TestMinimax {
	@Test
	public void Test() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{1,2,1},
			{-1,2,1},
			{-1,-1,-1}
		};
		Target turn= Target.O;
		Minimax minimax= new Minimax();
		assertArrayEquals(new int[] {2,1}, minimax.findBestMove(board, turn, 0));
	}
	@Test
	public void Test2() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{1,2,1},
			{2,2,1},
			{-1,-1,-1}
		};
		Target turn= Target.X;
		Minimax minimax= new Minimax();
		assertArrayEquals(new int[] {2,2}, minimax.findBestMove(board, turn, 0));
	}
	@Test
	public void Test3() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{2,1,-1},
			{-1,1,-1},
			{-1,-1,-1}
		};
		Target turn= Target.O;
		Minimax minimax= new Minimax();
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		assertArrayEquals(new int[] {2,1}, move);
	}
	@Test
	public void Test4() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{-1,-1,-1},
			{-1,-1,-1},
			{-1,-1,-1}
		};
		Target turn= Target.X;
		Minimax minimax= new Minimax();
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node node = null;
		for(int i=0; i<9;i++) {
			node=minimax.initial.getNeighbours().get(i);
			//System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
			
		}
		assertArrayEquals(new int[] {1,1}, move);
	}
	@Test
	public void Test5() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{-1,-1,2},
			{-1,1,2},
			{1,-1,-1}
		};
		Target turn= Target.X;
		Minimax minimax= new Minimax();
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node node = null;
		for(int i=0; i<5;i++) {
			node=minimax.initial.getNeighbours().get(i);
//			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
			
		}
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {2,2}, move);
	}
	@Test
	public void Test6() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{-1,1,-1},
			{-1,1,2}
			,{-1,2,-1}};
		Target turn= Target.X;
		Minimax minimax= new Minimax();
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node node = null;
		for(int i=0; i<5;i++) {
			node=minimax.initial.getNeighbours().get(i);
//			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
			
		}
		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {2,0}, move);
	}

}
