import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//
// class to do A* search through a maze
//
// Jason Armstrong
//
public class AStarSearch {
	// instance variables
	Node currentNode, northNode, eastNode, southNode, westNode;
	boolean northNodeOnFrontier, eastNodeOnFrontier, southNodeOnFrontier, westNodeOnFrontier;
	Maze maze;
	int leastNodeIndex; // index of the node with the least calculated distance: f(n) = g(n) h(n)
	int tempf, tempg, temph;
	ArrayList<Node> pathArray = new ArrayList<Node>();
	String leastDirection;
	ArrayList<Node> frontierArray = new ArrayList<Node>();

<<<<<<< HEAD
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

		while (!frontierArray.isEmpty()) {
			System.out.println("********************************************"); // debugging output
			System.out.println("starting large while loop"); // debugging output

			if (frontierArray.size() == 1) {
				System.out.println("only one node in frontier, so it's the cheapest"); // debugging output
				leastNodeIndex = 0;
			} else {
				// check items in frontier array
				for (int frontierCounter = 1; frontierCounter < frontierArray.size(); frontierCounter++) {
					System.out.println(
							"comparing " + frontierArray.size() + " nodes in frontier, node=" + frontierCounter); // debugging
																													// output
					if (frontierArray.get(frontierCounter).f < frontierArray.get(frontierCounter - 1).f) {
						leastNodeIndex = frontierCounter;
					}
				}
			}

			// output cheapest location
			System.out.println("");
			System.out.println("output cheapest location from frontier at position " + leastNodeIndex);
			frontierArray.get(leastNodeIndex).outputNodeInfo();
			System.out.println("");

			// now we are working with the least node, so make it current
			currentNode = frontierArray.get(leastNodeIndex);

			// output frontier before removing
			System.out.println("output frontier before removing");
			outputFrontier(frontierArray);
			printModdedMaze(maze, frontierArray, "open maze.txt");

			// remove current node from the array
			System.out.println("removing node from frontier");
			System.out.println("");
			frontierArray.remove(leastNodeIndex);
			currentNode.checked = true;

			// output frontier after removing
			System.out.println("output frontier after removing");
			outputFrontier(frontierArray);
			printModdedMaze(maze, frontierArray, "open maze.txt");

			// set up direction nodes
			northNode = maze.goDirection(maze, currentNode, "north");
			;
			eastNode = maze.goDirection(maze, currentNode, "east");
			southNode = maze.goDirection(maze, currentNode, "south");
			westNode = maze.goDirection(maze, currentNode, "west");

			// check to see if we are next to the goal
			if (northNode.type == '*' || eastNode.type == '*' || southNode.type == '*' || westNode.type == '*') {
				maze.goal.parent = currentNode; // set the goal's parent to the current node (direction doesn't really
												// matter)
				System.out.println("breaking since we found the goal"); // debugging output
				break; // we found the goal, so we're DONE, get out of the for loop
			}

			// set up directional nodes and change their parents, calculate distances

			// check north direction
			// **************************************************************
			if (maze.canMove(maze, currentNode, "north") && !northNode.checked) {
				tempg = currentNode.g + 1;
				temph = maze.calculateManDist(northNode, maze.goal);

				for (int counter = 0; counter < frontierArray.size(); counter++) {
					if (northNode.x == frontierArray.get(counter).x && northNode.y == frontierArray.get(counter).y) {
						northNodeOnFrontier = true;
					}
				}
				if (!northNodeOnFrontier) { // node isn't on the frontier
					northNode.g = tempg;
					northNode.h = temph;
					northNode.f = tempg + temph;
					frontierArray.add(northNode); // add it
				} else if (tempg < northNode.g) { // we found a better path
					northNode.parent = currentNode;
					northNode.g = tempg;
					northNode.h = temph;
					northNode.f = tempg + temph;
				}
			}
			// check east direction
			// **************************************************************
			if (maze.canMove(maze, currentNode, "east") && !eastNode.checked) {
				tempg = currentNode.g + 1;
				temph = maze.calculateManDist(eastNode, maze.goal);

				for (int counter = 0; counter < frontierArray.size(); counter++) {
					if (eastNode.x == frontierArray.get(counter).x && eastNode.y == frontierArray.get(counter).y) {
						eastNodeOnFrontier = true;
					}
				}
				if (!eastNodeOnFrontier) { // node isn't on the frontier
					eastNode.g = tempg;
					eastNode.h = temph;
					eastNode.f = tempg + temph;
					frontierArray.add(eastNode); // add it
				} else if (tempg < eastNode.g) { // we found a better path
					eastNode.parent = currentNode;
					eastNode.g = tempg;
					eastNode.h = temph;
					eastNode.f = tempg + temph;
				}
			}
			// check south direction
			// **************************************************************
			if (maze.canMove(maze, currentNode, "south") && !southNode.checked) {
				tempg = currentNode.g + 1;
				temph = maze.calculateManDist(southNode, maze.goal);

				for (int counter = 0; counter < frontierArray.size(); counter++) {
					if (southNode.x == frontierArray.get(counter).x && southNode.y == frontierArray.get(counter).y) {
						southNodeOnFrontier = true;
					}
				}
				if (!southNodeOnFrontier) { // node isn't on the frontier
					southNode.g = tempg;
					southNode.h = temph;
					southNode.f = tempg + temph;
					frontierArray.add(southNode); // add it
				} else if (tempg < southNode.g) { // we found a better path
					southNode.parent = currentNode;
					southNode.g = tempg;
					southNode.h = temph;
					southNode.f = tempg + temph;
				}
			}
			// check west direction
			// **************************************************************
			if (maze.canMove(maze, currentNode, "west") && !westNode.checked) {
				tempg = currentNode.g + 1;
				temph = maze.calculateManDist(westNode, maze.goal);

				for (int counter = 0; counter < frontierArray.size(); counter++) {
					if (westNode.x == frontierArray.get(counter).x && westNode.y == frontierArray.get(counter).y) {
						westNodeOnFrontier = true;
					}
				}
				if (!westNodeOnFrontier) { // node isn't on the frontier
					westNode.g = tempg;
					westNode.h = temph;
					westNode.f = tempg + temph;
					frontierArray.add(westNode); // add it
				} else if (tempg < westNode.g) { // we found a better path
					westNode.parent = currentNode;
					westNode.g = tempg;
					westNode.h = temph;
					westNode.f = tempg + temph;
				}
			}
			System.out.println("end of direction statements");
			// done with directions
			// **************************************************************
		}
		// output frontier array after each node sweep
		System.out.println("output frontier array after each node sweep");
		for (int othercounter = 0; othercounter < frontierArray.size(); othercounter++) {
			frontierArray.get(othercounter).outputNodeInfo();
		}
		// output values for review
		maze.printMaze();
		System.out.println("goal node stats:");
		maze.goal.outputNodeInfo();

		return frontierArray;
	}

	// modify maze with the most efficient path for output
	public void printModdedMaze(ArrayList<Node> frontier, String fileName) {
        this.grid = scanMaze(fileName);
        mazeName = fileName;


		int pathx = -1;
		int pathy = -1;
		for (int i = 0; i < frontier.size(); i++) {
			pathx = frontier.get(i).x;
			pathy = frontier.get(i).y;
			moddedMaze.grid[pathy][pathx].type = 'f';
		}
		moddedMaze.printMaze();
	}

	// modify maze with the most efficient path for output
	public void outputFrontier(ArrayList<Node> frontier) {
		System.out.println("output: frontier entries=" + frontier.size());
		for (int counter = 0; counter < frontier.size(); counter++) {
			System.out.println("frontier entry #" + counter);
			frontier.get(counter).outputNodeInfo();
		}
		System.out.println("");
	}
=======
    // Let's do this search!
    public ArrayList<Node> doAStarSearch() {
    	System.out.println("starting search..."); // debugging output
    	
    	// add start to the frontier
    	frontierArray.add(maze.start);
    	
   		while ( !frontierArray.isEmpty() ) {
   	    	System.out.println(""); //debugging output
           	System.out.println("starting large while loop"); //debugging output
           	
   	    	if ( frontierArray.size() == 1) {
   	           	System.out.println("only one node in frontier, so it's the cheapest"); //debugging output   	    		
   	    		leastNodeIndex = 0;
   	    	} else {
   	    		// check items in frontier array
        		for (int frontierCounter = 1; frontierCounter < frontierArray.size(); frontierCounter++) {
       	           	System.out.println("comparing " + frontierArray.size() + " nodes in frontier, node=" + frontierCounter); //debugging output
        			//frontierArray.get(frontierCounter).checked = true;
        			if ( frontierArray.get(frontierCounter).f < frontierArray.get(frontierCounter - 1).f ) {
        				leastNodeIndex = frontierCounter;
        			}
        		}
   	    	}
        		
   	    	// output cheapest location
   	    	System.out.println("");
   	    	System.out.println("output cheapest location from frontier");
   	    	frontierArray.get(leastNodeIndex).outputNodeInfo();
   	    	System.out.println("");
   	    	
        	// now we are working with the least node, so make it current
        	currentNode = frontierArray.get(leastNodeIndex);
   	    	System.out.println("output cheapest location from currentNode");
   	    	currentNode.outputNodeInfo();
   	    	System.out.println(""); //debugging output
   	    	
        	// remove current node from the array 
			frontierArray.remove(leastNodeIndex);
			currentNode.checked = true;
        		
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
            
            // check north direction **************************************************************
        	if ( maze.canMove(maze, currentNode, "north") ) { 
            	System.out.println("can move north"); // debugging output
               	tempg = currentNode.g + 1;
               	temph = maze.calculateManDist(maze.goDirection(maze, currentNode, "north"), maze.goal);
               	tempf = tempg + temph;
               	
               	// get some outputs
               	System.out.println("g=" + tempg+" h=" + temph + " f=" + tempf); //debugging output
               	System.out.println("f of northerly direction="+maze.goDirection(maze, currentNode, "north").f); //debugging output

                // if our newly calculated value of f (tempf) is cheaper than what's on the node, add it
               	if ( tempf <= maze.goDirection(maze, currentNode, "north").f ) { // if this was already on the frontier from another direction, we would skip this
               		System.out.println("found cheaper node to the north"); //debugging output
               		System.out.println("tempf=" + tempf + ", maze.goDirection(maze, currentNode, " +
               				"south).f=" + maze.goDirection(maze, currentNode, "south").f); //debugging output
           	    	System.out.println(""); //debugging output
               		
               		maze.goDirection(maze, currentNode, "north").parent = currentNode;
               		maze.goDirection(maze, currentNode, "north").f = tempf;
               		maze.goDirection(maze, currentNode, "north").g = tempg;
               		maze.goDirection(maze, currentNode, "north").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "north"));
               		
               	// our newly calculated value is MORE than what was already on the node
               	// in this case we need to put this back on the frontier

               	} else if (maze.goDirection(maze, currentNode, "north").checked) { 
               		System.out.println("found cheaper already on the frontier, so updating cheaper values"); // debugging output
           			maze.goDirection(maze, currentNode, "north").checked = false;
               		// process the cheaper node
               		maze.goDirection(maze, currentNode, "north").parent = currentNode;
                	maze.goDirection(maze, currentNode, "north").f = tempf;
                	maze.goDirection(maze, currentNode, "north").g = tempg;
                	maze.goDirection(maze, currentNode, "north").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "north"));
               	}
        	}
        	
            // check east direction **************************************************************
            if ( maze.canMove(maze, currentNode,  "east") ) { 
            	System.out.println("can move east"); // debugging output
               	tempg = currentNode.g + 1;
               	temph = maze.calculateManDist(maze.goDirection(maze, currentNode, "east"), maze.goal);
               	tempf = tempg + temph;
               	
               	// get some outputs
               	System.out.println("g=" + tempg +" h=" + temph + " f=" + tempf); //debugging output
               	System.out.println("f of easterly direction=" + maze.goDirection(maze, currentNode, "east").f); //debugging output
               	System.out.println("maze.goDirection(maze, currentNode, south).f=" + maze.goDirection(maze, currentNode, "south").f);
               	
               	// if our newly calculated value of f (tempf) is cheaper than what's on the node, add it
               	if ( tempf <= maze.goDirection(maze, currentNode, "east").f) { 
               		System.out.println("found cheaper node to the east"); // debugging output
               		maze.goDirection(maze, currentNode, "east").parent = currentNode;
               		maze.goDirection(maze, currentNode, "east").f = tempf;
               		maze.goDirection(maze, currentNode, "east").g = tempg;
               		maze.goDirection(maze, currentNode, "east").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "east"));
               		
               	// if our newly calculated value of f (tempf) is more expensive but we've already checked it, add it and remove checked flag
              	// our newly calculated value is MORE than what was already on the node
              	// in this case we need to put this back on the frontier
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
            
            // check south direction **************************************************************
            if ( maze.canMove(maze, currentNode, "south") ) { 
            	System.out.println("can move south"); // debugging output
               	tempg = currentNode.g + 1;
               	temph = maze.calculateManDist(maze.goDirection(maze, currentNode, "south"), maze.goal);
               	tempf = tempg + temph;
               	
               	// get some outputs
               	System.out.println("g=" + tempg +" h=" + temph + " f=" + tempf); //debugging output
               	System.out.println("f of easterly direction=" + maze.goDirection(maze, currentNode, "south").f); //debugging output
               	System.out.println("maze.goDirection(maze, currentNode, south).f=" + maze.goDirection(maze, currentNode, "south").f);

               	// if our newly calculated value of f (tempf) is cheaper than what's on the node, add it
               	if (tempf <= maze.goDirection(maze, currentNode, "south").f) { // if this was already on the frontier from another direction, we would skip this
               		System.out.println("found cheaper node to the south"); //debugging output
               		maze.goDirection(maze, currentNode, "south").parent = currentNode;
               		maze.goDirection(maze, currentNode, "south").f = tempf;
               		maze.goDirection(maze, currentNode, "south").g = tempg;
               		maze.goDirection(maze, currentNode, "south").h = temph;
               		frontierArray.add(maze.goDirection(maze, currentNode, "south"));
               		
                   	// if our newly calculated value of f (tempf) is more expensive but we've already checked it, add it and remove checked flag
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
            
            // check west direction **************************************************************
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
            // done with directions  **************************************************************
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
>>>>>>> parent of 0f9c002... Still debugging A*
}
