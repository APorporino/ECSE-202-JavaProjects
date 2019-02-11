
public class bTree {
	
	bNode root = null; //instantiate root
	boolean[] balls_moving = new boolean[15]; //array to hold boolean variables to see if ball is still moving
	gBall [] balls_size = new gBall[15]; //array to hold the all balls in increasing radius size
	int index = 0; //used for array placement of data
	
	private class bNode{ //nested class to create bNode object
		gBall iBall;
		bNode left;
		bNode right;
	}
	
	public void addNode(gBall iBall) { //addNode calls rNode method
		root = rNode(root,iBall);
	}
	
	public bNode rNode(bNode root, gBall iBall) { //method to add rNode to tree recursively 
		if (root==null) {					   // termination part
			bNode node = new bNode();
			node.iBall = iBall;
			node.left = null;
			node.right = null;
			root = node;						//set 
		}										//recursion part
		else if (iBall.bSize < root.iBall.bSize) {				//if data is < root then 
			root.left = rNode(root.left, iBall);		//function will be called again where root is now root.left
		}												//same for right (if data > root)
		else root.right = rNode(root.right, iBall);
		
		return root;
	}
	
	//this method will return true if one ball is still in motion or false if all balls have stopped 
	public boolean isRunning() {
		index = 0; //set index variable to zero
		traverse_in_order_moving(root); //run traverse_in_order_moving to update balls_moving array with correct information
		for (int i = 0; i < balls_moving.length; i++) {
			if (balls_moving[i] == true) return true; //if one is true, isRunning is true
		}
		return false;
	}

	//this method traverses in order through the bTree and adds each boolean value of the balls to balls_moving array
	public void traverse_in_order_moving(bNode root) {
		if (root.left != null) traverse_in_order_moving(root.left);
		balls_moving[index] = root.iBall.Running; //add or change the variable at index to true or false depending on if ball is moving
		index++; 
		if (root.right != null) traverse_in_order_moving(root.right);
	}
	
	//This method will sort the balls in order of diameter. Smallest -> largest
	public void moveSort() {
		
		index = 0; //reset index to zero
		final int yfinal = 600; //y position of all sorted balls before subtraction of diameter 
		traverse_in_order_size(root); //create the array with sorted gBalls in terms of bSize
		double total_diameter = 0; //total diameter of all balls
		
		for (int i=0; i<balls_size.length; i++) {
			total_diameter += balls_size[i].bSize; //get sum of diameter of all balls
		}
		
		double start = (1200 - 10*total_diameter)/2; //starting position of first sorted ball is width (1200) - diameter of all balls
		
		for (int i=0; i<balls_size.length; i++) {
			balls_size[i].moveTo(start, yfinal - 10*balls_size[i].bSize); //call moveTo method in gBall to move each ball
			start += 10*balls_size[i].bSize; //change start of next ball so it is right next to the preceding one
		}
	}
	
	//this method updates the array balls_size to contain each ball object listed in terms of diameter from smallest to largest
	public void traverse_in_order_size(bNode root) {
		if (root.left != null) traverse_in_order_size(root.left);
		balls_size[index] = root.iBall;
		index++;
		if (root.right != null) traverse_in_order_size(root.right);
	}
	
}
