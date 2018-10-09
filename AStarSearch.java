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

	public AStarSearch(Maze inMaze) {
		maze = inMaze;
		currentNode = maze.start;
	}

	public ArrayList<Node> doAStarSearch() {
		frontierArray.add(maze.start);
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
			frontierArray.get(leastNodeIndex).outputNodeInfo();
			currentNode = frontierArray.get(leastNodeIndex);
			frontierArray.remove(leastNodeIndex);
			currentNode.checked = true;

			northNode = maze.goDirection(maze, currentNode, "north");
			eastNode = maze.goDirection(maze, currentNode, "east");
			southNode = maze.goDirection(maze, currentNode, "south");
			westNode = maze.goDirection(maze, currentNode, "west");

			if (northNode.type == '*' || eastNode.type == '*' || southNode.type == '*' || westNode.type == '*') {
				maze.goal.parent = currentNode;
				break;
			}
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
		}
		return frontierArray;
	}


	public void outputFrontier(ArrayList<Node> frontier) {
		System.out.println("output: frontier entries=" + frontier.size());
		for (int counter = 0; counter < frontier.size(); counter++) {
			System.out.println("frontier entry #" + counter);
			frontier.get(counter).outputNodeInfo();
		}
		System.out.println("");
	}
}