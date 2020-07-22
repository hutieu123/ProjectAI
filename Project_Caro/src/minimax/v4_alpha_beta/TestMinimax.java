package minimax.v4_alpha_beta;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class TestMinimax {

	@Test
	public void Test3x3() {
		Board board = new Board(3, 3, 3);
		Target turn= Target.X;
		Minimax minimax= new Minimax(3);
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node node = null;
//		System.out.println(minimax.alpha);
//		System.out.println(minimax.beta);
	
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {1,1}, move);
	}
	@Test
	public void Test3x3_2() {
		Board board = new Board(3, 3, 3);
		Node initial=new Node(board, ConfigGame.Target.X, true, 0);
		Target turn= Target.X;
		Node leaf=initial.initLeaf(2);
		Minimax minimax= new Minimax(2);
	}
	@Test
	public void Test3x3_3() {
		Board board = new Board(3, 3, 3);
		Target turn= Target.X;
		Minimax minimax= new Minimax(2);
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node node = null;
		
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {1,1}, move);
	}
	@Test
	public void Test3x3_4() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
				{-1,-1,1},
				{1,1,2}
				,{-1,2,2}};
		Target turn= Target.X;
		Minimax minimax= new Minimax(3);
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node node = null;
		for(int i=0; i<3;i++) {
			node=minimax.initial.getNeighbours().get(i);
//			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
//			for(int j=0; i<2;i++) {
//				
//				Node node1=node.getNeighbours().get(j);
//				System.out.println(j+":"+node1.rowIndexBefore+":"+node1.colIndexBefore+":"+node1.value);
//			}
		}
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {2,0}, move);
	}
	@Test
	public void Test3x3_7() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{-1,1,-1},
			{-1,1,2}
			,{-1,2,-1}};
			Target turn= Target.X;
			Minimax minimax= new Minimax(5);
			int[] move =minimax.findBestMove(board, turn, 0);
			assertNotNull(move);
			Node node = null;
			for(int i=0; i<5;i++) {
				node=minimax.initial.getNeighbours().get(i);
				System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
			}
			System.out.println("---------");
			for(int j=0; j<3;j++) {
				
				Node node1=node.getNeighbours().get(3).getNeighbours().get(j);
				System.out.println(j+":"+node1.rowIndexBefore+":"+node1.colIndexBefore+":"+node1.value);
			}
		System.out.println(Arrays.toString(move));
			assertArrayEquals(new int[] {2,0}, move);
	}
	@Test
	public void Test3x3_5() {
		Board board = new Board(3, 3, 3);
		board.matrix=new int[][] {
			{-1,1,-1},
			{-1,1,-1}
			,{-1,2,2}};
		Target turn= Target.X;
		Minimax minimax= new Minimax(4);
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node node = null;
//		System.out.println(minimax.alpha);
//		System.out.println(minimax.beta);
		for(int i=0; i<5;i++) {
			node=minimax.initial.getNeighbours().get(i);
//			node=minimax.initial.getNeighbours().get(2).getNeighbours().get(i);
//			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
		}
//		System.out.println("-----------");
		for(int i=0; i<4;i++) {
//			node=minimax.initial.getNeighbours().get(i);
			node=minimax.initial.getNeighbours().get(1).getNeighbours().get(i);
//			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
		}
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {2,0}, move);
	}

//	@Test
//	public void Test3x3_4() {
//		Board board = new Board(15, 15, 5);
//		Target turn= Target.X;
//		Minimax minimax= new Minimax(2);
//		int[] move =minimax.findBestMove(board, turn, 0);
//		assertNotNull(move);
//		Node node = null;
//		for(int i=0; i<25;i++) {
//			node=minimax.initial.getNeighbours().get(i);
////			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
//		}
////		System.out.println(Arrays.toString(move));
//		assertArrayEquals(new int[] {7,7}, move);
//	}


}
