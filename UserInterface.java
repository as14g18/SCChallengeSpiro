import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		
		Slider sliderFixedRadius = sliderBuilder.buildSlider(0, 100); // variable R
		Slider sliderMovingRadius = sliderBuilder.buildSlider(0, 100); // variable r
		Slider sliderPenOffset = sliderBuilder.buildSlider(0, 100); // variable O
		Label labelFixedRadius = labelBuilder.buildLabel("Fixed radius:");
		Label labelMovingRadius = labelBuilder.buildLabel("Moving radius:");
		Label labelPenOffset = labelBuilder.buildLabel("Pen offset:");
		Button buttonDrawGraph = new Button("Draw Spirograph");
		
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		Pane wrapperPane = new Pane();
		wrapperPane.getChildren().add(canvas); // required for proper resizing of canvas
		// Bind the width/height property to the wrapper Pane
	    canvas.widthProperty().bind(wrapperPane.widthProperty()); 
	    canvas.heightProperty().bind(wrapperPane.heightProperty());
	    GraphicsContext gc = canvas.getGraphicsContext2D(); // this is used to actually draw stuff
	    
		VBox leftMenu = new VBox(); // left aligned menu
		leftMenu.getChildren().addAll(labelFixedRadius, sliderFixedRadius, labelMovingRadius, sliderMovingRadius, labelPenOffset, sliderPenOffset, buttonDrawGraph);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setLeft(leftMenu);
		borderPane.setCenter(wrapperPane);
		
		// standard code present in every javafx GUI application
		Scene scene = new Scene(borderPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setScene(scene);
		window.show();
		
		paint(gc);
	}
	private void paint(GraphicsContext gc) {
		// draws the spirograph
		// Samples: 250/56
		Hypocycloid hypocloid = new Hypocycloid(12344, 4321, 1, 1000, 100, -100, 0);
		Paintbrush paintbrush = new Paintbrush(gc);
		ArrayList<Tuple<Double, Double>> coordList = new ArrayList<Tuple<Double, Double>>();
		coordList = hypocloid.getCoordList();
		
		gc.setFill(Color.GREEN);
		for (Tuple<Double, Double> coord: coordList) {
			gc.fillOval(coord.getX() + WINDOW_WIDTH / 2, coord.getY() + WINDOW_HEIGHT / 2, 1, 1);
		}
	}
}
