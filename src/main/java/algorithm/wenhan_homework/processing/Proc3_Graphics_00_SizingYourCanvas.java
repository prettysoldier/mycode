package algorithm.wenhan_homework.processing;

import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class Proc3_Graphics_00_SizingYourCanvas extends PApplet {
  
    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_00_SizingYourCanvas");
    }
    
    public void settings() {
        // You can size your canvas by calling the size() method
        size(240, 180);
    }
    
  // Setup runs one time at the beginning of your program
  public void setup() {
    // You can then access the size of your canvas 
    // by calling on the width and height instance variables
    System.out.println("Canvas size: " + this.width 
        + " by " + this.height);
  }

  // Draw is an infinite loop it is called about 60 times a second
  public void draw() {
    PImage img = loadImage("../data/bird.png");
    image(img, 50, 50);
  }
}
