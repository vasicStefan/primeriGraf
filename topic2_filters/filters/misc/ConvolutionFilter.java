package topic2_filters.filters.misc;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import topic2_filters.filters.Filter;

/* Nismo radili na vezbama u 2017/2018 godini, nece doci na kolokvijum! */
public class ConvolutionFilter extends Filter {
	double[][] kernel;
	int wk, hk;
	
	
	public ConvolutionFilter(double[][] kernel) {
		if (kernel.length % 2 != 1 || kernel[0].length % 2 != 1) {
			throw new IllegalArgumentException("Both width and height of a kernel must be odd.");
		}
		this.kernel = kernel.clone();
		
		wk = kernel[0].length;
		hk = kernel.length;
	}
	
	
	double clamp(double v) {
		if (v < 0) return 0;
		if (v > 1) return 1;
		return v;
	}
	
	
	@Override
	public Image process(Image input) {
		final int wi = (int) input.getWidth();
		final int hi = (int) input.getHeight();
		
		WritableImage output = new WritableImage(wi, hi);

		PixelReader pr = input.getPixelReader();
		PixelWriter pw = output.getPixelWriter();

		for (int y = 0; y < hi; y++) {
			for (int x = 0; x < wi; x++) {
				double r = 0;
				double g = 0;
				double b = 0;
				
				for (int dy = 0; dy < hk; dy++) {
					for (int dx = 0; dx < wk; dx++) {
						int px = (x + dx - wk/2 + wi) % wi;
						int py = (y + hk/2 - dy + hi) % hi;

						Color c = pr.getColor(px, py);
						
						r += kernel[dy][dx] * c.getRed();
						g += kernel[dy][dx] * c.getGreen();
						b += kernel[dy][dx] * c.getBlue();						
					}
				}

				Color outputColor = new Color(clamp(r), clamp(g), clamp(b), 1);
				pw.setColor(x, y, outputColor);
			}
		}
		
		return output;
	}

	
	// Sum = 1
	
	// Postize blagi efekat zamucenja
	public static final double[][] BLUR_3x3 = new double[][] {
		{ 1.0/16, 2.0/16, 1.0/16 },
		{ 2.0/16, 4.0/16, 2.0/16 },
		{ 1.0/16, 2.0/16, 1.0/16 }
	};
	
	// Postize umereni efekat zamucenja
	public static final double[][] BLUR_5x5 = new double[][] {
		{  1.0/256,  4.0/256,  6.0/256,  4.0/256,  1.0/256},
		{  4.0/256, 16.0/256, 24.0/256, 16.0/256,  4.0/256},
		{  6.0/256, 24.0/256, 36.0/256, 24.0/256,  6.0/256},
		{  4.0/256, 16.0/256, 24.0/256, 16.0/256,  4.0/256},
		{  1.0/256,  4.0/256,  6.0/256,  4.0/256,  1.0/256},
	};
	
	// Izoštrava sliku, cineci prelaze izmedju kontrastnih oblasti izrazenijim.
	public static final double[][] SHARPEN = new double[][] {
		{  0, -1,  0},
		{ -1,  5, -1},
		{  0, -1,  0},
	};
	
	
	// Sum = 0
	
	// "Detektuje ivice", ostavljajuci vidljivim samo prelaze izmedju kontrastnih oblasti.
	public static final double[][] DETECT_EDGES = new double[][] {
		{ -1, -1, -1},
		{ -1,  8, -1},
		{ -1, -1, -1},
	};

}
