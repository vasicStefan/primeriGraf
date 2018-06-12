package topic2_filters;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;
import topic2_filters.filters.Filter;
import topic2_filters.filters.color.Colorize;



public class DemoAccentFilter implements Drawing {
	
	@GadgetDouble(min = 0, max = 360)
	double hue = 0;
	
	@GadgetDouble(min = 0, max = 1)
	double saturation = 0;
	
	@GadgetBoolean
	Boolean applyFilter = false;


	Image originalImage;

	
	@Override
	public void init() {
		originalImage = new Image("images/office.jpg");
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.2));

		Filter filter = new Colorize(hue, saturation);
		Image filteredImage = filter.process(originalImage);
		view.drawImageCentered(applyFilter ? filteredImage : originalImage, Vector.ZERO);
	}
	
	
	public static void main(String[] args) {
		Options options = new Options();
		options.redrawOnlyWhenGadgetValueChanged = true;
		DrawingApplication.launch(options);
	}
}
