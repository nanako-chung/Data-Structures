package project1;

//imports necessary packages
import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * This class contains the main method, which is responsible for opening and reading a file 
 * that the user provides the name of in the command line. It is also responsible for handling
 * errors with corresponding messages and performing validation.
 * 
 * @author Nanako Chung
 */
public class ColorConverter {

/**
  * Reads inputted txt file name from the command line, opens it, and reads it using File IO.
  * 
  * Asks the user for a hex value; if hex value is found in the data file, print the color's
  * respective information; if hex value is not found, add it to the colorList and provide 
  * its information.
  * 
  * Handles exceptions accordingly (see below).
  * 
  * @throws IllegalArgumentException if there is nothing inputted through the command line
  * 
  * @throws FileNotFoundException if file is not found in system
  */
	public static void main(String[] args) {
		
		try {

			//if there is no command line input, throw an exception
			if (args.length==0) {
				throw new IllegalArgumentException();
			} else {
				try {

					//create a new File object based on the file name inputted
					File list = new File(args[0]);

					//test to see if file actually exists; if not, throw an exception
					if (list.exists()==false) {
						throw new FileNotFoundException();
					} else {

						//create a new object ColorList (an ArrayList of Color objects)
						ColorList colorList = new ColorList();

						//use Scanner class to "scan" and read the data from the file line by line
						Scanner reading = new Scanner(list);

						while (reading.hasNextLine()) {
							String line = reading.nextLine();

							//if the line is empty, skip it
							if (line.length()==0) {
								continue;
							}

							//get rid of all the spaces in each line and create an array with only the color names and color hex values
							String newLine=line.replace(" ","");
							String[] lines=newLine.split(",");

							//based on the data in the lines array above, create a new Color object and add it to the colorList
							Color color = new Color(lines[1], lines[0]);
							colorList.add(color);
						}

						//create a new Scanner object to read in the following data input
						Scanner sc = new Scanner(System.in);
						
						//ask the user for a hex color, validate it, and create a new Color object for it
						String hex="";
						while (hex!="quit") {
							System.out.print("\nEnter the color in HEX format (#RRGGBB) or \"quit\" to stop:\n");
							hex = sc.nextLine();
							
							if (hex.matches("#([0-9A-Fa-f]{6})")) {
								Color col=new Color(hex);

								//if col's hex value does not match, add otherColor to colorList and display its information (without name)
								Color otherColor=colorList.getColorByHexValue(hex);
								if (otherColor==null) {
									colorList.add(col);
									System.out.println("\nColor information:\n"+col.toString()+"\n");
								
								//if col's hex value matches that of otherColor's in colorList, display the otherColor's information
								} else if (otherColor.equals(col)){
									System.out.println("\nColor information:\n"+otherColor.toString()+"\n");
								}	

							//exit the loop if any case of the word 'quit' is inputted
							} else if (hex.equalsIgnoreCase("quit")) {
								hex="quit";

							//print error message if hex is not valid
							} else {
								System.err.println("\nError: This is not a valid color specification.\n");
								
								//keep asking the user if color is not valid
								continue;
							}
						}
					}

				//catches FileNotFoundException if the file name inputted does not exist
				} catch (FileNotFoundException ex) {
					System.err.println("\nError: the file "+args[0]+" cannot be opened\n");
				}				
			}
		
		//catches IllegalArgumentException if there is no command line inputs and prints respective message
		} catch (IllegalArgumentException e) {
			System.err.println("Usage Error: the program expects file name as an argument\n");
		}
	}
}