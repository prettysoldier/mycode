package algorithm.wenhan_homework.processing.processing3_part1_intro;

import processing.core.PApplet;

// Changing color of the background and fill color of shapes. 

public class Processing3_Example_2 extends PApplet {

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.processing3_part1_intro.Processing3_Example_2");
    }
    
    // this method runs once at the beginning to setup the drawing area
    public void settings() {
        // set the size of the canvas for your "drawing"
        size(1000, 1000);
    }
    
  // this method runs once at the beginning to setup the drawing area
  public void setup() {
    // Set the background color of the canvas: the three values
    // represent levels of red, green and blue. They should be between
    // 0 and 255.
    background(0, 0, 0);
  }

  public void draw() {
    // Calling fill() before the shape is drawn changes the fill color
    // for the next drawn shape, the circles appear in different colors.
    fill(random(0, 255), random(0, 255), random(0, 255));
    ellipse(random(0, 1000), random(0, 1000), 30, 10);
  }

}
