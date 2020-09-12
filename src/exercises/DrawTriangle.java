package exercises;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;


/**
 * Draw isosceles triangles using input int for size of triangle (number of rows)
 * 
 * Eg.numOfRows = 2;
 * 123
 *  o      (1,2)
 * ooo     (2,1),(2,2),(2,3)
 * 
 * Eg.numOfRows = 3;
 * 12345
 *   o     (1,3)
 *  o o    (2,2),(2,4)
 * ooooo   (3,1),(3,2),(3,3),(3,4),(3,5)
 * 
 * Eg. numOfRows = 4;
 * 1234567
 *    o    (1,4)
 *   o o   (2,3),(2,5)
 *  o   o  (3,2),(3,6)
 * ooooooo (4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7)
 * 
 * @author omgitskuei
 *
 */
public class DrawTriangle {

	/**
	 * Generate coordinates of points that make up outline of triangle
	 * 
	 * Eg.
	 * numOfRows = 4
	 * TIP:     o    (1,4)
	 * SIDES:  o o   (2,3),(2,5)
	 *        o   o  (3,2),(3,6)
	 * BASE: ooooooo (4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7)
	 * 
	 * Resulting triangles will have 3 components; (1) tip, (2) sides, (3) base
	 * (1) Tip is always equal to (1, numOfRows)
	 * (2) Number of Sides are always equal to numOfRows-2 because tip and base each use 1 row
	 * (3) Base always has (2*numOfRows-1) points
	 * 
	 * @param numOfRows
	 * @return result - an ArrayList of coordinates (elements eg. "(1,2)")
	 */
	public ArrayList<String> getTrianglePointsCoordinates(Integer numOfRows) {
		ArrayList<String> coordinates = new ArrayList<String>();
		// Validate to make sure input is larger than 2
		if (numOfRows<2) {
			return coordinates;	// Return empty ArrayList
		}
		
		int leftPoint = numOfRows-1;
		int rightPoint = numOfRows+1;
		
		// Loop through making coordinates for each row, based on row number
		for (int currRow = 1; currRow <= numOfRows; currRow++) {
			// Tip
			if (currRow == 1) {
				coordinates.add(String.format("(%d,%d)", 1, numOfRows));
			}
			// Sides
			else if (currRow < numOfRows) {
				coordinates.add(String.format("(%d,%d)", currRow, leftPoint));
				coordinates.add(String.format("(%d,%d)", currRow, rightPoint));
				leftPoint--;
				rightPoint++;
			}
			// Base
			else if (currRow == numOfRows) {
				// Add coordinates for each point in base
				for (int index = 1; index <= (numOfRows*2-1); index++) {
					coordinates.add(String.format("(%d,%d)", currRow, index));
				}
			}
		}
		return coordinates;
	}
	
	/**
	 * Print Triangle from ArrayList of Coordinates
	 * 
	 * Eg. Convert ["(1,4)", "(2,3)", "(2,5)", "(3,2)", "(3,6)", 
	 * "(4,1)", "(4,2)", "(4,3)", "(4,4)", "(4,5)", "(4,6)", "(4,7)"]
	 * to
	 *    o
	 *   o o
	 *  o   o
	 * ooooooo
	 * 
	 * @param coordinates
	 */
	public void printTriangleCoordinates(ArrayList<String> coordinates) {
		Integer lastRow = 0;
		Integer lastColumn = 0;
		Integer numOfRows = 0;
		String result = "";
		
		for (int index = 0; index < coordinates.size(); index++) {
			String eachCoordinate = coordinates.get(index);
			// Extract numbers from Coordinates format
			eachCoordinate = eachCoordinate.replaceAll("[()]", "");
			String[] split = eachCoordinate.split(",");
			Integer row = Integer.valueOf(split[0]);
			Integer column = Integer.valueOf(split[1]);
			// Tip
			if(row==1) {
				// Add spaces
				for(int spacesIndex = 1; spacesIndex < column; spacesIndex++) {
					result = result+" ";
				}
				result = result + "o" + System.lineSeparator();
				numOfRows = column;
				lastRow = row;
				lastColumn = column;
			}
			// Sides
			else if (row < numOfRows) {
				// Left side
				if(row != lastRow) {
					// Add spaces
					for(int spacesIndex = 1; spacesIndex < column; spacesIndex++) {
						result = result+" ";
					}
					result = result + "o";
				}
				// Right side
				else {
					// Add spaces
					for(int spacesIndex = 1; spacesIndex < (column-lastColumn); spacesIndex++) {
						result = result+" ";
					}
					result = result + "o" + System.lineSeparator();
				}
				lastRow = row;
				lastColumn = column;
			}
			// Base
			else if (row==numOfRows) {
				result = result + "o";
			}
		}
		System.out.println(result);
	}
	
	
	
	
	
	class Coordinates {
		private int row;
		private int column;
		// Constructor
		public Coordinates(int row, int column) {
			this.row = row;
			this.column = column;
		}
		// Getters-Setters
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getColumn() {
			return column;
		}
		public void setColumn(int column) {
			this.column = column;
		}
	}
	
	/**
	 * 
	 */
	public ArrayList<Coordinates> getTrianglePointsCoordinatesObj(Integer numOfRows) {
		ArrayList<Coordinates> coordinates = new ArrayList<Coordinates>();
		// Validate to make sure input is larger than 2
		if (numOfRows<2) {
			return coordinates;	// Return empty ArrayList
		}
		
		int leftPoint = numOfRows-1;
		int rightPoint = numOfRows+1;
		
		// Loop through making coordinates for each row, based on row number
		for (int currRow = 1; currRow <= numOfRows; currRow++) {
			// Tip
			if (currRow == 1) {
				coordinates.add(new Coordinates(1, numOfRows));
			}
			// Sides
			else if (currRow < numOfRows) {
				coordinates.add(new Coordinates(currRow, leftPoint));
				coordinates.add(new Coordinates(currRow, rightPoint));
				leftPoint--;
				rightPoint++;
			}
			// Base
			else if (currRow == numOfRows) {
				// Add coordinates for each point in base
				for (int index = 1; index <= (numOfRows*2-1); index++) {
					coordinates.add(new Coordinates(currRow, index));
				}
			}
		}
		return coordinates;
	}
	
	/**
	 * Print Triangle using Coordinates object
	 * @param coordinatesObjs
	 */
	public void printTriangleCoordinatesObj(ArrayList<Coordinates> coordinatesObjs) {
		Integer lastRow = 0;
		Integer lastColumn = 0;
		Integer numOfRows = 0;
		String result = "";
		
		for (int index = 0; index < coordinatesObjs.size(); index++) {
			// Tip
			if(coordinatesObjs.get(index).getRow()==1) {
				// Add spaces
				for(int spacesIndex = 1; spacesIndex < coordinatesObjs.get(index).getColumn(); spacesIndex++) {
					result = result+" ";
				}
				result = result + "o" + System.lineSeparator();
				numOfRows = coordinatesObjs.get(index).getColumn();
				lastRow = coordinatesObjs.get(index).getRow();
				lastColumn = coordinatesObjs.get(index).getColumn();
			}
			// Sides
			else if (coordinatesObjs.get(index).getRow() < numOfRows) {
				// Left side
				if(coordinatesObjs.get(index).getRow() != lastRow) {
					// Add spaces
					for(int spacesIndex = 1; spacesIndex < coordinatesObjs.get(index).getColumn(); spacesIndex++) {
						result = result+" ";
					}
					result = result + "o";
				}
				// Right side
				else {
					// Add spaces
					for(int spacesIndex = 1; spacesIndex < (coordinatesObjs.get(index).getColumn()-lastColumn); spacesIndex++) {
						result = result+" ";
					}
					result = result + "o" + System.lineSeparator();
				}
				lastRow = coordinatesObjs.get(index).getRow();
				lastColumn = coordinatesObjs.get(index).getColumn();
			}
			// Base
			else if (coordinatesObjs.get(index).getRow()==numOfRows) {
				result = result + "o";
			}
		}
		System.out.println(result);
	}
	
	public static void main(String[] args) { 
		DrawTriangle test1 = new DrawTriangle();
		
		Integer numOfRows = 4;
		
		Instant start = java.time.Instant.now();
	    // Method calls;
		ArrayList<String> coordinates = test1.getTrianglePointsCoordinates(numOfRows);
		System.out.println(coordinates);
		test1.printTriangleCoordinates(coordinates);
		//
		Instant end = java.time.Instant.now();
		Duration between = java.time.Duration.between(start, end);
		System.out.format("Program took: %d Days, %02d:%02d:%02d.%04d \n", between.toDays(),
	        between.toHours(), between.toMinutes(), between.getSeconds(), between.toMillis());
		
		
		
		Instant start2 = java.time.Instant.now();
	    // Method calls;
		ArrayList<Coordinates> coordinatesList = test1.getTrianglePointsCoordinatesObj(numOfRows);
		for (Coordinates eachCoordinates : coordinatesList) {
			System.out.print(String.format("(%d,%d)", eachCoordinates.getRow(),eachCoordinates.getColumn()));
		}
		test1.printTriangleCoordinatesObj(coordinatesList);
		//
		Instant end2 = java.time.Instant.now();
		Duration between2 = java.time.Duration.between(start2, end2);
		System.out.format("Program took: %d Days, %02d:%02d:%02d.%04d \n", between2.toDays(),
	        between2.toHours(), between2.toMinutes(), between2.getSeconds(), between2.toMillis());
	}
}
