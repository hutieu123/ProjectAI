package project.caro.config;



public class ConfigGame {
	public static Target PLAYER_TARGET = Target.X;
	public static Target COMPUTER_TARGET = Target.O;
	public static final int NUMBER_WIN = 5;
	public static final int NUMBER_COLS = 15;
	public static final int NUMBER_ROWS = 15;
	public static final int TIME_TURN = 30000;
	
	public static final int DRAW = 40;
	public static final int TIME_OF_TURN = 30;
	public static final int DEPTH =3;
	public static enum Mode{TWO_PEOPLE, AGAIN_COMPUTER} 
	public static enum Target  {
		X(1), O(2), NOT_THING(-1);
		public Integer VALUE;
		private Target(int value) {
			this.VALUE=value;
		}
	};
	public static enum Status{X_WIN_GAME,X_LOSE_GAME,O_WIN_GAME,O_LOSE_GAME, NOT_OVER, STALEMATE};
	public static enum StatusMinimax{WIN_GAME, LOSE_GAME, NOT_OVER, STALEMATE};
	
}
