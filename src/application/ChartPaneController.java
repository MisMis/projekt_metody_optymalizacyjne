package application;

import java.util.Date;
import java.util.List;
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

public class ChartPaneController {
	ScatterChart<Number, Number> chart;
	XYChart.Series<Number, Number> series;
	ObservableList<XYChart.Data<Number, Number>> xyList = FXCollections.observableArrayList();
	private int i = 0;
	private Integer iterations;
	private IAlgorithm algorithm;
	private Task<Date> task = new Task<Date>() {
		@Override
		protected Date call() throws Exception {
			i = 0;
			while (true) {
				try {
					if (algorithm.getAlgType() == 1) {
						Thread.sleep(1);
					}
					if (algorithm.getAlgType() == 2) {
						Thread.sleep(100);
					}
				} catch (InterruptedException iex) {
					Thread.currentThread().interrupt();
				}

				if (isCancelled()) {
					break;
				}
				if (getI() > iterations) {
					break;
				}
				updateValue(new Date());
			}
			return new Date();
		}
	};

	public ChartPaneController(float xStart, float xEnd, float yStart, float yEnd, float step) {
		// set up the chart axis and chart
		final NumberAxis xAxis = new NumberAxis(xStart, xEnd, step);
		final NumberAxis yAxis = new NumberAxis(yStart, yEnd, step);
		chart = new ScatterChart<Number, Number>(xAxis, yAxis);
		series = new XYChart.Series<>(xyList);
		chart.getData().add(series);
		task.valueProperty().addListener(new ChangeListener<Date>() {
			@Override
			public void changed(ObservableValue<? extends Date> observableValue, Date oldDate, Date newDate) {
				if (algorithm.getAlgType() == 1) {
					xyList.add(new XYChart.Data<Number, Number>(algorithm.nextValue(), algorithm.nextValue()));
					increment();
				}
				if (algorithm.getAlgType() == 2) {
					List<Point> list = algorithm.nextList();
					xyList.clear();
					for (Point p : list) {
						xyList.add(new XYChart.Data<Number, Number>(p.getX(), p.getY()));
					}
					increment();
				}
			}
		});

	}

	public void start() {
		if (algorithm != null) {
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			executorService.submit(task);
		}
	}

	public ScatterChart<Number, Number> getChart() {
		return chart;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	private synchronized void increment() {
		i++;
	}

	private synchronized int getI() {
		return i;
	}

	public void SetAlgorithm(IAlgorithm algorithm) {
		this.algorithm = algorithm;
		if (algorithm.getAlgType() == 2) {
			chart.setAnimated(false);
		}
	}

	public void cancelTask() {
		task.cancel();
	}
}
