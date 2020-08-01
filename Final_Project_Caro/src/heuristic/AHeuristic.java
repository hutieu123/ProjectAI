package heuristic;

import model.Board;
import project.caro.config.ConfigGame.Target;

public abstract class AHeuristic {
	public Board board;
	public Target target;
	public static int[][][][] ArrayScore = {
			{//Cho chính mình
				{//Cho dường thẳng
					
					{ 0, 4, 27, 256, 1458,0 },//Hai đầu đều không bị chặn
					{ 0, 4, 27, 256, 1458,0 },//Bị chặn một đầu
					{ 0, 4, 27, 256, 1458,0 }//Bị chặn hai đầu
					
				},{//Cho đường chéo
					{ 0, 4, 27, 256, 1458,0 },//Hai đầu đều không bị chặn
					{ 0, 4, 27, 256, 1458,0 },//Bị chặn một đầu
					{ 0, 4, 27, 256, 1458,0 }//Bị chặn hai đầu
				}
			},{//Tính điểm cho đối thủ
				{//Cho dường thẳng
					
					{ 0, -3, -20, -200, -1200,0 },//Hai đầu đều không bị chặn
					{ 15, 2, 15, 150, 1200,0 },//Bị chặn một đầu
					{ 0, 4, 27, 256, 1458,0 }//Bị chặn hai đầu
					
				},{//Cho đường chéo
					{ 0, -4, -27, -256, -1458,0 },//Hai đầu đều không bị chặn
					{ 0, 2, 15, 150, 1200,0 },//Bị chặn một đầu
					{ 0, 4, 27, 256, 1458,0 }//Bị chặn hai đầu
				}
			}
			};
	
	public AHeuristic(Board board, Target target) {
		this.board=board;
		this.target=target;
	}
	public abstract long heuristic();

}
