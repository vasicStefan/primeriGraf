package topic2_filters.filters.displacement;

import mars.geometry.Vector;
import topic2_filters.filters.DisplacementFilter;

/**
 * Obrce sliku po x-osi.
 */
public class FlipHorizontal extends DisplacementFilter {
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		// Vracamo poziciju koja je osno simetricna u odosu na srednju vertikalu.
		return new Vector(dim.x - 1 - dst.x, dst.y);
	}
	
}
