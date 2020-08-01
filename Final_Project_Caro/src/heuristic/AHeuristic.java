package heuristic;

import model.Board;
import project.caro.config.ConfigGame.Target;

public abstract class AHeuristic {
	public Board board;
	public Target target;
	public static int[][][][] ArrayScore = {
			{//Cho máy
				{//Cho dường thẳng
					{ 0, 8, 250, 880, 1458,0 },//Hai đầu đều không bị chặn
					{ 30, 4, 25, 580, 1058,0 },//Bị chặn một đầu
					{ 0, 0, 0, 0, 1500,0 }//Bị chặn hai đầu
					
				},{//Cho đường chéo
					{ 0, 4, 250, 880, 1458,0 },//Hai đầu đều không bị chặn
					{ 30, 50, 25, 580, 1258,0 },//Bị chặn một đầu
					{ 0, 0, 0, 0, 1500,0 }//Bị chặn hai đầu
				}
			},{//Tính điểm cho đối thủ
				{//Cho dường thẳng
					{ 0, -8, -300, -200, -1200,0 },//Hai đầu đều không bị chặn
					{ 25, 50, 800, -1550, -3000,0 },//Bị chặn một đầu
					{ 0, 0, 0, 95, 1458,0 }//Bị chặn hai đầu
				},{//Cho đường chéo
					{ 0, -4, -300, -1058, -1458,0 },//Hai đầu đều không bị chặn
					{ 25, 50, 750, -1550, -2300, 0 },//Bị chặn một đầu
					{ 0, 0, 0, 80, 1458, 0 }//Bị chặn hai đầu
				}
			}
			};
	
	public AHeuristic(Board board, Target target) {
		this.board=board;
		this.target=target;
	}
	public abstract long heuristic();

}
