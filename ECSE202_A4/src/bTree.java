import acm.graphics.GOval;
import acm.graphics.GPoint;

public class bTree {
	
	
	bNode root = null; //instantiate root
	boolean running; //will help determine if all balls stop moving in isRunning method
	double nextX; //used to position the balls when sorting
	
	public void addNode(gBall iBall) { //addNode calls rNode method
		root = rNode(root,iBall);
	}
	
	public bNode findNode(GOval value) { //method will return the node that has the ball that was clicked
		return findNodeRecursive(root, value);
	}
	
	private bNode findNodeRecursive(bNode current, GOval value) {
		
		if (current.left != null) findNodeRecursive(current.left, value);
		
		if (current.iBall.myBall.equals(value)) {
			
			return current;
			
		}
		
		if (current.right != null) findNodeRecursive(current.right, value);
		return null;
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

	public void clear() { //will call the travers_inorder method which will search through the tree
		traverse_inorder_clear(root);
	}
	
	public void traverse_inorder_clear(bNode root) { //searches through the tree in order and prints the size of the ball
		if (root.left != null) traverse_inorder_clear(root.left);
		if (root.iBall != null) {
			//remove(root.iBall);
			root.iBall.delete(); //call the delete method in gBall
			
		}
		if (root.right != null) traverse_inorder_clear(root.right);
	}
	
	public void stop() { //will call the method to stop the balls
		traverse_inorder_stop(root);
	}
	
	public void traverse_inorder_stop(bNode root) { //searches through the tree in order and stops the thread of each one so they stop moving
		if (root.left != null) traverse_inorder_stop(root.left);
		if (root.iBall != null) root.iBall.stop(); //stops the thread of a gBall
		if (root.right != null) traverse_inorder_stop(root.right);
	}

	//this method will return true if one ball is still in motion or false if all balls have stopped 
	public boolean isRunning() {
		running = false;
		scan_for_running(root); //call a method to scan through the tree
		return running;
	}
	
	public void scan_for_running(bNode root) { //scans the tree to see if any balls are still moving
		if (root.left != null) scan_for_running(root.left);
		if (root.iBall != null) if (root.iBall.Running) running = true;;
		if (root.right != null) scan_for_running(root.right);
	}

	void moveSort() { //will call a method to scan through the tree and sort each ball in terms of size
		nextX = 0;
		scan_to_move(root);
	}
	
	public void scan_to_move(bNode root) { //scans the tree and updates their position to sort the balls by size
		if (root.left != null) scan_to_move(root.left);
		
		double x = nextX;
		double y = 600 - root.iBall.bSize*10; //height minus diameter of ball
		nextX = x + root.iBall.bSize*10;
		root.iBall.moveTo(x, y); //method in gBall class is called to move the ball to a certain position
		if (root.right != null) scan_to_move(root.right);
	}
	
}
