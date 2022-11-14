//Method1.java - Original code by Fawn Barisic - finds the fastest path through an 8x8 grid, outputs the shortest time possible and the directions (only down and right) taken to get from 0,0 to 7,7

//this is where it becomes kinda ironic that get rid of the System. considering how long my code is anyways
import static java.lang.System.out;
//java.util has the day off, well deserved

public class Method6 {
  //tankPath function takes in the 2d array of times as parameter
  public static void tankPath(int [][]grid) {
    //fin contains the output of testpaths (formatted as an array of strings {time, directions}) (time is a string which does become an issue later as it is an int but I couldn't think of a more efficient way of formatting my output so I can have directions return as well)
    String []fin = testpaths(grid, 0, 0);

    //outputs lowest possible time \n directions to get there
    out.println(fin[0]);
    out.println(fin[1]);
  }
  //testpaths takes the grid and x,y coordinates on the grid as parameters
  public static String []testpaths(int [][]grid, int x, int y) {
    //right and down are used to store the values from testpaths at one to the right and one to the bottom, only sets them if not base case though
    String []right=new String[2];
    String []down=new String[2];
    //rtime is the minimum time possible if the tank moves right, dtime is the same for moving down
    int rtime = 0;
    int dtime = 0;
    //outp gets filled with the outputs to return depending on case and such
    String []outp = {"", ""};

    //base case, at bottom right corner
    if (x==7 && y==7) {
      //sets time taken as the current square, since there are no further squares to add 
      outp[0] = ""+grid[x][y];
      outp[1] = "end";
    //recursive case, moves towards x==7, y==7 to end
    } else {
      //when there is a spot to the right available, test paths there
      if (x<7) {
        //grabs min time and directions available to the right
        right = testpaths(grid, x+1, y);
        //min time taken to the right
        rtime = grid[x][y] + Integer.parseInt(right[0]);
      }
      //when there is a spot down available, test paths there
      if (y<7) {
        //grabs min time and directions available below
        down = testpaths(grid, x, y+1);
        //min time taken to the bottom
        dtime = grid[x][y] + Integer.parseInt(down[0]);
      }
      //if against the right wall, can only move down
      if (x==7) {
        //sets min time to the down time, since there's no right to compare
        outp[0] = ""+dtime;
      //if against bottom wall, can only move right
      } else if (y==7) {
        //sets min time to the right since theres no down to compare
        outp[0] = ""+rtime;
      //when the tank is not against a wall impeding its movements, actual comparison between directions  
      } else {
        //sets min time to the lowest of the right and the bottom
        outp[0] = (rtime<dtime)? ""+rtime : ""+dtime;
      }
      //when it is not the final move, direction string is set to empty at base since it could be going either way
      if (x+y<13) {
        //sets direction to have the new earliest direction at the beginning (since this is backtracting) and then add the earlier directions on
        outp[1] = (rtime<dtime)? "R"+right[1] : "D"+down[1];
      //last movement, when the directions string will be empty (when run without this option directions show up as RRDDDRRDRRDDDDnull) 
      } else {
        //sets to R when it has to move right, sets to D when it has to move down
        outp[1] = (x<7)? "R" : "D";
      }
    }
    //returns outp, containing {current min time, current directions}
    return outp;
  }

  //main contains a few example grids I used for testing and calls tankpath to find the shortest path through whichever one is uncommented
  public static void main(String []args) {
    //base grid, given with assignment
    int [][]grid = {{ 0,21,20, 5,25,25,35,15},{12,26,43,29,15,26,15,12},{ 7,18,23,28,36,32,12,18},{43,34,35,18,25,18,21,25},{32,41,23, 9,21,17,24,14},{12, 9,20,42, 9,19,26,22},{30,17,17,35,14,25,14,21},{15,21,37,24,19,15,35,15}};
    
    //base grid with x and y swapped, good for testing if directions work
    //int [][]grid = {{0, 12, 7, 43, 32, 12, 30, 15}, {21, 26, 18, 34, 41, 9, 17, 21}, {20,43,23,35,23,20,17,37}, {5,29,28,18,9,42,35,24}, {25,15,36,25,21,9,14,19}, {25,26,32,18,17,19,25,15}, {35,15,12,21,24,26,14,35}, {15,12,18,25,14,22,21,15}};
    
    //base grid but I switched a few random numbers to change the path, good for testing if the count works
    //int [][]grid = {{0, 12, 7, 23, 32, 12, 30, 15}, {21, 26, 18, 14, 41, 9, 17, 21}, {20, 43, 23, 35, 23, 20, 17, 17}, {5, 29, 28, 18, 9, 2, 35, 24}, {25, 15, 36, 25, 21, 9, 24, 19},  {25, 26, 32, 18, 17, 19, 25, 55}, {35, 15, 12, 21, 24, 17, 14, 35}, {15, 12, 1, 25, 14, 22, 21, 15}};
    
    //cals tankPath, output is all covered in the method so it isnt 3 nested functions like the others
    tankPath(grid);
  
  }
}

//sorry, I kinda like commenting it makes me feel organized-er
