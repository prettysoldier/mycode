package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_11_PaintingWithRectangles extends PApplet {

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_11_PaintingWithRectangles");
    }
    
    public void settings() {
    size(500, 500);
    smooth();
  }

  // draw is an infinite loop it keeps running about 60 times a second
  public void draw() {
    // is the mouse down?
    if (this.mousePressed == true) {
      // pick a random color
      fill(random(255), random(255), random(255));

      // indicate that we want to draw from the center of the rectangle
      rectMode(CENTER);

      // draw a randomize rectangle here
      rect(this.mouseX, this.mouseY, random(20), random(20));
    }
  }
}
