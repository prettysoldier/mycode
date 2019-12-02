package algorithm.wenhan_homework.processing.app;

public class TestAnimals {
  public static void main(String[] args) {
    Animal george = new Animal();
    george.setName("George");
    george.setWeight(100);
    george.setHeight(10);

    Bird polly = new Bird();
    polly.setName("Polly");
    polly.setWeight(5);
    polly.setHeight(1);

    Fish nemo = new Fish();
    nemo.setName("Nemo");
    nemo.setWeight(5);
    nemo.setHeight(1);

    // all objects can report since they are all of type Animal
    george.report();
    polly.report();
    nemo.report();

    // only fish can swim!
    nemo.swim();
    // doesn't work..
    // george.swim();

    // only birds can sing!
    polly.sing();
    // doesn't work..
    // nemo.sing();
  }
}
