import javafx.scene.control.Label;

public class LabelBuilder {		
	public Label buildLabel(String text) {
		Label label = new Label();
		label.setText(text);
		
		return label;
	}
}
