package project2;

/**
 * This class extends the OrderedLinkedList class and thus inherits all of its methods. This allows us
 * to use the ColorList class to create an OrderedLinkedList of Color objects.
 * 
 * @author Nanako Chung
 */
public class ColorList extends OrderedLinkedList<Color> {

	/**
	  * Takes the colorName in the parameter and searches through OrderedLinkedList to see if there is a Color object
	  * that also has that name. 
	  *
	  * @param colorName name of Color object to search for in the OrderedLinkedList
	  * @return null if there is no Color object with that name; Color object that has that name if found in OrderedLinkedList
	  * @throws NullPointerException if the retrieved object at index is null
	  */
	public Color getColorByName(String colorName) throws NullPointerException {

		//checks to see if list is empty
		if (super.size()==0) {
			return null;
		}

		//iterate over each of the Color objects in OrderedLinkedList
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

	//no color in OrderedLinkedList with this name
	return null;
	}
	
	/**
	  * Takes the colorHexValue in the parameter and searches through OrderedLinkedList to see if there is a Color object
	  * that also has that hex value. 
	  *
	  * @param colorHexValue hex value of Color object to search for in the OrderedLinkedList
	  * @return null if there is no Color object with that hex value; Color object that has that hex value if found in OrderedLinkedList
	  * @throws NullPointerException if the retrieved object at index is null
	  */
	public Color getColorByHexValue(String colorHexValue) throws NullPointerException {

		//checks to see if list is empty
		if (super.size()==0) {
			return null;
		}
		
		//iterate over each of the Color objects in OrderedLinkedList
		for (int i=0; i<super.size(); i++) {
			
			//if any of the Color objects' hex values is the same as the current Color object's hex value, return this Color
			if (super.get(i).getHexValue().equalsIgnoreCase(colorHexValue)) {
				return super.get(i);
			}
		} 
		
		//no color in OrderedLinkedList with this hexidecimal value
		return null;
	}
}