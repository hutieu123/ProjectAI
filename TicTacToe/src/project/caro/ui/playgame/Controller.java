package project.caro.ui.playgame;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import minimax.Node;
import project.caro.ui.SubSceneBoard;

public class Controller implements Initializable {
	@FXML
	Label time;
	Node initial=null;
	SubSceneBoard subSceneBoard = null;
	public void setSubSceneBoard(SubSceneBoard subSceneBoard) {
		this.subSceneBoard = subSceneBoard;

	}
	public void setNode(Node initial) {
		this.initial=initial;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	}
	private Thread th;
	private SimpleStringProperty simpleStringProperty;
	public void cou() {
//		simpleStringProperty= new SimpleStringProperty();
//		this.time.textProperty().bind(simpleStringProperty);
//		this.th=new Thread(new  Runnable() {
//		int c=0;
//		@Override
//		public void run() {
//			while(true){
//				Controller.this.simpleStringProperty.setValue(""+c++);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//
//			}
//
//		}
//	});
//		this.th.start();

	}


}
