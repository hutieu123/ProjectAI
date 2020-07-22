package minimax.v5_alpha_beta;

import java.util.Arrays;

import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class MainTest {
	public static void main(String[] args) {
		Board board = new Board(3, 3, 3);
		board.matrix = new int[][] { { -1, 1, -1 }, { -1, 1, 2 }, { -1, 2, -1 } };
		Target turn = Target.X;
		Minimax minimax = new Minimax(5);
		int[] move = minimax.findBestMove(board, turn, 0);
		
		Node node = null;
		for (int i = 0; i < 5; i++) {
			node = minimax.initial.getNeighbours().get(i);
			System.out.println(i + ":" + node.rowIndexBefore + ":" + node.colIndexBefore + ":" + node.value);
		}
		System.out.println("---------");
		for (int j = 0; j < 4; j++) {

			node = minimax.initial.getNeighbours().get(3).getNeighbours().get(j);
			System.out.println(j + ":" + node.rowIndexBefore + ":" + node.colIndexBefore + ":" + node.value);
		}
		System.out.println("---------");
		for (int j = 0; j < 3; j++) {

			node = minimax.initial.getNeighbours().get(3).getNeighbours().get(1).getNeighbours().get(j);
			System.out.println(j + ":" + node.rowIndexBefore + ":" + node.colIndexBefore + ":" + node.value);
		}
		System.out.println("---------");
		for (int j = 0; j < 2; j++) {

			node = minimax.initial.getNeighbours().get(3).getNeighbours().get(1).getNeighbours().get(2).getNeighbours()
					.get(j);
			System.out.println(j + ":" + node.rowIndexBefore + ":" + node.colIndexBefore + ":" + node.value);
			node.board.printMatrix();
		}
		System.out.println("---------");
		for (int j = 0; j < 1; j++) {

			node = minimax.initial.getNeighbours().get(3).getNeighbours().get(1).getNeighbours().get(2).getNeighbours()
					.get(1).getNeighbours().get(j);
			System.out.println(j + ":" + node.rowIndexBefore + ":" + node.colIndexBefore + ":" + node.value);
			node.board.printMatrix();
			System.out.println(node.board.getCurrentStatus(ConfigGame.Target.X));
		}
		System.out.println(Arrays.toString(move));
	}

}
