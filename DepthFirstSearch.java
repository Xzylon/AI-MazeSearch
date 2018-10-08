import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author beau
 */
public class DepthFirstSearch {

    int expandedCount = 0;//count how many times we expand a node
    int depth = 0;//checks how far we trekked from the starting point
    int tempmax = 0; //temporarily holds the maximum
    int pathCost = 0;
    public DepthFirstSearch(Maze inMaze) {
        inMaze.start.parent = null;
        Node self = inMaze.start; //finds the root node
        
        Stack<Node> stack = new Stack<Node>();
        stack.push(inMaze.start);
        inMaze.printMaze();
        //check in order and repeat. This format checks left first, then down, then right, then up
        while (!stack.isEmpty()) {

            self = stack.pop();
            //inMaze.grid[self.x][self.y].type = 'x';
            //inMaze.printMaze();
            if (self == inMaze.goal) {
                //TODO build the results jumping back up the parents
                while (self != inMaze.start) {
                    self.type = '.';
                    self = self.parent;
                    pathCost++;
                }
                inMaze.printMaze();
                System.out.println("Expanded nodes:" + expandedCount);
                System.out.println("Path Cost:" + pathCost);
                        
                break;
            } else {
                expandedCount++;
                self.checked = true;
                if (inMaze.canMove(inMaze, self, "west") && !inMaze.grid[self.x][self.y - 1].checked) {
                    inMaze.grid[self.x][self.y - 1].parent = inMaze.grid[self.x][self.y];
                    stack.push(inMaze.grid[self.x][self.y - 1]);
                    inMaze.grid[self.x][self.y - 1].checked = true;
                }
                if (inMaze.canMove(inMaze, self, "south") && !inMaze.grid[self.x + 1][self.y].checked) {
                    inMaze.grid[self.x + 1][self.y].parent = inMaze.grid[self.x][self.y];
                    stack.push(inMaze.grid[self.x + 1][self.y]);
                    inMaze.grid[self.x + 1][self.y].checked = true;
                }
                if (inMaze.canMove(inMaze, self, "north") && !inMaze.grid[self.x - 1][self.y].checked) {
                    inMaze.grid[self.x-1][self.y].parent = inMaze.grid[self.x][self.y];
                    stack.push(inMaze.grid[self.x-1][self.y]);
                    inMaze.grid[self.x-1][self.y].checked = true;
                }
                if (inMaze.canMove(inMaze, self, "east") && !inMaze.grid[self.x][self.y + 1].checked) {
                    inMaze.grid[self.x ][self.y + 1].parent = inMaze.grid[self.x][self.y];
                    stack.push(inMaze.grid[self.x][self.y + 1]);
                    inMaze.grid[self.x][self.y + 1].checked = true;
                }
                
            }
        }
    }
}
