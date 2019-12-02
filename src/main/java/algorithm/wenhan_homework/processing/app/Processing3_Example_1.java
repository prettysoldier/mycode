package algorithm.wenhan_homework.processing.app;

import processing.core.PApplet;

// Draw rectangles at random locations.

public class Processing3_Example_1 extends PApplet {

    public static void main(String[] args){
        PApplet.main("processing3_part1_intro.Processing3_Example_1");
    }
    
    // this method runs once at the beginning to setup the drawing area
    public void settings() {
        // set the size of the canvas for your "drawing"
        size(500, 500); 
    }

    // this method will execute in a loop (by default, you do not need
    // to do anything special)
    public void draw() {
      // this draws a rectangle with both sides equal to 10
      // (that makes it a square) at a randomly generated x,y coordinates;
      // the method random(0,500) generates a random number from 0 to 500
      float x = random(0, 500);
      float y = random(0, 500);
      rect(x, y, 10, 10);
    }
}
