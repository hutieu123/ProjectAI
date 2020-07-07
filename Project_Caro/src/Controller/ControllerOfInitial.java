package Controller;

import Model.Board;
import View.SubSceneBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import project.caro.config.ConfigGame;

public class ControllerOfInitial {
	public void clickToStart(ActionEvent actionEvent) {
		try {
			Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../View/sceneOfTwoPlayer.fxml"));
			BorderPane root = loader.load();
			SubSceneBoard subSceneBoard = new SubSceneBoard(new Board(ConfigGame.NUMBER_ROWS, ConfigGame.NUMBER_COLS));
			root.setCenter(subSceneBoard.getSubScene());
			ControllerForTwoPlayer c = loader.getController();
			c.setSubSceneBoard(subSceneBoard);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
		} catch (Exception e) {
		}

	}

	public void exit(ActionEvent actionEvent) {
		Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		primaryStage.close();
//		Platform.exit();
//		System.exit(0);

	}

}
