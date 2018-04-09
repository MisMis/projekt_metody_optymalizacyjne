package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	
	private Stage primaryStage;
	private SplitPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Znajomi Pana Ku³aka");
		showMainWindow();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showMainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("MainWindow.fxml"));
			rootLayout = (SplitPane) loader.load();
			
			//set scene
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			MainWindowController controller= loader.getController();
			controller.setMainClass(this);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Stage getStage() {
		return primaryStage;
	}
	public void addScatterChart(@SuppressWarnings("rawtypes") ScatterChart ch) {
		AnchorPane chartPane=new AnchorPane(ch);
		AnchorPane.setTopAnchor(ch, 5.0);
		AnchorPane.setBottomAnchor(ch, 5.0);
		AnchorPane.setLeftAnchor(ch, 5.0);
		AnchorPane.setRightAnchor(ch, 5.0);
		rootLayout.getItems().set(1, chartPane);
	}
}
