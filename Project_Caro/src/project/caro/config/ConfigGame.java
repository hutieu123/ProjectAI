package project.caro.config;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigGame {
	public static final int DRAW = 50;
	
	
	public static final int NUMBER_WIN = 5;
	public static final int NUMBER_COLS = 15;
	public static final int NUMBER_ROWS = 15;
	public static final int TIME_OF_TURN = 10;
	
	
	public static final int DEPTH = 1;
	public enum Status{X_WIN_GAME,X_LOSE_GAME,O_WIN_GAME,O_LOSE_GAME, NOT_OVER, STALEMATE};
	public enum StatusMinimax{WIN_GAME, LOSE_GAME, NOT_OVER, STALEMATE};
	public static class X {
		public static final int VALUE = 1;
	}
	public static class O{
		public static final int VALUE = 2;
	}
	public static class NOT_THING{
		public static final int VALUE = -1;
	}
}
