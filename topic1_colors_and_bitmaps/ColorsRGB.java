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

public class ColorsRGB implements Drawing {

	public Image imgSolidColor() {
		// Svi pikseli su ljubicasti.
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, Color.PURPLE);
			}
		}
		
		return image;
	}

	
	public Image imgLinearGradient() {
		// Linearni gradijent po x-osi od crne do plave.  
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, new Color(0, 0, x / image.getWidth(), 1));
			}
		}
		
		return image;
	}
	
	
	public Image imgLinearGradient2() {
		// Linearni gradijent crvene po x-osi i zelene po y-osi.  
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				pw.setColor(x, y, new Color(x / image.getWidth(), y / image.getHeight(), 0, 1));
			}
		}
		
		return image;
	}
	
	

	public Image imgRadialGradient() {
		// Radijalni gradijent sive.
		
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double dx = (2.0 * x / w) - 1;                // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                // Udaljenost po y-osi od centra (od -1 do 1).
				double s = Math.sqrt(dx*dx + dy*dy);          // Udaljenost od centra.
				if (s > 1) {
					s = 1.0;
				}
				pw.setColor(x, y, Color.gray(1-s));           // Isto sto i new Color(1-s, 1-s, 1-s, 1).
			}
		}
		
		return image;
	}


	public Image imgRadialGradientOpacity() {
		// Radijalni gradijent providnosti.
		
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double dx = (2.0 * x / w) - 1;                // Udaljenost po x-osi od centra (od -1 do 1).
				double dy = (2.0 * y / h) - 1;                // Udaljenost po y-osi od centra (od -1 do 1).
				double s = Math.sqrt(dx*dx + dy*dy);          // Udaljenost od centra.
				if (s > 1) {
					s = 1.0;
				}
				pw.setColor(x, y, new Color(1, 1, 1, 1-s));
			}
		}
		
		return image;
	}

	
	public Image imgWave() {
		// Intenzitet boje je talasne funkcije po x osi. 
		
		WritableImage image = new WritableImage(500, 200);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double s = Math.cos(2 * Math.PI * (x + 50) / 100) / 2 + 0.5; 
				pw.setColor(x, y, Color.gray(s));
			}
		}
		
		return image;
	}
	
	
	public Image imgWaves() {
		// Crvena i zelena komponenta su talasne funkcije po x odnosno po y, plava je uvek 1. 
		
		WritableImage image = new WritableImage(500, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				double r = Math.cos(2 * Math.PI * x / 100) / 2 + 0.5; 
				double g = Math.cos(2 * Math.PI * y / 100) / 2 + 0.5;
				double b = 1;
				pw.setColor(x, y, new Color(r, g, b, 1));
			}
		}
		
		return image;
	}

	
	public Image imgDiagonals() {
		// Crvena i zelena komponenta su funkcija dijagonalnih udaljenosti (x+y, odnosno x-y osa).
		// Kompononte boja se "pale" i "gase" periodicno duz tih osa. Plava je uvek 0.5.

		WritableImage image = new WritableImage(500, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				float r = (x + y) % 200 > 100 ? 0 : 1; 
				float g = (x - y + 400) % 200 > 100 ? 0 : 1;
				float b = 0.5f;
				pw.setColor(x, y, new Color(r, g, b, 1));
			}
		}
		
		return image;
	}

	
	
	// ============================================================================================
	
	
	Image[] images;
	

	@Override
	public void init() {
		images = new Image[] {
			imgSolidColor(),
			imgLinearGradient(),
			imgLinearGradient2(),
			imgRadialGradient(),
			imgRadialGradientOpacity(),
			imgWave(),
			imgWaves(),
			imgDiagonals(),
		};
	}
	
	
	@GadgetColorPicker
	Color colorBackground = new Color(0.2, 0.2, 0.2, 1);
	
	@GadgetInteger(min = 0, max = 7)
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
