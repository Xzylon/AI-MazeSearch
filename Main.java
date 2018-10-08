/*AI
 *By: Robert Tiller, Beau Anderson, Kyle Ungersma, and Jason Armstrong
 *
 * DFS
 * BFS
 * Greedy Best-first search
 * A* search
 *
*/



// import java.math.*;
// import java.io.*;
import java.util.*;
// import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static Maze medMaze = new Maze("medium maze.txt");
    public static Maze largeMaze = new Maze("large maze.txt");
    public static Maze openMaze = new Maze("open maze.txt");
    
    public static void main(String[] args) {
    	openMaze.printMaze();
		
		//BFS Maze
		/*
		System.out.println("\n\n\n---------------------------------------\n Breadth First Maze medMaze \n ---------------------------------------");
        BreadthFirst medMazeBFS = new BreadthFirst(medMaze);
        System.out.println("\n\n\n---------------------------------------\n Breadth First Maze largeMaze \n ---------------------------------------");
        BreadthFirst mlargeMazeBFS = new BreadthFirst(largeMaze);
        System.out.println("\n\n\n---------------------------------------\n Breadth First Maze openMaze \n ---------------------------------------");
        BreadthFirst openMazeBFS = new BreadthFirst(openMaze);
		*/
    	
    	AStarSearch aStarSearchOpenMaze = new AStarSearch(openMaze);
    	aStarSearchOpenMaze.doAStarSearch();
    	
    	//AStarSearch aStarSearchMedMaze = new AStarSearch(medMaze);
    	//aStarSearchMedMaze.doAStarSearch();    	
    	
    	//AStarSearch aStarSearchLargeMaze = new AStarSearch(largeMaze);    	
    	//aStarSearchLargeMaze.doAStarSearch();    	

    	
    }
    

    
}