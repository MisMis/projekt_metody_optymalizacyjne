package application;


import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class ChartPaneController{
	ScatterChart <Number, Number> chart;
	XYChart.Series<Number,Number> series;
	ObservableList<XYChart.Data<Number, Number>> xyList = FXCollections.observableArrayList();
	private int i=0;
	private Integer iterations;
	
	public ChartPaneController(float xStart,float xEnd,float yStart, float yEnd ,float step) {
		//set up the chart axis and chart
		final NumberAxis xAxis = new NumberAxis(xStart, xEnd, step);
	    final NumberAxis yAxis = new NumberAxis(yStart, yEnd, step);
	    chart=new ScatterChart<Number,Number>(xAxis,yAxis);
	    series= new XYChart.Series<>(xyList);
	    chart.getData().add(series);
	    Random r=new Random();
		task.valueProperty().addListener(new ChangeListener<Date>() {      
            @Override
            public void changed(ObservableValue<? extends Date> observableValue, Date oldDate, Date newDate) {
                xyList.add(new XYChart.Data<Number, Number>(r.nextInt(100),r.nextInt(100)));
                increment();
            }
        });
	 
	}
	public void addPoint() {
		 ExecutorService executorService =Executors.newSingleThreadExecutor();
		 executorService.submit(task); 
	}
	
	public ScatterChart <Number, Number>getChart() {
		return chart;
	}
	

	private Task<Date> task = new Task<Date>() {
          @Override
          protected Date call() throws Exception {
        	  i=0;
              while (true) {
                  try {
                      Thread.sleep(1);
                  } catch (InterruptedException iex) {
                      Thread.currentThread().interrupt();
                  }

                  if (isCancelled()) {
                      break;
                  }
                  if(getI()>iterations) {
                	  break;
                  }
                  updateValue(new Date());
              }
              return new Date();
          }
      };
      public void setIterations(int iterations) {
    	  this.iterations=iterations;
      }
      private synchronized void increment() {
    	  i++;
      }
      private synchronized int getI() {
    	  return i;
      }
}
