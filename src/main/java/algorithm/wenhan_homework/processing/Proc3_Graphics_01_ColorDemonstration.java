package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_01_ColorDemonstration extends PApplet {
  
    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_01_ColorDemonstration");
    }
    
    public void settings() {
        // set up our canvas size
        size(500, 500);
    }
    
  // Setup runs one time at the beginning of your program
  public void setup() {
    // Color in Java is represented using integers.
    // There are 4 color "channels" you can manipulate.
    // Each channel is represented using a single byte where
    // possible values are 0 (totally off) to 255 (totally on).
    
    // Uncomment one of the background() method calls below to see
    // each color in action

    // The greyscale channel
    //   range: 0 = black, 255 = white
    // background(0);   // black
    // background(255); // white
    // background(125); // grey

    // RGB color is represented in this order - Red, Green and Blue
    //   range: 0 = black, 255 = pure red, green or blue
    // background(255, 0, 0); // pure red
    // background(0, 255, 0); // pure green
    // background(0, 0, 255); // pure blue
         
     background(255, 0, 0); // a mixture!

  }

  // Draw is an infinite loop - it keeps running about 60 times a second
  public void draw() {
  }
}