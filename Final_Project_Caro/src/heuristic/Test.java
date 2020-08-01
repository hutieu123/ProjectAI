package heuristic;

import static org.junit.Assert.*;

import java.util.Arrays;

import model.Board;
import project.caro.config.ConfigGame;

public class Test {

	@org.junit.Test
	public void testH1() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,1,1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countH(0, 1);
		assertArrayEquals(new int[] {2}, array);
	}
	@org.junit.Test
	public void testH2() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{2,1,1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countH(0, 1);
		assertArrayEquals(new int[] {0,2}, array);
	}
	@org.junit.Test
	public void testH3() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{2,1,1,2,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countH(0, 1);
		
		assertArrayEquals(new int[] {0,0,2}, array);
	}
	@org.junit.Test
	public void testHIgnore() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{1,1,1,2,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countH(0, 1);
		
		assertArrayEquals(null, array);
	}
	@org.junit.Test
	public void testV1() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countV(1, 1);
		assertArrayEquals(new int[] {2}, array);
	}
	@org.junit.Test
	public void testV2() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,2,-1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countV(1, 1);
		assertArrayEquals(new int[] {0,2}, array);
	}
	@org.junit.Test
	public void testV3() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,2,-1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{-1,2,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countV(1, 1);
		assertArrayEquals(new int[] {0,0,2}, array);
	}
	@org.junit.Test
	public void testVIgnore() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,1,-1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{-1,2,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countV(1, 1);
		assertArrayEquals(null, array);
	}
	@org.junit.Test
	public void testBackSlash1() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,1,-1,-1,-1,-1},
			{-1,-1,-1,1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countBackSlash(1, 2);
		assertArrayEquals(new int[] {2}, array);
	}
	@org.junit.Test
	public void testBackSlash2() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,2,-1,-1,-1,-1,-1},
			{-1,-1,1,-1,-1,-1,-1},
			{-1,-1,-1,1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countBackSlash(1, 2);
		assertArrayEquals(new int[] {0,2}, array);
	}
	@org.junit.Test
	public void testBackSlash3() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,2,-1,-1,-1,-1,-1},
			{-1,-1,1,-1,-1,-1,-1},
			{-1,-1,-1,1,-1,-1,-1},
			{-1,-1,-1,-1,2,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countBackSlash(1, 2);
		assertArrayEquals(new int[] {0,0,2}, array);
	}
	@org.junit.Test
	public void testBackSlashIgnore() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,1,-1,-1,-1,-1,-1},
			{-1,-1,1,-1,-1,-1,-1},
			{-1,-1,-1,1,-1,-1,-1},
			{-1,-1,-1,-1,2,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countBackSlash(1, 2);
		assertArrayEquals(null, array);
	}
	@org.junit.Test
	public void testSlash1() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countSlash(5, 1);
		assertArrayEquals(new int[] {2}, array);
	}
	@org.junit.Test
	public void testSlash2() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{2,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countSlash(5, 1);
		assertArrayEquals(new int[] {0,2}, array);
	}
	@org.junit.Test
	public void testSlash3() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,2,-1,-1,-1},
			{-1,-1,1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{2,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countSlash(5, 1);
		assertArrayEquals(new int[] {0,0,2}, array);
	}
	@org.junit.Test
	public void testSlash4() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countSlash(6, 0);
		assertArrayEquals(new int[] {3}, array);
	}
	@org.junit.Test
	public void testSlash5() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{1,1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countSlash(0, 0);
		assertArrayEquals(new int[] {1}, array);
	}
	@org.junit.Test
	public void testSlashIgnore() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,2,-1,-1,-1},
			{-1,-1,1,-1,-1,-1,-1},
			{-1,1,-1,-1,-1,-1,-1},
			{1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[] array =aHeuristic.countSlash(5, 1);
		assertArrayEquals(null, array);
	}
	@org.junit.Test
	public void testArrayCount() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{2,2,1,1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[][][][] array =aHeuristic.arrayCountTarget();
		int[][][][] expect = new int[2][2][][];
		expect[0][0]= new int[][] {
			{2,0,0,0,0,0},
			{0,1,0,0,0,0},
			{0,0,0,0,0,0}
		};
		expect[0][1]= new int[][] {
			{4,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0}
		};
		expect[1][0]= new int[][] {
			{2,0,0,0,0,0},
			{0,1,0,0,0,0},
			{0,0,0,0,0,0}
		};
		expect[1][1]= new int[][] {
			{4,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0}
		};
		//System.out.println(Arrays.toString(array));
		assertArrayEquals(expect, array);
	}
	@org.junit.Test
	public void testArrayCount2() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{2,2,2,-1,-1,-1,-1},
			{1,1,1,1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		int[][][][] array =aHeuristic.arrayCountTarget();
		int[][][][] expect = new int[2][2][][];
		expect[0][0]= new int[][] {
			{1,0,0,1,0,0},
			{3,0,0,0,0,0},
			{0,0,0,0,0,0}
		};
		expect[0][1]= new int[][] {
			{3,0,0,0,0,0},
			{5,0,0,0,0,0},
			{0,0,0,0,0,0}
		};
		expect[1][0]= new int[][] {
			{0,0,1,0,0,0},
			{3,0,0,0,0,0},
			{0,0,0,0,0,0}
		};
		expect[1][1]= new int[][] {
			{1,0,0,0,0,0},
			{5,0,0,0,0,0},
			{0,0,0,0,0,0}
		};
		//System.out.println(Arrays.toString(array));
		assertArrayEquals(expect, array);
	}
	
	@org.junit.Test
	public void testHeuristic() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{2,2,2,1,-1,-1,-1},
			{1,1,1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		
		System.out.println(aHeuristic.heuristic());
	}
	@org.junit.Test
	public void testHeuristic2() {
		Board board = new Board(7, 7, 5);
		board.matrix=new int[][] {
			{2,2,1,1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1}
		};
		Heuristic aHeuristic= new Heuristic(board, ConfigGame.Target.X);
		
		//System.out.println(aHeuristic.heuristic());
	}

}
