import javafx.scene.control.Slider;

public class SliderBuilder {
	public Slider buildSlider(double valueSetMin, double valueSetMax, double initialValue, double blockIncrement) {
		Slider slider = new Slider();
		slider.setValue(initialValue);
		slider.setBlockIncrement(blockIncrement); // increases value of slider by 1 when the user clicks on the slider line
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMin(valueSetMin);
		slider.setMax(valueSetMax);
		
		return slider;
	}
}
