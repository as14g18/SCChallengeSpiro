import javafx.scene.control.Slider;

public class SliderBuilder {
	public Slider buildSlider(int valueSetMin, int valueSetMax) {
		Slider slider = new Slider();
		slider.setValue(0);
		slider.setBlockIncrement(1); // increases value of slider by 1 when the user clicks on the slider line
		
		slider.setMin(valueSetMin);
		slider.setMax(valueSetMax);
		
		return slider;
	}
}
