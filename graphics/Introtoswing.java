import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Introtoswing extends JFrame {
  public static void main(String []args) {
    Introtoswing frame = new Introtoswing();
  }

  public Introtoswing() {
    super("Basic frame"); //calls constructor and immediate superclass
    PanelIntro pane = new PanelIntro();
    add(pane);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); //dont show till everything is loaded
  }

}

//contains everything here           interface
class PanelIntro extends JPanel implements ActionListener, MouseListener, KeyListener {
  int x = 0, y = 0;
  Timer myTimer;
  Image background;
  boolean []keys;

  public PanelIntro() {
    super();
    keys = new boolean[1000];
    background = new ImageIcon(getClass().getResource("cat.jpg")).getImage();
    setPreferredSize(new Dimension(800,600));
    myTimer = new Timer(20, this);
    myTimer.start();
    addMouseListener(this);
    addKeyListener(this);
    setFocusable(true);
    requestFocus();
  }
  
  @Override
  public void paint(Graphics g) { 
    //g.setColor(Color.WHITE);
    //g.fillRect(0,0, getWidth(), getHeight());
    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    g.setColor(new Color(123, 32, 1));
    g.fillOval(x, y, 120, 120);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Point mouse = MouseInfo.getPointerInfo().getLocation();
    Point offset = getLocationOnScreen();
    x=(int)mouse.getX() - (int)offset.getX();
    y=(int)mouse.getY() - (int)offset.getY();

    move();
    repaint();
  }

  public void move() {
    
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    x= e.getX() -60;
    y= e.getY() -60;
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
    System.out.println(e.getKeyCode());
  }
  @Override
  public void keyReleased(KeyEvent e) {
    keys[e.getKeyCode()]= false;
  }
  @Override
  public void keyTyped(KeyEvent e) {
    
  }
}