import java.io.File;
import java.util.Scanner;

public class Maze {
    int rows;
    int columns;
    Node start;
    Node goal;
    Node currentNode;
    Node[][] grid;
    
    public Maze(String fileName) {
        this.grid = scanMaze(fileName);
    }
    
    public Node[][] scanMaze(String fileName) {
        try {
            Node[][] maze;
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            
            // find the number of rows and columns
            while(scanner.hasNextLine()){
                rows++;
                columns = scanner.nextLine().length();
            }
            scanner.close();
            
            maze = new Node[rows][columns]; // instantiate the maze with known dimensions
            
            // rescan the file now that the dimensions are known
            // this results in quadrant IV or the origin is in the upper left corner of the grid 
            scanner = new Scanner(file);
            String line; // a line of the file
            while(scanner.hasNextLine()) {
                for (int i = 0; i < rows; i++) {
                    line = scanner.nextLine();
                    for (int j = 0; j < columns; j++) {
                        maze[i][j] = new Node(i, j, line.charAt(j));
                        if(line.charAt(j) == 'P') {
                            start = maze[i][j];
                            currentNode = maze[i][j];
                        }
                        if(line.charAt(j) == '*')
                            goal = maze[i][j];
                    }
                }
            }
            scanner.close();
            return maze;
        } catch (Exception ex) {
            System.out.println("File Not Found");
            ex.printStackTrace();
            return null;
        }
    }
    
    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j].type);
            }
            System.out.println();
        }
    }
    
    //check if there is a wall in a direction
    public boolean canMove(Maze maze, Node currentNode, String direction) {
        int x = currentNode.x;
        int y = currentNode.y;
        
        // return true if its not a wall(%)
        if ( (direction == "north" && maze.grid [x]   [y-1].type != '%') ||
             (direction == "east"  && maze.grid [x+1]   [y].type != '%') ||
             (direction == "south" && maze.grid [x]   [y+1].type != '%') ||
             (direction == "west"  && maze.grid [x-1]   [y].type != '%') ) {
        	return true;        	
        } else {return false;}        	
   }
    
    //return a node that is one space away in a given direction, for the sake of moving
    public Node goDirection(Maze maze, Node currentNode, String direction) {
        int x = currentNode.x;
        int y = currentNode.y;
        
        // return true if its not a wall(&)
        if        ( direction == "north" && canMove(maze, currentNode, "north") ) {
        	return maze.grid[x][y-1];
        } else if ( direction ==  "east" && canMove(maze, currentNode,  "east") ) {
        	return maze.grid[x+1][y];
        } else if ( direction == "south" && canMove(maze, currentNode, "south") ) { 
        	return maze.grid[x][y+1];
        } else  if ( direction == "west" && canMove(maze, currentNode,  "west") ) { 
        	return maze.grid[x-1][y];
        } else {return null;}        	
   }
    
    // goal test
    public boolean isGoal(Node node) {
        if(node.type == '*')
            return true;
        else
            return false;
    }    
    
    public int calculateManDist(Node node1, Node node2) {
    	int distance = 0;
    	
    	if(node1.x > node2.x) { distance = node1.x - node2.x; }
    	else { distance = node2.x - node1.x; }
    	if(node1.y > node2.y) { distance = distance + (node1.y - node2.y); } 
    	else { distance = distance + (node2.y - node1.y); }    	
        return distance;
    }
}
