public class RLRBT<Key extends Comparable<Key>, Value> 
{
    private Node root;
    private int N;

    // CONSTRUCTOR 
    public RLRBT() 
    {
        this.root = null;
        this.N = 0;
    }

    // PUBLIC METHODS 

    //
    // insert a new (key, val) into tree
    // or replace value of existing key
    //
    public void insert(Key key, Value val) 
    {
        if (root == null) {
            root = new Node(key, val);
            root.height = 0;
            root.isRed = false;
            N = 1;
            return;
        }

        //insert a non-root
        root = search(root, key, val);
        root.height = updateHeight(root);
        root.isRed = false;
    }

    public int updateHeight(Node current) {
        if (current == null) {
            return -1;
        }

        if (current.right == null && current.left == null) {
            current.height = 0;
            return current.height;
        }

        else {
            current.height = Math.max(updateHeight(current.left), updateHeight(current.right)) + 1;
            return current.height;
        }
    }

    public Node search(Node current, Key key, Value val) {
        if (current == null) {
            current = new Node(key, val);
            N++;
            current.height = 0;
            return current;
        }

        if (key.compareTo(current.key) == 0) {
            current.val = val;
        }
        else if (key.compareTo(current.key) > 0) {
            current.right = search(current.right, key, val);
        }
        else if (key.compareTo(current.key) < 0) {
            current.left = search(current.left, key, val);
        }

        colorFlip(current);
        if (current.left != null && current.left.isRed) {
            current = rotateRight(current);
        }

        if (current.right != null) {
            if (current.right.right != null) {
                if (current.right.isRed && current.right.right.isRed) {
                    //check
                    current = rotateLeft(current);
                    colorFlip(current);
                }
            }
            if (current.right.left != null) {
                if (current.right.isRed && current.right.left.isRed) {
                    current = rotateRight(current.right);
                    current = rotateLeft(current);
                    colorFlip(current);
                }
            }
        }
        return current;
    }
    
    //
    // get the value associated with the given key;
    // return null if key doesn't exist
    //
    public Value get(Key key) 
    {
        Value val = getSpecific(root, key);
        return val;
    }

    public Value getSpecific(Node current, Key key) {
        Value val = null;
        if (current == null) {
            return null;
        }

        if (key.compareTo(current.key) == 0) {
            return current.val;
        }
        else if (key.compareTo(current.key) > 0) {
            val = getSpecific(current.right, key);
        }
        else if (key.compareTo(current.key) < 0) {
            val = getSpecific(current.left, key);
        }
        return val;
    }

    //
    // return true if the tree
    // is empty and false 
    // otherwise
    //
    public boolean isEmpty() 
    {
	    return root == null;
    }

    //
    // return the number of Nodes
    // in the tree
    //
    public int size() 
    {
	    return N;
    }

    //
    // returns the height of the tree
    //
    public int height() 
    {
	    return height(root);
    }

    //
    // returns the height of node 
    // with given key in the tree;
    // return -1 if the key does
    // not exist in the tree
    //
    public int height(Key key) 
    {
        Node current = findNode(root, key);
        if (current == null) return -1;
        //else {
        return current.height;
        //}
    }

    public Node findNode(Node current, Key key) {
        if (current == null || current.key.compareTo(key) == 0) {
            return current;
        }
        else if (current.key.compareTo(key) > 0) {
            return findNode(current.left, key);
        }
        return findNode(current.right, key);
    }

    //                                                                        
    // return String representation of tree                                      
    // level by level                                                            
    //
    public String toString() 
    {
        String ret = "Level 0: ";
        Pair x = null;
        Queue<Pair> queue = new Queue<Pair>(N);
        int level = 0;
        queue.enqueue(new Pair(root, level));
        
        while(!queue.isEmpty()) 
        {
            x = queue.dequeue();
            Node n = x.node;

            if(x.depth > level) 
            {
                level++;
                ret += "\nLevel " + level + ": ";
            }
            
            if(n != null) 
            {
                ret += "|" + n.toString() + "|";
                queue.enqueue(new Pair(n.left, x.depth + 1));
                queue.enqueue(new Pair(n.right, x.depth + 1));
            }
            else
                ret += "|null|";
        }

        ret += "\n";
        return ret;
    }


    //
    // return the black height of the tree
    //
    public int blackHeight() 
    {
        int height = getBlack(root, 0);
        return height;
    }

    public int getBlack(Node current, int height) {
        if (current.right == null) {
            return height;
        }
         if (!current.right.isRed) height++;

        return getBlack(current.right, height);
    }
		
    // PRIVATE METHODS 

    //
    // swap colors of two Nodes
    //
    private void swapColors(Node x, Node y) 
    {
        if(x.isRed == y.isRed)
            return;
        
        boolean temp = x.isRed;
        x.isRed = y.isRed;
        y.isRed = temp;
    }

    //
    // rotate a link to the right
    //
    private Node rotateRight(Node x) 
    {
        Node temp = x.left;
        x.left = temp.right;
        temp.right = x;
        swapColors(x, temp);
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        temp.height = Math.max(height(temp.left), x.height) + 1;
        return temp;
    }

    //
    // rotate a link to the left
    //
    private Node rotateLeft(Node x) 
    {
        Node temp = x.right;
        x.right = temp.left;
        temp.left = x;
        swapColors(x, temp);
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        temp.height = Math.max(height(temp.right), x.height) + 1;
        return temp;
    }

    //
    // color flip
    //
    private Node colorFlip(Node x) 
    {
        if(x.left == null || x.right == null)
            return x;
        
        if(x.left.isRed == x.right.isRed) 
        {
            x.left.flip();
            x.right.flip();
            x.flip();
        }
        
        return x;
    }

    //
    // return the neight of Node x
    // or -1 if x is null
    //
    private int height(Node x) 
    {
        if(x == null)
            return -1;
        
        return x.height;
    }

    // NODE CLASS 
    public class Node 
    {
        Key key;
        Value val;
        Node left, right;
        int height;
        boolean isRed;

        public Node(Key key, Value val) 
        {
            this.key = key;
            this.val = val;
            this.isRed = true;
        }
        
        public String toString() 
        {
            return "(" + key + ", " + val + "): " 
            + height + "; " + (this.isRed?"R":"B");
        }

        public void flip() 
        {
            this.isRed = !this.isRed;
        }
    }

    // PAIR CLASS 
    public class Pair 
    {
        Node node;
        int depth;
        
        public Pair(Node node, int depth) 
        {
            this.node = node;
            this.depth = depth;
        }
    }

}
