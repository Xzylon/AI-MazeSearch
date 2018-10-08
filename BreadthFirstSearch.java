
import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author rtill
 */
public class BreadthFirstSearch {
    
    static void BreadthFirst(Maze maze)//Written by Robert Tiller
    {
        //Section Title Printout
        

        //create new queue (first in first out) for holding the fronteir nodes
        Queue<Node> que = new LinkedList<>();
        int pathCost =1;
        int ExpandedNodeCount=0; //start with 1 node since we will be the first node expanded and then addind to this number after each complete expansion around a node. 

        que.add( maze.start );
  
        //keep checking around while there are node on the queue
        while( !que.isEmpty() )
        {
                Node current = que.remove();
                //current.type = 'x';
                //Maze.printMaze(maze);
                
                //check if node pulled off queue is goal
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
                        maze.printMaze();
                        System.out.println("Found the End!!!");
                        System.out.println("Number of Nodes Expanded: " + ExpandedNodeCount);
                        System.out.println("Cost of Path: " + pathCost);
                        System.out.println();
                        

                        return;
                }

                //if node isnt goal check around it and push to queue
                else
                {
                        //Count Nodes Expanded
                        ExpandedNodeCount=ExpandedNodeCount+1;
                        //add top neighbor
                       if( maze.canMove(maze, current, "north") && !maze.grid[current.y-1][current.x].checked )
                        {
                            maze.grid[current.y-1][current.x].checked=true;
                            maze.grid[current.y-1][current.x].parent = maze.grid[current.y][current.x];
                            que.add(maze.grid[current.y-1][current.x]);
                        }
                        //add right neighbor
                        if( maze.canMove(maze, current, "east") && !maze.grid[current.y][current.x+1].checked )
                        {
                            maze.grid[current.y][current.x+1].checked=true;
                            maze.grid[current.y][current.x+1].parent = maze.grid[current.y][current.x];
                            que.add(maze.grid[current.y][current.x+1]); 
                        }
                        //add down neighbor
                        if( maze.canMove(maze, current, "south") && !maze.grid[current.y+1][current.x].checked )
                        {
                            maze.grid[current.y+1][current.x].checked=true;
                            maze.grid[current.y+1][current.x].parent = maze.grid[current.y][current.x];
                            que.add(maze.grid[current.y+1][current.x]);
                        }
                        //add left neighbor
                        if( maze.canMove( maze,current, "west") && !maze.grid[current.y][current.x-1].checked )
                        {
                            maze.grid[current.y][current.x-1].checked=true;
                            maze.grid[current.y][current.x-1].parent = maze.grid[current.y][current.x];
                            que.add(maze.grid[current.y][current.x-1]);
                        }
                }
        }
        
    }
    
}
