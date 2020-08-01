package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import minimax.Node;
import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;
import view.SubSceneBoard;

public class ControllerGamePlayer implements Initializable {
	@FXML
	public Label clock;
	@FXML
	AnchorPane showTarget;
	Node initial = null;
	SubSceneBoard subSceneBoard = null;
	private boolean clockRunnging = true;
	Stage primarystage;

	public void setSubSceneBoard(SubSceneBoard subSceneBoard) {
		this.subSceneBoard = subSceneBoard;
		this.subSceneBoard.setController(this);
		//System.out.println(vbox.getPrefWidth());
	}

	public void setPrimaryStage(Stage primarystage) {
		this.primarystage = primarystage;
	}

	public void setNode(Node initial) {
		this.initial = initial;
	}

	public Thread clockThread;
	private Group x;
	private Group o;
	private Group groupShowTarget;
	public int clockTime = ConfigGame.TIME_TURN;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				ControllerGamePlayer.this.groupShowTarget = new Group();
				ControllerGamePlayer.this.x = ControllerGamePlayer.this.paintX();
				ControllerGamePlayer.this.o = ControllerGamePlayer.this.paintO();
				SubScene ss=new SubScene(ControllerGamePlayer.this.groupShowTarget, ControllerGamePlayer.this.showTarget.getWidth(), ControllerGamePlayer.this.showTarget.getHeight());
//				x.sca
				ControllerGamePlayer.this.showTarget.getChildren().add(ss);
				
				ConfigGame.Target target=ControllerGamePlayer.this.subSceneBoard.getTurn();
				switch (target) {
				case X:
					ControllerGamePlayer.this.groupShowTarget.getChildren().add(ControllerGamePlayer.this.x);
					break;
				case O:
					ControllerGamePlayer.this.groupShowTarget.getChildren().add(ControllerGamePlayer.this.o);
					break;

				default:
					break;
				}
			}
		});
		this.clockThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int timeDelay=250;
				while (ControllerGamePlayer.this.clockRunnging) {
					try {
						
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								
								ControllerGamePlayer.this.clock.setText("" + ((ControllerGamePlayer.this.clockTime-=timeDelay)/1000));
								
//								System.out.println(ControllerGamePlayer.this.subSceneBoard.getTurn());
//								System.out.println("change");
							}
						});
						if (ControllerGamePlayer.this.clockTime == 0) {
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
						Thread.sleep(timeDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
						
					}

				}
				
			}
		});
		//this.clockThread.setDaemon(true);
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
			this.clockTime=ConfigGame.TIME_TURN;
			int[] location = subSceneBoard.getAgent().findBestMove(subSceneBoard.getBoard(), target);
			if(location!=null) {
				Board boardTry = subSceneBoard.getBoard().move(location[0], location[1], target);
				if(boardTry!=null) {
					subSceneBoard.setBoard(boardTry);
					subSceneBoard.paint(subSceneBoard.getGroup(), location[0], location[1], target);
				}
			}
			this.clockTime=ConfigGame.TIME_TURN;
			this.subSceneBoard.count++;
			this.subSceneBoard.turnListen=1;
			
		}else {
			ConfigGame.PLAYER_TARGET=target;
			ConfigGame.COMPUTER_TARGET=(target==Target.O)?Target.X:Target.O;;
			this.subSceneBoard.turnListen=0;
		}
		this.subSceneBoard.targetFirst= target;
		
		//this.subSceneBoard.mode = ConfigGame.Mode.AGAIN_COMPUTER;
		
	}
	public Group paintO() {
		Group o = new Group();
		Circle circle= new Circle(ConfigGame.DRAW,null);
		circle.setStroke(Color.LAWNGREEN);
		circle.setStrokeWidth(5.0);
		o.getChildren().add(circle);
		o.setTranslateX(ConfigGame.DRAW+(ConfigGame.DRAW/2));
		o.setTranslateY(ConfigGame.DRAW+(ConfigGame.DRAW/2));
		o.setScaleX(0.4);
		o.setScaleY(0.4);
		return o;
	}
	public Group paintX() {
		Group x = new Group();
		Line line1 = new Line(0, 0, ConfigGame.DRAW, ConfigGame.DRAW);
		Line line2 = new Line(ConfigGame.DRAW, 0, 0, ConfigGame.DRAW);
		line1.setStroke(Color.ORANGERED);
		line1.setStrokeWidth(5.0);
		line2.setStroke(Color.ORANGERED);
		line2.setStrokeWidth(5.0);
		x.getChildren().add(line1);
		x.getChildren().add(line2);
		x.setTranslateX(ConfigGame.DRAW);
		x.setTranslateY(ConfigGame.DRAW);
		x.setScaleX(0.5);
		x.setScaleY(0.5);
		return x;
	}
	public void changeTarget(ConfigGame.Target target) {
		switch (target) {
		case X:
			if(groupShowTarget.getChildren().contains(o)) {
				groupShowTarget.getChildren().remove(o);
				
			}
			if(!groupShowTarget.getChildren().contains(x)) {
				groupShowTarget.getChildren().add(x);
			}
			
			break;
		case O:
			if(groupShowTarget.getChildren().contains(x)) {
				groupShowTarget.getChildren().remove(x);
				
			}
			if(!groupShowTarget.getChildren().contains(o)) {
				groupShowTarget.getChildren().add(o);
			}
			
			break;

		default:
			break;
		}
		
	}

}
