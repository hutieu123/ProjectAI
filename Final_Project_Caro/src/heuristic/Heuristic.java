package heuristic;

import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class Heuristic extends AHeuristic {

	public Heuristic(Board board, Target target) {
		super(board, target);
	}

	@Override
	public long heuristic() {
		int [][][][] arr=arrayCountTarget();
		long score=0;
		for (int index_Target = 0; index_Target < arr.length; index_Target++) {
			for (int index_Straige_Cross = 0; index_Straige_Cross < arr[index_Target].length; index_Straige_Cross++) {
				for (int index_Prevent_Hit = 0; index_Prevent_Hit < arr[index_Target][index_Straige_Cross].length; index_Prevent_Hit++) {
					for (int index_Number_Target = 0; index_Number_Target < arr[index_Target][index_Straige_Cross][index_Prevent_Hit].length; index_Number_Target++) {
						//Get value -> số đường
						score+=arr[index_Target][index_Straige_Cross][index_Prevent_Hit][index_Number_Target]*ArrayScore[index_Target][index_Straige_Cross][index_Prevent_Hit][index_Number_Target];
					}
				}
			}
		}
		return score;
	}
	
	public int [][][][] arrayCountTarget(){
		int[][][][] arrayScore = new int[2][2][3][6];//Me-Ememy|Straige-Cross|HitPrevent|NumberTarget
		for (int location_row = 0; location_row < this.board.matrix.length; location_row++) {
			for (int location_col = 0; location_col < this.board.matrix[location_row].length; location_col++) {
				if(this.board.matrix[location_row][location_col]==this.target.VALUE) {
					int[] arrH=countH(location_row, location_col);
					if(arrH!=null) {
						arrayScore[0][0][arrH.length-1][arrH[arrH.length-1]-1]++;
					}
					int[] arrV=countV(location_row, location_col);
					if(arrV!=null) {
						arrayScore[0][0][arrV.length-1][arrV[arrV.length-1]-1]++;
					}
					int[] arrBackSlash=countBackSlash(location_row, location_col);
					if(arrBackSlash!=null) {
						arrayScore[0][1][arrBackSlash.length-1][arrBackSlash[arrBackSlash.length-1]-1]++;
					}
					int[] arrSlash=countSlash(location_row, location_col);
					if(arrSlash!=null) {
						arrayScore[0][1][arrSlash.length-1][arrSlash[arrSlash.length-1]-1]++;
					}
					continue;
				}
				Target tmp=null;
				if(this.target==ConfigGame.Target.X) {
					tmp= Target.O;
				}else {tmp=Target.X;}
				if(this.board.matrix[location_row][location_col]==tmp.VALUE) {
					int[] arrH=countH(location_row, location_col);
					if(arrH!=null) {
						arrayScore[1][0][arrH.length-1][arrH[arrH.length-1]-1]++;
					}
					int[] arrV=countV(location_row, location_col);
					if(arrV!=null) {
						arrayScore[1][0][arrV.length-1][arrV[arrV.length-1]-1]++;
					}
					int[] arrBackSlash=countBackSlash(location_row, location_col);
					if(arrBackSlash!=null) {
						arrayScore[1][1][arrBackSlash.length-1][arrBackSlash[arrBackSlash.length-1]-1]++;
					}
					int[] arrSlash=countSlash(location_row, location_col);
					if(arrSlash!=null) {
						arrayScore[1][1][arrSlash.length-1][arrSlash[arrSlash.length-1]-1]++;
					}
					continue;
				}
			}
		}
		return arrayScore;
		
	}
	public int[] countH(int location_row, int location_col) {
		if (this.board.checkCorrect(location_row, location_col -1)&&this.board.matrix[location_row][location_col] ==this.board.matrix[location_row][location_col - 1]) {
			return null;
		}
		int count=1;
		// Đếm theo hàng ngang
		int i = location_col + 1;
		for (; i < this.board.matrix[location_row].length; i++) {
			if (this.board.matrix[location_row][location_col] == this.board.matrix[location_row][i]) {
				count++;
			} else {
				break;
			}

		}
		if ((!this.board.checkCorrect(location_row, location_col -1)|| this.board.matrix[location_row][location_col - 1] == -1 )
				&&( !this.board.checkCorrect(location_row, i) || this.board.matrix[location_row][i] == -1)) {
			return new int[] {count};
		} else if (this.board.checkCorrect(location_row, location_col -1)&&this.board.matrix[location_row][location_col] !=this.board.matrix[location_row][location_col - 1]&&this.board.matrix[location_row][location_col - 1]!=-1
				&& this.board.checkCorrect(location_row, i) && this.board.matrix[location_row][location_col] != this.board.matrix[location_row][i]&&this.board.matrix[location_row][i]!=-1) {
			return new int[] {0,0,count};
			
		} else if ((this.board.checkCorrect(location_row, location_col -1)&& this.board.matrix[location_row][location_col - 1] != this.board.matrix[location_row][location_col]) 
				|| (this.board.checkCorrect(location_row, i) && this.board.matrix[location_row][i] != this.board.matrix[location_row][location_col])) {
			return new int[] {0,count};
		}
		return null;
	}
	public int[] countV(int location_row, int location_col) {
		if (this.board.checkCorrect(location_row -1, location_col)&&this.board.matrix[location_row][location_col] ==this.board.matrix[location_row-1][location_col]) {
			return null;
		}
		int count=1;
		// Đếm theo hàng dọc
		int i = location_row + 1;
		for (; i < this.board.matrix[location_row].length; i++) {
			if (this.board.matrix[location_row][location_col] == this.board.matrix[i][location_col]) {
				count++;
			} else {
				break;
			}
			
		}
		if ((!this.board.checkCorrect(location_row-1, location_col)|| this.board.matrix[location_row-1][location_col] == -1 )
				&&( !this.board.checkCorrect(i, location_col) || this.board.matrix[i][location_col] == -1)) {
			return new int[] {count};
		} else if (this.board.checkCorrect(location_row-1, location_col)&&this.board.matrix[location_row][location_col] !=this.board.matrix[location_row-1][location_col]&&this.board.matrix[location_row -1][location_col]!=-1
				&& this.board.checkCorrect(i, location_col) && this.board.matrix[location_row][location_col] != this.board.matrix[i][location_col]&&this.board.matrix[i][location_col]!=-1) {
			return new int[] {0,0,count};
			
		} else if ((this.board.checkCorrect(location_row -1 , location_col)&& this.board.matrix[location_row -1 ][location_col] != this.board.matrix[location_row][location_col]) 
				|| (this.board.checkCorrect(i, location_col) && this.board.matrix[i][location_col] != this.board.matrix[location_row][location_col])) {
			return new int[] {0,count};
		}
		return null;
	}
	public int[] countBackSlash(int location_row, int location_col) {
		if (this.board.checkCorrect(location_row -1, location_col-1)&&this.board.matrix[location_row][location_col] ==this.board.matrix[location_row-1][location_col-1]) {
			return null;
		}
		int count=1;
		// Đếm theo dường chéo nghịch
		int i = 1;
		for (; i < this.board.matrix.length - location_col && i < this.board.matrix.length - location_row; i++) {
			if (this.board.matrix[location_row][location_col] == this.board.matrix[location_row + i][location_col + i]) {
				count++;
			} else {
				break;
			}
			
		}
		if ((!this.board.checkCorrect(location_row-1, location_col-1)|| this.board.matrix[location_row-1][location_col-1] == -1 )
				&&( !this.board.checkCorrect(location_row+i, location_col+i) || this.board.matrix[location_row+i][location_col+i] == -1)) {
			return new int[] {count};
		} else if (this.board.checkCorrect(location_row-1, location_col-1)&&this.board.matrix[location_row][location_col] !=this.board.matrix[location_row-1][location_col-1]&&this.board.matrix[location_row -1][location_col-1]!=-1
				&& this.board.checkCorrect(location_row+i, location_col+i) && this.board.matrix[location_row][location_col] != this.board.matrix[location_row+i][location_col+i]&&this.board.matrix[location_row+i][location_col+i]!=-1) {
			return new int[] {0,0,count};			
		} else if ((this.board.checkCorrect(location_row -1 , location_col-1)&& this.board.matrix[location_row -1 ][location_col-1] != this.board.matrix[location_row][location_col]) 
				|| (this.board.checkCorrect(location_row+i, location_col+i) && this.board.matrix[location_row+i][location_col+i] != this.board.matrix[location_row][location_col])) {
			return new int[] {0,count};
		}
		return null;
	}
	public int[] countSlash(int location_row, int location_col) {
		if (this.board.checkCorrect(location_row +1, location_col-1)&&this.board.matrix[location_row][location_col] ==this.board.matrix[location_row+1][location_col-1]) {
			return null;
		}
		int count=1;
		// Đếm theo dường chéo thuận
		int i = 1;
		for (; i < this.board.matrix.length - location_col &&location_row-i>=0; i++) {
			//System.out.println(i);
			if (this.board.matrix[location_row][location_col] == this.board.matrix[location_row - i][location_col + i]) {
				count++;
			} else {
				break;
			}
			
		}
		if ((!this.board.checkCorrect(location_row+1, location_col-1)|| this.board.matrix[location_row+1][location_col-1] == -1 )
				&&( !this.board.checkCorrect(location_row-i, location_col+i) || this.board.matrix[location_row-i][location_col+i] == -1)) {
			return new int[] {count};
		} else if (this.board.checkCorrect(location_row+1, location_col-1)&&this.board.matrix[location_row][location_col] !=this.board.matrix[location_row+1][location_col-1]&&this.board.matrix[location_row +1][location_col-1]!=-1
				&& this.board.checkCorrect(location_row-i, location_col+i) && this.board.matrix[location_row][location_col] != this.board.matrix[location_row-i][location_col+i]&&this.board.matrix[location_row-i][location_col+i]!=-1) {
			return new int[] {0,0,count};
			
		} else if ((this.board.checkCorrect(location_row +1 , location_col-1)&& this.board.matrix[location_row +1 ][location_col-1] != this.board.matrix[location_row][location_col]) 
				|| (this.board.checkCorrect(location_row-i, location_col+i) && this.board.matrix[location_row-i][location_col+i] != this.board.matrix[location_row][location_col])) {
			return new int[] {0,count};
		}
		return null;
	}
	

}
