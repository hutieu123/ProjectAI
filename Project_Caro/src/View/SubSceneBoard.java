package View;

import Model.Board;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class SubSceneBoard {
	int count = 0;
	Group group = new Group();
	Board board = null;
	SubScene subScene;
	public SubSceneBoard(Board board) {
		this.board=board;
		this.subScene=DrawBoard.createSubScene(board, getGroup());
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setSubScene(SubScene subScene) {
		this.subScene = subScene;
	}

	public SubScene getSubScene() {
		return subScene;
	}
	public Group getGroup() {
		return group;
	}
	public boolean move(int rows, int cols, int target){
		return this.getBoard().move(rows, cols, target)!=null;
	}

	public void paintX(Group group,int row_index, int col_index) {
		Group x = new Group();
		Line line1 = new Line(0, 0, 50, 50);
		Line line2 = new Line(50, 0, 0, 50);
		line1.setStroke(Color.ORANGERED);
		line1.setStrokeWidth(5.0);
		line2.setStroke(Color.ORANGERED);
		line2.setStrokeWidth(5.0);
		x.getChildren().add(line1);
		x.getChildren().add(line2);
		x.setTranslateX(col_index*50);
		x.setTranslateY(row_index*50);
		x.setScaleX(0.5);
		x.setScaleY(0.5);
		group.getChildren().add(x);
	}
	public void paintO(Group group,int row_index, int col_index) {
		Group o = new Group();
		Circle circle= new Circle(50,null);
		circle.setStroke(Color.LAWNGREEN);
		circle.setStrokeWidth(5.0);
		o.getChildren().add(circle);
		o.setTranslateX(col_index*50+25);
		o.setTranslateY(row_index*50+25);
		o.setScaleX(0.4);
		o.setScaleY(0.4);
		group.getChildren().add(o);
	}
	// Controller
//	private void addListenerMouseClick() {
//		group.setPickOnBounds(true);
//		//this.subScene.z
//		group.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
//			double x = e.getSceneX() - 50 - this.subScene.getLocalToSceneTransform().getTx();
//			double y = e.getSceneY() - 50 - this.subScene.getLocalToSceneTransform().getTy();
//			int row_index = (int) (y / 50);
//			int col_index = (int) (x / 50);
//			//System.out.println(col_index + ":" +row_index);
//			count++;
//			if (count % 2 == 1) {
//				if (board.matrix[row_index][col_index] != -1) {
//					count--;
//				} else {
//					Board boardTry = this.board.move(row_index, col_index, 1);
//					if(boardTry!=null) {
//						this.board=boardTry;
//						this.paintX(group, row_index, col_index);
//						System.out.println("Tới O đi");
//
//					}
//
//				}
//
//			} else {
//				if (board.matrix[row_index][col_index] != -1) {
//					count--;
//				} else {
//					Board boardTry = this.board.move(row_index, col_index, 2);
//					if(boardTry!=null) {
//						this.board=boardTry;
//						this.paintO(group, row_index, col_index);
//						System.out.println("Tới X đi");
//					}
//				}
//			}
//			int maxNumX=this.board.check(1);
//			int maxNumO=this.board.check(2);
//			if ( maxNumX!= -1) {
//				System.out.println("X win");
//				System.exit(0);
//			} else if (maxNumO != -1) {
//				System.out.println("O win");
//				System.exit(0);
//			}
//		});
//	}
	public Board getBoard() {
		return board;
	}

}
