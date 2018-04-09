package application;

import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class ChartPaneController{
	ScatterChart <Number, Number> chart;
	XYChart.Series<Number,Number> series;
	
	
	public ChartPaneController(float xStart,float xEnd,float yStart, float yEnd ,float step) {
		//set up the chart axis and chart
		final NumberAxis xAxis = new NumberAxis(xStart, xEnd, step);
	    final NumberAxis yAxis = new NumberAxis(yStart, yEnd, step);
	    chart=new ScatterChart<Number,Number>(xAxis,yAxis);	
	    

	}
	public void addObservableList(ObservableList list) {
			series.setData(list);
	        chart.getData().add(series);
	}
	
	public ScatterChart <Number, Number>getChart() {
		return chart;
	}
	
}
