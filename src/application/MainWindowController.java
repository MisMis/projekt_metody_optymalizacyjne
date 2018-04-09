package application;

import java.io.IOException;

import application.linearRandomGenerator.LinearGeneratorController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class MainWindowController {

	// components
	
	//left application pane
	@FXML
	private SplitPane optionPane;
	@FXML
	private ChoiceBox<String> choiceBox;
	
	//reference to main class
	private Main mainClass;
	
	
	public MainWindowController() {
		super();
	}
	public void setMainClass(Main main) {
		mainClass=main;
	}
	public Main getMain() {
		return mainClass;
	}
	
	@FXML
	private void initialize() {
		//set choiceBox options
		choiceBox.setItems(FXCollections.observableArrayList("Liniowy generator liczb pseudolosowych", "opcja 2", "opcja 3"));
		choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue arg0, Number value, Number new_value) {
				switch (new_value.intValue()) {
				case 0:
					loadOption1Pane();
					break;
				case 1:
					break;
				case 2:
					break;
				default:
					break;
				}
			}
		});
	}

	private void loadOption1Pane() {
		try {
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Main.class.getResource("linearRandomGenerator/linearGeneratorLayout.fxml"));
			AnchorPane root=(AnchorPane) loader.load();
			
			//change layout
			optionPane.getItems().set(1, root);
			
			//set controller
			LinearGeneratorController controller =loader.getController();
			controller.setMainClass(mainClass);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}