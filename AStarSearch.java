import java.util.ArrayList;
// class to do A* search through a maze
// Jason Armstrong
//
public class AStarSearch {
	Node currentNode, northNode, eastNode, southNode, westNode;
	boolean northNodeOnFrontier, eastNodeOnFrontier, southNodeOnFrontier, westNodeOnFrontier;
	Maze maze, moddedMaze;
	int leastNodeIndex;
	int tempf, tempg, temph;
	ArrayList<Node> pathArray = new ArrayList<Node>();
	String leastDirection;
	ArrayList<Node> frontierArray = new ArrayList<Node>();
	int nodeCount; // used to keep tack of total nodes expanded or inspected
	public AStarSearch(Maze inMaze) {
		maze = inMaze;
		currentNode = maze.start;
	}

	public void doAStarSearch() {
		System.out.println("beginning search...");
		frontierArray.add(maze.start);
		nodeCount=1;
		while (!frontierArray.isEmpty()) {
			if (frontierArray.size() == 1) {
				leastNodeIndex = 0;
			} else {
				for (int frontierCounter = 1; frontierCounter < frontierArray.size(); frontierCounter++) {
					if (frontierArray.get(frontierCounter).f < frontierArray.get(frontierCounter - 1).f) {
						leastNodeIndex = frontierCounter;
					}
				}
			}
			
			//before node removal from frontier
			System.out.println("before node removal from frontier");
			System.out.println("     frontier size=" + frontierArray.size() + "   least index=" + leastNodeIndex);
			outputFrontier(frontierArray);
			System.out.println("output least node details (to be removed)");
			frontierArray.get(leastNodeIndex).altOutputNodeInfo();
			
			currentNode = frontierArray.get(leastNodeIndex);
			frontierArray.remove(leastNodeIndex);
			currentNode.checked = true;

			//after node removal from frontier
			System.out.println("after node removal from frontier");
			System.out.println("     frontier size=" + frontierArray.size() + "   least index=" + leastNodeIndex);
			outputFrontier(frontierArray);
			
			northNode = maze.goDirection(maze, currentNode, "north");
			eastNode = maze.goDirection(maze, currentNode, "east");
			southNode = maze.goDirection(maze, currentNode, "south");
			westNode = maze.goDirection(maze, currentNode, "west");

			if (northNode.type == '*' || eastNode.type == '*' || southNode.type == '*' || westNode.type == '*') {
				maze.goal.parent = currentNode;
				break;
			}
			System.out.println("starting on directions");
			
			// check north direction **************************************************************
			if (maze.canMove(maze, currentNode, "north") && !northNode.checked) {
				tempg = currentNode.g + 1;
				temph = maze.calculateManDist(northNode, maze.goal);

				for (int counter = 0; counter < frontierArray.size(); counter++) {
					if (northNode.x == frontierArray.get(counter).x && northNode.y == frontierArray.get(counter).y) {
						northNodeOnFrontier = true;
					}
				}
				if (!northNodeOnFrontier) { // undiscovered node
					nodeCount++;
					northNode.g = tempg;
					northNode.h = temph;
					northNode.f = tempg + temph;
					frontierArray.add(northNode);
				} else if (tempg < northNode.g) { // found a better path
					northNode.parent = currentNode;
					northNode.g = tempg;
					northNode.h = temph;
					northNode.f = tempg + temph;
				}
			}
			// check east direction **************************************************************
			if (maze.canMove(maze, currentNode, "east") && !eastNode.checked) {
				tempg = currentNode.g + 1;
				temph = maze.calculateManDist(eastNode, maze.goal);

				for (int counter = 0; counter < frontierArray.size(); counter++) {
					if (eastNode.x == frontierArray.get(counter).x && eastNode.y == frontierArray.get(counter).y) {
						eastNodeOnFrontier = true;
					}
				}
				if (!eastNodeOnFrontier) { // undiscovered node
					nodeCount++;
					eastNode.g = tempg;
					eastNode.h = temph;
					eastNode.f = tempg + temph;
					frontierArray.add(eastNode);
				} else if (tempg < eastNode.g) { // found a better path
					eastNode.parent = currentNode;
					eastNode.g = tempg;
					eastNode.h = temph;
					eastNode.f = tempg + temph;
				}
			}
			// check south direction **************************************************************
			if (maze.canMove(maze, currentNode, "south") && !southNode.checked) {
				tempg = currentNode.g + 1;
				temph = maze.calculateManDist(southNode, maze.goal);

				for (int counter = 0; counter < frontierArray.size(); counter++) {
					if (southNode.x == frontierArray.get(counter).x && southNode.y == frontierArray.get(counter).y) {
						southNodeOnFrontier = true;
					}
				}
				if (!southNodeOnFrontier) { // undiscovered node
					nodeCount++;
					southNode.g = tempg;
					southNode.h = temph;
					southNode.f = tempg + temph;
					frontierArray.add(southNode);
				} else if (tempg < southNode.g) { // found a better path
					southNode.parent = currentNode;
					southNode.g = tempg;
					southNode.h = temph;
					southNode.f = tempg + temph;
				}
			}
			// check west direction **************************************************************
			if (maze.canMove(maze, currentNode, "west") && !westNode.checked) {
				tempg = currentNode.g + 1;
				temph = maze.calculateManDist(westNode, maze.goal);

				for (int counter = 0; counter < frontierArray.size(); counter++) {
					if (westNode.x == frontierArray.get(counter).x && westNode.y == frontierArray.get(counter).y) {
						westNodeOnFrontier = true;
					}
				}
				if (!westNodeOnFrontier) { // undiscovered node
					nodeCount++;
					westNode.g = tempg;
					westNode.h = temph;
					westNode.f = tempg + temph;
					frontierArray.add(westNode);
				} else if (tempg < westNode.g) { // found a better path
					westNode.parent = currentNode;
					westNode.g = tempg;
					westNode.h = temph;
					westNode.f = tempg + temph;
				}
			}
			// done with directions **************************************************************
			System.out.println("Nodes inspected=" + nodeCount);
		}
		outputPath();
	}

	public void outputPath() {
		Node current = maze.goal;
		while ( current.x != maze.start.x && current.x != maze.start.x ) {
			System.out.println("backwards from goal, x=" + current.x + " y=" + current.y);
			current = current.parent;
		}
	}
	
	public void outputFrontier(ArrayList<Node> frontier) {
		System.out.println("output: frontier entries=" + frontier.size());
		for (int counter = 0; counter < frontier.size(); counter++) {
			System.out.println("     frontier entry #" + counter);
			frontier.get(counter).altOutputNodeInfo();
		}
	}
}