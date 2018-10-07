import java.util.*;
 //
 // class to do A* search through a maze
 //
 // Jason Armstrong
 //
public class AStarSearch {
    // instance variables
	Node currentNode, northNode, eastNode, southNode, westNode, leastNode;
	Maze maze, moddedMaze;
	int northValue, eastValue, southValue, westValue, traveledDist, leastValue; 
	String leastDirection;
	ArrayList<Node> pathArray = new ArrayList<Node>();
	
    // Constructor
    public AStarSearch(Maze inMaze) {
        // initialize instance variables
    	maze = inMaze;
    	currentNode = maze.start;
    	traveledDist = 0; 
    	leastValue = northValue = eastValue = southValue = westValue = 2147483647;
    	leastDirection = "";
    }

    ///
    public ArrayList<Node> doAStarSearch() {
        // 
    	System.out.println("starting search..."); // debugging output

    	while ( !maze.isGoal(currentNode) ) {
    		// reinitialize values
    		leastValue = northValue = eastValue = southValue = westValue = 2147483647;
    		
    		// calculate distances
        	System.out.println("checking move areas"); // debugging output
        	System.out.println("current node is x=" + currentNode.x + " Y=" + currentNode.y); // debugging output

        	// set up directional nodes
        	northNode = maze.goDirection(maze, currentNode, "north");
        	eastNode =  maze.goDirection(maze, currentNode, "east");
        	southNode = maze.goDirection(maze, currentNode, "south");
        	westNode =  maze.goDirection(maze, currentNode, "west");
        	
    		if (maze.canMove(maze, currentNode, "north") && !northNode.checked) {
            	System.out.println("can move north"); // debugging output
            	northValue = 1 + traveledDist + maze.calculateManDist(northNode, maze.goal);
    		}
    		if (maze.canMove(maze, currentNode, "east") && !eastNode.checked) {
            	System.out.println("can move east"); // debugging output
            	eastValue = 1 + traveledDist + maze.calculateManDist(eastNode, maze.goal);
    		}
    		if (maze.canMove(maze, currentNode, "south") && !southNode.checked) {
            	System.out.println("can move south"); // debugging output
            	southValue = 1 + traveledDist + maze.calculateManDist(southNode, maze.goal);
    		}
    		if (maze.canMove(maze, currentNode, "west") && !westNode.checked) {
            	System.out.println("can move west"); // debugging output
            	westValue = 1 + traveledDist + maze.calculateManDist(westNode, maze.goal);
    		}
    		//mark all checked nodes as....well, checked!
        	northNode.checked = true;
        	eastNode.checked  = true;
        	southNode.checked = true;
        	westNode.checked  = true;
    		
    		//calculate which is least
    		if ( northValue < leastValue ) {
    			leastValue = northValue; leastDirection = "north"; leastNode = northNode;
    		} if ( eastValue < northValue ) {
    			leastValue = eastValue;  leastDirection = "east";  leastNode = eastNode;
    		} if ( southValue < eastValue ) {
    			leastValue = southValue; leastDirection = "south"; leastNode = southNode;
    		} if ( westValue < southValue) {
    			leastValue = westValue;  leastDirection = "west";  leastNode = westNode;
    		}
    		
    		// we found the shortest distance node, so let's use it
        	pathArray.add(leastNode); //add the least node to our path
        	currentNode = leastNode; // move to the least node
        	traveledDist++; // we moved, so better increment the traveled distance
    	}
    	// output values for review
    	moddedMaze = modifyMaze(maze, pathArray);
    	maze.printMaze();
    	moddedMaze.printMaze();
        //System.out.print("pathArray = ",pathArray);
    	return pathArray;
    }
    
    public Maze modifyMaze(Maze inMaze, ArrayList<Node> pathArray) {
    	maze = inMaze;
    	int pathx = -1; int pathy= -1;
    	for (int i=0; i < pathArray.size(); i++) {
    		pathx = pathArray.get(i).x;
    		pathy = pathArray.get(i).y;
    		maze.grid[pathx][pathy].type = pathArray.get(i).type;
    		maze.printMaze();
    	}
    	return maze;
    }

}
