package application.linearRandomGenerator;

import application.ChartPaneController;
import application.IAlgorithm;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Slider;

public class LinearGeneratorController  {

	// Controls
	@FXML
	private TextField firstTextBox;
	@FXML
	private TextField secondTextBox;
	@FXML
	private TextField thirdTextBox;
	@FXML
	private TextField iterationTextBox;
	@FXML
	private Slider iterationSlider;
	ChartPaneController oldController;
	
	//reference to main class
	private Main mainClass;
	
	
	public LinearGeneratorController() {
	}
	
	@FXML
	private void initialize() {
		// add action to slider
		iterationSlider.valueProperty().addListener(e -> {
			handleSliderAction();
		});
	}
	
	public void setMainClass(Main main){
		mainClass=main;
	}
	
	@FXML
	private void handleOKButtton() {
		if(isValidInput()) {
			IAlgorithm algorithm= new LinearPseudorandomNumberGenerator(Long.parseLong(firstTextBox.getText()),
					Long.parseLong(secondTextBox.getText()),
					Long.parseLong(thirdTextBox.getText()),
					123);
			
			//set up chart
			ChartPaneController controller=new ChartPaneController(0,1,0,1,(float)0.02);
			
			//very important anuluje stare zadanie gdy okButton zostanie klikniety zanim skonczy si� stare zadanie;
			if(oldController!=null) {
				oldController.cancelTask();
			}
			oldController=controller;
			
			//pass chart to mainClass scene
			mainClass.addScatterChart(controller.getChart());
			
			//set algorithm and needed data
			controller.SetAlgorithm(algorithm);
			controller.setIterations(Integer.parseInt(iterationTextBox.getText()));
			
			//start calculating data
			controller.start();
		}
	}
	
	@FXML
	private void handleSliderAction() {
		iterationTextBox.setText(String.valueOf((int)iterationSlider.getValue()));
	}
	
	@FXML 	
	private void handleChangeIterationTextBox() {
		iterationSlider.setValue(Double.parseDouble(iterationTextBox.getText()));
	}
	
	// valid input message
	private boolean isValidInput() {
		String errMessage = "";
		try {
			Long.parseLong(firstTextBox.getText());
		}catch(NumberFormatException e) {
			errMessage+="valid value a \n";
		}
		try {
			Long.parseLong(secondTextBox.getText());
		}catch(NumberFormatException e) {
			errMessage+="valid value b \n";
		}
		try {
			Long.parseLong(thirdTextBox.getText());
		}catch(NumberFormatException e) {
			errMessage+="valid value m \n";
		}
		if(errMessage.length()==0) {
			return true;
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainClass.getStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errMessage);
            alert.showAndWait();
            return false;
		}	
	}

}
