//BTTest.java - original code by Fawn Barisic - binary search tree slash really weird family tree slash it's weird as a plant type of tree too

package BinTree; //probably have to delete this for it to work for you

import java.util.*; 

public class BTTest {
    public static void main(String[] args) {
        BTree terrence = new BTree();      // terrence is the main testing tree here
        terrence.add(50);               // just adds a ton of values
        terrence.add(25);
        terrence.add(75);
        terrence.add(10);
        terrence.add(80);
        terrence.add(55);
        terrence.add(85);

        /*
        * I hate automatic formatting here's terrence
        * ------50
        * ---25----75
        * -10----55---80
        * ---------------85
        */

        System.out.println("IN output" + terrence);                                                   // standard IN order output
        System.out.println("PRE output" + terrence.display(BTree.PRE));                              // PRE order output
        System.out.println("POST output" + terrence.display(BTree.POST));                           // POST order output
        System.out.println("how far down is 80? " + terrence.depth(80));                         // depth of 80
        System.out.println("how far down is 79? (it isn't in the tree) " + terrence.depth(79)); // testing that depth works properly if it's not in the tree
        System.out.println("how many leaves? " + terrence.countLeaves());                            // count of all leaves
        System.out.println("how tall is terrence? " + terrence.height());                             // height of terrence
        System.out.println("is 80 an ancestor of 55? (no)" + terrence.isAncestor(80, 55));  // checks ancestry that doesnt work
        System.out.println("is 75 an ancestor of 55? (yes)" + terrence.isAncestor(75, 55)); // checks ancestry that does
        terrence.delete(75);                                                                      // deletes 75
        System.out.println("terrence with no 75 " + terrence);                                       // the new and improved terrence
        terrence.delete(50);                                                                      // deletes 50
        System.out.println("terrence with no 50(root) " + terrence);                                 // the root-less, toot-less terrence

        /*
         * terree looks like
         * --------54
         * ----30------67
         * --20
         */

        BTree terree = new BTree();                                                         // terree is a baby tree, you could say terrence's child, but not quite yet
        terree.add(54);
        terree.add(30);
        terree.add(67);
        terree.add(20);
        System.out.println("baby tree " + terree);                                          // shows us terree before it gets eaten
        terrence.add(terree);
        System.out.println("Now terrence looks like this" + terrence.display(BTree.PRE)); // little bit less clear output of what terrence looks like now (my comments are so helpful you should check those)

        /*
         * terrence (yeah it looks like he just ate terree that's brutal)
         * ---------------25
         * --------10-------------55
         * -----------20------54------80
         * ----------------30-----67-----85
         */

        System.out.println("is bigger terrence balanced now? (no) "+ terrence.isBalanced());

        /*
         * balanced test tree
         * ---------3
         * ----1-------5
         * --0--2----4---6
         * ----------------7
         * -------------------8         //optional nodes commented below
         * ---------------------9
         */

        BTree balancedTest = new BTree(); // just a simple balanced tree to test with
        balancedTest.add(3);
        balancedTest.add(1);
        balancedTest.add(0);
        balancedTest.add(2);
        balancedTest.add(5);
        balancedTest.add(4);
        balancedTest.add(6);
        balancedTest.add(7);
        // balancedTest.add(8);
        // balancedTest.add(9);
        System.out.println("is " + balancedTest.display(BTree.PRE) + " balanced? (yes) " + balancedTest.isBalanced()); // checks if our balancing tree is balanced or not
    }
}

class BTree {                           // main binary tree class
    public static final int IN = 0;     // constants
    public static final int PRE = 1;
    public static final int POST = 2;

    BNode root = null;                  // root is null at first

    // no need for constructors here (though it might be neat if it could create a tree automatically from an array or arraylist)

    public void add(int v) {                    // adds by value
        if (root == null) {                     // when there is no root, adds the node as the root
            root = new BNode(v, null);
        } else {                                // if not it runs the cooler add method
            add(v, root);
        }
    }

    public void add(int v, BNode branch) {              // cooler add(), this is where it actually traverses the tree to put it in a good spot
        if (v < branch.getVal()) {                      // if the value is less than the node's value
            if (branch.getLeft() == null) {             // when there's no child node to the left (lower) side, it just adds a node with the specified value there
                branch.setLeft(new BNode(v, branch));
            } else {                                    // if there's a child node, recurse from that new node position
                add(v, branch.getLeft());
            }
        } else if (v > branch.getVal()) {               // if the value is more than the node's value
            if (branch.getRight() == null) {            // if there's no child to the right adds our new node there
                branch.setRight(new BNode(v, branch));
            } else {                                    // runs it again from the right child node
                add(v, branch.getRight());
            }
        } // note I didn't do anything if it's equal since that means there's already a node with that same value
    }

    private BNode getRoot() { // gets the root, only private and mostly for use in the hellhole below
        return root;
    }

    /*
     * ok, so I did a weird thing with add(BTree) and toSubTree here
     * I figured both would be easier to make with the other, so I made them VERY
     * interconnected and I can't properly explain in single-line comments.
     * so add calls itself with sub-trees of the original sub-tree until it reaches a
     * leaf and the conga line ends
     * but in order to recurse it needs to convert the children of our root node (which it just adds normally)
     * into more sub-trees
     * which pulls in toSubTree now, this guy adds the root value to the tree AND
     * THEN ADDS SUB-TREES OF THE ROOT IN ORDER TO MAKE THE SUB-TREE
     * IT LITERALLY NEVER ONCE INTERACTS WITH IT AS A TREE, JUST TAKES THE ROOT AND
     * PASSES IT INTO THE OTHER METHOD TO GIVE IT THE NEXT GENERATION'S ROOT
     * I RECURSED THE RECURSION OF THE RECURSION I'M IN TOO DEEP
     */

    public void add(BTree branch) {                              // adds a subtree to our tree
        add(branch.getRoot().getVal());                          // adds the root of the sub tree as normal value addition
        if (branch.getRoot().getLeft() != null)                  // calls itself again with a subtree of the left side
            add(branch.toSubTree(branch.getRoot().getLeft()));   // gets the entire left branch of the root and uses the add(BTree) to add it on
        if (branch.getRoot().getRight() != null)                 // calls itself again with a subtree of the right side
            add(branch.toSubTree(branch.getRoot().getRight())); // gets entire right branch and adds it using the add(BTree)
    }

    public BTree toSubTree(BNode n) {          // makes a tree with node n as the root of it
        BTree sub = new BTree();               // makes a new BTree
        sub.add(n.getVal());                   // adds the root as normal
        if (n.getLeft() != null)               // then calls the add(BTree) method of this tree's left subtree
            sub.add(toSubTree(n.getLeft()));   // uses the add(BTree) to put the entire left branch into the subtree
        if (n.getRight() != null)              // calls the add(BTree) method on the right side
            sub.add(toSubTree(n.getRight())); // uses add(BTree) to put the entire right side into a subtree
        return sub;                            // eventually it gives you a full subtree
    }

    public int depth(int val) {                     // looks for the value and returns the depth it was found
        int d = 1;                                  // stores current depth
        BNode tmp = root;                           // pointer, starts at root and goes down
        while (tmp != null && tmp.getVal() != val) { // while there is more tree to search, and the value isn't the same, also handles the case where the tree is empty
            d++;                                    // adds one to depth every time it goes down a generation
            if (val > tmp.getVal()) {               // if the value is more, goes down to the right
                tmp = tmp.getRight();
            } else {                                // if the value is less, go down to the left
                tmp = tmp.getLeft();
            }
        }

        return (tmp != null) ? d : -1;              // if the node is found output the depth, otherwise output -1
    }

    public int height() {
        return (root != null) ? countHeight(root) : 0; // if there's a root it runs countheight() from root down
    }

    private int countHeight(BNode n) {      // checks the height of every branch and finds the longest one
        int r = 0;                          // left and right both default to height of 0
        int l = 0;
        if (n.getLeft() != null) {          // if there's a branch to the left, count its height
            l = countHeight(n.getLeft());
        }
        if (n.getRight() != null) {         // if there's a branch to the right count its height
            r = countHeight(n.getRight());
        }
        return (l > r) ? 1 + l : 1 + r;     // returns the highest of the heights + 1 for the current node
    }

    public int countLeaves() {
        return (root != null) ? countLeaves(root) : 0; // similar to height(), if there is a root node, runs countLeaves(BNode) from root
    }

    private int countLeaves(BNode n) {                      // counts leaves on every branch
        if (n.getLeft() == null && n.getRight() == null) { // if it has no children (leaf), returns 1
            return 1;
        } else {                                             // if it's not a leaf
            return ((n.getLeft() != null) ? countLeaves(n.getLeft()) : 0) + ((n.getRight() != null) ? countLeaves(n.getRight()) : 0); // adds the left and right side of the node's leaves together
        }
    }

    public boolean isAncestor(int anc, int chi) { // finds ancestor, then sees if their section of the family tree contains the child
        if (find(chi, find(anc)) != null) {       //finds the ancestor, and then finds the child starting from the ancestor 
            return true;                          //if it's there then we return true
        } else {                                   //otherwise that's false
            return false;
        }
    }

    public BNode find(int v) {                      // returns node with value specified
        BNode tmp = root;                           // pointer to return at end of method
        while (tmp != null && tmp.getVal() != v) { // if it's not null or the value we want it keeps looping
            if (v > tmp.getVal()) {                 // moves to right if the desired value is bigger
                tmp = tmp.getRight();
            } else {                                // moves to left if desired is smaller
                tmp = tmp.getLeft();
            }
        }
        return tmp;                                 // returning tmp plainly works because if the item isn't in the tree, it'll run off the edge and be null, otherwise tmp will point to the desired value's node
    }

    public BNode find(int v, BNode start) {         // returns node with value specified, this time it starts from a specified BNode though
        BNode tmp = start;                          // pointer to return at end of method, now at the start node and not root
        while (tmp != null && tmp.getVal() != v) { // if it's not null or the value we want it keeps looping
            if (v > tmp.getVal()) {                 // moves to right if the desired value is bigger
                tmp = tmp.getRight();
            } else {                                // moves to left if desired is smaller
                tmp = tmp.getLeft();
            }
        }
        return tmp;                                 // returning tmp plainly works because if the item isn't in the tree, it'll run off the edge and be null, otherwise tmp will point to the desired value's node
    }

    public void delete(int v) {                     // deletes a node
        BNode rem = find(v);                        // finds the node we want removed
        if (rem != null) {                          // if it's not null we can run some checks, if it is null we don't need to delete it
            BNode par = rem.getParent();            // look at this spiffy young gentle-method getting the parent in a binary search tree so sophisticated
            if (par == null) {                      //if we're removing root there will be no parent, we just set root to null and let graft handle the special case
                root = null;       
            if (rem.getLeft() != null)              // if the removed node has a left child, graft it onto the root
                graft(rem.getLeft(), root);
            if (rem.getRight() != null)             // if the removed node has a right child, graft that onto the root (note that they'll both find a nice little spot in the tree in the graft method despite both being added to the same node)
                graft(rem.getRight(), root);
            } else {
            if (par.getVal() < rem.getVal()) {      // if the parent is up to the left, set the parent's right child to null (until we graft on the grandchildren (maybe family tree is a tragic analogy in this scenario why are we deleting children))
                par.setRight(null);
            } else {                                // if the parent is up to the right, set its left child node to null for the graft
                par.setLeft(null);
            }
            if (rem.getLeft() != null)              // if the removed node has a left child, graft it onto the parent
                graft(rem.getLeft(), par);
            if (rem.getRight() != null)             // if the removed node has a right child, graft that onto the parent (note that they'll both find a nice little spot in the tree in the graft method despite both being added to the same node)
                graft(rem.getRight(), par);
            }
        }
    }

    public void graft(BNode n, BNode branch) {          // this is where the magic happens sorta
        if (n != null) {                                // double checking that we actually have to add something
            if (branch == null) {                       // when we are grafting onto a deleted root we just set root to n
                root = n;
            } else {
                if (n.getVal() < branch.getVal()) {         // if the value is less than the node's value
                    if (branch.getLeft() == null) {         // when there's no child node to the left (lower) side, it just adds a node with the specified value there
                        branch.setLeft(n);
                        n.setParent(branch);
                    } else {                                // if there's a child node, recurse from that new node position
                        graft(n, branch.getLeft());
                    }
                } else if (n.getVal() > branch.getVal()) {  // if the value is more than the node's value
                    if (branch.getRight() == null) {        // if there's no child to the right adds our new node there
                        branch.setRight(n);
                        n.setParent(branch);
                    } else {                                // runs it again from the right child node
                        graft(n, branch.getRight());
                    }
                } // note I didn't do anything if it's equal since that means there's already a node with that same value
            }
        }
    }

    public boolean isBalanced() {                       // checks if the tree is balanced (and not an icky linked list)
        if (root == null) {                             // if there are no nodes, it can't be unbalanced
            return true;
        }
        ArrayList<Integer> heights = listheights(root); // grabs the heights from every possible path down the tree
        int min = heights.get(0);
        int max = heights.get(0);
        for (int i : heights) {                         // with every height we see if it's the tallest, or the shortest, otherwise I don't care it is not helping me live, laugh, or love
            if (i < min) {                              // if it's smaller than min, it is new min
                min = i;
            } else if (i > max) {                       // if it's bigger than max, it is max
                max = i;
            }
        }
        return (max == min || max - 1 == min);          // if min and max are the same number or max is one bigger, it is balanced
    }

    private ArrayList<Integer> listheights(BNode n) {       // makes a neat little list of all the heights for me, just a friendly little recursive fella
        ArrayList<Integer> out = new ArrayList<Integer>(); // arraylist to be output later
        if (n.getLeft() == null) {                          // if there's no child, adds a 1 to the arraylist, this is the base case for the left side
            out.add(1);
        } else {                                            // if there is a left child, it adds the child's heights to the current arraylist (and adds 1 to each to include itself in the path)
            for (int c : listheights(n.getLeft())) {
                out.add(c + 1);
            }
        }
        if (n.getRight() == null) {                         // base case for right side, if there's no child the end of the path is here and it'll just add a 1
            out.add(1);
        } else {                                            // otherwise, same idea as the left side, we go through the child's heights and add 1 for the current node
            for (int c : listheights(n.getRight())) {
                out.add(c + 1);
            }
        }
        return out;                                         // returns our arraylist, now with all the possible heights to get to a leaf
    }

    //both display and tostring because the assignment says display but tostring makes writing tests easier in my opinion

    @Override
    public String toString() {                                   // no argument toString() so I can have some peace as i print out 50 times in my test cases
        String ans = Stringify(root, IN);                         // stringify will return the tree (in numerical order in this case) all comma separated and nice
        if (ans != "")
            ans = ans.substring(0, ans.length() - 2); // removes trailing comma
        return "<" + ans + ">";                                  // idk why I chose angle brackets but I don't hate it
    }
    public String toString(int arg) {                           // same as above, but now you can set the argument (all of them are in constants around ln 85)
        String ans = Stringify(root, arg);                      // stringify with user args now
        if (ans != "")
            ans = ans.substring(0, ans.length() - 2); // removes trailing comma
        return "<" + ans + ">";                                  // like imagine if html were cool this would be that... sorta
    }

    public String display() {                                    // same as above, but it's under display()
        String ans = Stringify(root, IN);                      // stringify with default args
        if (ans != "")
            ans = ans.substring(0, ans.length() - 2); // removes trailing comma
        return "<" + ans + ">";                                  // like imagine if html were cool this would be that... sorta
    }

    public String display(int arg) {                           // same as above, but now you can set the argument (all of them are in constants around ln 85)
        String ans = Stringify(root, arg);                      // stringify with user args now
        if (ans != "")
            ans = ans.substring(0, ans.length() - 2); // removes trailing comma
        return "<" + ans + ">";                                  // like imagine if html were cool this would be that... sorta
    }

    public String Stringify(BNode n, int arg) {                                                        // this is the real tostring for real cool kids
        if (n == null) {                                                                                // if there's nothing to print out, it doesn't print anything out
            return "";
        } else {
            if (arg == BTree.IN) {                                                                      // ordered lowest to smallest with root in the middle
                return Stringify(n.getLeft(), arg) + n.getVal() + ", " + Stringify(n.getRight(), arg); // recurses down the tree, with the parent in the middle of the two sides
            } else if (arg == BTree.PRE) {                                                             // ordered in branches so root is at the left and we get the full left sub-tree first
                return n.getVal() + ", " + Stringify(n.getLeft(), arg) + Stringify(n.getRight(), arg); // recurses down the tree with the parent on the left
            } else {                                                                                  // post is just ordered backwards to pre
                return Stringify(n.getLeft(), arg) + Stringify(n.getRight(), arg) + n.getVal() + ", "; // recurses down the tree with the parent on the right (icky gross bad (probably useful and I just don't know))
            }
        }
    }

}

class BNode {                                   // the nodes (it's ok they don't bite usually)

    private int val;                             // value stored by the node
    private BNode left = null, right = null, par; // left child, right child, and parent node pointers (root will still have a parent node pointer it will just be the sad opposite of a leaf that doesn't have a dad)

    public BNode(int v, BNode parent) {          // sets parent and value on construction (since both of these will be known at the time)
        val = v;
        par = parent;
    }

    public BNode getParent() {return par;}       // returns the parent
    public int getVal() {return val;}            // returns the value
    public BNode getLeft() {return left;}        // returns the left child
    public BNode getRight() {return right;}      // returns the right child
    public void setRight(BNode n) {right = n;}   // sets the right child
    public void setLeft(BNode n) {left = n;}     // sets the left child
    public void setParent(BNode n) {par = n;}    // sets the parent

}