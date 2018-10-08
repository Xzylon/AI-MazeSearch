import java.util.ArrayList;
 //
 // class to do A* search through a maze
 //
 // Jason Armstrong
 //
public class AStarSearch {
    // instance variables
	Node currentNode;
	Maze maze, moddedMaze;
	int leastNodeIndex; // index of the node with the least calculated distance: f(n) = g(n) h(n)
	int tempf, tempg, temph;
	ArrayList<Node> pathArray = new ArrayList<Node>();
	String leastDirection;
	ArrayList<Node> frontierArray = new ArrayList<Node>();
	
    // Constructor
    public AStarSearch(Maze inMaze) {
        // initialize instance variables
    	maze = inMaze;
    	currentNode = maze.start;
    }

    // Let's do this search!
    public ArrayList<Node> doAStarSearch() {
    	System.out.println("starting search..."); // debugging output
    	
    	// add start to the frontier
    	frontierArray.add(maze.start);
    	
   		while ( !frontierArray.isEmpty() ) {
   			
           	System.out.println("starting large while loop"); //debugging output
           	
   	    	if ( frontierArray.size() == 1) {
   	           	System.out.println("only one node in frontier, so it's the cheapest"); //debugging output   	    		
   	    		leastNodeIndex = 0;
   	    	} else {
   	    		// check items in frontier array
        		for (int frontierCounter = 1; frontierCounter < frontierArray.size(); frontierCounter++) {
       	           	System.out.println("comparing nodes in frontier, node=" + frontierCounter); //debugging output
        			frontierArray.get(frontierCounter).checked = true;
        			if ( frontierArray.get(frontierCounter).f < frontierArray.get(frontierCounter - 1).f ) {
        				leastNodeIndex = frontierCounter;
        			}
        		}
   	    	}
        		
   	    	// output cheapest location
   	    	System.out.println("output cheapest location");
   	    	frontierArray.get(leastNodeIndex).outputNodeInfo();
   	    	
        	// now we are working with the least node, so make it current
        	currentNode = frontierArray.get(leastNodeIndex);
        		
        	// remove current node from the array 
			frontierArray.remove(leastNodeIndex);
        		
			//check to see if we are next to the goal
            if ((maze.goDirection(maze, currentNode, "north").type=='*') || 
            	(maze.goDirection(maze, currentNode,  "east").type=='*') || 
            	(maze.goDirection(maze, currentNode, "south").type=='*') || 
            	(maze.goDirection(maze, currentNode,  "west").type=='*') ) {
            	maze.goal.parent = currentNode; // set the goal's parent to the current node (direction doesn't really matter)
            	System.out.println("breaking since we found the goal"); // debugging output
            	break; // we found the goal, so we're DONE, get out of the for loop
        	}			
			
			// set up directional nodes and change their parents, calculate distances
        	if ( maze.canMove(maze, currentNode, "north") ) { 
            	System.out.println("can move north"); // debugging output
               	tempg = currentNode.g + 1;
               	temph = maze.calculateManDist(maze.goDirection(maze, currentNode, "north"), maze.goal);
               	tempf = tempg + temph;
               	
               	// get some outputs
               	System.out.println("g=" + tempg+" h=" + temph + " f=" + tempf); //debugging output
               	System.out.println("f of northerly direction="+maze.goDirection(maze, currentNode, "north").f); //debugging output
               	System.out.println("maze.goDirection(maze, currentNode, south).f=" + maze.goDirection(maze, currentNode, "south").f);
               	
               	if ( tempf <= maze.goDirection(maze, currentNode, "north").f ) { // if this was already on the frontier from another direction, we would skip this
               		System.out.println("found cheaper node to the north"); //debugging output
               		
               		maze.goDirection(maze, currentNode, "north").parent = currentNode;
               		maze.goDirection(maze, currentNode, "north").f = tempf;
               		maze.goDirection(maze, currentNode, "north").g = tempg;
               		maze.goDirection(maze, currentNode, "north").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "north"));
               	} else if (maze.goDirection(maze, currentNode, " north").checked) {
               		System.out.println("found cheaper already on the frontier, so updating cheaper values"); // debugging output
           			maze.goDirection(maze, currentNode, " north").checked = false;
               		// process the cheaper node
               		maze.goDirection(maze, currentNode, " north").parent = currentNode;
                	maze.goDirection(maze, currentNode, " north").f = tempf;
                	maze.goDirection(maze, currentNode, " north").g = tempg;
                	maze.goDirection(maze, currentNode, " north").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, " north"));
               	}
        	}
            if ( maze.canMove(maze, currentNode,  "east") ) { 
            	System.out.println("can move east"); // debugging output
               	tempg = currentNode.g + 1;
               	temph = maze.calculateManDist(maze.goDirection(maze, currentNode, "east"), maze.goal);
               	tempf = tempg + temph;
               	
               	// get some outputs
               	System.out.println("g=" + tempg +" h=" + temph + " f=" + tempf); //debugging output
               	System.out.println("f of easterly direction=" + maze.goDirection(maze, currentNode, "east").f); //debugging output
               	System.out.println("maze.goDirection(maze, currentNode, south).f=" + maze.goDirection(maze, currentNode, "south").f);
               	
               	if ( tempf <= maze.goDirection(maze, currentNode, "east").f) { // if this was already on the frontier from another direction, we would skip this
               		System.out.println("found cheaper node to the east"); // debugging output
               		maze.goDirection(maze, currentNode, "east").parent = currentNode;
               		maze.goDirection(maze, currentNode, "east").f = tempf;
               		maze.goDirection(maze, currentNode, "east").g = tempg;
               		maze.goDirection(maze, currentNode, "east").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "east"));
               	} else if (maze.goDirection(maze, currentNode, "east").checked) {
               		System.out.println("found cheaper already on the frontier, so updating cheaper values"); // debugging output
           			maze.goDirection(maze, currentNode, "east").checked = false;
               		// process the cheaper node
               		maze.goDirection(maze, currentNode, "east").parent = currentNode;
                	maze.goDirection(maze, currentNode, "east").f = tempf;
                	maze.goDirection(maze, currentNode, "east").g = tempg;
                	maze.goDirection(maze, currentNode, "east").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "east"));
               	}
            }
            if ( maze.canMove(maze, currentNode, "south") ) { 
            	System.out.println("can move south"); // debugging output
               	tempg = currentNode.g + 1;
               	temph = maze.calculateManDist(maze.goDirection(maze, currentNode, "south"), maze.goal);
               	tempf = tempg + temph;
               	
               	// get some outputs
               	System.out.println("g=" + tempg +" h=" + temph + " f=" + tempf); //debugging output
               	System.out.println("f of easterly direction=" + maze.goDirection(maze, currentNode, "south").f); //debugging output
               	System.out.println("maze.goDirection(maze, currentNode, south).f=" + maze.goDirection(maze, currentNode, "south").f);
               	
               	if (tempf <= maze.goDirection(maze, currentNode, "south").f) { // if this was already on the frontier from another direction, we would skip this
               		System.out.println("found cheaper node to the south"); //debugging output
               		maze.goDirection(maze, currentNode, "south").parent = currentNode;
               		maze.goDirection(maze, currentNode, "south").f = tempf;
               		maze.goDirection(maze, currentNode, "south").g = tempg;
               		maze.goDirection(maze, currentNode, "south").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "south"));
               	} else if (maze.goDirection(maze, currentNode, "south").checked) {
               		System.out.println("found cheaper already on the frontier, so updating cheaper values"); // debugging output
           			maze.goDirection(maze, currentNode, "south").checked = false;
               		// process the cheaper node
               		maze.goDirection(maze, currentNode, "south").parent = currentNode;
                	maze.goDirection(maze, currentNode, "south").f = tempf;
                	maze.goDirection(maze, currentNode, "south").g = tempg;
                	maze.goDirection(maze, currentNode, "south").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "south"));
               	}
            }
            if ( maze.canMove(maze, currentNode,  "west") ) { 
            	System.out.println("can move west"); // debugging output
               	tempg = currentNode.g + 1;
               	temph = maze.calculateManDist(maze.goDirection(maze, currentNode, "west"), maze.goal);
               	tempf = tempg + temph;
               	
               	// get some outputs
               	System.out.println("g=" + tempg+" h=" + temph + " f=" + tempf); //debugging output
               	System.out.println("f of northerly direction="+maze.goDirection(maze, currentNode, "north").f); //debugging output
               	System.out.println("maze.goDirection(maze, currentNode, south).f=" + maze.goDirection(maze, currentNode, "south").f);
               	
               	if ( (tempf <= maze.goDirection(maze, currentNode, "west").f)  ) { // if we find a distance that's cheaper than what's there, use the new cheaper one
               		System.out.println("found cheaper node to the west"); //debugging output
               		maze.goDirection(maze, currentNode, "west").parent = currentNode;
                	maze.goDirection(maze, currentNode, "west").f = tempf;
                	maze.goDirection(maze, currentNode, "west").g = tempg;
                	maze.goDirection(maze, currentNode, "west").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "west"));
               	} else if (maze.goDirection(maze, currentNode, "west").checked) {
               		System.out.println("found cheaper already on the frontier, so updating cheaper values"); // debugging output
               			maze.goDirection(maze, currentNode, "west").checked = false;
                   		// process the cheaper node
                   		maze.goDirection(maze, currentNode, "west").parent = currentNode;
                    	maze.goDirection(maze, currentNode, "west").f = tempf;
                    	maze.goDirection(maze, currentNode, "west").g = tempg;
                    	maze.goDirection(maze, currentNode, "west").h = temph;
                   		frontierArray.add(maze.goDirection(maze, currentNode, "west"));
               	}
            }
   		}
    	// output frontier array after each node sweep
   		System.out.println("output frontier array after each node sweep");
    	for (int othercounter=0; othercounter<frontierArray.size(); othercounter++) {
        	frontierArray.get(othercounter).outputNodeInfo();    		
    	}
       	// output values for review
    	maze.printMaze();
    	System.out.println("goal node stats:");
    	maze.goal.outputNodeInfo();
    	
    	
    	
    	/*
    	currentNode = maze.goal;
    	while (currentNode != maze.start) {
    		pathArray.add(0, currentNode);
    		currentNode = currentNode.parent;
    	}
    	modifyMaze(maze, pathArray);
    	*/
    	return frontierArray;
    }
    
    // modify maze with the most efficient path for output
    public Maze modifyMaze(Maze inMaze, ArrayList<Node> pathArray) {
    	moddedMaze = inMaze;
    	int pathx = -1; int pathy= -1;
    	for (int i=0; i < pathArray.size(); i++) {
    		pathx = pathArray.get(i).x;
    		pathy = pathArray.get(i).y;
    		maze.grid[pathy][pathx].type = '.';
    	}
		moddedMaze.printMaze();
    	return moddedMaze;
    }
}
