package View;

import project.caro.config.ConfigGame;
import Model.Board;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.shape.Line;

public class DrawBoard {
	public static SubScene createSubScene(Board board, Group group) {
		int cols = board.matrix[0].length;
		int rows = board.matrix.length;
		int draw = ConfigGame.DRAW;
		int width = cols * draw + draw*2;
		int height = rows * draw + draw*2;
		group.setTranslateX(50);
		group.setTranslateY(50);
		Line line_1 = new Line(0, 0, 0, cols * draw);
		group.getChildren().add(line_1);
		Line line_2 = new Line(0, cols * draw, rows * draw, cols * draw);
		group.getChildren().add(line_2);
		Line line_3 = new Line(rows * draw, cols * draw, rows * draw, 0);
		group.getChildren().add(line_3);
		Line line_4 = new Line(rows * draw, 0, 0, 0);
		group.getChildren().add(line_4);
		// Board
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				Line line1 = new Line(i * draw, j * draw, i * draw, (j + 1) * draw);
				group.getChildren().add(line1);
				Line line2 = new Line(i * draw, (j + 1) * draw, (i + 1) * draw, (j + 1) * draw);
				group.getChildren().add(line2);
				Line line3 = new Line((i + 1) * draw, (j + 1) * draw, (i + 1) * draw, j * draw);
				group.getChildren().add(line3);
				Line line4 = new Line(i * draw, j * draw, (i + 1) * draw, j * draw);
				group.getChildren().add(line4);
			}
		}

		return new SubScene(group, width, height);
	}

}
