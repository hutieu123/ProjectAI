package minimax.v4_heuristic_notalpha;

import java.util.List;

import model.Board;
import project.caro.config.ConfigGame;


public interface Agent {
	public int[] findBestMove(Board board, ConfigGame.Target target, int depth);
	
}
