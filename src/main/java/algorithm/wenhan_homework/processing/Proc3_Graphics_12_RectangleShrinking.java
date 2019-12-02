package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_12_RectangleShrinking extends PApplet {
  int locX = 0;
  int locY = 0;
  int size = 500;

  public static void main(String[] args){
      PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_12_RectangleShrinking");
  }
  
  public void settings() {
    size(500, 500);
    smooth();
  }

  // draw is an infinite loop
  // it keeps running about 60 times a second
  public void draw() {
    // only do this if our size is big enough
    if (this.size > 5) {

      // turn off the stroke
      noStroke();

      // random fill
      fill(random(255));

      // draw a rectangle at our current location
      rect(this.locX, this.locY, size, size);

      // increase location by 2
      this.locX += 2;
      this.locY += 2;

      // decrease size by 4
      this.size -= 4;
    }
  }
}
