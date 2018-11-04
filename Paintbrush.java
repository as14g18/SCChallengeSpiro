import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Paintbrush implements Runnable {
	private GraphicsContext gc;
	private ArrayList<Tuple<Double, Double>> coordList;
	private Button buttonDrawGraph;
	private int windowWidth;
	private int windowHeight;
	private final int DELAY = 1;
	
	public Paintbrush(GraphicsContext gc, ArrayList<Tuple<Double, Double>> coordList, Button buttonDrawGraph, int windowWidth, int windowHeight) {
		this.gc = gc;
		this.coordList = coordList;
		this.buttonDrawGraph = buttonDrawGraph;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}

	@Override
	public void run() {
		buttonDrawGraph.setDisable(true);
		gc.setStroke(Color.RED);
		for (int i = 0; i < coordList.size() - 1; i++) {
			Tuple<Double, Double> point1 = coordList.get(i);
			Tuple<Double, Double> point2 = coordList.get(i + 1);
			gc.strokeLine(point1.getX() + windowWidth / 2, point1.getY() + windowHeight / 2, point2.getX() + windowWidth / 2, point2.getY() + windowHeight / 2);
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buttonDrawGraph.setDisable(false);
	}
}
