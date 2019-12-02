package algorithm.wenhan_homework.processing.processing3_part1_intro;

import processing.core.PApplet;

// Printing text and changing its properties. Detecting mouse clicks.

public class Processing3_Example_3 extends PApplet {
    
    public static void main(String[] args){
        PApplet.main("processing3_part1_intro.Processing3_Example_3");
    }
    
    // this method runs once at the beginning to setup the drawing area
    public void settings() {
        // set the size of the canvas for your "drawing"
        size(500, 500);
    }
    
  public void setup() {
    // set the background color to white
    background(255, 255, 255);
  }

  public void draw() {
    // print text on the canvas: the font size is 32, the text color is black,
    // the text as centered on its x and y coordinates
    textSize(32);
    fill(0, 0, 0);
    textAlign(CENTER);
    text("Click to change the color", 250, 250);

    // change the background color to a random color on mouse click
    if (mousePressed)
      background(random(0, 255), random(0, 255), random(0, 255));
  }
}