package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import View.SubSceneBoard;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import minimax.Node;
import project.caro.config.ConfigGame;

public class ControllerGamePlayer implements Initializable {
	@FXML
	public Label clock;
	Node initial = null;
	SubSceneBoard subSceneBoard = null;
	private boolean clockRunnging = true;

	public void setSubSceneBoard(SubSceneBoard subSceneBoard) {
		this.subSceneBoard = subSceneBoard;
		this.subSceneBoard.setController(this);
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
								if(time==0) {
									ControllerGamePlayer.this.clockRunnging=false;
									System.out.println("Time Out.");
									ConfigGame.Target target =ControllerGamePlayer.this.subSceneBoard.getTurn();
									switch (target) {
									case X:
										String strX="X Lose.";
										System.out.println(strX);
										ControllerGamePlayer.this.clock.setText(strX);
										break;
									case O:
										String strO="O Lose.";
										System.out.println(strO);
										ControllerGamePlayer.this.clock.setText(strO);
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
		if(this.subSceneBoard==null) {
			System.out.println("Set subscene before");
			return;
		}
		this.subSceneBoard.addListenerMouseClickForOnePeople();
	}
	public void addListenerMouseClickForTwoPeople() {
		if(this.subSceneBoard==null) {
			System.out.println("Set subscene before");
			return;
		}
		this.subSceneBoard.addListenerMouseClickForTwoPeople();
	}

	public void stopClock() {
		this.clockRunnging=false;
		
	}


}
