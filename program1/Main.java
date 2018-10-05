import java.io.File;
import java.util.Scanner;

public class main {
    public static Node[][] medMaze = Maze.scanMaze("medium maze.txt");
    public static Node[][] largeMaze = Maze.scanMaze("large maze.txt");
    public static Node[][] openMaze = Maze.scanMaze("open maze.txt");
    
    public static void main(String[] args) {
        Maze.printMaze(medMaze);
    }
    

}
