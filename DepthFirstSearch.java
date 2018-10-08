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
        
        Stack<Node> stack = new Stack();
        stack.push(inMaze.start);
        //check in order and repeat. This format checks left first, then down, then right, then up
        while (!stack.isEmpty()) {

            self = stack.pop();
            //inMaze.grid[self.x][self.y].type = 'x';
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
                if (inMaze.canMove(inMaze, self, "west") && !inMaze.grid[self.y][self.x - 1].checked) {
                    inMaze.grid[self.y][self.x - 1].parent = inMaze.grid[self.y][self.x];
                    stack.push(inMaze.grid[self.y][self.x - 1]);
                    inMaze.grid[self.y][self.x - 1].checked = true;
                }
                if (inMaze.canMove(inMaze, self, "south") && !inMaze.grid[self.y + 1][self.x].checked) {
                    inMaze.grid[self.y + 1][self.x].parent = inMaze.grid[self.y][self.x];
                    stack.push(inMaze.grid[self.y + 1][self.x]);
                    inMaze.grid[self.y + 1][self.x].checked = true;
                }
                if (inMaze.canMove(inMaze, self, "north") && !inMaze.grid[self.y - 1][self.x].checked) {
                    inMaze.grid[self.y-1][self.x].parent = inMaze.grid[self.y][self.x];
                    stack.push(inMaze.grid[self.y-1][self.x]);
                    inMaze.grid[self.y-1][self.x].checked = true;
                }
                if (inMaze.canMove(inMaze, self, "east") && !inMaze.grid[self.y][self.x + 1].checked) {
                    inMaze.grid[self.y][self.x + 1].parent = inMaze.grid[self.y][self.x];
                    stack.push(inMaze.grid[self.y][self.x + 1]);
                    inMaze.grid[self.y][self.x + 1].checked = true;
                }
                
            }
        }
    }
}
