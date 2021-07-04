import java.lang.Math;

public class BST<Key extends Comparable<Key>, Value>
{
    private Node root;
    private int N;

    // CONSTRUCTOR 
    public BST() 
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
        //insert root
        if (root == null) {
            root = new Node(key, val);
            root.height = 0;
            N = 1;
            return;
        }

        //insert a non-root
        root = search(root, key, val);
        root.height = updateHeight(root);
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
          //  val = current.val;
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

//    public int getHeight(Node current, Key key) {
//        return 2;
//    }

//    public Node findNode(Node current, Key key) {
//        if (current == null) {
//            return current;
//        }
//
//        /* Otherwise, recur down the tree */
//        if (key.compareTo(current.key) == 0) {
//            //current.val = val;
//        }
//        else if (key.compareTo(current.key) > 0) {
//            current.right = findNode(current.right, key);
//        }
//        else if (key.compareTo(current.key) < 0) {
//            current.left = findNode(current.left, key);
//        }
//
//        return current;
//    }

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
    // return a String version of the tree
    // level by level
    //
    public String toString() 
    {
        String str = "";
        Pair x = null;
        Queue<Pair> queue = new Queue<Pair>(N);
        int level = 0;
        queue.enqueue(new Pair(root, level));
        str += "Level 0: ";

        while(!queue.isEmpty()) 
        {
            x = queue.dequeue();
            Node n = x.node;

            if(x.depth > level) 
            {
                level++;
                str += "\nLevel " + level + ": ";
            }

            if(n != null) 
            {
                str += "|" + n.toString() + "|";
                queue.enqueue(new Pair(n.left, x.depth + 1));
                queue.enqueue(new Pair(n.right, x.depth + 1));
            } 
            else  
                str += "|null|";
        }

        str += "\n";
        return str;
    }
		

    // PRIVATE METHODS 

    //
    // return the height of x
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

        public Node(Key key, Value val) 
        {
            this.key = key;
            this.val = val;
        }
        
        public String toString() 
        {
            return "(" + key + ", " + val + "): " + height;
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
