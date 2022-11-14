public class Rect {
  private double x, y, w, h;

  public Rect(double x, double y, double w, double h) {
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
  }

  public double getX() {return this.x;}

  public double getY() {return this.y;}

  public double getWidth() {return this.w;}

  public double getHeight() {return this.h;}

  public void translate(double dx, double dy) {
    this.x+=dx;
    this.y+=dy;
  }

  public void resize(double amount) {
    this.x -= (w*amount-w)/2;
    this.y -= (h*amount-h)/2;
    this.w*=amount;
    this.h*=amount;
  }

  private boolean checkWithin(double a1, double b1, double a2, double b2) {

    if ( ( (a1 >= a2 &&  a1 <= b2) || (b1 >= a2 && b1 <= b2) ) || ( (a2 >= a1 &&  a2 <= b1) || (b2 >= a1 && b2 <= b1) ) )
    return true;
    else 
    return false;
  }

  public boolean overlaps(Rect other) {

    boolean x1 = checkWithin(this.x, this.x+this.w, other.getX(), other.getX() + other.getWidth());
    boolean x2 = checkWithin(other.getX(), other.getX() + other.getWidth(), this.x, this.x+this.w);
    boolean y1 = checkWithin(this.y, this.y+this.h, other.getY(), other.getY() + other.getHeight());
    boolean y2 = checkWithin(other.getY(), other.getY() + other.getHeight(), this.y, this.y+this.h);

    return ((x1||x2) && (y1||y2));
  }

  public boolean contains(double ix, double iy) {

    if (ix >= this.x && ix <= this.x+this.w && iy >= this.y && iy <= this.y+this.h)
    return true;
    else
    return false;
  }

  public double []centre() {

    double []mid = new double[2];
    mid[0] = this.x+this.w/2;
    mid[1] = this.y+this.h/2;

    return mid;
  }

}
