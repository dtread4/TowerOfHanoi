// David Treadwell
// April 7 2023
// Java 17
// Disc.java
// Purpose: Disc class to store information about each disc
//          Using the width to properly draw each disc when recursively looping through solve function


package towerOfHanoi;
import java.awt.*;

public class Disc {
	
	public int width;
	public int height;
	public Color color;
	
	// Function:   Disc
	// Purpose:    Constructor for Disc class
	// Parameters: int for width and height
	// Returns:    Nothing
	public Disc(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		// Default color is black
		color = Color.black;
		
	}

}
