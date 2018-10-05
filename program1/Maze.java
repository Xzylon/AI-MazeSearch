import java.io.File;
import java.util.Scanner;

public class Maze {
    public static Node[][] scanMaze(String fileName) {
        try {
            Node[][] maze;
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            
            // find the number of rows and columns
            int rows = 0;    // # of rows
            int columns = 0; // # of columns
            while(scanner.hasNextLine()){
                rows++;
                columns = scanner.nextLine().length();
            }
            maze = new Node[rows][columns]; // instantiate the maze with known dimensions
            
            // rescan the file now that the dimensions are known
            scanner = new Scanner(file);
            String line; // a line of the file
            while(scanner.hasNextLine()) {
                for (int i = 0; i < rows; i++) {
                    line = scanner.nextLine();
                    for (int j = 0; j < columns; j++) {
                        maze[i][j] = new Node(line.charAt(j));
                    }
                }
            }
            
            return maze;
        } catch (Exception ex) {
            System.out.println("File Not Found");
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void printMaze(Node[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[1].length; j++) {
                System.out.print(maze[i][j].type);
            }
            System.out.println();
        }
    }
    
    public static boolean isGoal(Node node) {
        if(node.type == '*')
            return true;
        else
            return false;
    }
}
