package project.caro.config;



public class ConfigGame {
	
	public static final int NUMBER_WIN = 5;
	public static final int NUMBER_COLS = 15;
	public static final int NUMBER_ROWS = 15;
	
	public static final int DRAW = 50;
	public static final int TIME_OF_TURN = 10;
	public static final int DEPTH = 1;
	public enum Target  {
		X(1), O(2), NOT_THING(-1);
		public Integer VALUE;
		private Target(int value) {
			this.VALUE=value;
		}
	};
	public enum Status{X_WIN_GAME,X_LOSE_GAME,O_WIN_GAME,O_LOSE_GAME, NOT_OVER, STALEMATE};
	public enum StatusMinimax{WIN_GAME, LOSE_GAME, NOT_OVER, STALEMATE};
	
}
