package algorithm.wenhan_homework.processing.app;

public class Animal {
  protected String name;
  protected double weight;
  protected double height;
  
  public void sayName() {
    System.out.println("Hello, my name is " + this.name);
  }

  public void report() {
    System.out.println(this.name + ": I am " + height 
        + " inches tall and " + weight + " lbs.");
  }
  
  public void setName(String name) {
      this.name = name;
  }

  public void setWeight(double weight) {
    if (weight > 0)
      this.weight = weight;
  }

  public void setHeight(double height) {
    if (height > 0)
      this.height = height;
  }
}
