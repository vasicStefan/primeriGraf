package topic3_drawing;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;


public class SemaphoreSign implements Drawing {

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.5));
		
		double a = 200;
		Vector[] corners = new Vector[] {
				new Vector(a, 0),
				new Vector(0, a),
				new Vector(-a, 0),
				new Vector(0, -a),
		};
		
		view.setFill(Color.hsb(60, 0.7, 0.96));
		view.fillPolygon(corners);
		
		view.setLineWidth(20);
		view.setStroke(Color.hsb(0, 0, 0));
		view.strokePolygon(corners);
		
		double r = 32;
		double d = 12;
				
		view.setFill(Color.hsb(0, 0, 0));
		view.fillRectCentered(Vector.ZERO, new Vector(r+d, 3*r+2*d));
		
		view.setFill(Color.hsb(0, 0.7, 0.96));
		view.fillCircleCentered(new Vector(0, 2*r+d), r);

		view.setFill(Color.hsb(60, 0.7, 0.96));
		view.fillCircleCentered(new Vector(0, 0), r);
		
		view.setFill(Color.hsb(120, 0.7, 0.96));
		view.fillCircleCentered(new Vector(0, -2*r-d), r);
	}
	
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
