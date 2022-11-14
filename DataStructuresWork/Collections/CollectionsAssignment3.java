//CollectionsAssignment3.java - original code by Fawn Barisic - sorts children based on their movie of choice, for some reason albert's file has my name but mine doesn't I don't know how to feel about it... nevermind ctrl+f is just bugging out
package Collections;

import java.util.*; //I'm using collections in all of these hopefully it's clear that's what I'm importing this for
import java.io.*;   //now not only am I reading, but I'm writing. There is no mercy in this world

public class CollectionsAssignment3 {             //similar to collections 1, this handles all the IO ad output and data structures, while my helper class just stores values in a neater way for me
  
  public static void main(String [] args) {                                       //grabs student's choices, puts em in a file, and holy cow it takes a lot of code to put it in a file 
    HashMap<String, movie> picks= fromFile();                                     //uses my newfound love, hashmaps of course, to grab the students from the file and give them movies to watch 
    try {                                                                         //file IO errors need to leave me alone I have nothing left to give
      File path = new File(/*you know the drill ..\\..\\*/"DataStructuresWork\\Collections\\movies.txt"); //didnt wanna lose it in my data folder
      BufferedWriter bw = new BufferedWriter(new FileWriter(path, false)); //new bufferedwriter from a new filewriter from a new file 
      bw.write("");                                                           //clears out old junk
      bw.close();
      bw = new BufferedWriter(new FileWriter(path, true));                 //new writer same deal but now it doesn't overwrite anything 
      for(movie m : picks.values()) {                                              //writes each movie to the file, found a way to make hashmap iterable, what can't it do
        writeFile(m, bw);
      }
      bw.close();                                                                  //closes writers too, such a good person
    } catch (IOException e) {                                                      //actually gets sadder and sadder every error message
      System.out.println("no file found");
    } 
  }

  public static void writeFile(movie m, BufferedWriter bw) {    //handles all the actual writing outside of main to keep it clean
    try{                                                        //catches errors again
      String wr = "";                                           //file output string
      wr += m.name + ": \n";                                    //movie name: <newline>
      for (String s : m.students) {                             //each student
        wr+="\t"+s+", \n";                                      //<tab>name, <newline>
      }
      wr=wr.substring(0, wr.length()-3) + "\n";     //takes off the last comma space and just leaves the newline
      bw.write(wr);                                             //writes it all to the buffer
    } catch (IOException e) {                                   //catches errors writing
      System.out.println("stuff's writing weird");
    }
  }

  public static HashMap<String, movie> fromFile() {                                   //this bad boy can sure read a file and make a hashmap of movies
    HashMap<String, movie> out = new HashMap<String, movie>();                        //output hashmap
    try {                                                                             //more file IO errors
    File f = new File(/*..\\..\\*/"DataStructuresWork\\advanced data structures\\data\\picks.txt");
    Scanner inFile = new Scanner(new FileInputStream(f));                             //scanner for the file above
    while (inFile.hasNextLine()) {                                                    //grabs each line from the file
      String []splitted = inFile.nextLine().split(",");                         //splits it by the commas into [first, last, movie]
      if (out.containsKey(splitted[2])) {                                             //if we already have an entry for that movie
        out.get(splitted[2]).add(splitted[1]+ " " + splitted[0]);                     //grabs the entry and adds the kid's name
      } else {
        out.put(splitted[2], new movie(splitted[2], splitted[1]+ " " + splitted[0])); //otherwise we make a new entry and a new movie object with the kid's name
      }
    }
    } catch (FileNotFoundException e) {                                               //giving up more and more with these error messages
      System.out.println("no file you little rascal");
    }
    return out;
  }

}


class movie {                                       //stores a treeset of all the students and the name of the movie and that's about it

  TreeSet<String> students = new TreeSet<String>(); //students' names         
  String name;                                      //movie name

  public movie (String n, String stud) {            //constructor takes the name of the movie and the first student on the list
    students.add(stud);
    name = n;
  }

  public void add(String stud) {                    //adds the student to the set
    students.add(stud);
  }

  public TreeSet<String> getStudents() {            //returns the set of students to be output to the file after
    return students;
  }

  public String getName() {                         //returns the name of the movie also to be output to the file
    return name;
  }

  @Override     
  public String toString() {                        //used to write it to file, just outputs all our students
    return "\n"+students;
  }

}