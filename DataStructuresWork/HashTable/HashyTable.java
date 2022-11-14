//HashyTable.java - original code by Fawn Barisic - a homemade hash table for use in the hashtable assignment

package HashTable;

import java.util.*; //always something from util

public class HashyTable<T> {                    //look at this spiffy little generic thingy here, ain't that just neat
    
    private int items;                          //quantity of items stored
    private int max = 10;                       //maximum amount of items
    private double percentmax = 0.7;            //maximum percentage the table can be filled to

    private ArrayList<LinkedList<T>> table;     //main storage compartment is an arraylist of linkedlists

    public HashyTable() {                       //on construct it runs setMax() to fill the table with blank values and ensure the maximum is 10
        setMax(10);
    }

    public void add(T v) {                       //add() method puts the item in the list in a sneaky little efficient way to be easily found again
        items++;                                 //increments items to keep track of our new item
        int pos = Math.abs(v.hashCode()%max);   //calculates the position of the object in the table based upon its hashcode
        if (table.get(pos) == null) {           //if the table is empty at the location we're adding to, create a linkedlist there to store our value
            table.set(pos, new LinkedList<T>());
        }
        table.get(pos).add(v);                  //then we access the linkedlist at the desired location and add our value to the list
        if (getLoad() > percentmax) {           //finally, if the table is too full, it resizes to double the current size
            resize(max*2);
        }
    }

    public void setMaxLoad(double nPercent) {    //sets our maximum fullness percentage 
        if (nPercent >= 0.1 && nPercent <= 0.8) {   //only lets us set it 0.1 to 0.8 though
            percentmax = nPercent;                 //sets our percentmax for later checks
            if (getLoad() > nPercent) {             //if we're over the new max load percent, resize the table
                resize(max);
            }
        }
    }
    
    public void setLoad(double nPercent) {                  //setLoad() takes a given load percent and increases the max value to give us that percent (or as close as possible anyways)
        if (nPercent <= percentmax) {                       //if it's not above our maximum percentage(so it can actually reach that percentage)
            int nMax = (int)Math.floor(items / nPercent);   //new maximum value determined by dividing our current items by the percent we need since the percentage is equal to items/max, max is equal to items/percent
            if ((double)items / nMax >= percentmax) {       //when we reach a point where the rounding to an integer makes it go over our maximum percentage and resize again to the wrong percent
                nMax++;                                     //it's as simple as adding one to the maximum to lower the current load percentage (it should never need more than one since it's just rounding to nearest int)
            }
            resize(nMax);                                   //then we resize with our new maximum value
        }
    }

    public boolean contains(T v) { //quick check if the table has the desired value in it
        if (items > 0 && table.get(Math.abs(v.hashCode())%max) != null && table.get(Math.abs(v.hashCode())%max).contains(v) == true) { //okay, so obviously it's not there if we don't have items in our table, then it's a quick check to see if the table at the position the value would be stored is not null (meaning it could be stored there), then a final check to see if the linkedlist at the right position in the table contains the value
            return true;           //it's in the table so we return true
        }
        return false;              //if we did not return true in that if statement, it's not in the table, so returns false
    }

    public double getLoad() {       //get a load of this guy
        return (double)items/max;   //literally just a one-liner to save typing
    }

    public void remove(T v) {                   //removes a value, slightly easier than add(), since we don't have to resize
        int pos = Math.abs(v.hashCode()%max);   //calculates position based on hashcode as with add
        if (table.get(pos) != null) {           //if there is a linkedlist at that position in our table
            table.get(pos).remove(v);           //we quickly remove it from said linkedlist
            items--;                            //and decrement items by one so we don't think it's still there
        }
    }

    public LinkedList<T> get(int key) {        //returns the linkedlist of possible values for the given key
        int pos = Math.abs(key%max);           //finds the position based on hash as ususal
        return table.get(pos);                 //returns that position in the arrayList
    }

    public void resize(int nMax) {             //resizes table to new set maximum size
        ArrayList<T> values = toArray();       //grabs all the values before we null out the table
        setMax(nMax);                          //setMax() sets the table to a bunch of nulls at the new desired size
        for (T v : values) {                   //then re-adds our values so theyre positioned right
            add(v);
        }
    }
    
    public ArrayList<T> toArray() {             //grabs our values and adds em to an arrayList
        ArrayList<T> out = new ArrayList<T>();  //output arrayList
        for (LinkedList<T> ll : table) {        //grabs each linkedlist from our table
            if (ll != null) {                   //if it's not null we grab all the values and add them to our arraylist
                for (T val : ll) {
                    out.add(val);
                }
            }
        }
        return out;
    }

    public void setMax(int nMax) {      //fills table with nulls up until our new maximum size
        max = nMax;                     //sets our max to the new maximum, so it resizes and such properly later
        ArrayList<LinkedList<T>> nTab = new ArrayList<LinkedList<T>>(max); //creates a new table to store our values in
        for (int i = 0; i < max; i++) { //literally fills it with nulls until we reach the maximum size
            nTab.add(null);
        }
        items = 0;                      //sets items to 0, so when we re-add them we dont double the value
        table = nTab;                   //sets our table to the new table
    }

    @Override
    public String toString() {          //gives us a neat little output string
        String out = "";                //eventual output string
        for (LinkedList<T> ll : table) {//grabs all the values from the table
            if (ll != null)
            for (T val : ll) {
                out += val + ", ";      //adds em with a lil' comma space
            }
        }
        if (!out.equals("")) { //if the output string is not empty (we have values), cuts off the additional comma space at the end
            out = out.substring(0, out.length()-3);
        }
        return "{"+out+"}";             //gives us a lil' curly brace, sorta follows some semblance of styling if you squint
    }
}
