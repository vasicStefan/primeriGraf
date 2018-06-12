package topic2_filters;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import topic2_filters.filters.Filter;
import topic2_filters.filters.color.Accent;
import topic2_filters.filters.color.Colorize;
import topic2_filters.filters.color.Desaturate;
import topic2_filters.filters.color.Grayscale;
import topic2_filters.filters.color.Invert;
import topic2_filters.filters.color.Saturate;
import topic2_filters.filters.color.Sepia;
import topic2_filters.filters.displacement.FlipHorizontal;
import topic2_filters.filters.displacement.FlipVertical;
import topic2_filters.filters.displacement.Jitter;
import topic2_filters.filters.displacement.Rotate180;
import topic2_filters.filters.displacement.Wave;
import topic2_filters.filters.displacement.Zoom;
import topic2_filters.filters.misc.ConvolutionFilter;
import topic2_filters.filters.misc.Vignette;


public class DemoFilters implements Drawing {
	@GadgetInteger(min = 0, max = 12)
	Integer imageIndex = 10;
	
	@GadgetInteger(min = 0, max = 17)
	Integer filterIndex = 0;
	
	@GadgetBoolean
	Boolean applyFilter = false;
	
	
	Filter[] filters = {
			new Grayscale(),
			new Invert(),
			new Sepia(),
			new Desaturate(0.6),
			new Saturate(0.5),
			new Colorize(216, 0.5),
			new Accent(28),

			new FlipHorizontal(),
			new FlipVertical(),
			new Rotate180(),
			new Wave(5, 100),
			new Zoom(2.0),
			new Jitter(10.0),

			new ConvolutionFilter(ConvolutionFilter.BLUR_3x3),
			new ConvolutionFilter(ConvolutionFilter.BLUR_5x5),
			new ConvolutionFilter(ConvolutionFilter.SHARPEN),
			new ConvolutionFilter(ConvolutionFilter.DETECT_EDGES),

			new Vignette(),
	};

	String[] fileNames = {
			"building.jpg",
			"catparty.jpg",
			"christmas.jpg",
			"couple.jpg",
			"dive.jpg",
			"doggo.jpg",
			"fall.jpg",
			"forecast.jpg",
			"kitchen.jpg",
			"meterologist.jpg",
			"office.jpg",
			"skirts.jpg",
			"waiting.jpg",
	};
	

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));
		
		Filter filter = filters[filterIndex];
		Image originalImage = new Image("images/" + fileNames[imageIndex]);
		Image filteredImage = filter.process(originalImage);
		view.drawImageCentered(applyFilter ? filteredImage : originalImage, Vector.ZERO);
		
		DrawingUtils.drawInfoText(view, "Image: " + fileNames[imageIndex] + "   Filter: " + filter.getClass().getSimpleName());
	}
	
	
	public static void main(String[] args) {
		Options options = new Options();
		options.redrawOnlyWhenGadgetValueChanged = true;
		DrawingApplication.launch(options);
	}
}
