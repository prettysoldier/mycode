package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_04_ChangeBackgroundOnMousePressed extends PApplet {

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_04_ChangeBackgroundOnMousePressed");
    }
    
    public void settings() {
    size(500, 500);
  }

  // draw runs 60 times a second by default
  public void draw() {
    // did the user click the mouse?
    if (this.mousePressed == true) {
      // change the background color!
      float randomRed = random(255);
      float randomGreen = random(255);
      float randomBlue = random(255);

      background(randomRed, randomGreen, randomBlue);
    }
  }
}
