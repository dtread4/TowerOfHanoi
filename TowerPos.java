// David Treadwell
// April 7 2023
// Java 17
// TowerPos.java
// Purpose: TowerPos class to store information about each position of a tower and holds discs

package towerOfHanoi;

public class TowerPos {
	
	// CLASS INVARIANTS
	// 1. Starting disc width is used to help center discs as they move between towers by providing a reference to what a
	//    a disc's width at that position would be if the tower were 'full'
	
	public Disc disc;
	public int xPos;
	public int yPos;
	public int startDiscWidth;
	
	// Function:   TowerPos
	// Purpose:    Constructor for TowerPos class
	// Parameters: int for x and y position, and the starting disc width for the tower position
	// Returns:    Nothing
	public TowerPos(int xPos, int yPos, int startDiscWidth) {
		
		// Set internal position
		this.xPos = xPos;
		this.yPos = yPos;
		this.startDiscWidth = startDiscWidth;
		
		// Create a blank disc to go with the tower position
		disc = new Disc(0, 0);
		
	}

}
