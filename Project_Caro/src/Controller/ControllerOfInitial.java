package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Board;
import View.SubSceneBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class ControllerOfInitial implements Initializable {
	@FXML
	Button startGameWithTwoPeople;
	@FXML
	Button startGame_v1;
	@FXML
	Button startGameTicTacToe;
	@FXML
	Button	exitGame;
	public void clickToStart(ActionEvent actionEvent) {
		try {
			Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../View/sceneOfGamePlay.fxml"));
			BorderPane root = loader.load();
			SubSceneBoard subSceneBoard = new SubSceneBoard(new Board(ConfigGame.NUMBER_ROWS, ConfigGame.NUMBER_COLS,ConfigGame.NUMBER_WIN));
			
			root.setCenter(subSceneBoard.getSubScene());
			ControllerGamePlayer c = loader.getController();
			c.setPrimaryStage(primaryStage);
			c.setSubSceneBoard(subSceneBoard);
			
			//Add listenerMouseClick For One People Again Agent
//			minimax.Node initial = new minimax.Node(subSceneBoard.getBoard(), Target.O, !true);
//			c.setNode(initial);
//			c.addListenerMouseClickForOnePeople();
			
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
	public void clickToStartTicTacToe(ActionEvent actionEvent) {
		try {
			Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../View/sceneOfGamePlay.fxml"));
			BorderPane root = loader.load();
			SubSceneBoard subSceneBoard = new SubSceneBoard(new Board(3, 3,3));
			//Set Agent
			subSceneBoard.setAgent(new minimax.v1_3x3.Minimax());
			root.setCenter(subSceneBoard.getSubScene());
			ControllerGamePlayer c = loader.getController();
			c.setSubSceneBoard(subSceneBoard);
			c.setPrimaryStage(primaryStage);
			
			//Add listenerMouseClick For One People Again Agent
			
			c.addListenerMouseClickForOnePeople();
			//Set computer first
			c.setTurmFirst(ConfigGame.Target.X, false);//isHuman=true, target=X, => player hit X, computer hit O
			Scene scene = new Scene(root);
			primaryStage.hide();
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	public void clickToTest(ActionEvent actionEvent) {
		try {
			Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../View/sceneOfGamePlay.fxml"));
			BorderPane root = loader.load();
			SubSceneBoard subSceneBoard = new SubSceneBoard(new Board(15,15,5));
			//Set Agent
			subSceneBoard.setAgent(new minimax.v2_NotAlpha_Beta.Minimax_v2(2));
			root.setCenter(subSceneBoard.getSubScene());
			ControllerGamePlayer c = loader.getController();
			c.setSubSceneBoard(subSceneBoard);
			c.setPrimaryStage(primaryStage);
			
			//Add listenerMouseClick For One People Again Agent
			
			c.addListenerMouseClickForOnePeople();
			//Set computer first
			c.setTurmFirst(ConfigGame.Target.X, false);//isHuman=true, target=X, => player hit X, computer hit O
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VBox vbox = (VBox) this.startGameWithTwoPeople.getParent();
		this.startGameWithTwoPeople.prefWidthProperty().bind(vbox.prefWidthProperty());
		this.exitGame.prefWidthProperty().bind(vbox.prefWidthProperty());
		this.startGame_v1.prefWidthProperty().bind(vbox.prefWidthProperty());
		this.startGameTicTacToe.prefWidthProperty().bind(vbox.prefWidthProperty());
		System.out.println(vbox.getPrefWidth());
	}

}
