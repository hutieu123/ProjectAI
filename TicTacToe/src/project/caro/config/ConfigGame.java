package project.caro.config;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigGame {
	public static final int DRAW = 50;

	public static final int NULL_VALUE = -1;
	public enum Target{X,O};
	public static final int NUMBER_WIN = 3;
	public static final int NUMBER_COLS = 3;
	public static final int NUMBER_ROWS = 3;
	public static final int DEPTH = 1;
	public enum Status{X_WIN_GAME,X_LOSE_GAME,O_WIN_GAME,O_LOSE_GAME, NOT_OVER, STALEMATE};
	public enum StatusMinimax{WIN_GAME, LOSE_GAME, NOT_OVER, STALEMATE};
	public static Map<Target, Integer> values= Stream.of(new Object[][] {{Target.O,2},{Target.X,1}}).collect(Collectors.toMap(data->(Target)data[0], data->(Integer)data[1]));
}
