package project5;

//imports proper packages needed
import java.util.*;
import project5.Date;
import java.io.*;

/**
 * This class is a runnable program that is responsible for handling and processing command
 * line arguments, reading from a file, creating a CollisionData object that takes in this input,
 * prompting the user, and calling the respective methods for the CollisionData object.
 *
 * @author Nanako Chung
 */
public class CollisionInfo {

	/** 
	 * This is the main method
	 */
	public static void main(String[] args) throws Exception {

		//prints error message if there is no command line input
		if (args.length==0) {
			System.err.println("Error: missing name of the input file");
		} else {

			//creates CollisionsData object to store Collision objects in
			CollisionsData collisions = new CollisionsData();

			try {
			
				//create a new File object based on the file name inputted
				File file = new File(args[0]);

				//use Scanner class to "scan" and read the data from the file line by line
				Scanner reading = new Scanner(file);

				//ignore header
				String line = reading.nextLine();

				//reads all the lines of file
				while (reading.hasNextLine()) {
					line = reading.nextLine();

					//if the line is empty, skip it
					if (line.length()==0) {
						continue;
					}

					//calls splitCSVLine method to properly separate csv values
					//stores them into ArrayList of Strings
					ArrayList<String> data=splitCSVLine(line);

					//if any input is invalid, skip the line
					try { 
						//creates a new Collision object with the line info
						Collision collision = new Collision(data);

						//add to CollisionsData object
						collisions.add(collision);
					} catch (Exception ex) {
						continue;
					}
				}

				//create a new Scanner object to read in the following data input
				Scanner sc = new Scanner(System.in);
				
				//ask the user for a zip
				String zip="";
				while (zip!="quit") {
					System.out.print("Enter a zipcode (\'quit\' to exit): ");
					zip = sc.nextLine();
					
					//checks to see if it is a valid zip
					if (zip.matches("^(\\d{5})$")) {

						//prompts user for start and end date
						System.out.print("Enter start date (MM/DD/YYYY): ");
						String startDate=sc.nextLine();

						System.out.print("Enter end date (MM/DD/YYYY): ");
						String endDate=sc.nextLine();

						//checks to see if dates are valid and catches them if they are not
						try {
							Date validStartDate=new Date(startDate);
							Date validEndDate=new Date(endDate);

							//checks to see if start date is earlier than end date
							if (validStartDate.compareTo(validEndDate)>=0) {
								throw new IllegalArgumentException();
							}

							System.out.println(collisions.getReport(zip, validStartDate, validEndDate));
						} catch (IllegalArgumentException e) {
							System.err.println("Invalid date format. Try again.\n");

							//ask user again for new zip
							continue;
						}
					//exit the loop if any case of the word 'quit' is inputted
					} else if (zip.equalsIgnoreCase("quit")) {
						zip="quit";

					//print error message if zip is not valid
					} else {
						System.err.println("Invalid zip code. Try again.\n");
						
						//ask user again for new zip
						continue;
					}
				}
			} catch (FileNotFoundException ex) {
				System.err.println("Error: the file "+args[0]+" does not exist");
			}
		}
	}

	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may contain commas)
	 * @author Joanna Klukowska
	 * @param textLine	a line of text to be passed
	 * @return an Arraylist object containing all individual entries found on that line
	 */
	public static ArrayList<String> splitCSVLine(String textLine){
		
		ArrayList<String> entries = new ArrayList<String>(); 
		int lineLength = textLine.length(); 
		StringBuffer nextWord = new StringBuffer(); 
		char nextChar; 
		boolean insideQuotes = false; 
		boolean insideEntry= false;
		
		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
					
				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false; 
					insideEntry = false;
				}else {
					insideQuotes = true; 
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if ( insideQuotes || insideEntry ) {
				// add it to the current entry 
					nextWord.append( nextChar );
				}else { // skip all spaces between entries
					continue; 
				}
			} else if ( nextChar == ',') {
				if (insideQuotes){ // comma inside an entry
					nextWord.append(nextChar); 
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			} 
			
		}
		// add the last word ( assuming not empty ) 
		// trim the white space before adding to the list 
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
}