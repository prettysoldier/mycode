package algorithm.wenhan_homework.processing.app;
public class V2Rectangle extends V2Shape {
  private int width, height;

  public V2Rectangle(int x, int y, int width, int height) {
    // set the superclass properties
    this.x = x;
    this.y = y;
    
    // set Rectangle properties
    this.width = width;
    this.height = height;
  }

  @Override
  public void printInfo() {
    System.out.println("Rectangle at " + this.x + ", " + this.y + "; width="
        + this.width + "; height=" + this.height);
  }

  @Override
  public String toString() {
    return "Rectangle has area of: " + this.width * this.height;
  }

  // getters & setters
  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}