public class Node {
    public boolean checked;
    public float manhat;
    public char type;
    
    public Node() {
        type = '\0'; // null character
        checked = false;
        manhat = 0;
    }
    
    public Node(char type) {
        this.type = type;
        checked = false;
        manhat = 0;
    }
}
