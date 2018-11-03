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
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

public class UserInterface extends Application{
	
	private Stage window;

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
		
		Canvas canvas = new Canvas(801, 601);
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
		Scene scene = new Scene(borderPane, 801, 601);
		window.setScene(scene);
		window.show();
	}
}
