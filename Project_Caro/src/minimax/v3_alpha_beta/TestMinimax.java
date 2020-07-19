package minimax.v3_alpha_beta;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class TestMinimax {

//	@Test
//	public void Test3x3() {
//		Board board = new Board(3, 3, 3);
//		Target turn= Target.X;
//		Minimax minimax= new Minimax(1);
//		int[] move =minimax.findBestMove(board, turn, 0);
//		assertNotNull(move);
//		Node node = null;
//		for(int i=0; i<9;i++) {
//			node=minimax.initial.getNeighbours().get(i);
////			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
//		}
////		System.out.println(Arrays.toString(move));
//		assertArrayEquals(new int[] {1,1}, move);
//	}
//	@Test
//	public void Test3x3_2() {
//		Board board = new Board(3, 3, 3);
//		Node initial=new Node(board, ConfigGame.Target.X, true, 0);
//		Target turn= Target.X;
//		Node leaf=initial.initLeaf(2);
//		Minimax minimax= new Minimax(2);
//	}
	@Test
	public void Test3x3_3() {
		Board board = new Board(3, 3, 3);
		Target turn= Target.X;
		Minimax minimax= new Minimax(2);
		int[] move =minimax.findBestMove(board, turn, 0);
		assertNotNull(move);
		Node node = null;
		for(int i=0; i<9;i++) {
			node=minimax.initial.getNeighbours().get(i);
			System.out.println(i+":"+node.rowIndexBefore+":"+node.colIndexBefore+":"+node.value);
		}
//		System.out.println(Arrays.toString(move));
		assertArrayEquals(new int[] {1,1}, move);
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
