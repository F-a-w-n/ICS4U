class MyRobot {
  private double x=0, y=0;
  private double heading = 0;

  public MyRobot() {
    this(0,0,0);
  }
  public MyRobot(double x, double y, double heading) {

    this.x = x;
    this.y = y;
    this.heading = heading;

  }

  public void turn(double angle) {
    this.heading=((((this.heading+angle)%360)+360)%360);
  }

  public void advance(double dist) {
    this.y-=dist*Math.sin(Math.toRadians(heading));
    this.x+=dist*Math.cos(Math.toRadians(heading));
  }

  @Override
  public String toString() {
    return "x: " + this.x + " y: " + this.y + " heading: " + this.heading;
  }
}