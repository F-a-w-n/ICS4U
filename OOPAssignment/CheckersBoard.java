//CheckersBoard.java - Original code by Fawn Barisic - this one makes a checkers game

public class CheckersBoard {
  //starting gameboard, transposed sideways because arrays read y,x not x,y
  private int [][]board = {
    {1,0,1,0,0,0,2,0},
    {0,1,0,0,0,2,0,2},
    {1,0,1,0,0,0,2,0},
    {0,1,0,0,0,2,0,2},
    {1,0,1,0,0,0,2,0},
    {0,1,0,0,0,2,0,2},
    {1,0,1,0,0,0,2,0},
    {0,1,0,0,0,2,0,2}
  };
  //reads 2 as red, 1 as black
  public static final int RED = 2;
  public static final int BLACK = 1;

  //stores number of each piece for count function, alters them when you jump so counting goes faster
  private int rcount = 12;
  private int bcount = 12;
  
  //returns counts
  public int count(int colour) {
    return (colour == CheckersBoard.RED)? rcount : bcount;
  }

  //outputs an ascii version of the checkerboard
/*
+-+-+-+-+-+-+-+-+
|B| |B| |B| |B| |
+-+-+-+-+-+-+-+-+
| |B| |B| |B| |B|
+-+-+-+-+-+-+-+-+
|B| |B| |B| |B| |
+-+-+-+-+-+-+-+-+
| | | | | | | | |
+-+-+-+-+-+-+-+-+
| | | | | | | | |
+-+-+-+-+-+-+-+-+
| |R| |R| |R| |R|
+-+-+-+-+-+-+-+-+
|R| |R| |R| |R| |
+-+-+-+-+-+-+-+-+
| |R| |R| |R| |R|
+-+-+-+-+-+-+-+-+
*/
  public void display() {
    //loops through lines output, 8 for pieces, 8 for line separators
    for (int i=0; i<16; i++) {
      //o stores the output line
      String o = "";
      //every even line has a separator
      if (i%2==0) {
        o = "+-+-+-+-+-+-+-+-+";
      } else { //every off one has the pieces
      for (int j=0; j<8; j++) {
        //black is output as a line and a B, the line separates the output into columns
        if(board[j][i/2] == CheckersBoard.BLACK) {
          o+="|B";
        //red is output as a line and an R, the line separates output into columns
        } else if (board[j][i/2] == CheckersBoard.RED) {
          o+="|R";
        } else { //when there are no pieces, it outputs an empty space, still with lines so the columns are clear
          o+="| ";
        }
      } //final column needs a line on the end 
      o+="|";
    } //outputs whatever the line is to console
      System.out.println(o);
    } //final separator line
    System.out.println("+-+-+-+-+-+-+-+-+");
  }

  //coordinates moves and captures
  public boolean move(int x1, int y1, int x2, int y2) {
    //converts 1-8 coords in console to 0-7 coords in array
    x1--; x2--; y1--; y2--;
    //confirms that the piece is there, moving to a valid square (since one colour of square is never touched), and is moving the right direction for its type
    if (board[x1][y1] == 0 || x2-y2%2==1 || (y1>y2 && this.board[x1][y1] == CheckersBoard.BLACK) || (y1<y2 && this.board[x1][y1] == CheckersBoard.RED)) {
      System.out.println("invalid move");
      return false;
    //single jumps, no takes
    } else if (Math.abs(x2-x1) == 1 && Math.abs(y2-y1) == 1 && board[x2][y2] == 0) {
      //moves the piece's value to the new point
      board[x2][y2] = board[x1][y1];
      //removes piece from old point
      board[x1][y1] = 0;
      return true;
    } else {
      //calls jump method to see if bigger jumps are possible
      int [][]tryingjump = jump(this.board,x1,y1,x2,y2);
      //when the jump doesn't work returns false, does nothing
      if (tryingjump == this.board) {
        return false;
      } else { //when jump works, update board
        this.board = tryingjump;
        return true;
      }
    }
  }

  //recurses through every path and moves that piece to the desired spot if possible
  private int [][]jump(int [][]board, int x1, int y1, int x2, int y2) {
    //so many checks to make sure a move is valid
    if (x1 < 0 || x1 > 7 || y1 < 0 || y1 > 7 || (y1>y2 && this.board[x1][y1] == CheckersBoard.BLACK) || (y1<y2 && this.board[x1][y1] == CheckersBoard.RED)) {
      return this.board; //returns old board, does nothing
    } else if (y1==y2 && x1==x2) {
      return board; //success! you found the right move now stop calling jump and move the piece already
    } else { //not yet successful, trying possible jumps
      //delta y, red pieces move up (-y), black move down (+y)
      int dy = (board[x1][y1] == CheckersBoard.RED)? -2 : 2;

      //left stores the attempt at jumping left, right stores attempt for right side
      int [][]left = board;
      int [][]right = board;

      //if statement hell VVVV

      //only moves left when there is room to the left
      if (x1 - 2 >= 0) {
        //if there is a piece one over, and it is a different colour, AND the place behind it is empty
        if (left[x1-1][y1+dy/2] != 0 && left[x1-1][y1+dy/2] != left[x1][y1]  && left[x1-2][y1+dy] == 0) {
          if (left[x1][y1] == CheckersBoard.BLACK) { //removes one from red count
            rcount--;
          } else { //removes one from black count
            bcount--;
          }
          //removes enemy piece
          left[x1-1][y1+dy/2] = 0;
          //moves current piece to new location
          left[x1-2][y1+dy] = left[x1][y1];
          //removes current piece from new location
          left[x1][y1] = 0;
          //checks for further jumps (will return current board if no more jumps can be made)
          left =  jump(left, x1-2, y1+dy, x2, y2); 
        }
      } 
      //only moves right when there is room to the right
      if (x1 + 2 < 8) {
        //if there is a piece one over, and it is a different colour, AND the place behind it is empty
        if (right[x1+1][y1+dy/2] != 0 && right[x1+1][y1+dy/2] != right[x1][y1] && right[x1+2][y1+dy] == 0) {
          if (left[x1][y1] == CheckersBoard.BLACK) { //if black, removes one red
            rcount--;
          } else { //if red, remves one black
            bcount--;
          }
          //removes enemy piece
          right[x1+1][y1+dy/2] = 0;
          //moves current piece to new location
          right[x1+2][y1+dy] = right[x1][y1];
          //removes old piece
          right[x1][y1] = 0;
          //checks for further jumps
          right = jump(right, x1+2, y1+dy, x2, y2);
        }
      }
      //if left doesn't work, go right; otherwise go left. when both are bad it returns to the previous branch of the tree as failed case
      if (left == this.board) {
        return right;
      } else {
        return left;
      }
    }
  }

  public int get(int gx, int gy) {
    return board[gx][gy];
  }

}
