//LLTest.java - original code by Fawn Barisic - makes and tests various use cases for doubly linked lists 

package LinkedList;

import java.util.*;

class LLTest {
  public static void main(String[] args) {
    LList nums = new LList();               // push and pop test
    nums.push(42);
    nums.push(62);
    nums.push(122);
    nums.push(90);
    System.out.println(nums);
    System.out.println(nums.pop());
    System.out.println(nums);

    System.out.println("-----------");

    LList queue = new LList();            // enque and deque test (deque is just another pop)
    queue.enque(69);
    queue.enque(70);
    queue.enque(71);
    queue.enque(72);
    System.out.println(queue.deque());
    System.out.println(queue);

    System.out.println("-----------");

    LList deleter = new LList();        // delete value, position, and node reference test
    deleter.push(1);
    deleter.push(2);
    deleter.push(3);
    deleter.push(4);
    deleter.push(5);
    deleter.push(6);
    deleter.push(7);
    deleter.push(8);
    deleter.delete(6);
    deleter.deleteAt(3);
    deleter.delete(deleter.getHead());
    System.out.println(deleter);

    System.out.println("-----------");

    LList sorted = new LList();         // sorted insert test
    sorted.sortedInsert(3);
    sorted.sortedInsert(4);
    sorted.sortedInsert(2);
    sorted.sortedInsert(5);
    sorted.sortedInsert(1);
    System.out.println(sorted);

    System.out.println("-----------");

    LList singlePringles = new LList();   // duplicate removal test
    singlePringles.push(5);
    singlePringles.push(3);
    singlePringles.push(5);
    singlePringles.push(5);
    singlePringles.push(4);
    singlePringles.push(7);
    singlePringles.push(3);
    singlePringles.removeDuplicates();
    System.out.println(singlePringles);

    System.out.println("-----------");

    LList reversed = new LList();        // reversing test
    reversed.push(10);
    reversed.push(9);
    reversed.push(8);
    reversed.push(7);
    reversed.push(6);
    reversed.push(5);
    reversed.push(4);
    reversed.push(3);
    reversed.push(2);
    reversed.push(1);
    System.out.println(reversed);
    reversed.reverse();
    System.out.println(reversed);

    System.out.println("-----------");

    LList cloned = singlePringles.clone();  // cloning test
    cloned.push(5);
    System.out.println(singlePringles);     // proving that it's a different reference in memory
    System.out.println(cloned);
  }
}

class LList {           // makes a fancy little doubly linked list with all the fun methods
  private LNode head;   // this is the front of the line
  public LNode tail;   // this is the end of the line

  public LList() {     // basic basic constructor
    head = null;
    tail = null;
  }

  public void push(int v) {                 // adds to the head side of the list
    LNode tmp = new LNode(head, v, null); // head is its tail, its head is null
    if (head != null) {                   // makes head point back if it exists
      head.setHead(tmp);
    }
    head = tmp;                            // after making previous head point properly sets our new head
    if (tail == null) {                    // if it's empty make the tail the new node as well
      tail = tmp;
    }
  }

  public void enque(int v) {                // adds to the tail side of the list
    LNode tmp = new LNode(null, v, tail); // no tail, it's head is the previous tail
    if (tail != null) {                     // if there is already a tail set it's tail to the new tail
      tail.setTail(tmp);
    }
    tail = tmp;                             // now that the previous tail points correctly, set the list's tail to the new tail
    if (head == null) {                     // sets the head to point to the new value as well if there are no other nodes
      head = tmp;
    }
  }

  public LNode deque() {      // removes from the head side
    LNode tmp = head;         // stores old head to return
    head = head.getTail();    // sends the head pointer to the second in line
    head.setHead(null);    // sets the new head pointer's head to null (cutting off the old head)
    return tmp;               // now it can return the old head
  }

  public LNode pop() {        // same as deque just different names
    LNode tmp = head;         // stores old head
    head = head.getTail();   // moves head back one
    head.setHead(null);    // cuts the old head out of the loop
    return tmp;              // returns old head
  }

  public void delete(int val) { // deletes based on value stored
    LNode tmp = head;           // pointer to go from head to tail
    while (tmp != null) {       // if the list is empty or you reach the end it stops looping
      if (tmp.getVal() == val) { // if the temporary pointer is looking at the same value
        remove(tmp);            // I have a nice little built in method for nodes
      }
      tmp = tmp.getTail();      // every loop it moves the pointer down the line
    }
  }

  public void delete(LNode node) { // deetes based on specific node reference
      remove(node);               //can just call remove since I have the node reference already
  }

  public void deleteAt(int pos) {   // deletes based upon position in the list
    LNode tmp = head;
    for (int i = 0; i < pos; i++) { // moves pointer to the position specified
      tmp = tmp.getTail();
    }
    remove(tmp);                    // removes it
  }

  public void sortedInsert(int val) {           // adds to front or back depending on value to keep it organized
    LNode tmp = head;
    if (head == null) {
      push(val);
    } else {
      if (tmp != null && val > head.getVal()) {
        push(val);
      } else {
        tmp = tmp.getTail();
        while (tmp != null) {
          if (tmp.getHead().getVal() > val && tmp.getVal() < val) {
            LNode n = new LNode(tmp, val, tmp.getHead());
            tmp.getHead().setTail(n);
            tmp.setHead(n);
          } else {
            tmp = tmp.getTail();
            if (tmp == null) {
              enque(val);
            }
          }
        } 
      }
    }
  }

  public void removeDuplicates() {                      // believe it or not, this removes the duplicates in the list
    ArrayList<Integer> prev = new ArrayList<Integer>(); // ew not a linked list gross (it stores all of the values that the pointer has seen already and is actually quite useful)
    LNode tmp = head;                                   // pointer to loop through the list
    while (tmp != null) {                               // loops 'til the end of the list or doesnt loop if it's empty
      if (prev.contains(tmp.getVal())) {                // if the value is already there, remove the node from the list
        remove(tmp);
      } else {                                          // if the value isnt there yet, keep it for later but leave the node alone
        prev.add(tmp.getVal());
      }
      tmp = tmp.getTail();                              // moves pointer down one each loop
    }
  }

  public void reverse() {         // puts the first last and the last first and the middle ones do a little switcheroo
    LNode tmp = head;             // stores the head so I can swap them, then I use the same variable as a pointer to loop the list
    head = tail;                  // head is the tail
    tail = tmp;                    // tail is the head
    while (tmp != null) {         // if it's empty or the end of the list I stop
      LNode thead = tmp.getHead(); // stores the pointer's head for the switcheroo of the heads and tails
      tmp.setHead(tmp.getTail()); // head is tail
      tmp.setTail(thead);         // tail is the stored head
      tmp = tmp.getHead();      // tmp goes to its head now (since the list is reversed it doesnt go to its tail)
    }
  }

  public LList clone() {       // makes a list with the exact save values but different memory addresses
    LList o = new LList();     // output list
    LNode tmp = head;          // could go tail first one of these days but I enjoy monotony
    while (tmp != null) {     // loops every node in the list
      o.enque(tmp.getVal()); // adds the value to the tail (since I go head to tail in my loop)
      tmp = tmp.getTail();   // shifts the pointer down one in the original list
    }
    return o;                 // returns that fancy new carbon copy list
  }

  public LNode getHead() {   // added this quick to test the delete method that takes a node reference
    return head;
  }

  private void remove(LNode r) {         // it surprisingly enough removes the node from the list
    if (r == head)                        // if it's the head, make the head point to the next in line
      head = r.getTail();
    else if (r == tail)                  // if it's the tail, make the tail point to the next in line
      tail = r.getHead();
    if (r.getHead() != null)            // if it has a head, set it's head's new tail to be the removed node's tail
      r.getHead().setTail(r.getTail());
    if (r.getTail() != null)            // if it has a tail, set it's tail's new head to the removed node's head
      r.getTail().setHead(r.getHead()); // basically it criss-crosses the node's pointers around the removed node to keep the list connected
    r = null;                           // look at that it's so neat I even cleared out the removed node
  }

  @Override                             // tostring just loops head to tail and adds em to a nice little string
  public String toString() {
    LNode tmp = head;                   // pointer again
    String ans = "";                   // stores the output string (sans square brackets)
    while (tmp != null) {               // for every node I add it to the string and move to the next node
      ans += tmp + ", ";
      tmp = tmp.getTail();
    }
    if (!ans.equals("")) {    // if the answer string isnt empty I remove the trailing comma and space at the end
      ans = ans.substring(0, ans.length() - 2);
    }
    return "[" + ans + "]";              // look at that it outputs just like a normal array thats so neat and nice
  }
}

class LNode {           // stores each node of the list
  private int val;     // value of the node (ex. 5)
  private LNode next;  // points to the memory address of the node in front of it (closer to head)
  private LNode prev;  // points to memory address of the node behind (closer to tail)

  public LNode(LNode p, int v, LNode n) { // initialized (tail, value, head) like in the drawings I swear I'm not a criminal who wrote (value, head, tail) at one point in my life I could never
    val = v;
    next = n;
    prev = p;
  }

  // setter and getter for every value
  public void setHead(LNode h) {next = h;}
  public void setTail(LNode t) {prev = t;}
  public LNode getHead() {return next;}
  public LNode getTail() {return prev;}
  public int getVal() {return val;}

  @Override // literally just returns the value as a string so that it goes nicer into the big list toString()
  public String toString() {
    return "" + val;
  }

}