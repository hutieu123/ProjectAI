package project.caro;

import project.caro.config.ConfigGame.Target;
import project.caro.core.Board;

public interface ThuatToan {
	public int[] findBestMove(Board board, Target target, int depth);
}
