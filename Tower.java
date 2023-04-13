// David Treadwell
// April 7 2023
// Java 17
// TowerPos.java
// Purpose: Used as each tower and contains tower positions

package towerOfHanoi;
import java.awt.*;

public class Tower {
	
	public TowerPos tower[];
	public int maxDiscWidth;
	
	public int n;
	
	// Function:      Tower
	// Purpose:       Constructor for Tower class
	// Parameters:    int n for number of discs, int for base width and total height of tower, and int for start x/y position
	// Returns:       Nothing
	// Preconditions: n =< 10 and n >=3
	public Tower(int n, int baseWidth, int towerHeight, int firstXpos, int firstYpos) {
		
		this.n = n;
		
		tower = new TowerPos[n];
		
		// Don't want to use full length of tower, so reduce it by 1/4 (make it 3/4 original height)
		towerHeight = towerHeight * 3 / 4;
		
		// Disc height set by the 3/4 towerHeight (this done above) divided by 10
		discHeight = towerHeight / n;
		
		// Subtract discHeight from firstYpos to put first disc above tower structure's base
		firstYpos -= discHeight;
		
		// Will use this to reduce the size of each disc by 1/10 the total width of base,
		// with the highest disc being the smallest.
		discWidthScale = baseWidth / n;
		
		// Get the maximum width of a disc, which is the baseWidth minus the scale times the difference of 10 and n
		// So that discs are always the same size, even if using smaller n
		maxDiscWidth = baseWidth;
		
		// Create new TowerPos object at each slot of the array, calculating their x/y positions
		for (int i = 0; i < n; i++) {
			
			// Calculate expected first disc width at each position
			int startDiscWidth = maxDiscWidth - (i * discWidthScale);
			
			int xPos = firstXpos + (i * discWidthScale / 2);
			int yPos = firstYpos - (discHeight * i);
			tower[i] = new TowerPos(xPos, yPos, startDiscWidth);
			tower[i].disc.height = discHeight;
			
			// Want each disc to have a default white color so it doesn't show
			tower[i].disc.color = new Color(255, 255, 255);
			
		}
		
	}
	
	// Function:   addDisc
	// Purpose:    Adds a disc to the next available spot in the tower
	// Parameters: None
	// Returns:    Nothing
	public void addDisc() {
		
		if (numberDiscs < n) {
			
			// Find first empty discs position
			int i = 0;
			while (tower[i].disc.width != 0) {
				
				i++;
				
			}
			
			// Set the disc's color
			tower[i].disc.color = setColor(i);
			
			// Set width of that disc to maxDiscWidth minus the xPos of that slot
			tower[i].disc.width = maxDiscWidth - (i * discWidthScale);
			
			numberDiscs++;
			
		}
		
	}
	
	// Function:   setColor
	// Purpose:    Sets the color of a disk, depending on which disk it is
	// Parameters: The index of the disc
	// Returns:    Updated disc
	private Color setColor(int i) {
		
		// Default color is black, and will be returned if i == 9
		Color c = new Color(0, 0, 0);
		
		// 1. Red
		if (i == 0) {
			
			c = new Color(204, 0, 0);
			
		}
		// 2. Orange
		if (i == 1) {
			
			c = new Color(255, 128, 0);
			
		}
		// 3. Yellow
		if (i == 2) {
			
			c = new Color(255, 255, 0);
			
		}
		// 4. Green
		if (i == 3) {
			
			c = new Color(0, 153, 0);
			
		}
		// 5. Blue
		if (i == 4) {
			
			c = new Color(0, 0, 255);
			
		}
		// 6. Indigo
		if (i == 5) {
			
			c = new Color(0, 0, 102);
			
		}
		// 7. Violet
		if (i == 6) {
			
			c = new Color(102, 0, 204);
			
		}
		// 8. Brown
		if (i == 7) {
			
			c = new Color(92, 64, 51);
			
		}
		// 9. Grey
		if (i == 8) {
			
			c = new Color(64, 64, 64);
			
		}
			
		return c;
		
	}
	
	private int discWidthScale;
	private int discHeight;
	private int numberDiscs;
	
}
