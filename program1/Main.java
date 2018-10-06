import java.io.File;
import java.util.Scanner;


public class Main {
    public static Maze medMaze = new Maze("medium maze.txt");
    public static Maze largeMaze = new Maze("large maze.txt");
    public static Maze openMaze = new Maze("open maze.txt");
    
    public static void main(String[] args) {
        Maze.printMaze(medMaze);
    }
    


   public boolean canMove(Maze maze, Node currentNode, String direction) {
        int x = currentNode.x;
        int y = currentNode.y;
        
        // return true if its not a wall(&)
        if(direction == "up") {
            if (maze.grid[x][y-1].type != '&')
                return true;
            else
                return false;
        }
        /*
        * TODO:
        * Add the other directions
        */
        else
            return false;
   }
    
    
    // goal test
    public boolean isGoal(Node node) {
        if(node.type == '*')
            return true;
        else
            return false;
    }
    
}