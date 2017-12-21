package project1;

//import necessary packages
import java.util.ArrayList;

/**
 * This class extends the ArrayList class and thus inherits all of its methods. This allows us
 * to use the ColorList class to create an ArrayList of Color objects.
 * 
 * @author Nanako Chung
 */
public class ColorList extends ArrayList<Color> {

	/**
	  * Takes the colorName in the parameter and searches through ArrayList to see if there is a Color object
	  * that also has that name. 
	  *
	  * @param colorName name of Color object to search for in the ArrayList
	  * 
	  * @return null if there is no Color object with that name; Color object that has that name if found in ArrayList
	  */
	public Color getColorByName(String colorName) {

		//iterate over each of the Color objects in ArrayList
		for (int i=0; i<super.size(); i++) {

			//checks to see if the name of the Color is not null
			//this is important because if the name IS null, then we cannot use the .equalsIgnoreCase() method since null cannot be used in methods
			if (super.get(i).getName()!=null) {
			
			//if any of the Color objects' names is the same as the current Color object's name, return this Color
				if (super.get(i).getName().equalsIgnoreCase(colorName)) {
					return super.get(i);
				}
			} 
		} 

	//no color in ArrayList with this name
	return null;
	}
	
	/**
	  * Takes the colorHexValue in the parameter and searches through ArrayList to see if there is a Color object
	  * that also has that hex value. 
	  *
	  * @param colorHexValue hex value of Color object to search for in the ArrayList
	  * 
	  * @return null if there is no Color object with that hex value; Color object that has that hex value if found in ArrayList
	  */
	public Color getColorByHexValue(String colorHexValue) {

		//iterate over each of the Color objects in ArrayList
		for (int i=0; i<super.size(); i++) {
			
			//if any of the Color objects' hex values is the same as the current Color object's hex value, return this Color
			if (super.get(i).getHexValue().equalsIgnoreCase(colorHexValue)) {
				return super.get(i);
			}
		} 

	//no color in ArrayList with this hexidecimal value
	return null;
	}
}