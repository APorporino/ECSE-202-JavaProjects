/* Anthony Porporino
 * ECSE202-001
 * Assignment 1 (Bounce.java)
 * This program simulates a real ball being dropped on the floor
 */
import java.awt.Color;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

public class Bounce extends GraphicsProgram {
	
	//Set constants 
	private static final double TIME_INTERVAL = 0.1;
	private static final double TIME_OUT = 30;
	private static final double GRAVITY = 9.8;
	private static final double BALL_D = 60; //Ball diameter
	private static final int WIDTH = 600; //Width of canvas
	private static final int HEIGHT = 800; //Height of canvas
	
	
	public void run() {
		
		this.resize(WIDTH, HEIGHT);
		//Get user input h0 = starting height, l = energy loss constant
		double h0 = readDouble ("Enter the starting height of the bottom of the ball in meters [0, 60]");
		double l = readDouble ("Enter a constant (l) for the amount of energy loss after each bounce [0,1]");
		
		//Initialize the variables
		double totalTime = 0;
		double time = 0;
		boolean directionUp = false;
		double height = 0;
		double initialUpPosition = 0;
		double vt = Math.sqrt(2*(GRAVITY)*h0);
		double energy_loss_factor = Math.sqrt(1-l);
		double vx = 8; //for x velocity
		double xPos = 0;
	
		
		//Create a new blue ball and line for floor
		GOval ball = new GOval(0,((600 - (10*h0)) - BALL_D), BALL_D, BALL_D);
		ball.setFilled(true);
		ball.setColor(Color.BLUE);
		add(ball);
		GLine line = new GLine(0, 600, 600, 600);
		add(line);
		
		//Simulation loop
		while (totalTime < TIME_OUT) {
			if (directionUp == false) { //Ball is descending 
				height = h0 - 0.5*GRAVITY*Math.pow(time, 2); //height of ball decreases
				if (height <= 0)  { //Ball hits the ground
					height = 0; //to make sure ball does not go below the floor
					h0 = height;
					initialUpPosition = height;
					directionUp = true;
					time = 0;
					vt = vt*energy_loss_factor; //

				}
			}

			else //Direction is up
			{ 
				height = initialUpPosition + vt*time - 0.5*GRAVITY*Math.pow(time, 2); //height of ball now increases
				if (height > h0) { //ball coontinues to ascend
					h0 = height;
				}
				else { //ball reached its peak
					directionUp = false;
					time = 0;
				}
			}
		
			//Update xPos, time variable and prints the balls position and total time of fall
			xPos += vx*TIME_INTERVAL;
			println("TIME: " + totalTime + " X: " + xPos + " Y: " + height);
			time += TIME_INTERVAL;
			totalTime += TIME_INTERVAL;
			pause(TIME_INTERVAL*500);
			
			//Add the dashed trace lines and change ball position after each time step
			add(new GOval(xPos + 30, (600 - (10*height)) - ((BALL_D)/2), 1,1));
			ball.setLocation(xPos, ((600 - (10*height)) - BALL_D));
			
		}
		
	}

}
