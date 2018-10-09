public class Node {
    public int x, y;
    public boolean checked;
    public int manhat;
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
        f = 2147483647;
        g = 2147483647;
        h = 2147483647;
    }
    
    public void outputNodeInfo() { //generally used for debugging
        System.out.println("     x=" + x + "   y=" + y + "   type=" + type + "   checked=" + checked + "   manhat=" + manhat);
        System.out.print("      f="+f + "   g="+g + "   h="+h);
        if (parent == null) {
            System.out.println("   no parent");
        } else {
            System.out.println("parentX=" + parent.x + " parentY=" + parent.y);
        }
    }

    public void altOutputNodeInfo() { //generally used for debugging
    	int tempx = x+1; int tempy = y+1;
        System.out.println("     x=" + tempx + "   y=" + tempy + "   type=" + type + "   checked=" + checked + "   manhat=" + manhat);
        System.out.print("      f="+f + "   g="+g + "   h="+h);
        if (parent == null) {
            System.out.println("   no parent");
        } else {
            System.out.println("parentX=" + parent.x + " parentY=" + parent.y);
        }
    }}
