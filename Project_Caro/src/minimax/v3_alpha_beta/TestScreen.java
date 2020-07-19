package minimax.v3_alpha_beta;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Board;
import project.caro.config.ConfigGame;
import view.SubSceneBoard;

public class TestScreen extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Node node = new Node(new Board(3, 3, 3), ConfigGame.Target.X, true, 0);
		Minimax minimax= new Minimax(4);
		minimax.findBestMove(node.board,ConfigGame.Target.X, 4);
		while(node.getNeighbours().size()!=0) {
			node=minimax.findBestNode(node.board,ConfigGame.Target.X, 4);
		}
//		System.out.println(leaf.getBoard().matrix[0][0]);
		BorderPane layout= new BorderPane();
		layout.setCenter(new SubSceneBoard(node.getBoard()).getSubScene());
		primaryStage.setScene(new Scene(layout));
		//primaryStage.
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	

}
