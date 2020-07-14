package minimax;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import Model.Board;
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
		Node node=minimax.initial.getNeighbours().get(0);
		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
		node=minimax.initial.getNeighbours().get(1);
		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
		node=minimax.initial.getNeighbours().get(2);
		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
		node=minimax.initial.getNeighbours().get(3);
		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
		node=minimax.initial.getNeighbours().get(4);
		System.out.println(node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {2,1}, move);
	}

}
