package topic2_filters;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;
import topic2_filters.filters.BinaryFilter;
import topic2_filters.filters.binary.ChromaKey;


public class DemoChromaKey implements Drawing {
	
	@GadgetDouble(min = 0, max = 360)
	Double hue = 120.0;
	
	Image originalImage1, originalImage2;

	
	@Override
	public void init() {
		originalImage1 = new Image("images/meterologist.jpg");
		originalImage2 = new Image("images/forecast.jpg");
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		BinaryFilter filter = new ChromaKey(hue);
		Image filteredImage = filter.process(originalImage1, originalImage2);
		view.drawImageCentered(filteredImage, Vector.ZERO);
	}
	
	
	public static void main(String[] args) {
		Options options = new Options();
		options.redrawOnlyWhenGadgetValueChanged = true;
		DrawingApplication.launch(options);
	}
}
