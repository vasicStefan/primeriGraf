package topic2_filters.filters.displacement;

import mars.geometry.Vector;
import topic2_filters.filters.DisplacementFilter;

/**
 * Obrce sliku po y-osi.
 */
public class FlipVertical extends DisplacementFilter {
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		// Vracamo poziciju koja je osno simetricna u odosu na srednju horizontalu.
		return new Vector(dst.x, dim.y - 1 - dst.y);
	}
	
}
