package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_04_ChangeBackgroundOnMousePressedUsingMethod extends PApplet {

  public static void main(String[] args){
    PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_04_ChangeBackgroundOnMousePressedUsingMethod");
  }
    
  public void settings() {
    size(500, 500);
  }

  // draw runs 60 times a second by default
  public void draw() {
    // nothing to do here! all the action takes place in mousePressed below
  }

  // mousePressed is a method that you can "override" off of the PApplet class
  // this method is designed to be called one time when the mouse is pressed
  // note: the @Override declaration isn't required. I'm just putting it here so
  // that we know that we are overriding a method that already exists instead of making
  // up a new one
  @Override
  public void mousePressed() {
    // change the background color!
    float randomRed = random(255);
    float randomGreen = random(255);
    float randomBlue = random(255);

    background(randomRed, randomGreen, randomBlue);
  }
}
