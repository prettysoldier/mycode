package algorithm.wenhan_homework.processing.app;
public class V2Shape {
  protected int x, y;

  public void printInfo() {
    System.out.println("Shape at: " + this.x + ", " + this.y);
  }

  @Override
  public String toString() {
    return "Shapes have no area";
  }
  
  // getters & setters
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}