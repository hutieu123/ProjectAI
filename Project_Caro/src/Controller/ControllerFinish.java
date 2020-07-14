package Controller;

import Model.Board;
import View.SubSceneBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import minimax.v1_3x3.Minimax;
import project.caro.config.ConfigGame;

public class ControllerFinish {
	@FXML
	public Label whowin;
	String winner;

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public void displayWinner() {
		whowin.setText("Nguoi thang cuoc la " + winner);
	}

	public void clickToStart(ActionEvent actionEvent) {
		try {
			Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../View/sceneOfGamePlay.fxml"));
			BorderPane root = loader.load();
			SubSceneBoard subSceneBoard = new SubSceneBoard(
					new Board(ConfigGame.NUMBER_ROWS, ConfigGame.NUMBER_COLS, ConfigGame.NUMBER_WIN));
			// Set Agent
			subSceneBoard.setAgent(new Minimax());
			root.setCenter(subSceneBoard.getSubScene());
			ControllerGamePlayer c = loader.getController();

			c.setPrimaryStage(primaryStage);
			c.setSubSceneBoard(subSceneBoard);

			// Add listenerMouseClick For One People Again Agent
			// minimax.Node initial = new minimax.Node(subSceneBoard.getBoard(),
			// Target.O, !true);
			// c.setNode(initial);
			// c.addListenerMouseClickForOnePeople();

			c.addListenerMouseClickForTwoPeople();

			Scene scene = new Scene(root);
			primaryStage.hide();
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void exit(ActionEvent actionEvent) {
		Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		primaryStage.close();
		System.exit(0);

	}

}
