package algorithm.wenhan_homework.processing.app;
public class V2Circle extends V2Shape {
  private int radius;

  public V2Circle(int x, int y, int radius) {
    // set the superclass properties
    this.x = x;
    this.y = y;

    // set Circle properties
    this.radius = radius;
  }

  @Override
  public void printInfo() {
    System.out.println("Circle at " + this.x + ", " + this.y + "; radius="
        + this.radius);
  }
  
  @Override
  public String toString() {
    return "Circle has an area of: " + Math.PI * radius * radius;
  }
  
  // getters & setters
  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }
}