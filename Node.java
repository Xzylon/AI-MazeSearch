public class Node {
    public int x, y;
    public boolean checked;
    public float manhat;
    public char type;
    public Node parent;
    public int f, g, h;
    
    public Node() {
        x = 0;
        y = 0;
        type = '\0'; // null character
        checked = false;
        manhat = 0;
        parent = null;
        f = 2147483647;
        g = 2147483647;
        h = 2147483647;
    }
    
    public Node(int y, int x, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
        checked = false;
        manhat = 0;
        parent = null;
    }
}
