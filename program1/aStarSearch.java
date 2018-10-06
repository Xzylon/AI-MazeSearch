
 //
 // class to do A* search through a maze
 //
 // Jason Armstrong
 //
public class aStarSearch {
    // instance variables
	Node currentNode;
	Maze maze;
	int northValue, eastValue, southValue, westValue, traveledDist;
	
    // Constructor
    public aStarSearch(Maze inMaze) {
        // initialize instance variables
    	maze = inMaze;
    	currentNode = maze.start;
    	traveledDist = northValue = eastValue = southValue = westValue = 0;
    }

    //*
    // An example of a method - replace this comment with your own
    //
    // @param  y  a sample parameter for a method
    // @return    the sum of x and y
    ///
    public int doSearch(int x) {
        // 
    	while ( maze.isGoal(currentNode) ) {
    		if (maze.canMove(maze, maze.start, "north") ) {
    			northValue = traveledDist + maze.calculateManDist(currentNode, maze.goal);
    		}
    		if (maze.canMove(maze, maze.start, "east") ) {
    			eastValue = traveledDist + maze.calculateManDist(currentNode, maze.goal);
    		}
    		if (maze.canMove(maze, maze.start, "south") ) {
    			southValue = traveledDist + maze.calculateManDist(currentNode, maze.goal);
    		}
    		if (maze.canMove(maze, maze.start, "west") ) {
    			westValue = traveledDist + maze.calculateManDist(currentNode, maze.goal);
    		}    	
    	}
    	return 0;
    }
}
