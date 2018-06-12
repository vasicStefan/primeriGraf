package topic2_filters.filters;

import javafx.scene.image.Image;


/**
 * Filter je funkcija transformacije slike. Za zadatu ulaznu sliku primenom filtera dobijamo izlaznu sliku. 
 */
public abstract class Filter {
	public abstract Image process(Image input);
}
