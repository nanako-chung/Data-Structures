package project2;

/**
 * This class, which implements the Comparable interface, is responsible for creating Color objects and
 * assigning each object's corresponding hex value, RGB values, and name value (if applicable). All of the
 * constructors are designed to handle incorrectly-formatted hexidecimal values that are inputted.
 * 
 * @author Nanako Chung
 */
public class Color implements Comparable<Color> {

	//private data field colorHexValue is an empty String (NOT null) because there will always be a hex value assigned to a Color object
	private String colorHexValue="";

	//private data field that assumes that there is no name assigned to the Color object at first and is, thus, null
	private String colorName=null;

	//private data field of RGB values
	private int red=0;
	private int green=0;
	private int blue=0;

	/**
	  * Creates an object with the inputted colorHexValue as its hexidecimal value
	  *
	  * @param colorHexValue hex value for the object
	  *
	  * @throws IllegalArgumentException if the hex value is not in the correct format
	  */
	public Color(String colorHexValue) throws IllegalArgumentException {

		//input validation for hex value
		//hex value that gets assigned to the object is in #XXXXXX (all upper case) format
		if (colorHexValue.matches("#([0-9A-Fa-f]{6})")) {

			this.colorHexValue=colorHexValue.toUpperCase();

			//calls hex to RGB converter method
			hexToRgb(colorHexValue);
		} else {

			//throws exception if the format is not matched
			throw new IllegalArgumentException("Invalid hexidecimal value");
		}
	}

	/**
	  * Creates a Color object with the inputted colorHexValue as its hexidecimal value and
	  * inputted colorName as its name
	  *
	  * @param colorHexValue hexidecimal for the object
	  * @param colorName 	 name of color object
	  *
	  * @throws IllegalArgumentException if the hex value is not in the correct format
	  */
	public Color (String colorHexValue, String colorName) throws IllegalArgumentException {

		//sets Color object's name to inputted name value
		this.colorName=colorName;

		//input validation for hex value
		//hex value that gets assigned to the object is in #XXXXXX (all upper case) format
		if (colorHexValue.matches("#([0-9A-Fa-f]{6})")) {

			this.colorHexValue=colorHexValue.toUpperCase();

			//calls hex to RGB converter method
			hexToRgb(colorHexValue);
		} else {
			
			//throws exception if the format is not matched
			throw new IllegalArgumentException("Invalid hexidecimal value");
		}
	}

	/**
	  * Creates an object with the inputted RGB values
	  *
	  * @param red   amount of red in Color object from 0-255
	  * @param green amount of green in Color object from 0-255
	  * @param blue  amount of blue in Color object from 0-255
	  *
	  * @throws IllegalArgumentException if the value is not between 0-255
	  */
	public Color (int red, int green, int blue) throws IllegalArgumentException{
		
		//checks to see if each RGB value is within range
		if ((red<=255 && red>=0) && (green<=255 && green>=0) && (blue<=255 && blue>=0)) {
			this.red=red;
			this.green=green;
			this.blue=blue;

			//calls RGB to hex converter method
			rgbToHex(red, green, blue);

			//colorName is set
		} else {
			
			//throws exception if RGB value is not within range
			throw new IllegalArgumentException("Invalid color value");
		}
	}

	/**
	  * A getter method to retrieve Color object's red RGB value
	  *
	  * @return red value in the form of RGB (from 0-255)
	  */
	public int getRed() {

		//returns the object's red value
		return this.red;
	}

	/**
	  * A getter method to retrieve Color object's green RGB value
	  *
	  * @return green value in the form of RGB (from 0-255)
	  */
	public int getGreen() {

		//returns the object's green value
		return this.green;
	}

	/**
	  * A getter method to retrieve Color object's blue RGB value
	  *
	  * @return blue value in the form of RGB (from 0-255)
	  */
	public int getBlue() {

		//returns the object's blue value
		return this.blue;
	}

	/**
	  * A getter method to retrieve Color object's name
	  *
	  * @return object's name
	  */
	public String getName() {

		//returns the object's name
		return this.colorName;
	}

	/**
	  * A getter method to retrieve Color object's hexidecimal value
	  *
	  * @return object's hexidecimal value
	  */
	public String getHexValue() {

		//returns the object's hexidecimal value
		return this.colorHexValue;
	}

	/**
	  * Overrides the compareTo() method in the Comparable interface using the String class's compareToIgnoreCase() method
	  *
	  * @param otherCol Color object to compare current Color object to regardless of case
	  *
	  * @return 0 if Colors are equal, -1 if Color is less than current Color object, 1 if Color is greater than current Color object
	  */
	@Override
	public int compareTo(Color otherCol) {

		//returns an int that determines which hexidecimal value is greater/lesser
		return this.colorHexValue.compareToIgnoreCase(otherCol.getHexValue());
	}

	/**
	  * Overrides equals() method in the Object class
	  *
	  * @param any Object obj (cannot be of type Color because then the method is overloading, NOT overriding)
	  *
	  * @return a boolean value that is true if the Color objects are equal in hex value
	  */
	@Override
	public boolean equals(Object obj) {

		//checks to see if the reference of current object is the same as obj
		if (this==obj) {
			return true;
		} 

		//if the reference of current object and obj are not the same, check to see if obj is null
		if (obj==null) {
			return false;
		} 

		//checks to see if obj is an instance of the Color class (false if it is not because it cannot be equal to current Color object)
		//and castes it into a Color object		
		if (!(obj instanceof Color)) {
			return false;
		} Color other = (Color) obj;
		
		//if the hex value of the current Color object is null, check to see if hex value of the other Color object is NOT null
		if (colorHexValue==null) {
			if (other.getHexValue()!=null) {
				return false;
			} 
		} 

		//checks to see if hex values of each Color object are equal regardless of the alphabetical case
		else if (!colorHexValue.equalsIgnoreCase(other.colorHexValue)) {
			return false;
		} return true;
	}

	/**
	  * Overrides toString() method in the String class
	  *
	  * @return a String value that displays all the color information about the Color object in proper formatting
	  */
	@Override
	public String toString() {

		//if the object's color name is empty or null, do not display name; otherwise, display all of the object's information
		if (colorName=="" || colorName==null) {
			return (this.colorHexValue+", ("+String.format("%3d",this.red)+","+String.format("%3d",this.green)+","+String.format("%3d",this.blue)+")");
		}
		else {
			return (this.colorHexValue+", ("+String.format("%3d",this.red)+","+String.format("%3d",this.green)+","+String.format("%3d",this.blue)+"), "+this.colorName);
		}
	}

	/**
	  * Converts an inputted colorHexValue to RGB value and sets the values to the object's respective color (red, green, or blue)
	  *
	  * @param colorHexValue Color object's hexidecimal value
	  */
	public void hexToRgb(String colorHexValue) {

		//converts and sets the RR/GG/BB values in hex to respective RGB values
		this.red=Integer.parseInt(colorHexValue.substring(1,3), 16);
		this.green=Integer.parseInt(colorHexValue.substring(3,5), 16);
		this.blue=Integer.parseInt(colorHexValue.substring(5,7), 16);
	}

	/**
	  * Converts inputted RGB value to hexidecimal value and sets this to the object's hex value
	  *
	  * @param red   amount of red in Color object from 0-255
	  * @param green amount of green in Color object from 0-255
	  * @param blue  amount of blue in Color object from 0-255
	  */
	public void rgbToHex(int red, int green, int blue) {

		//converts and sets the RGB values to respective hexidecimal values
		String redHex=String.format("%02X", red);
		String greenHex=String.format("%02X", green);
		String blueHex=String.format("%02X", blue);

		this.colorHexValue=("#"+redHex.toUpperCase()+greenHex.toUpperCase()+blueHex.toUpperCase());
	}
}