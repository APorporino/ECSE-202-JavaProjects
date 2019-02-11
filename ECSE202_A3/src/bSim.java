import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class bSim extends GraphicsProgram {
	
	//set constants
	private static final int WIDTH = 1200;      // n.b. screen coords
	private static final int HEIGHT = 600;  
	private static final int OFFSET = 200;
	private static final int NUMBALLS = 15; 	// # balls to sim.
	private static final double MINSIZE = 1;    // Min ball size
	private static final double MAXSIZE = 8;   // Max ball size
	private static final double XMIN = 10;      // Min X start location
	private static final double XMAX = 50;      // Max X start location
	private static final double YMIN = 50;      // Min Y start location
	private static final double YMAX = 100;     // Max Y start location
	private static final double EMIN = 0.4;     // Min loss coeff.
	private static final double EMAX = 0.9;     // Max loss coeff.
	private static final double VMIN = 0.5;     // Min X velocity
	private static final double VMAX = 3.0;     // Max X velocity
	
	//Get an instance of the random generator
	RandomGenerator rg = RandomGenerator.getInstance();
	
	bTree myTree = new bTree();   //create a new tree to store ball objects
	gBall[] allBalls; //create an array of balls
	 
	public void run() {
		this.resize(WIDTH, HEIGHT + OFFSET);
		GLine line = new GLine(0, HEIGHT, WIDTH, HEIGHT);
		add(line);
		
		allBalls = new gBall[NUMBALLS]; //creates 15 elements of allBalls
		
		//creates 15 balls with random arguments and stores them in allBalls array
		for (int i = 0; i < allBalls.length; i++) {
			
			double Xi = rg.nextDouble(XMIN, XMAX);
			double Yi = rg.nextDouble(YMIN, YMAX);
			double bSize = rg.nextDouble(MINSIZE, MAXSIZE);
			Color bColor = rg.nextColor();
			double bLoss = rg.nextDouble(EMIN, EMAX);
			double bVel = rg.nextDouble(VMIN, VMAX);
			boolean running = true;
			
			allBalls[i] = new gBall(Xi, Yi, bSize, bColor, bLoss, bVel, running);
			
		}
		
		//Add each ball to the screen and run them simultaneously using gBall class run function
		for (int i = 0; i < allBalls.length; i ++) {
			add(allBalls[i].myBall);
			allBalls[i].start(); 
			myTree.addNode(allBalls[i]); //add each ball object to myTree
			
		}
		
		
		while (myTree.isRunning()); //wait for balls to stop moving
		
		GLabel label = new GLabel("Click mouse to continue"); //add label to get user input
		double x = (WIDTH - 1.5*label.getWidth()); //x position of label 
		double y = (HEIGHT - 3*label.getHeight()); // y position of label 
		label.setColor(Color.RED);
		add(label,x,y); 
		
		waitForClick(); //wait until user clicks to sort balls
		myTree.moveSort(); //runs moveSort method in bTree class to sort the balls
		label.setLabel("All sorted"); //create new label to say all sorted
		
	}	
}
