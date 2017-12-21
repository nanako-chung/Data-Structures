package project5;

//imports proper packages needed
import project5.Date;
import java.util.*;
import java.lang.*;

/**
 * This class is responsible for processing info about single collisions (i.e. a single valid
 * row from input file).
 *
 * @author Nanako Chung
 */
public class Collision implements Comparable<Collision> {

	//creates private data fields for date, zip, number of people/pedestrians/cyclists/motorists injured/killed, and key
	private Date date;
	private String zip;
	private int personsInjured;
	private int personsKilled;
	private int pedestriansInjured;
	private int pedestriansKilled;
	private int cyclistsInjured;
	private int cyclistsKilled;
	private int motoristsInjured;
	private int motoristsKilled;
	private String key;

	/**
	 * This constructor is responsible for taking in an ArrayList
	 *
	 * @param ArrayList of Strings
	 * @throws IllegalArgumentException date or zip is not valid, injured/killed are negative numbers, or key is empty
	 */
	public Collision(ArrayList<String> entries) throws IllegalArgumentException {

		//if parameter is null or an empty array list or any lines have less than 24 entries or more than 29, throw exception
		if (entries==null || entries.size()==0 || entries.size()<24 || entries.size()>29) {
			throw new IllegalArgumentException();
		}

		//sets the data fields using ArrayList input
		try {
			this.date=new Date(entries.get(0));
			this.key=entries.get(23);
			this.zip=entries.get(3);
			this.personsInjured=Integer.parseInt(entries.get(10));
			this.personsKilled=Integer.parseInt(entries.get(11));
			this.pedestriansInjured=Integer.parseInt(entries.get(12));
			this.pedestriansKilled=Integer.parseInt(entries.get(13));
			this.cyclistsInjured=Integer.parseInt(entries.get(14));
			this.cyclistsKilled=Integer.parseInt(entries.get(15));
			this.motoristsInjured=Integer.parseInt(entries.get(16));
			this.motoristsKilled=Integer.parseInt(entries.get(17));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}

		//throws exception if zip is not valid
		if (zip.matches("\\d{5}")==false) {
			throw new IllegalArgumentException();
		}

		//throws exception if number of people/pedestrians/cyclists/motorists injured/killed are negative
		if (personsInjured<0 || personsKilled<0 || pedestriansInjured<0 || pedestriansKilled<0 || cyclistsInjured<0 || cyclistsKilled<0 || motoristsInjured<0 || motoristsKilled<0) {
			throw new IllegalArgumentException();
		}

		//key cannot be empty
		if (key=="" || key==null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * This method is a getter for the zip code
	 *
	 * @return zip code
	 */
	public String getZip() {
		return this.zip;
	}

	/**
	 * This method is a getter for the date 
	 *
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * This method is a getter for the key
	 *
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * This method is a getter for the number of persons injured
	 *
	 * @return number of persons injured
	 */
	public int getPersonsInjured() {
		return this.personsInjured;
	}

	/**
	 * This method is a getter for the number of pedestrians injured
	 *
	 * @return number of pedestrians injured
	 */
	public int getPedestriansInjured() {
		return this.pedestriansInjured;
	}

	/**
	 * This method is a getter for the number of cyclists injured
	 *
	 * @return number of cyclists injured
	 */
	public int getCyclistsInjured() {
		return this.cyclistsInjured;
	}

	/**
	 * This method is a getter for the number of motorists injured
	 *
	 * @return number of motorists injured
	 */
	public int getMotoristsInjured() {
		return this.motoristsInjured;
	}

	/**
	 * This method is a getter for the number of persons killed
	 *
	 * @return number of persons killed
	 */
	public int getPersonsKilled() {
		return this.personsKilled;
	}

	/**
	 * This method is a getter for the number of pedestrians killed
	 *
	 * @return number of pedestrians killed
	 */
	public int getPedestriansKilled() {
		return this.pedestriansKilled;
	}

	/**
	 * This method is a getter for the number of cyclists killed
	 *
	 * @return number of cyclists killed
	 */
	public int getCyclistsKilled() {
		return this.cyclistsKilled;
	}

	/**
	 * This method is a getter for the number of motorists killed
	 *
	 * @return number of motorists killed
	 */
	public int getMotoristsKilled() {
		return this.motoristsKilled;
	}

	/**
	 * This method overrides the compareTo() method and is specific to Collision objects
	 *
	 * @param Collision object c
	 * @return an int based on respective comparisons
	 */
	@Override
	public int compareTo(Collision c) throws NullPointerException {

		//if c is null, throw an error
		if (c==null) {
			throw new NullPointerException();
		}

		//compares zip and then date (if zip is same) and then key (if date is same)
		if (this.getZip().equals(c.getZip())) {
			if (this.getDate().equals(c.getDate())) {
				return this.getKey().compareToIgnoreCase(c.getKey());
			} else {
				return this.getDate().compareTo(c.getDate());
			}
		} else {
			return this.getZip().compareTo(c.getZip());
		}
	}

	/**
	 * This method overrides the equals() method in the Object class and uses compareTo()
	 *
	 * @param Object o
	 * @return a boolean value based on whether object is found
	 */
	@Override
	public boolean equals(Object o) {

		//if o is null the objects are not equal
		if (o==null) {
			return false;
		}

		//if this object has the same pointer reference in memory as o, they are equal
		if (this == o) {
			return true;
		}

		//if o is not an instance of the Collision class, the objects are not equal
		if (!(o instanceof Collision)) {
			return false;
		}

		//cast object
		Collision other=(Collision) o;

		//use compare method to observe equality
		if (this.compareTo(other)==0) {
			return true;
		} return false;
	}
}