import javafx.scene.control.Slider;

public class SliderBuilder {
	public Slider buildSlider(int valueSetMin, int valueSetMax, int initialValue, boolean snapToTicks) {
		Slider slider = new Slider();
		slider.setValue(initialValue);
		slider.setBlockIncrement(1); // increases value of slider by 1 when the user clicks on the slider line
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMin(valueSetMin);
		slider.setMax(valueSetMax);
		slider.setSnapToTicks(snapToTicks);
		
		return slider;
	}
}
