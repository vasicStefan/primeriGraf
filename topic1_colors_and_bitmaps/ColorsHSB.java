package topic1_colors_and_bitmaps;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetColorPicker;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class ColorsHSB implements Drawing {

	
	public Image imgFixedHue() {
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.hsb(0, x/image.getWidth(), y/image.getHeight()));
			}
		}
		
		return image;
	}

	
	public Image imgFixedSaturation() {
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.hsb(360*x/image.getWidth(), 1, y/image.getHeight()));
			}
		}
		
		return image;
	}
	
	
	public Image imgFixedBrightness() {
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.hsb(360*x/image.getWidth(), y/image.getHeight(), 1));
			}
		}
		
		return image;
	}
	
	

	public Image imgDisk1() {
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double dx = (2.0 * x / w) - 1;                         // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                         // Udaljenost po y-osi od centra (od -1 do 1).
				double d = Math.sqrt(dx*dx + dy*dy);                   // Udaljenost od centra.
				double phi = Math.atan2(dy, dx) * 360 / (2 * Math.PI); // Ugao (0-360)

				if (d <= 1) {
					pw.setColor(x, y, Color.hsb(phi, 1, 1));
				}
			}
		}
		
		return image;
	}


	
	public Image imgDisk2() {
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double dx = (2.0 * x / w) - 1;                         // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                         // Udaljenost po y-osi od centra (od -1 do 1).
				double d = Math.sqrt(dx*dx + dy*dy);                   // Udaljenost od centra.
				double phi = Math.atan2(dy, dx) * 360 / (2 * Math.PI); // Ugao (0-360)
				
				if (d <= 1) {
					pw.setColor(x, y, Color.hsb(phi, 1, d));
				}
			}
		}
		
		return image;
	}
	
	
	
	public Image imgDisk3() {
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double dx = (2.0 * x / w) - 1;                         // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                         // Udaljenost po y-osi od centra (od -1 do 1).
				double d = Math.sqrt(dx*dx + dy*dy);                   // Udaljenost od centra.
				double phi = Math.atan2(dy, dx) * 360 / (2 * Math.PI); // Ugao (0-360)
				
				if (d <= 1) {
					pw.setColor(x, y, Color.hsb(phi, d, 1));
				}
			}
		}
		
		return image;
	}
	
	
	
	
	// ============================================================================================
	
	
	Image[] images;
	

	@Override
	public void init() {
		images = new Image[] {
				imgFixedHue(),
				imgFixedSaturation(),
				imgFixedBrightness(),
				imgDisk1(),
				imgDisk2(),
				imgDisk3(),
		};
	}
	
	
	@GadgetColorPicker
	Color colorBackground = new Color(0.2, 0.2, 0.2, 1);
	
	@GadgetInteger(min = 0, max = 5)
	int imageIndex = 0;

	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, colorBackground);
		view.drawImageCentered(images[imageIndex], Vector.ZERO);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}

		
}
