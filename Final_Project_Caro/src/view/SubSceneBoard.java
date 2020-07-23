package view;

import java.io.IOException;
import java.util.Stack;

import controller.ControllerGamePlayer;
import controller.ControllerOfInitial;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Agent;
import model.Board;
import project.caro.config.ConfigGame;
import project.caro.config.ConfigGame.Target;

public class SubSceneBoard {
	public int count = 0;
	Group group = new Group();
	private Board board = null;
	private Agent agent = null;
	SubScene subScene;
	public ConfigGame.Mode mode=ConfigGame.Mode.TWO_PEOPLE;

	private ControllerGamePlayer controller;
	private Stack<EventHandler<MouseEvent>> stackListenerMouseClick = new Stack<EventHandler<MouseEvent>>();

	public SubSceneBoard(Board board) {
		this.setBoard(board);
		this.subScene = DrawBoard.createSubScene(board, getGroup());
		for (int i = 0; i < board.matrix.length; i++) {
			for (int j = 0; j < board.matrix[i].length; j++) {
				if (board.matrix[i][j] == ConfigGame.Target.X.VALUE) {
					this.paint(group, i, j, ConfigGame.Target.X);
				} else if (board.matrix[i][j] == ConfigGame.Target.O.VALUE) {
					this.paint(group, i, j, ConfigGame.Target.O);
				}
			}
		}

	}

	public SubScene getSubScene() {
		return subScene;
	}

	public Group getGroup() {
		return group;
	}

	public boolean move(int rows, int cols, ConfigGame.Target target) {
		return this.getBoard().move(rows, cols, target) != null;
	}

	public void paintO(Group group, int row_index, int col_index) {
		Group o = new Group();
		Circle circle = new Circle(ConfigGame.DRAW, null);
		circle.setStroke(Color.LAWNGREEN);
		circle.setStrokeWidth(5.0);
		o.getChildren().add(circle);
		o.setTranslateX(col_index * ConfigGame.DRAW + (ConfigGame.DRAW / 2));
		o.setTranslateY(row_index * ConfigGame.DRAW + (ConfigGame.DRAW / 2));
		o.setScaleX(0.4);
		o.setScaleY(0.4);
		group.getChildren().add(o);
	}

	public void paintX(Group group, int row_index, int col_index) {
		Group x = new Group();
		Line line1 = new Line(0, 0, ConfigGame.DRAW, ConfigGame.DRAW);
		Line line2 = new Line(ConfigGame.DRAW, 0, 0, ConfigGame.DRAW);
		line1.setStroke(Color.ORANGERED);
		line1.setStrokeWidth(5.0);
		line2.setStroke(Color.ORANGERED);
		line2.setStrokeWidth(5.0);
		x.getChildren().add(line1);
		x.getChildren().add(line2);
		x.setTranslateX(col_index * ConfigGame.DRAW);
		x.setTranslateY(row_index * ConfigGame.DRAW);
		x.setScaleX(0.5);
		x.setScaleY(0.5);
		group.getChildren().add(x);
	}

	public void paint(Group group, int row_index, int col_index, ConfigGame.Target target) {
		switch (target) {
		case X:
			paintX(group, row_index, col_index);
			break;
		case O:
			paintO(group, row_index, col_index);
			break;

		default:
			break;
		}

	}

	boolean running = true;
	int countTarget=0;
	int countOther=0;
	public int turnListen=0;
	public void addListenerMouseClickForOnePeople() {
		
		group.setPickOnBounds(true);
		removeAllListenerMouseClick();
		EventHandler<MouseEvent> listenerMouseClickForOnePeople = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(SubSceneBoard.this.th!=null&&SubSceneBoard.this.th.isAlive())return;
				double x = e.getSceneX() - ConfigGame.DRAW
						- SubSceneBoard.this.subScene.getLocalToSceneTransform().getTx();
				double y = e.getSceneY() - ConfigGame.DRAW
						- SubSceneBoard.this.subScene.getLocalToSceneTransform().getTy();
				int row_index = (int) (y / ConfigGame.DRAW);
				int col_index = (int) (x / ConfigGame.DRAW);
//						System.out.println(col_index + ":" +row_index);
				if (!SubSceneBoard.this.getBoard().isValid(row_index, col_index)) {
					System.out.println("isValid");
					return;
				}
				
				if (SubSceneBoard.this.count % 2 == turnListen) {
					// X đi
					if (SubSceneBoard.this
							.getBoard().matrix[row_index][col_index] != ConfigGame.Target.NOT_THING.VALUE) {
						
						return;
					} else {
						Board boardTry = SubSceneBoard.this.getBoard().move(row_index, col_index,
								ConfigGame.PLAYER_TARGET);
						if (boardTry != null) {
							SubSceneBoard.this.setBoard(boardTry);
							SubSceneBoard.this.paint(group, row_index, col_index, ConfigGame.PLAYER_TARGET);

						}
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								SubSceneBoard.this.controller.changeTarget(SubSceneBoard.this.getTurn());
								
							}
						});
						

					}

				}
				SubSceneBoard.this.count++;
				ConfigGame.Status status = SubSceneBoard.this.getBoard().getCurrentStatus(ConfigGame.PLAYER_TARGET);
				if (status != ConfigGame.Status.NOT_OVER) {
					System.out.println(status);
					controller.stopClock();
					removeAllListenerMouseClick();
					running = false;
					// TODO display SceneFinish with stalemate

				}
				
				SubSceneBoard.this.th = new Thread(new Runnable() {
					
					@Override
					public void run() {
						controller.clockTime = ConfigGame.TIME_TURN;
						
						if (!running)
							return;
						long lb = System.currentTimeMillis();
						int[] location = agent.findBestMove(SubSceneBoard.this.getBoard(), ConfigGame.COMPUTER_TARGET);
						long la = System.currentTimeMillis();
						System.out.println(((la - lb) / 1000)+" s");
						if (location != null) {
//							System.out.println(location[0]+":"+location[1]);
							Board boardTry = SubSceneBoard.this.getBoard().move(location[0], location[1],
									ConfigGame.COMPUTER_TARGET);
							if (boardTry != null) {
								SubSceneBoard.this.board=boardTry;
								Platform.runLater(new Runnable() {

									@Override
									public void run() {
										SubSceneBoard.this.paint(group, location[0], location[1], ConfigGame.COMPUTER_TARGET);
									}
								});
								

							}
						}else {
							System.out.println("Minimax not found");
						}

						ConfigGame.Status status = SubSceneBoard.this.getBoard()
								.getCurrentStatus(ConfigGame.PLAYER_TARGET);
						if (status != ConfigGame.Status.NOT_OVER) {
							System.out.println(status);
							controller.stopClock();
							removeAllListenerMouseClick();
							// TODO display SceneFinish with stalemate
							running = false;

						}
						controller.clockTime = ConfigGame.TIME_TURN;
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								SubSceneBoard.this.controller.changeTarget(SubSceneBoard.this.getTurn());
								
							}
						});
						SubSceneBoard.this.count++;
					}
				});
				SubSceneBoard.this.th.start();
				

			}
		};
		this.group.addEventHandler(MouseEvent.MOUSE_CLICKED, listenerMouseClickForOnePeople);
		// listenerMouseClickForOnePeople.
		stackListenerMouseClick.push(listenerMouseClickForOnePeople);
	}
	private Thread th=null;
	public Target targetFirst=Target.X;
	public void addListenerMouseClickForTwoPeople() {
		group.setPickOnBounds(true);
		// this.subScene.z
		EventHandler<MouseEvent> listenerMouseClickForTwoPeople = e -> {

			double x = e.getSceneX() - ConfigGame.DRAW - SubSceneBoard.this.subScene.getLocalToSceneTransform().getTx();
			double y = e.getSceneY() - ConfigGame.DRAW - SubSceneBoard.this.subScene.getLocalToSceneTransform().getTy();
			int row_index = (int) (y / ConfigGame.DRAW);
			int col_index = (int) (x / ConfigGame.DRAW);
//			System.out.println(col_index + ":" +row_index);
			if (!SubSceneBoard.this.getBoard().isValid(row_index, col_index)) {
				System.out.println("isValid");
				return;
			}
			
			if (count % 2 == 0) {
				// X đi
				if (getBoard().matrix[row_index][col_index] != -1) {
					return;
				} else {
					Board boardTry = this.getBoard().move(row_index, col_index, ConfigGame.Target.X);
					if (boardTry != null) {
						this.setBoard(boardTry);
						this.paintX(group, row_index, col_index);
						System.out.println("Tới O đi");
						controller.clockTime=ConfigGame.TIME_OF_TURN;
						count++;
					}

				}

			} else {
				// O đi
				if (getBoard().matrix[row_index][col_index] != -1) {
					return;
				} else {
					Board boardTry = this.getBoard().move(row_index, col_index, ConfigGame.Target.O);
					if (boardTry != null) {
						this.setBoard(boardTry);
						this.paintO(group, row_index, col_index);
						System.out.println("Tới X đi");
						controller.clockTime=ConfigGame.TIME_OF_TURN;
						count++;
					}
				}
			}
			int maxNumX = this.getBoard().check(ConfigGame.Target.X);
			int maxNumO = this.getBoard().check(ConfigGame.Target.O);
			if (maxNumX != -1) {
				controller.stopClock();
				removeAllListenerMouseClick();
				System.out.println("X win");
				try {
					controller.displayFinishScene("X");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (maxNumO != -1) {
				controller.stopClock();
				removeAllListenerMouseClick();
				System.out.println("O win");
				try {
					controller.displayFinishScene("O");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		group.addEventHandler(MouseEvent.MOUSE_CLICKED, listenerMouseClickForTwoPeople);
		stackListenerMouseClick.push(listenerMouseClickForTwoPeople);
	}

	public void removeAllListenerMouseClick() {
		while (stackListenerMouseClick.size() != 0) {
			this.group.removeEventHandler(MouseEvent.MOUSE_CLICKED, stackListenerMouseClick.pop());
		}
	}

	public Board getBoard() {
		return board;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
		this.board.setAgent(agent);
	}

	public void setController(ControllerGamePlayer controller) {
		this.controller = controller;

	}

	public ConfigGame.Target getTurn() {
		if (count % 2 == 0) {
			return this.targetFirst;
		}
		return (ConfigGame.Target.X==this.targetFirst)?ConfigGame.Target.O:ConfigGame.Target.X;
	
//		switch (mode) {
//		case TWO_PEOPLE:
//			if (count % 2 == 0) {
//				return this.targetFirst;
//			}
//			return (ConfigGame.Target.X==this.targetFirst)?ConfigGame.Target.O:ConfigGame.Target.X;
//		case AGAIN_COMPUTER:
//			if(this.countTarget%2==1) {
//				return this.targetFirst;
//			}
//			if(this.countOther%2==1) {
//				return (ConfigGame.Target.X==this.targetFirst)?ConfigGame.Target.O:ConfigGame.Target.X;
//			}
//		default:
//			return Target.NOT_THING;
//		}
		
	}

	public Agent getAgent() {
		return agent;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
