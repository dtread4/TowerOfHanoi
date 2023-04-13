Visualization of the famous Tower of Hanoi for 3 <= n <= 10. Larger sizes of n would likely work, but would take too much time so I restricted the upper bound. Two could work but is not interesting.

The objective of the Tower of Hanoi problem is to move all discs in tower A to tower C (utilizing a third auxilliary tower B), while ensuring no disc is placed on top of another disc if it would be larger than the disc below it (smallest discs must always be on top). This is solved recursively in this program.

This program utilizes multiple classes to track information related to a tower, the available positions for discs in a tower, and the discs. This information is used to draw each of the towers.

Graphics utilize the DrawingPanel class from Stuart Reges and Marty Stepp from the Building Java Programs textbook. All other files designed by me.
