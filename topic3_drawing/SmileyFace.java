package topic3_drawing;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;


public class SmileyFace implements Drawing {

	@GadgetDouble(min = 0, max = 200)
	Double xEye = 70.0;                    // Udaljenost oka od centra po x-osi.

	@GadgetDouble(min = 0, max = 300)
	Double yEye = 70.0;                    // Udaljenost oka od centra po y-osi.

	@GadgetDouble(min = 0, max = 100)
	Double rEye = 30.0;                    // Poluprecnik oka.

	@GadgetDouble(min = 0, max = 200)
	Double rMouth = 130.0;                 // Poluprecnik luka usta.
	
	@GadgetDouble(min = 0, max = Math.PI)
	Double phiMouth = 2*Math.PI/3;         // Ugao luka usta.
	
	

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.2));

		view.setFill(Color.hsb(60, 0.9, 0.9));
		view.fillCircleCentered(new Vector(0, 0), 200);
		
		view.setFill(Color.hsb(0, 0, 0));
		view.fillCircleCentered(new Vector(-xEye, yEye), rEye);		
		view.fillCircleCentered(new Vector(xEye, yEye), rEye);
		view.setLineWidth(30);
		view.setStroke(Color.hsb(0, 0, 0));
		view.strokeArcCentered(new Vector(0, 0), new Vector(rMouth), 3*Math.PI/2-phiMouth/2, phiMouth);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
