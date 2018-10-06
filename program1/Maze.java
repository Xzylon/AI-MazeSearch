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
            maze = new Node[rows][columns]; // instantiate the maze with known dimensions
            
            // rescan the file now that the dimensions are known
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
            
            return maze;
        } catch (Exception ex) {
            System.out.println("File Not Found");
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void printMaze(Maze maze) {
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.columns; j++) {
                System.out.print(maze.grid[i][j].type);
            }
            System.out.println();
        }
    }
    
}
