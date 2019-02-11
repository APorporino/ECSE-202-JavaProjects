import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;





public class bSim extends GraphicsProgram implements ChangeListener, ActionListener, MouseListener {
	
	//set constants
	private static final int WIDTH = 1200;      // n.b. screen coords
	private static final int HEIGHT = 600;  
	private static final int OFFSET = 200;
	public static final int MINNUMBALLS = 1;	//min num balls
	public static final int MAXNUMBALLS = 25; 	//max num balls
	private static final double SIZE = 25.0;    // Max size for the min and max size slider  
	private static final double X = 200.0;      // Max X start location for the min and max x position slider
	private static final double Y = 100.0;      // Max Y start location for the min and max y position slider
	private static final double E_loss = 1.0;     // Max loss coeff. for the min and max e loss slider
	private static final double Vel = 10.0;     // Max X velocity for the min and max xvel slider
	
		
	RandomGenerator rg = RandomGenerator.getInstance(); //create instance of the random class

	bTree myTree = new bTree(); //create a new tree to store ball objects
	
	
	
	private GObject gobj; // The object being dragged 
	private GPoint last; // the point at which the mouse is pressed/released
	private GOval clickedOval; //will cast GObject to this variable

	//this method responds to a mouse being pressed and if an object is present
	//at that location it will cast it as an Oval
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
	    if (!(gobj==null)) {
	    	clickedOval = (GOval) gobj; //casting to GOval
	    	
	     }
	} 
	
	//moves the gobj at that position to wherever the mouse moves
	public void mouseDragged(MouseEvent e) {
		if (gobj != null) {
			gobj.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());

		}
	}
	//once mouse is released a new gBall is created with parameter of the single
	//ball slider, is added to the tree and canvas.
	//the old ball is then removed 
	public void mouseReleased(MouseEvent e) {
		if (clickedOval != null) {
			remove(gobj);
			last = new GPoint(e.getPoint());
			gBall ball = new gBall(last.getX(), (-last.getY() + 600)/10 +15, ball_size_slider_value, color_of_touched_ball, e_loss_slider_value, x_vel_slider_value, true); 
			add(ball.myBall);
			myTree.addNode(ball);
			ball.start();
			clickedOval = null;
		}
	}
	//initialize all the sliders needed
	sliderBox numballsSlider;
	sliderBox minSizeSlider;
	sliderBox maxSizeSlider;
	sliderBox x_minSlider;
	sliderBox x_maxSlider;
	sliderBox y_minSlider;
	sliderBox y_maxSlider;
	sliderBox b_loss_minSlider;
	sliderBox b_loss_maxSlider;
	sliderBox x_vel_minSlider;
	sliderBox x_vel_maxSlider;

	sliderBox ball_sizeSlider;
	sliderBox e_lossSlider;
	sliderBox x_velSlider;
		
	//instantiate all slider values
	int numballs_slider_value = 15;
	double min_size_slider_value = 1.0; 
	double max_size_slider_value = 8.0;
	double x_min_slider_value = 10.0;
	double x_max_slider_value = 50.0;
	double y_min_slider_value = 50.0;
	double y_max_slider_value = 100.0;
	double b_loss_min_slider_value = 0.4;
	double b_loss_max_slider_value = 1.0;
	double x_vel_min_slider_value = 1.0;
	double x_vel_max_slider_value = 5.0;
	
	double ball_size_slider_value = 4.0;
	double e_loss_slider_value = 0.4;
	double x_vel_slider_value = 1.0;
	
	////this method responds to the changes in sliders and updates the values
	public void stateChanged(ChangeEvent e) { 
		JSlider source = (JSlider)e.getSource();
		
		if (source == numballsSlider.mySlider) { //check if source = a certain slider
			numballs_slider_value=numballsSlider.getISlider_int(); //get the value the user wants
			numballsSlider.setISlider(numballs_slider_value); //change the value to that
		}
		else if (source == minSizeSlider.mySlider) {
			min_size_slider_value=minSizeSlider.getISlider();
			minSizeSlider.setISlider(min_size_slider_value);
		}
		else if (source == maxSizeSlider.mySlider) {
			max_size_slider_value=maxSizeSlider.getISlider();
			maxSizeSlider.setISlider(max_size_slider_value);
		}
		else if (source == x_minSlider.mySlider) {
			x_min_slider_value=x_minSlider.getISlider();
			x_minSlider.setISlider(x_min_slider_value);
		}
		else if (source == x_maxSlider.mySlider) {
			x_max_slider_value=x_maxSlider.getISlider();
			x_maxSlider.setISlider(x_max_slider_value);
		}
		else if (source == y_minSlider.mySlider) {
			y_min_slider_value=y_minSlider.getISlider();
			y_minSlider.setISlider(y_min_slider_value);
		}
		else if (source == y_maxSlider.mySlider) {
			y_max_slider_value=y_maxSlider.getISlider();
			y_maxSlider.setISlider(y_max_slider_value);
		}
		else if (source == b_loss_minSlider.mySlider) {
			b_loss_min_slider_value=b_loss_minSlider.getISlider();
			b_loss_minSlider.setISlider(b_loss_min_slider_value);
		}
		else if (source == b_loss_maxSlider.mySlider) {
			b_loss_max_slider_value=b_loss_maxSlider.getISlider();
			b_loss_maxSlider.setISlider(b_loss_max_slider_value);
		}
		else if (source == x_vel_minSlider.mySlider) {
			x_vel_min_slider_value=x_vel_minSlider.getISlider();
			x_vel_minSlider.setISlider(x_vel_min_slider_value);
		}
		else if (source == x_vel_maxSlider.mySlider) {
			x_vel_max_slider_value=x_vel_maxSlider.getISlider();
			x_vel_maxSlider.setISlider(x_vel_max_slider_value);
		}
		else if (source == ball_sizeSlider.mySlider) {
			ball_size_slider_value=ball_sizeSlider.getISlider();
			ball_sizeSlider.setISlider(ball_size_slider_value);
		}
		else if (source == e_lossSlider.mySlider) {
			e_loss_slider_value=e_lossSlider.getISlider();
			e_lossSlider.setISlider(e_loss_slider_value);
		}
		else if (source == x_velSlider.mySlider) {
			x_vel_slider_value=x_velSlider.getISlider();
			x_velSlider.setISlider(x_vel_slider_value);
		}
		
		
	}
	
	//initialize the JComboBoxes
	JComboBox <String> bSimC; 
	Color color_of_touched_ball; //instead of slider the color of the single ball 
	JComboBox <String> colors; //will be determined using this variable and comboBox
	
	
	//set all items of the JComboBox for bSim
	void setChoosers_bSimC() {
		bSimC = new JComboBox<String>(); 
		bSimC.addItem("bSim"); 
		bSimC.addItem("Run"); 
		bSimC.addItem("Clear"); 
		bSimC.addItem("Stop"); 
		bSimC.addItem("Quit"); 
		add(bSimC,NORTH);
		addJComboListeners_bSimC();
	}
	
	//set all color options for individual ball 
	void setChoosers_colors() {
		colors = new JComboBox<String>(); 
		colors.addItem("colors"); 
		colors.addItem("RED"); 
		colors.addItem("ORANGE"); 
		colors.addItem("YELLOW"); 
		colors.addItem("GREEN"); 
		colors.addItem("BLUE"); 
		colors.addItem("MAGENTA"); 
		add(colors,NORTH);
		addJComboListeners_colors();
	}
	
	//add listeners for both JComboBoxes
	void addJComboListeners_bSimC() {
		bSimC.addActionListener((ActionListener)this);
	}
	void addJComboListeners_colors() {
		colors.addActionListener((ActionListener)this);
	}
	
	//responds to changes in each JComboBox
	public void actionPerformed(ActionEvent e) {
		
		JComboBox source = (JComboBox)e.getSource(); //find which one was clicked
		if (source == bSimC) {
			if (bSimC.getSelectedIndex() == 0) { //find which option was clicked
				System.out.println("Starting bSim");
			}
			else if(bSimC.getSelectedIndex() == 1) {
				//will create a new set of balls using the slider values
				for (int i = 0; i < numballsSlider.getISlider_int(); i++) {
					
					//uses slider values 
					
					double Xi = rg.nextDouble(x_minSlider.getISlider(), x_maxSlider.getISlider());
					double Yi = rg.nextDouble(y_minSlider.getISlider(), y_maxSlider.getISlider());
					double bSize = rg.nextDouble(minSizeSlider.getISlider(), maxSizeSlider.getISlider());
					Color bColor = rg.nextColor();
					double bLoss = rg.nextDouble(b_loss_minSlider.getISlider(), b_loss_maxSlider.getISlider());
					double bVel = rg.nextDouble(x_vel_minSlider.getISlider(), x_vel_maxSlider.getISlider());
					boolean running = true;

					gBall iBall = new gBall(Xi, Yi, bSize, bColor, bLoss, bVel, running);
					add(iBall.myBall);
				
					myTree.addNode(iBall);
					iBall.start();
				}
				

			}
			else if(bSimC.getSelectedIndex() == 2) {
				
				
				myTree.clear(); //runs the clear method in bTree class that clears all the ball on screen
				
			}
			else if(bSimC.getSelectedIndex() == 3) {
				
				myTree.stop(); //runs the stop method in bTree class that stops all the balls
				
			}
			else if(bSimC.getSelectedIndex() == 4) {
				System.exit(0); //shuts down the applet
			}
		}
		if (source == colors) {
			//will change the color variable to whichever color that was clicked
			if (colors.getSelectedIndex() == 0) {
				color_of_touched_ball = Color.BLACK;
			}
			if (colors.getSelectedIndex() == 1) {
				color_of_touched_ball = Color.RED;
			}
			if (colors.getSelectedIndex() == 2) {
				color_of_touched_ball = Color.ORANGE;
			}
			if (colors.getSelectedIndex() == 3) {
				color_of_touched_ball = Color.YELLOW;
			}
			if (colors.getSelectedIndex() == 4) {
				color_of_touched_ball = Color.GREEN;
			}
			if (colors.getSelectedIndex() == 5) {
				color_of_touched_ball = Color.BLUE;
			}
			if (colors.getSelectedIndex() == 6) {
				color_of_touched_ball = Color.MAGENTA;
			}
			
		}
	}
	

	
	public void run() {
		
		//set up panel which contains all sliders and gLabels
		JPanel eastPanel = new JPanel();
		
		JLabel gen_sim = new JLabel("General Simulation Parameters");
		
		eastPanel.add(gen_sim);
		
		eastPanel.setLayout(new GridLayout(20,1));
		
		//add values to each sliderBox and add it to eastPanel 
		//as well as a listener for when its value is changed
		numballsSlider = new sliderBox("NUMBALLS", (int) MINNUMBALLS, 15, (int) MAXNUMBALLS);
		eastPanel.add(numballsSlider.myPanel, "EAST");
		numballsSlider.mySlider.addChangeListener((ChangeListener)this);
		
		minSizeSlider = new sliderBox("MIN SIZE", 1.0, 1.0, SIZE);
		eastPanel.add(minSizeSlider.myPanel, "EAST");
		minSizeSlider.mySlider.addChangeListener((ChangeListener)this);
		
		maxSizeSlider = new sliderBox("MAX SIZE", 1.0, 8.0, SIZE);
		eastPanel.add(maxSizeSlider.myPanel, "EAST");
		maxSizeSlider.mySlider.addChangeListener((ChangeListener)this);
		
		x_minSlider = new sliderBox("XMIN", 1.0, 10.0, X);
		eastPanel.add(x_minSlider.myPanel, "EAST");
		x_minSlider.mySlider.addChangeListener((ChangeListener)this);
		
		x_maxSlider = new sliderBox("XMAX", 1.0, 50.0, X);
		eastPanel.add(x_maxSlider.myPanel, "EAST");
		x_maxSlider.mySlider.addChangeListener((ChangeListener)this);
		
		y_minSlider = new sliderBox("YMIN", 1.0, 50.0, Y);
		eastPanel.add(y_minSlider.myPanel, "EAST");
		y_minSlider.mySlider.addChangeListener((ChangeListener)this);
		
		y_maxSlider = new sliderBox("YMAX", 1.0, 100.0, Y);
		eastPanel.add(y_maxSlider.myPanel, "EAST");
		y_maxSlider.mySlider.addChangeListener((ChangeListener)this);
		
		b_loss_minSlider = new sliderBox("LOSS MIN", 0.0, 0.4, E_loss);
		eastPanel.add(b_loss_minSlider.myPanel, "EAST");
		b_loss_minSlider.mySlider.addChangeListener((ChangeListener)this);
		
		b_loss_maxSlider = new sliderBox("LOSS MAX", 0.0, 1.0, E_loss);
		eastPanel.add(b_loss_maxSlider.myPanel, "EAST");
		b_loss_maxSlider.mySlider.addChangeListener((ChangeListener)this);
		
		x_vel_minSlider = new sliderBox("X VEL MIN", 0.0, 1.0, Vel);
		eastPanel.add(x_vel_minSlider.myPanel, "EAST");
		x_vel_minSlider.mySlider.addChangeListener((ChangeListener)this);
		
		x_vel_maxSlider = new sliderBox("X VEL MAX", 0.0, 5.0, Vel);
		eastPanel.add(x_vel_maxSlider.myPanel, "EAST");
		x_vel_maxSlider.mySlider.addChangeListener((ChangeListener)this);
		
		
		JLabel single_sim = new JLabel("Single Ball Instance Parameters");
		eastPanel.add(single_sim);
		
		ball_sizeSlider = new sliderBox("BALL SIZE", 1.0, 4.0, 8.0);
		eastPanel.add(ball_sizeSlider.myPanel, "EAST");
		ball_sizeSlider.mySlider.addChangeListener((ChangeListener)this);
		
		e_lossSlider = new sliderBox("E LOSS", 0.4, 0.4, 1.0);
		eastPanel.add(e_lossSlider.myPanel, "EAST");
		e_lossSlider.mySlider.addChangeListener((ChangeListener)this);
		
		x_velSlider = new sliderBox("X VEL", 1.0, 1.0, 5.0);
		eastPanel.add(x_velSlider.myPanel, "EAST");
		x_velSlider.mySlider.addChangeListener((ChangeListener)this);
		
		add(eastPanel);
		
		//will add both JComboBoxes to screen
		setChoosers_bSimC();
		setChoosers_colors();
		
		
		this.resize(WIDTH, HEIGHT + OFFSET);
		GLine line = new GLine(0, HEIGHT, WIDTH, HEIGHT);
		add(line);
		
		addMouseListeners(); //to be able to listen and respond to mouse events
	
		//create all the gBalls and add them to the bTree and run them
		for (int i = 0; i < numballsSlider.getISlider_int(); i++) {
			
			//uses slider values 
			
			double Xi = rg.nextDouble(x_minSlider.getISlider(), x_maxSlider.getISlider());
			double Yi = rg.nextDouble(y_minSlider.getISlider(), y_maxSlider.getISlider());
			double bSize = rg.nextDouble(minSizeSlider.getISlider(), maxSizeSlider.getISlider());
			Color bColor = rg.nextColor();
			double bLoss = rg.nextDouble(b_loss_minSlider.getISlider(), b_loss_maxSlider.getISlider());
			double bVel = rg.nextDouble(x_vel_minSlider.getISlider(), x_vel_maxSlider.getISlider());
			boolean running = true;

			gBall iBall = new gBall(Xi, Yi, bSize, bColor, bLoss, bVel, running);
			add(iBall.myBall);
		
			myTree.addNode(iBall);
			iBall.start();
		}
	
		while (myTree.isRunning()); //wait for balls to stop moving
	
		GLabel label = new GLabel("Click mouse to continue"); //add label to get user input
		label.setLocation(WIDTH/2 - 2*label.getWidth(), HEIGHT/2 - 2*label.getHeight());
		label.setColor(Color.RED);
		add(label); 
	
		waitForClick(); //wait until user clicks to sort balls
		myTree.moveSort(); //runs moveSort method in bTree class to sort the balls
		label.setLabel("All sorted"); //create new label to say all sorted
	}

	
	
	
}


