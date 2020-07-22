package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.scene.traversal.Algorithm;

import javafx.application.Platform;
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
import minimax.v4_heuristic_notalpha.minimax;
import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;
import view.SubSceneBoard;

public class ControllerOfInitial implements Initializable {
	
	@FXML
	VBox vbox_btn;
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
public void clickToTest2(ActionEvent actionEvent) {
		try {
			Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../View/sceneOfGamePlay.fxml"));
			BorderPane root = loader.load();
			SubSceneBoard subSceneBoard = new SubSceneBoard(new Board(15,15,5));
			//Set Agent
			minimax agent= new minimax();/////////////////
			subSceneBoard.setAgent(agent);
			root.setCenter(subSceneBoard.getSubScene());
			ControllerGamePlayer c = loader.getController();
			c.setSubSceneBoard(subSceneBoard);
			c.setPrimaryStage(primaryStage);

			//Add listenerMouseClick For One People Again Agent

			c.addListenerMouseClickForOnePeople();
			//Set computer first
			c.setTurnFirst(ConfigGame.Target.X, true);//isHuman=true, target=X, => player hit X, computer hit O
			
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
	
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for(Node n:ControllerOfInitial.this.vbox_btn.getChildren()) {
					Button b=(Button) n;
					b.prefWidthProperty().bind(ControllerOfInitial.this.vbox_btn.widthProperty());
				}
			}
		});
	}

}
