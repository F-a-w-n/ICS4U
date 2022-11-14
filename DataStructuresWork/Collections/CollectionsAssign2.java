//CollectionsAssign2.java - original code by Fawn Barisic - cheks speling ov wordz past az argunemts... I hated typing that it's just spellcheck

package Collections;

import java.util.*;  //lets me use my neat little sets and maps
import java.io.*;    //I have to do file IO again T-T

public class CollectionsAssign2 { //been ages since I've had a single-class project, this one does everything

  public static void main(String []args) {              //grabs the dictionary and cross-references our arguments with it to see if they're in it
    HashSet<String> words = checkfile();                //look at this meager little hashSet, it surely doesnt have 80,000 words in it... heheh... heheh...
    if (args.length < 1)                                //in case you don't like opening command prompt to test but cant run with parameters in VS Code like me
      args = new String []{"test", "DKBSJHV"};
    if (words.size() > 0)                               //if I didn't mess up my file path again
    for (String s : args) {                             //grabs all our args
      if (words.contains(s)) {                          //if the word is in the dictionary it lets you know how cool you are
        System.out.println(s+" is spelled correctly!");
      } else {                                          //only uncool kids get this one
        System.out.println(s+" is spelled incorrectly T-T");
      }
    }
  }
  public static HashSet<String> checkfile() {   //grabs the words from the file and puts em in a hashset
    File dictionary;                             //file location for the dictionary
    HashSet<String> out = new HashSet<String>();  //eventually this will be the output Set that has all the words

    try {                                       //all these file IO errors won't handle themselves 
    dictionary = new File(/*maybe try ..\\*/"DataStructuresWork\\advanced data structures\\data\\dictionary.txt");
    Scanner inFile = new Scanner(dictionary);   //scans all the words, what a professional scanner, walmart should hire this guy

    while (inFile.hasNextLine()) {              //if it has another line I just pop it right into my set.. wait pop is another thing it.... it gently places it right into my set
        out.add(inFile.nextLine());
    }

    inFile.close();                             //closing scanners like it's going out of style... because it is

    } catch (FileNotFoundException e) {         //been a while since I left a nice file error message
        System.out.println("can't find the dictionary ");
    }
    return out;
}
}
