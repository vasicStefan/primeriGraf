package topic2_filters.filters.displacement;

import mars.geometry.Vector;
import topic2_filters.filters.DisplacementFilter;


/**
 * Uvelicava gornji levi cosak slike koristeci zadati zoom faktor.
 */
public class Zoom extends DisplacementFilter {
	final double k;
	
	
	public Zoom(double k) {
		this.k = k;
	}
	
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		// Delimo obe koordinate vektora sa k.
		return dst.div(k);
	}
	
}
