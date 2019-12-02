package algorithm.wenhan_homework.processing.app;

public class Fish extends Animal {
  private double swimSpeed;

  public void swim() {
    System.out.println(this.name + ": Glug Glug!");
  }

  public double getSwimSpeed() {
    return swimSpeed;
  }

  public void setSwimSpeed(double swimSpeed) {
    this.swimSpeed = swimSpeed;
  }
  
}
