//Anthony Porporino
//260863300
//Assignment 2 ECSE-202
//This program simluates 100 randomly sized, colored
//positioned balls being dropped all with different energy loss coefficients using the gBall class

import java.awt.Color;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class bSim extends GraphicsProgram {
	
	//set constants
	private static final int WIDTH = 1200;      // n.b. screen coords
	private static final int HEIGHT = 600;  
	private static final int OFFSET = 200;
	private static final int NUMBALLS = 100; 	// # balls to sim.
	private static final double MINSIZE = 3;    // Min ball size
	private static final double MAXSIZE = 20;   // Max ball size
	private static final double XMIN = 10;      // Min X start location
	private static final double XMAX = 50;      // Max X start location
	private static final double YMIN = 50;      // Min Y start location
	private static final double YMAX = 100;     // Max Y start location
	private static final double EMIN = 0.1;     // Min loss coeff.
	private static final double EMAX = 0.3;     // Max loss coeff.
	private static final double VMIN = 0.5;     // Min X velocity
	private static final double VMAX = 3.0;     // Max X velocity
	
	//Get an instance of the random generator
	RandomGenerator rg = RandomGenerator.getInstance();
	
	
	gBall[] allBalls; //create an array of balls
	 
	public void run() {
		this.resize(WIDTH, HEIGHT + OFFSET);
		GLine line = new GLine(0, HEIGHT, WIDTH, HEIGHT);
		add(line);
		
		allBalls = new gBall[NUMBALLS];
		
		//creates 100 balls (objects) in myDisks array and uses the random generator to generate arguments
		for (int i = 0; i < allBalls.length; i++) {
			
			double Xi = rg.nextDouble(XMIN, XMAX);
			double Yi = rg.nextDouble(YMIN, YMAX);
			double bSize = rg.nextDouble(MINSIZE, MAXSIZE);
			Color bColor = rg.nextColor();
			double bLoss = rg.nextDouble(EMIN, EMAX);
			double bVel = rg.nextDouble(VMIN, VMAX);
			
			allBalls[i] = new gBall(Xi, Yi, bSize, bColor, bLoss, bVel);
			
		}
		
		//Add each ball to the screen and run them simultaneously using gBall class run function
		for (int i = 0; i < allBalls.length; i ++) {
			add(allBalls[i].myBall);
			allBalls[i].start();
	
			
			
		}
	}	
}
