

public class main {
    public static Maze medMaze = new Maze("medium maze.txt");
    public static Maze largeMaze = new Maze("large maze.txt");
    public static Maze openMaze = new Maze("open maze.txt");
    
    public static void main(String[] args) {
    	openMaze.printMaze();
    	
    	AStarSearch aStarSearchOpenMaze = new AStarSearch(openMaze);
    	AStarSearch aStarSearchMedMaze = new AStarSearch(medMaze);
    	AStarSearch aStarSearchLargeMaze = new AStarSearch(largeMaze);    	

    	
    	aStarSearchOpenMaze.doAStarSearch();
    	aStarSearchMedMaze.doAStarSearch();
    	aStarSearchLargeMaze.doAStarSearch();    	

    	
    }
    



    
}