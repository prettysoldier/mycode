package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_02_BackgroundFading extends PApplet {
  // Keep track of the current color of the background
  int currentColor = 0;

  // Keep track of which "direction" we are moving -
  // true means we are getting brighter, false means we are getting darker
  boolean colorDirection = true;

  public static void main(String[] args){
      PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_02_BackgroundFading");
  }
  
  public void settings() {
      // set up our canvas size
     size(500, 500);
  }

  // draw runs 60 times per second by default
  public void draw() {
    // draw the background using our instance variable
    background(this.currentColor);

    // change our color based on our color direction
    this.currentColor = this.colorDirection ? this.currentColor + 1 : this.currentColor - 1;

    // If we hit the upper range (255) we need to start moving back down
    // If we hit the lower range (0) we need to start moving back up
    if (this.currentColor >= 255 || this.currentColor <= 0) {
      // flip our color direction 
      this.colorDirection = !this.colorDirection;
    }
  }

}
