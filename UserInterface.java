import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

public class UserInterface extends Application{
	
	private Stage window;
	private static final int WINDOW_WIDTH = 801;
	private static final int WINDOW_HEIGHT = 601;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.window = primaryStage;
		window.setTitle("Spirograph generator");
		
		SliderBuilder sliderBuilder = new SliderBuilder(); // Factory class for building sliders in one line
		LabelBuilder labelBuilder = new LabelBuilder(); // Label factory
		
		Slider sliderFixedRadius = sliderBuilder.buildSlider(1, 500, 250, false); // variable n
		Slider sliderMovingRadius = sliderBuilder.buildSlider(1, 500, 56, false); // variable m
		Slider sliderPenOffset = sliderBuilder.buildSlider(-10, 10, 5, false); // variable f
		Slider sliderLoops = sliderBuilder.buildSlider(0, 150, 20, false);
		Slider sliderScale = sliderBuilder.buildSlider(1, 20, 10, false);
		Slider sliderXOffset = sliderBuilder.buildSlider(-500, 500, -100, false);
		Slider sliderYOffset = sliderBuilder.buildSlider(-500, 500, 0, false);
		Label labelFixedRadius = labelBuilder.buildLabel("  Fixed radius:");
		Label labelMovingRadius = labelBuilder.buildLabel("  Moving radius:");
		Label labelPenOffset = labelBuilder.buildLabel("  Pen offset:");
		Label labelLoops = labelBuilder.buildLabel("  Number of loops:");
		Label labelScale = labelBuilder.buildLabel("  Scale:");
		Label labelXOffset = labelBuilder.buildLabel("  x Offset:");
		Label labelYOffset = labelBuilder.buildLabel("  y Offset:");
		Button buttonDrawGraph = new Button("Draw Spirograph");
		
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		Pane wrapperPane = new Pane();
		wrapperPane.getChildren().add(canvas); // required for proper resizing of canvas
		// Bind the width/height property to the wrapper Pane
	    canvas.widthProperty().bind(wrapperPane.widthProperty()); 
	    canvas.heightProperty().bind(wrapperPane.heightProperty());
	    GraphicsContext gc = canvas.getGraphicsContext2D(); // this is used to actually draw stuff
	    
		VBox leftMenu = new VBox(); // left aligned menu
		leftMenu.getChildren().addAll(labelFixedRadius, sliderFixedRadius, 
				labelMovingRadius,sliderMovingRadius, 
				labelPenOffset, sliderPenOffset, 
				labelLoops, sliderLoops,
				labelScale, sliderScale, 
				labelXOffset, sliderXOffset, 
				labelYOffset, sliderYOffset, 
				buttonDrawGraph);
		
		buttonDrawGraph.setOnAction(e -> {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			paint(gc, (int)sliderFixedRadius.getValue(), (int)sliderMovingRadius.getValue(), (int)sliderPenOffset.getValue(), (int)sliderLoops.getValue(), (int)sliderScale.getValue(), (int)sliderXOffset.getValue(), (int)sliderYOffset.getValue());
		});
		Slider[] sliderArray = new Slider[]{sliderFixedRadius, sliderMovingRadius, sliderPenOffset, sliderLoops, sliderScale, sliderXOffset, sliderYOffset}; 
		for (Slider slider : sliderArray) {
			makeSliderUpdateDynamically(slider, canvas, gc, sliderFixedRadius, sliderMovingRadius, sliderPenOffset, sliderLoops, sliderScale, sliderXOffset, sliderYOffset);
		}
		
		BorderPane borderPane = new BorderPane();
		borderPane.setLeft(leftMenu);
		borderPane.setCenter(wrapperPane);
		
		// standard code present in every javafx GUI application
		Scene scene = new Scene(borderPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setScene(scene);
		window.show();
	}
	
	private void paint(GraphicsContext gc, int fixedRadius, int movingRadius, int penOffset, int loops, int scale, int xOffset, int yOffset) {
		// paints the spirograph
		// Hypocycloid hypocloid = new Hypocycloid(250, 56, 5, 1, 10, -100, 0);
		Hypocycloid hypocycloid = new Hypocycloid(fixedRadius, movingRadius, penOffset, loops, scale, xOffset, yOffset);
		
		ArrayList<Tuple<Double, Double>> coordList = new ArrayList<Tuple<Double, Double>>();
		coordList = hypocycloid.getCoordList();
		
		gc.setStroke(Color.RED);
		for (int i = 0; i < coordList.size() - 1; i++) {
			Tuple<Double, Double> point1 = coordList.get(i);
			Tuple<Double, Double> point2 = coordList.get(i + 1);
			gc.strokeLine(point1.getX() + WINDOW_WIDTH / 2, point1.getY() + WINDOW_HEIGHT / 2, point2.getX() + WINDOW_WIDTH / 2, point2.getY() + WINDOW_HEIGHT / 2);
		}
	}
	
	private void makeSliderUpdateDynamically(Slider masterSlider, Canvas canvas, GraphicsContext gc, Slider sliderFixedRadius, Slider sliderMovingRadius, Slider sliderPenOffset, Slider sliderLoops, Slider sliderScale, Slider sliderXOffset, Slider sliderYOffset) {
		masterSlider.valueProperty().addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
            	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    			paint(gc, (int)sliderFixedRadius.getValue(), (int)sliderMovingRadius.getValue(), (int)sliderPenOffset.getValue(), (int)sliderLoops.getValue(), (int)sliderScale.getValue(), (int)sliderXOffset.getValue(), (int)sliderYOffset.getValue());
            }
        });
	}
}
