import java.util.*;
 //
 // class to do A* search through a maze
 //
 // Jason Armstrong
 //
public class aStarSearch {
    // instance variables
	Node currentNode, northNode, eastNode, southNode, westNode, leastNode;
	Maze maze;
	int northValue, eastValue, southValue, westValue, traveledDist, leastValue; 
	String leastDirection;
	ArrayList<Node> pathArray = new ArrayList<Node>();
	
    // Constructor
    public aStarSearch(Maze inMaze) {
        // initialize instance variables
    	maze = inMaze;
    	currentNode = maze.start;
    	traveledDist = 0; 
    	leastValue = northValue = eastValue = southValue = westValue = 2147483647;
    	leastDirection = "";

    }

    //*
    // An example of a method - replace this comment with your own
    //
    // @param  y  a sample parameter for a method
    // @return    the sum of x and y
    ///
    public int doSearch() {
        // 

    	while ( !maze.isGoal(currentNode) ) {
    		if (maze.canMove(maze, maze.start, "north") ) {
            	northNode = maze.goDirection(maze, currentNode, "north");
            	northValue = 1 + traveledDist + maze.calculateManDist(northNode, maze.goal);
    		}
    		if (maze.canMove(maze, maze.start, "east") ) {
            	eastNode =  maze.goDirection(maze, currentNode, "east");
            	eastValue = 1 + traveledDist + maze.calculateManDist(eastNode, maze.goal);
    		}
    		if (maze.canMove(maze, maze.start, "south") ) {
            	southNode = maze.goDirection(maze, currentNode, "south");
            	southValue = 1 + traveledDist + maze.calculateManDist(southNode, maze.goal);
    		}
    		if (maze.canMove(maze, maze.start, "west") ) {
            	westNode =  maze.goDirection(maze, currentNode, "west");
            	westValue = 1 + traveledDist + maze.calculateManDist(westNode, maze.goal);
    		}
    		
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
    	}
    	return 0;
    }
    
    public int findLeast() {
    	return 0;
    	
    }
}
