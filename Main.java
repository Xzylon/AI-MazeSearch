/*AI
 *By: Robert Tiller, Beau Anderson, Kyle Ungersma, and Jason Armstrong
 *
 * DFS
 * BFS
 * Greedy Best-first search
 * A* search
 *
<<<<<<< HEAD
*/
=======
 */
>>>>>>> 98eaeb3495fc343fa6bd18f07fa6b296904a7ebe

public class Main {
    public static Maze medMaze = new Maze("medium maze.txt");
    public static Maze largeMaze = new Maze("large maze.txt");
    public static Maze openMaze = new Maze("open maze.txt");
    
    public static void main(String[] args) {
        // ** Greedy Best First Search **
        //GreedyBestFirst medGBF = new GreedyBestFirst(medMaze);
        //GreedyBestFirst largeGBF = new GreedyBestFirst(largeMaze);
        //GreedyBestFirst openGBF = new GreedyBestFirst(openMaze);
        
        
        // ** Breadth First Search **
		/*
		System.out.println("\n\n\n---------------------------------------\n Breadth First Maze medMaze \n ---------------------------------------");
        BreadthFirst medBFS = new BreadthFirst(medMaze);
        System.out.println("\n\n\n---------------------------------------\n Breadth First Maze largeMaze \n ---------------------------------------");
        BreadthFirst largeBFS = new BreadthFirst(largeMaze);
        System.out.println("\n\n\n---------------------------------------\n Breadth First Maze openMaze \n ---------------------------------------");
        BreadthFirst openBFS = new BreadthFirst(openMaze);
		*/
		
		
		// ** Depth First Search **
        //DepthFirstSearch medDFS = new DepthFirstSearch(medMaze);
        //DepthFirstSearch largeDFS = new DepthFirstSearch(largeMaze);
        //DepthFirstSearch openDFS = new DepthFirstSearch(openMaze);
    
    
        // ** A* Search **
        //AStarSearch aStarSearchOpenMaze = new AStarSearch(openMaze);
        //aStarSearchOpenMaze.doAStarSearch();
        
        //AStarSearch aStarSearchMedMaze = new AStarSearch(medMaze);
        //aStarSearchMedMaze.doAStarSearch();
        
        //AStarSearch aStarSearchLargeMaze = new AStarSearch(largeMaze);
        //aStarSearchLargeMaze.doAStarSearch();
        
        
    }
    
}