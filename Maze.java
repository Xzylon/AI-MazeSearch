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
                for (int y = 0; y < rows; y++) {
                    line = scanner.nextLine();
                    for (int x = 0; x < columns; x++) {
                        maze[y][x] = new Node(y, x, line.charAt(x));
                        if(line.charAt(x) == 'P') {
                            start = maze[y][x];
                            currentNode = maze[y][x];
                        }
                        if(line.charAt(x) == '*')
                            goal = maze[y][x];
                    }
                }
            }
            // initialize start values
            start.g = 0;
            start.h = calculateManDist(start, goal);
            goal.h = 0;
            
            scanner.close();
            return maze;
        } catch (Exception ex) {
            System.out.println("File Not Found");
            ex.printStackTrace();
            return null;
        }
    }
    
    public void printMaze() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                System.out.print(grid[y][x].type);
            }
            System.out.println();
        }
    }
    
    //check if there is a wall in a direction
    public boolean canMove(Maze maze, Node currentNode, String direction) {
        int x = currentNode.x;
        int y = currentNode.y;
        
        // return true if its not a wall(&)
        if ( (direction == "north" && maze.grid [y-1]   [x].type != '%') ||
             (direction == "east"  && maze.grid [y]   [x+1].type != '%') ||
             (direction == "south" && maze.grid [y+1]   [x].type != '%') ||
             (direction == "west"  && maze.grid [y]   [x-1].type != '%') ) {
        	return true;        	
        } else {return false;}        	
   }
    
    //return a node that is one space away in a given direction, for the sake of moving
    public Node goDirection(Maze maze, Node currentNode, String direction) {
        int x = currentNode.x;
        int y = currentNode.y;
        
        // more outputs
        // System.out.println("in go direction method");
     	// System.out.println("currentnode X=" + x + ", Y=" + y + ", type=" + currentNode.type);
        
        // return true if its not a wall(&)
        if        ( direction == "north" ) {
        	return maze.grid[y-1][x];
        } else if ( direction ==  "east" ) {
        	return maze.grid[y][x+1];
        } else if ( direction == "south" ) { 
        	return maze.grid[y+1][x];
        } else  if ( direction == "west" ) { 
        	return maze.grid[y][x-1];
        } else { return null; }
   }
    
    // goal test
    public boolean isGoal(Node node) {
        if(node.type == '*')
            return true;
        else
            return false;
    }    
    
    // calculate the Manhattan distance between two points
    public int calculateManDist(Node node1, Node node2) {
        // 
    	int distance = 0;
    	
    	if(node1.x > node2.x) { distance = node1.x - node2.x; }
    	else { distance = node2.x - node1.x; }
    	if(node1.y > node2.y) { distance = node1.y - node2.y; } 
    	else { distance = node2.y - node1.y; }    	
        return distance;
    }
    

}
