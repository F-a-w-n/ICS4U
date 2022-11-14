package pong;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Pong extends JFrame {
  
  public static void main(String []args) {
    Pong frame = new Pong();
  }

  public Pong() {
    super("Basic frame"); //calls constructor and immediate superclass
    PongPanel panel = new PongPanel();
    add(panel);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); //dont show till everything is loaded

  }

}

//contains everything here           interface
class PongPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
  Timer myTimer;
  boolean []keys;
  private Paddle player1, player2;
  private Ball ball;
  private int score1 = 0, score2 = 0;
  private String screen = "intro";
  Image introPic;
  File file = new File("");
  String currentPath = file.getAbsolutePath();

  public PongPanel() {
    super();
    player1=new Paddle(20, 300, KeyEvent.VK_W, KeyEvent.VK_S);
    player2=new Paddle(750, 300, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
    ball = new Ball();
    keys = new boolean[1000];
    introPic = new ImageIcon(currentPath+"/pong/introPic.png").getImage();
    setPreferredSize(new Dimension(800,600));
    setFocusable(true);
    requestFocus();
    myTimer = new Timer(20, this);
    myTimer.start();
    addMouseListener(this);
    addKeyListener(this);
  }
  
  private void drawGame(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0,0, getWidth(), getHeight());
    g.setColor(Color.WHITE);
    for (int y=0; y<600; y+=40) {
      if (y%80 == 0) {
        g.fillRect(395, y, 10, 40);
      }
    }
    player1.draw(g);
    player2.draw(g);
    ball.draw(g);
    g.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    g.drawString("Player 1: " + score1, 100, 20);
    g.drawString("Player 2: " + score2, 600, 20); 
  
  }

  @Override
  public void paint(Graphics g) { 
    if (screen == "game") {
      drawGame(g);
    } else if (screen == "intro") {
      g.drawImage(introPic, 0, 0, null);
    } else if (screen == "winner") {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, getWidth(), getHeight());
      g.setColor(Color.WHITE);
      g.setFont(new Font("Comic Sans MS", Font.BOLD, 75));  
      g.drawString("Player "+ ((score1 > score2)? 1 : 2) + " wins!", 300, 200);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (score1 >= 3 || score2 >= 3) {
      screen = "winner";
    }
    if (screen == "game") {
      move();
    }
    repaint();
  }

  public void move() {
    ball.move(player1, player2);
    player1.move(keys);
    player2.move(keys);
    if (ball.getOutOfBound()) {
      if (ball.getX() < 0) {
        score2+=1;
        ball = new Ball();
      } else if (ball.getX() > 800) {
        score1+=1;
        ball = new Ball();
      }
    }
}

  @Override
  public void mouseClicked(MouseEvent e) {
    screen = "game";
  }
  @Override
  public void mouseEntered(MouseEvent e) {}
  @Override
  public void mouseExited(MouseEvent e) {}
  @Override
  public void mousePressed(MouseEvent e) {}
  @Override
  public void mouseReleased(MouseEvent e) {}
  @Override
  public void keyPressed(KeyEvent e) {
    keys[e.getKeyCode()]= true;
  }
  @Override
  public void keyReleased(KeyEvent e) {
    keys[e.getKeyCode()]= false;
  }
  @Override
  public void keyTyped(KeyEvent e) {
    
  }
}