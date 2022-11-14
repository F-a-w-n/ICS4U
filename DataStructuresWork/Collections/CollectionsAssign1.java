//CollectionsAssign1.java - original code by Fawn Barisic - parses a text file and outputs the percentage each word takes of it, nobody is surprised to the "the" at the top and "unconcerned" at the bottom

package Collections;

import java.util.*;   //for all my little maps and sets and fancy little data structures
import java.io.*;     //to read files

public class CollectionsAssign1 {   //does all the heavy lifting here, takes in the file, reads it, maps it out, orders it, formats it to percentages, and outputs it

  public static float total = 0;    //float that acts as an int, just needed that sweet sweet decimal place in my output          
  public static void main(String []args) {                      //calls all the things and outputs all the words
    Scanner stdin = new Scanner(System.in);                     //user input so you can test it with your random text files of people's locations or whatever you do
    HashMap<String, word> counts = readFile(stdin.nextLine());  //grabs a quick hashmap of the file input by the user
    TreeSet<word> sCounts = new TreeSet<word>(counts.values()); //then makes a treeset of the words stored in the map, since it'd be too awkward to add values to a treeset the way I do here
    System.out.println("Percentages:");                       //string formatting queen? professional string formatter award? senior UI/UX developer jobs near me indeed.com?
      for (word w : sCounts.descendingSet()) {                  //look at that I looked up docs and found a way to make it go high-low
        System.out.println(String.format("%s: %f", w.toString(), 100*w.toInt()/total)); //STRING FORMATTING QUEEN? SO GRACEFUL SO ELEGANT. yeah so it takes the word, and then it takes the number and divides by total and multiplies by 100 to get the percentage of the total
      }
      stdin.close();                                            //SHE CLOSES HER SCANNERS? GOOGLE? AMAZON? BIG TECH COMPANIES BREAKING DOWN MY DOOR? WATERLOO FULL RIDE CS SCHOLARSHIP???
  }

  public static HashMap<String, word> readFile(String fName) {  //so anyways this one reads the file and puts it in a neat little hashmap
    HashMap<String, word> out = new HashMap<String, word>();    //output hashmap, she returns on her functions, she learned about asynchronous functions in javascript yesterday, she's a baller, she's the best
    try {
      File f = new File(fName);                         //this is the file I will be reading today it is called "moby dick" I think my computer will really like opening this up and analyzing every word of it
      Scanner inFile = new Scanner(f);                  //so it basically scans in the file, the file of course being everyone's favourite "the odyssey" by homer, a tad outdated but I'm sure you'll find it works flawlessly in my hashmap here
      while (inFile.hasNext()) {                        //anyways joking aside, it loops through each word until it runs out
        total++;                                        //each word increments our total, using float for it does seem inefficient now but also I'm storing individual words from a story in a hashmap right now so it's fine
        String wordString =clean(inFile.next());        //look at that, she cleans punctuation off her words, what a thoughtful citizen, jokes aside it takes off any punctuation and brackets and such until it reaches the word, so things like it's and yee-haw should still keep their inner punctuations
        if (!wordString.equals("")) {         //if I didn't clean out the entire word, AKA it wasn't a word it was another thingy separated by spaces ex " 8 " would be cut out
          word w = out.get(wordString);                //tries to grab the word from the hashmap
          if (w != null) {                              //if there is one already I add one into the object so it knows there's more than one of the word
            w.add();
          } else {                                      //otherwise I make a word object and add it to the map
            out.put(wordString, new word(wordString));  //man I love when constructors save me time
          }
        }
      }
    } catch (FileNotFoundException e) {               //never once have I made a simple, helpful error message, then again who does?
      System.out.println("no file silly goose");
    }
    return out;
  }

  public static String clean(String icky) {       //cleans icky strings into lowercased, unpunctuated strings ready to re-enter society
    String o = icky.toLowerCase();                //easy enough, just toLowerCase on it
    if (o.length() >= 1) {                        //if I'm not feeding myself empty strings like a dingus
      while (o.length() > 0 && !"abcdefghijklmnopqrstuvwxyz".contains(""+o.charAt(0))) {  //as long as I still have a string left and it doesn't start with a letter I chop the first letter off of it
        o=o.substring(1);                                                             //look at me brutally de-leg-itating this poor string, it just wanted to have a double quote on it, but life is unfair, and who doesn't enjoy life?
      }
      while (o.length() > 0 && !"abcdefghijklmnopqrstuvwxyz".contains(""+o.charAt(o.length()-1))) { //anyways so now I chop off it's head the same way, if it's not empty and it has a non-letter character at the end, I chop it's head off
        o=o.substring(0, o.length()-1);                                                 //"just a small trim" *removes 50 characters*
      }
    }
    return o;
  }

}

class word implements Comparable<word> {  //stores each word and the amount of it, also comparisons are neat
  int count;                              //stores the amount of each word I got
  String word;                            //stores the actual word
                                          //this object just stores a lot
  public word(String w) {                 //grabs the word string I wanna store and welp, it stores it
    word = w;
    count = 1;                            //when it's initialized it'll always just have 1
  }

  public void add() {                     //increments amount of the word since hashmaps only have one of every key
    count++;
  }
  @Override
  public String toString() {              //lets me see my word in a way that isnt "Collections.word@368239c8"
    return word;
  }
  public int toInt() {                    //so I can grab the count when I calculate percentages
    return count;
  }
  @Override
  public int compareTo(word other) {        //SHE IS OVERRIDING EVERYTHING TODAY just compares the counts to see which is bigger and then returns negative or positive to position it in the treeset
    return (other.count > this.count)? -1 : 1;
  }

}