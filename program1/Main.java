/*AI
 *By: Robert Tiller, Beau Anderson, Kyle Ungersma, and Jason Armstrong
 *
 * DFS
 * BFS
 * Greedy Best-first search
 * A* search
 *
*/



import java.math.*;
import java.io.*;
import java.util.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class main {
    public static Maze medMaze = new Maze("medium maze.txt");
    public static Maze largeMaze = new Maze("large maze.txt");
    public static Maze openMaze = new Maze("open maze.txt");
    
    public static void main(String[] args) {
    	medMaze.printMaze();
    }
    

	static void BreadthFirst(Maze maze)//Written by Robert Tiller
		{
			//Section Title Printout
			System.out.println("\n\n\n---------------------------------------\n Breadth First Maze \n ---------------------------------------");

			//create new queue (first in first out) for holding the fronteir nodes
			Queue<Node> que = new LinkedList<>();
			int pathCost =1;
			int ExpandedNodeCount=0; //start with 1 node since we will be the first node expanded and then addind to this number after each complete expansion around a node. 

			que.add( maze.start );
	  

			while( !que.isEmpty() )
			{
					Node current = que.remove();
					//current.type = 'x';
					//Maze.printMaze(maze);
					
					if(current == maze.goal)
					{
							while(current != maze.start)
							{ 
								if(current.type == '*')
								{}
								else
								{
								current.type = '.';
								}
								pathCost += 1;
								current = current.parent;
								//Maze.printMaze(maze);
							}
							Maze.printMaze(maze);
							System.out.println("Found the End!!!");
							System.out.println("Number of Nodes Expanded: " + ExpandedNodeCount);
							System.out.println("Cost of Path: " + pathCost);
							System.out.println();
							

							return;
					}

					//add neighbors
					else
					{
							//Count Nodes Expanded
							ExpandedNodeCount=ExpandedNodeCount+1;

						   if( maze.canMove(maze, current, "north") && !maze.grid[current.x][current.y-1].checked )
							{
								maze.grid[current.x][current.y-1].checked=true;
								maze.grid[current.x][current.y-1].parent = maze.grid[current.x][current.y];
								que.add(maze.grid[current.x][current.y-1]);
							}
							//add right neighbor
							if( maze.canMove(maze, current, "east") && !maze.grid[current.x+1][current.y].checked )
							{
								maze.grid[current.x+1][current.y].checked=true;
								maze.grid[current.x+1][current.y].parent = maze.grid[current.x][current.y];
								que.add(maze.grid[current.x+1][current.y]); 
							}
							//add down neighbor
							if( maze.canMove(maze, current, "south") && !maze.grid[current.x][current.y+1].checked )
							{
								maze.grid[current.x][current.y+1].checked=true;
								maze.grid[current.x][current.y+1].parent = maze.grid[current.x][current.y];
								que.add(maze.grid[current.x][current.y+1]);
							}
							//add left neighbor
							if( maze.canMove( maze,current, "west") && !maze.grid[current.x-1][current.y].checked )
							{
								maze.grid[current.x-1][current.y].checked=true;
								maze.grid[current.x-1][current.y].parent = maze.grid[current.x][current.y];
								que.add(maze.grid[current.x-1][current.y]);
							}
					}
			}
			
		}

    
}