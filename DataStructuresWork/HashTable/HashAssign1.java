package HashTable;

import java.io.*;   //needs to use filesystem to access the dictionary
import java.util.*; //as far as I know this is only here for scanner

public class HashAssign1 {
    
    public static HashyTable<String> words = new HashyTable<String>(); //if you close your eyes you can barely tell this is an off-branded hashtable
    public static void main(String[] args) {    //calls other files, takes the user's letters
        checkfile();                            //you will be shocked to know this checks the dictionary file

        Scanner stdin = new Scanner(System.in); //user input scanner
        String letters = stdin.nextLine();      //string of letters input from user
        
        ana("", letters);                   //messy two param anagram that also lets you know whenever a word is in the dictionary
        
        stdin.close();                          //gotta keep this place clean and close my scanners, it's my moral duty
    }

    public static void checkfile() {                        //here's where we update our dictionary in the program
        try {                                               //all the file IO stuff that throws errors
        File dictionary = new File(/* maybe try ..\\..\\*/"DataStructuresWork\\advanced data structures\\data\\dictionary.txt");  //file path to the dictionary (note I run this script from the parent folder by default so you may need to tweak this and all my other file paths if it doesn't open)
        Scanner inFile = new Scanner(dictionary);           //scanner to grab all the words from the dictionary

        while (inFile.hasNextLine()) {                      //loops until I run out of words
            words.add(inFile.nextLine());                   //adds the word to the table
        }

        inFile.close();                                     //literally such a good person

        } catch (FileNotFoundException e) {                 //if it doesn't find the dictionary, it won't blow up
            System.out.println("can't find the dictionary "); //I do recommend checking out what I suggested for the path if it isn't working
        }
    }


    public static void ana (String cur, String left) {                              //finds all permutations of the word, checks if the word is in the dictionary, and if it is will quickly print it to the terminal
        if (words.contains(cur)) {                                                  //if our hashtable of words has our word in it
            System.out.println(cur);                                                //we just print it out real quick
        }
        if (left.length() != 0) {                                                   //if there are more letters to add on to our "working" word then we add onto it
            for (int i=0; i<left.length(); i++) {                                   //runs for every possible next step (checks with each letter of what's left to add)
                if (!left.substring(0,i).contains(""+left.charAt(i))) { //if the letter we are trying to add is not a duplicate (checks all letters behind it to see if it's in there), then we use it. otherwise it's been used already and would waste our time
                    char let = left.charAt(i);                                       //letter we are adding on to our working word in this segment
                    String newLeft = left.substring(0, i) + left.substring(i+1); //chops the added letter out of our letters left to add on (so we don't add it again)
                    ana(cur+let, newLeft);                                            //calls the method again with the new working word and leftover letters (will check on the next call if it's a real word)
                }
            }
        }
    }

}

/* quick test script for my hashtable class
HashyTable tab = new HashyTable<Integer>();
tab.add(2);
tab.add(3);
tab.remove(3);
tab.add(6); 
tab.add(8);
tab.add(29);
tab.add(30); 
tab.add(35);
tab.add(60);
tab.add(65);
tab.add(70);
tab.add(76);
tab.add(81);
tab.add(92);
System.out.println(tab);
System.out.println(tab.getLoad());
System.out.println(tab.get(35));
System.out.println(tab.getMax());
tab.setLoad(0.7);
System.out.println(tab.getLoad());
System.out.println(tab.getMax());
tab.setMaxLoad(0.2);
System.out.println(tab);
System.out.println(tab.getLoad());
 */