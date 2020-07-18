package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Board;
import View.SubSceneBoard;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import minimax.v1_3x3.Node;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class ControllerGamePlayer implements Initializable {
	@FXML
	public Label clock;
	Node initial = null;
	SubSceneBoard subSceneBoard = null;
	private boolean clockRunnging = true;
	Stage primarystage;

	public void setSubSceneBoard(SubSceneBoard subSceneBoard) {
		this.subSceneBoard = subSceneBoard;
		this.subSceneBoard.setController(this);
	}

	public void setPrimaryStage(Stage primarystage) {
		this.primarystage = primarystage;
	}

	public void setNode(Node initial) {
		this.initial = initial;
	}

	public Thread clockThread;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.clockThread = new Thread(new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				while (ControllerGamePlayer.this.clockRunnging) {
					try {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								int time = Integer.parseInt(ControllerGamePlayer.this.clock.getText());
								ControllerGamePlayer.this.clock.setText("" + --time);
								if (time == 0) {
									ControllerGamePlayer.this.clockRunnging = false;
									System.out.println("Time Out.");
									ConfigGame.Target target = ControllerGamePlayer.this.subSceneBoard.getTurn();
									ControllerGamePlayer.this.subSceneBoard.removeAllListenerMouseClick();
									switch (target) {
									case X:
										String strX = "X Lose.";
										System.out.println(strX);
										ControllerGamePlayer.this.clock.setText(strX);
										try {
											displayFinishScene("O");
										} catch (IOException e) {
											e.printStackTrace();
										}
										break;
									case O:
										String strO = "O Lose.";
										System.out.println(strO);
										ControllerGamePlayer.this.clock.setText(strO);
										try {
											displayFinishScene("X");
										} catch (IOException e) {
											e.printStackTrace();
										}
										break;

									default:
										break;
									}
								}
							}
						});

						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

				return null;
			}
		});
		clockThread.start();

	}

	public void addListenerMouseClickForOnePeople() {
		if (this.subSceneBoard == null) {
			System.out.println("Set subscene before");
			return;
		}
		this.subSceneBoard.addListenerMouseClickForOnePeople();
	}

	public void addListenerMouseClickForTwoPeople() {
		if (this.subSceneBoard == null) {
			System.out.println("Set subscene before");
			return;
		}
		this.subSceneBoard.addListenerMouseClickForTwoPeople();
	}

	public void stopClock() {
		this.clockRunnging = false;

	}

	public void displayFinishScene(String winner) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../View/sceneOfFinish.fxml"));
		BorderPane root = loader.load();
		ControllerFinish c = loader.getController();
		c.setWinner(winner);
		c.displayWinner();
		Scene scene = new Scene(root);
		primarystage.hide();
		primarystage.setScene(scene);
		primarystage.centerOnScreen();
		primarystage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				primarystage.close();
				System.exit(0);
			}
		});
		primarystage.show();
	}
	/**
	 * isHuman=true, target=X, => player hit X, computer hit O
	 * isHuman=false, target=X, =>computer hit X, player hit O
	 * target will turn first
	 */
	public void setTurnFirst(Target target,boolean isHuman) {
		if(!isHuman) {
			ConfigGame.COMPUTER_TARGET=target;
			ConfigGame.PLAYER_TARGET=(target==Target.O)?Target.X:Target.O;;
			//this.subSceneBoard.getAgent().
			this.clock.setText(""+10);
//			int[] location = subSceneBoard.getAgent().findBestMove(subSceneBoard.getBoard(), target, 0);
			int[] location= new int[2];
			location[0]= subSceneBoard.getBoard().matrix.length/2;
			location[1]= subSceneBoard.getBoard().matrix.length/2;
			if(location!=null) {
				Board boardTry = subSceneBoard.getBoard().move(location[0], location[1], target);
				if(boardTry!=null) {
					subSceneBoard.setBoard(boardTry);
					subSceneBoard.paint(subSceneBoard.getGroup(), location[0], location[1], target);
				}
			}
			this.clock.setText(""+10);
		}else {
			ConfigGame.PLAYER_TARGET=target;
			ConfigGame.COMPUTER_TARGET=(target==Target.O)?Target.X:Target.O;;
		}
		
	}

}
