package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class ChartPaneController{
	ScatterChart <Number, Number> chart;
	XYChart.Series<Number,Number> series;
	ObservableList<XYChart.Data<Number, Number>> xyList = FXCollections.observableArrayList();
	
	
	public ChartPaneController(float xStart,float xEnd,float yStart, float yEnd ,float step) {
		//set up the chart axis and chart
		final NumberAxis xAxis = new NumberAxis(xStart, xEnd, step);
	    final NumberAxis yAxis = new NumberAxis(yStart, yEnd, step);
	    chart=new ScatterChart<Number,Number>(xAxis,yAxis);
	    series = new XYChart.Series<>(xyList);
	    chart.getData().add(series);
	}
	public void addPoint(Number x,Number y) {
		xyList.add(new XYChart.Data<Number, Number>(x,y));
	}
	
	public ScatterChart <Number, Number>getChart() {
		return chart;
	}
}
