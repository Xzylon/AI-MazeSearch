public class Node {
    public int x, y;
    public boolean checked;
    public float manhat;
    public char type;
    public Node parent;
    
    public Node() {
        x = 0;
        y = 0;
        type = '\0'; // null character
        checked = false;
        manhat = 0;
        parent = null;
        
    }
    
    public Node(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
        checked = false;
        manhat = 0;
        parent = null;
    }
}
