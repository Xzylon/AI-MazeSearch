// @author: Kyle Ungersma
import java.util.Comparator;
import java.util.PriorityQueue;

public class GreedyBestFirst {
    int nodesExpanded = 0;
    int pathCost = 0;
    Node backtrackNode = new Node(); // Node used to backtrack from goal, to find path cost
    Node directionalNode; // Node of the different directions
    
    
    public GreedyBestFirst(Maze maze) {
        System.out.println();
        System.out.println("**** Greedy Best First Search: " + maze.mazeName + " ****");
        System.out.println();
        
        // minPriorityQueue
        PriorityQueue<Node> pq = new PriorityQueue<Node>(100, new comparableNode());
        // manhat distance for starting node
        maze.start.manhat = maze.calculateManDist(maze.start, maze.goal);
        pq.add(maze.start);
        
        while(!pq.isEmpty()) {
            Node popped = pq.poll();
            nodesExpanded++;
            popped.type = '.';
            
            if (popped.x == maze.goal.x && popped.y == maze.goal.y) {
                System.out.println("Goal Found!");
                backtrackNode = popped; // start the backtracking from the goal (popped)
                while (backtrackNode != maze.start) {
                    pathCost++;
                    backtrackNode.type = '!'; // '!' shows the optimal path
                    backtrackNode = backtrackNode.parent;
                }
                System.out.println("Nodes Expanded: " + nodesExpanded);
                System.out.println("Path Cost: " + pathCost);
                maze.printMaze();
                System.out.println();
                return;
            }
            else { // check the surrounding 4 nodes. if valid and unchecked, add to minPQ
                if (maze.canMove(maze, popped, "north") && !maze.goDirection(maze, popped, "north").checked) {
                    directionalNode = maze.goDirection(maze, popped, "north"); // store 'directionalNode' to reuse
                    directionalNode.checked = true;
                    directionalNode.parent = popped; // set the node's parent to the last one popped
                    directionalNode.manhat = maze.calculateManDist(directionalNode, maze.goal);
                    pq.add(directionalNode);
                }
                if (maze.canMove(maze, popped, "east") && !maze.goDirection(maze, popped, "east").checked) {
                    directionalNode = maze.goDirection(maze, popped, "east");
                    directionalNode.checked = true;
                    directionalNode.parent = popped;
                    directionalNode.manhat = maze.calculateManDist(directionalNode, maze.goal);
                    pq.add(directionalNode);
                }
                if (maze.canMove(maze, popped, "south") && !maze.goDirection(maze, popped, "south").checked) {
                    directionalNode = maze.goDirection(maze, popped, "south");
                    directionalNode.checked = true;
                    directionalNode.parent = popped;
                    directionalNode.manhat = maze.calculateManDist(directionalNode, maze.goal);
                    pq.add(directionalNode);
                }
                if (maze.canMove(maze, popped, "west") && !maze.goDirection(maze, popped, "west").checked) {
                    directionalNode = maze.goDirection(maze, popped, "west");
                    directionalNode.checked = true;
                    directionalNode.parent = popped;
                    directionalNode.manhat = maze.calculateManDist(directionalNode, maze.goal);
                    pq.add(directionalNode);
                }
            }
            popped.checked = true; // all checking done. flag the current node true, and keep looping
        }
    }
    
    
    // comparator for the minPriorityQueue
    public class comparableNode implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return Integer.compare(n1.manhat, n2.manhat);
        }
    }
    
}


