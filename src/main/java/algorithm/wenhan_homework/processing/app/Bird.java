package algorithm.wenhan_homework.processing.app;

public class Bird extends Animal {
  private boolean flight = false;

  public void sing() {
    System.out.println(this.name + ": Tweet Tweet!");
    this.sayName();
  }

  public boolean getFlight() {
    return flight;
  }

  public void setFlight(boolean flight) {
    this.flight = flight;
  }
}
