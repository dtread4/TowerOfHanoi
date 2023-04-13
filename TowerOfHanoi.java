// David Treadwell
// April 7 2023
// Java 17
// TowerOfHanoi.java
// Purpose: Creates a visual demonstration of the famous 'Tower of Hanoi' problem.
//          I built this after learning about the 'Tower of Hanoi' puzzle in my CPSC 5031 - Algorithms class at
//          Seattle University, and thought this would be an interesting way to reinforce the concepts and try something
//          new by creating a visualized algorithm.
//          Used Java because of easy (and already known) graphics capabilities

package towerOfHanoi;

import java.awt.*;
import javax.swing.JOptionPane;

public class TowerOfHanoi {

	public static void main(String[] args) throws InterruptedException {
		
		// Get a value of n
		int n = getN();
		
		// Setup graphics using DrawingPanel
		int panelWidth = 1000;
		int panelHeight = 500;
		DrawingPanel drawingPanel = new DrawingPanel(panelWidth, panelHeight);
		
		// Create the template for the towers
		Graphics graphics = drawingPanel.getGraphics();
		
		// Create the three towers
		Tower tower1 = createTower(panelWidth, panelHeight, n, 1);
		Tower tower2 = createTower(panelWidth, panelHeight, n, 2);
		Tower tower3 = createTower(panelWidth, panelHeight, n, 3);
		
		// Add discs to the first tower
		tower1 = addStartingDiscs(tower1);
		
		// Draw the original panel
		drawAll(tower1, tower2, tower3, graphics, panelWidth, panelHeight);
		
		// Call the towerOfHanoi
		towerOfHanoi(n, n, tower1, tower3, tower2, graphics, panelWidth, panelHeight);
		
		// Draw a white box over "Running..." and then write "Finished!"
		// Use fontSize as basis for each character, with multiples when drawing the white rectangle to ensure all of
		// "Running..." is covered
		int fontSize = 24;
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, fontSize * 10, fontSize * 2);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Monospaced", Font.BOLD, fontSize));
		graphics.drawString("Finished!", fontSize, fontSize);

	}
	
	// Function:   getN
	// Purpose:    Prompts the user for a value of n to test within a valid range
	// Parameters: None
	// Returns:    Integer n to run algorithm with
	public static int getN() {
		
		int n = 0;
		String input;
		
		// While n < 3 || n > 10, keep prompting user for number
		while (n < 3 || n > 10) {
			
			try {
				input = JOptionPane.showInputDialog(null, "Please enter a number of discs to test with: ");
				
				// If user entered a null value, terminate
				if (input == null) {
					
					System.exit(0);
					
				} else {
					
					n = Integer.parseInt(input);
					
				}
				
				// Error message if user enters an integer out of bounds
				if (n < 3 || n > 10) {
					
					JOptionPane.showMessageDialog(null, "ERROR: Please enter a number between 3 and 10", null, 0, null);
					
				}
				
			} catch (Exception NumberFormatException) {
				
				// Error message if user does not enter an integer
				JOptionPane.showMessageDialog(null, "ERROR: Please enter a number between 3 and 10", null, 0, null);
				
			}			
			
		}
		
		return n;
		
	}
	
	// Function:      drawBackground
	// Purpose:       Draws the background template for towers
	// Parameters:    Graphics object, panel width and height
	// Returns:       None
	public static void drawBackground(Graphics graphics, int panelWidth, int panelHeight) {
		
		// Always draw three towers
		for (int i = 1; i <= 3; i++) {
			
			// Create the color brown for the tower location file
			Color brown = new Color(150, 75, 0);
			
			// Use color gray for the fill of the tower locations
			graphics.setColor(brown);
			
			// Draw the base. Everything will be statically related to width/height of panel except x position, which changes
			// depending on the iteration of the loop.
			// baseXpos is 1/6 the panel width, plus a spacer of 100 (multiply i-1 by 100 to keep positioning correct)
			int baseXpos = i * panelWidth / 6 + (i - 1) * 100;
			int baseYpos = panelHeight * 2 / 3;
			int baseWidth = panelWidth / 6;
			int baseHeight = 15;
			graphics.fillRect(baseXpos, baseYpos, baseWidth, baseHeight);
			
			// Create an outline of the same rectangle
			graphics.setColor(Color.black);
			graphics.drawRect(baseXpos, baseYpos, baseWidth, baseHeight);
			
			// Add an identifying character below the tower. Character uses ASCII values and value of i to print A, B, or C
			// Subtract fontSize /4 from the calculated xPos to center the letter
			int fontSize = 24;
			graphics.setFont(new Font("Monospaced", Font.BOLD, fontSize));
			graphics.drawString(String.valueOf((char)(65 + (i - 1))), 
					baseXpos + (baseWidth / 2) - (fontSize / 4), baseYpos + fontSize * 2);
			
			// Draw a string in the top left corner to communicate that the program is in progress
			graphics.drawString("Running...", fontSize, fontSize);
			
			// Use color gray for the fill of the tower locations
			graphics.setColor(brown);
			
			// Draw the a tall rectangle in the center of each base
			// to help visualize movement of rings (typical for TOH visualization)
			// Subtract baseHeight / 2 on centerXpos to put center of pillar in center
			int centerXpos = baseXpos + baseWidth / 2 - baseHeight / 2;
			int centerYpos = panelHeight - baseYpos;
			int centerWidth = baseHeight;
			int centerHeight = panelWidth / 6;
			graphics.fillRect(centerXpos, centerYpos, centerWidth, centerHeight);
			
			// Create an outline of the same rectangle
			graphics.setColor(Color.black);
			graphics.drawRect(centerXpos, centerYpos, centerWidth, centerHeight);
			
		}
		
	}
	
	// Function:      createTower
	// Purpose:       Creates a tower object
	// Parameters:    Panel width and height, number of discs, tower number
	// Returns:       A Tower object
	// Preconditions: towerNum should only be 1, 2, or 3, depending on which tower is being created
	public static Tower createTower(int panelWidth, int panelHeight, int n, int towerNum) {
		
		// Draw the base. Everything will be statically related to width/height of panel except x position, which changes
		// depending on the iteration of the loop.
		// baseXpos is 1/6 the panel width, plus a spacer of 100 (multiply i-1 by 100 to keep positioning correct)
		int baseXpos = towerNum * panelWidth / 6 + (towerNum - 1) * 100;
		int baseYpos = panelHeight * 2 / 3;
		int baseWidth = panelWidth / 6;
		
		// Draw the a tall rectangle in the center of each base
		// to help visualize movement of rings (typical for TOH visualization)
		// Subtract baseHeight / 2 on centerXpos to put center of pillar in center
		int centerHeight = panelWidth / 6;
		
		// Create a Tower object using the coordinates defined above
		Tower returnTower = new Tower(n, baseWidth, centerHeight, baseXpos, baseYpos);
		return returnTower;
		
	}
	
	// Function:   addStartingDiscs
	// Purpose:    Adds n discs to the tower passed in
	// Parameters: Tower to add discs to
	// Returns:    A Tower object
	public static Tower addStartingDiscs(Tower tower) {
		
		// Add the discs to the tower
		for (int i = 0; i < tower.n; i++) {
			
			tower.addDisc();
			
		}
		
		return tower;
		
	}
	
	// Function:   drawTower
	// Purpose:    Draws the tower passed in
	// Parameters: Tower to draw, graphics object
	// Returns:    Nothing
	public static void drawTower(Tower tower, Graphics graphics) {
		
		// Draw the discs for the first tower
		for (int i = 0; i < tower.n; i++) {
			
			// Check that the disc width isn't 0 so empty discs are not drawn
			if (tower.tower[i].disc.width != 0) {
				
				// Subtract half of the difference between current disc width and starting disc width for that tower position 
				// to the x position to center discs
				int tempXpos = tower.tower[i].xPos - ((tower.tower[i].disc.width - tower.tower[i].startDiscWidth) / 2);
				
				// Draw the inner rectangle
				graphics.setColor(tower.tower[i].disc.color);
				graphics.fillRect(tempXpos, tower.tower[i].yPos, 
						tower.tower[i].disc.width, tower.tower[i].disc.height);
				
				// Add a black outline
				graphics.setColor(Color.BLACK);
				graphics.drawRect(tempXpos, tower.tower[i].yPos, 
						tower.tower[i].disc.width, tower.tower[i].disc.height);
				
			}
			
		}
		
	}
	
	// Function:   drawAll
	// Purpose:    Draws the image
	// Parameters: The three towers, graphics object, and panel width and height
	// Returns:    A Tower object
	public static void drawAll(Tower tower1, Tower tower2, Tower tower3, Graphics graphics, int panelWidth, int panelHeight) {
		
		// Erase everything by drawing a white box over everything
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, panelWidth, panelHeight);
		
		// Draw the background towers
		drawBackground(graphics, panelWidth, panelHeight);
		
		// Draw the three towers' discs
		drawTower(tower1, graphics);
		drawTower(tower2, graphics);
		drawTower(tower3, graphics);
		
	}
	
	// Function:   getTopIndex
	// Purpose:    Gets the index of the topmost disc in a tower, or -1 if the tower is empty
	// Parameters: Tower to find the topmost disc's index
	// Returns:    integer index
	public static int getTopIndex(Tower tower, int nOrg) {
		
		// Loop through tower until the disc is 0, or the end of the tower is reached. Empty disc will have width == 0
		int i = 0;
		while(i < tower.n && tower.tower[i].disc.width != 0) {
			
			i++;
			
		}
		
		return i - 1;
		
	}
	
	// Function:   Tower of Hanoi
	// Purpose:    Move all discs of a tower from one location to another
	// Parameters: Integer n for number of discs in tower, three integers to represent locations for discs
	// Returns:    Nothing
	public static void 
	towerOfHanoi(int nOrg, int n, Tower from, Tower to, Tower aux, Graphics graphics, int panelWidth, int panelHeight) 
	throws InterruptedException {

	    // Basis step: n == 0, return
	    if (n == 0) {
	    	
	        return;

	    }

	    // Recursive step 1: move n-1 disc to the auxiliary location
	    towerOfHanoi(nOrg, n-1, from, aux, to, graphics, panelWidth, panelHeight);
	    
	    // Sleep for effect timing
	    Thread.sleep(500);
	    
	    // Take the topmost disc of start and place it on the next empty disc of tower dest by swapping their colors/width
    	// First get the indexes of the discs to swap
    	int startTopIndex = getTopIndex(from, nOrg);
    	int destOpenDiscIndex = getTopIndex(to, nOrg) + 1;
    	
    	// Next swap the colors and widths. Use temporary variables as storage
    	int tempWidth = from.tower[startTopIndex].disc.width;
    	Color tempColor = from.tower[startTopIndex].disc.color;
    	from.tower[startTopIndex].disc.width = to.tower[destOpenDiscIndex].disc.width;
    	from.tower[startTopIndex].disc.color = to.tower[destOpenDiscIndex].disc.color;
    	to.tower[destOpenDiscIndex].disc.width = tempWidth;
    	to.tower[destOpenDiscIndex].disc.color = tempColor;
    	
    	System.out.println("Moved disc " + n);
    	
    	// Draw everything after the swap
    	drawAll(from, to, aux, graphics, panelWidth, panelHeight);

	    // Recursive step 2: move n-1 disc (same as above) to
	    towerOfHanoi(nOrg, n-1, aux, to, from, graphics, panelWidth, panelHeight);

	}

}

