package Controller;


import Model.Board;
import View.SubSceneBoard;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class ControllerForTwoPlayer {

	SubSceneBoard subSceneBoard = null;
	int count=0;
	public void setSubSceneBoard(SubSceneBoard s) {
		subSceneBoard = s;
		count=subSceneBoard.getCount();
		addListenerMouseClick();
	}
	private void addListenerMouseClick() {
		subSceneBoard.getGroup().setPickOnBounds(true);
		//this.subScene.z
		subSceneBoard.getGroup().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			double x = e.getSceneX() - 50 - this.subSceneBoard.getSubScene().getLocalToSceneTransform().getTx();
			double y = e.getSceneY() - 50 - this.subSceneBoard.getSubScene().getLocalToSceneTransform().getTy();
			int row_index = (int) (y / 50);
			int col_index = (int) (x / 50);
			//System.out.println(col_index + ":" +row_index);
			count++;
			if (count % 2 == 1) {
				if (subSceneBoard.getBoard().matrix[row_index][col_index] != -1) {
					subSceneBoard.setCount((subSceneBoard.getCount())-1);
							count--;
				} else {
					Board boardTry = this.subSceneBoard.getBoard().move(row_index, col_index, 1);
					if(boardTry!=null) {
						subSceneBoard.setBoard(boardTry);
						subSceneBoard.paintX(subSceneBoard.getGroup(), row_index, col_index);
						System.out.println("Tới O đi");

					}

				}

			} else {
				if (subSceneBoard.getBoard().matrix[row_index][col_index] != -1) {
					count--;

				} else {
					Board boardTry =subSceneBoard.getBoard().move(row_index, col_index, 2);
					if(boardTry!=null) {
						subSceneBoard.setBoard(boardTry);
						subSceneBoard.paintO(subSceneBoard.getGroup(), row_index, col_index);
						System.out.println("Tới X đi");
					}
				}
			}
			int maxNumX=subSceneBoard.getBoard().check(1);
			int maxNumO=subSceneBoard.getBoard().check(2);
			if ( maxNumX!= -1) {
				System.out.println("X win");
				System.exit(0);
			} else if (maxNumO != -1) {
				System.out.println("O win");
				System.exit(0);
			}
		});
	}



}
