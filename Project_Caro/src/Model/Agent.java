package Model;

import java.util.List;

import project.caro.config.ConfigGame;


public interface Agent {
	public int[] findBestMove(Board board, ConfigGame.Target target, int depth);

	public List<Node> MinMax() ;

	public void AlphaBeta();
	public int heuristic();
}
