package project.caro;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import minimax.Minimax;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;
import project.caro.core.Board;
import project.caro.ui.SubSceneBoard;

public class Controller {
	public void click(ActionEvent actionEvent) {
		try {
			Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ui/playgame/application.fxml"));
			BorderPane root = loader.load();
			SubSceneBoard subSceneBoard = new SubSceneBoard(new Board(ConfigGame.NUMBER_ROWS, ConfigGame.NUMBER_COLS));
			subSceneBoard.setAgent(new Minimax());
			root.setCenter(subSceneBoard.getSubScene());
			project.caro.ui.playgame.Controller c = loader.getController();
			c.setSubSceneBoard(subSceneBoard);
			minimax.Node initial = new minimax.Node(subSceneBoard.getBoard(), Target.O, !true);
			c.setNode(initial);
			c.cou();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
		} catch (Exception e) {

		}

	}

}
