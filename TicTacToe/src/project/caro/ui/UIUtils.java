package project.caro.ui;

import project.caro.config.ConfigGame;
import project.caro.core.Board;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class UIUtils {
	public static SubScene createSubScene(Board board, Group group) {
		int cols = board.matrix[0].length;
		int rows = board.matrix.length;
		int draw = ConfigGame.DRAW;
		int width = cols * draw + draw*2;
		int height = rows * draw + draw*2;
		group.setTranslateX(50);
		group.setTranslateY(50);
		//Board border
		int padding = 5;
		Line lineLeftVertical = new Line(0-padding, 0-padding, 0-padding, cols * draw+padding);
		group.getChildren().add(lineLeftVertical);// Left V
		Line lineBottomHorizontal = new Line(0-padding, cols * draw+padding, rows * draw+padding, cols * draw+padding);
		group.getChildren().add(lineBottomHorizontal);// Bottom H
		Line lineRightVertical = new Line(rows * draw+padding, cols * draw+padding, rows * draw+padding, 0-padding);
		group.getChildren().add(lineRightVertical);// Right V
		Line lineTopHorizontal = new Line(rows * draw+padding, 0-padding, 0-padding, 0-padding);
		group.getChildren().add(lineTopHorizontal);// Top H
		lineLeftVertical.setStroke(Color.DEEPSKYBLUE);
		lineBottomHorizontal.setStroke(Color.DEEPSKYBLUE);
		lineRightVertical.setStroke(Color.DEEPSKYBLUE);
		lineTopHorizontal.setStroke(Color.DEEPSKYBLUE);
		// Board Fill ─
		for (int i = 1; i < cols; i++) {
			Line lineV = new Line(i * draw, 0, i * draw, (cols) * draw);
			group.getChildren().add(lineV);
			lineV.setStroke(Color.LIGHTSKYBLUE);
		}
		for (int i = 1; i < rows; i++) {
			Line lineH = new Line(0,i * draw, (rows) * draw, i * draw );
			group.getChildren().add(lineH);
			lineH.setStroke(Color.LIGHTSKYBLUE);
		}

		return new SubScene(group, width, height);
	}

}
