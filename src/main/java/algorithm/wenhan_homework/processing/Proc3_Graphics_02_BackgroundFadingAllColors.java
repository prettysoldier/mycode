package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_02_BackgroundFadingAllColors extends PApplet {
  // keep track of the current color of the background
  // keep track of each color channel separately
  int currentRedColor = 0;
  int currentGreenColor = 100;
  int currentBlueColor = 200;

  // keep track of which "direction" we are moving -
  // +1 means we are getting brighter, -1 means we are getting darker
  int colorDirectionRed = 1;
  int colorDirectionGreen = 1;
  int colorDirectionBlue = 1;

  public static void main(String[] args){
      PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_02_BackgroundFadingAllColors");
  }
  
  // setup runs one time
  public void settings() {
    // size our canvas
    size(500, 500);
  }

  // draw runs 60 times a second
  public void draw() {
    // draw the background using our instance variable
    background(this.currentRedColor, this.currentGreenColor, this.currentBlueColor);

    // change our color based on our color direction
    this.currentRedColor += this.colorDirectionRed;
    this.currentGreenColor += this.colorDirectionGreen;
    this.currentBlueColor += this.colorDirectionBlue;

    // if we hit the upper range (255) or the lower range (0) we need to flip
    if (this.currentRedColor >= 255 || this.currentRedColor <= 0) {
      // flip our color direction so that we get darker on our next iteration
      this.colorDirectionRed *= -1;
    }
    if (this.currentGreenColor >= 255 || this.currentGreenColor <= 0) {
      // flip our color direction so that we get darker on our next iteration
      this.colorDirectionGreen *= -1;
    }
    if (this.currentBlueColor >= 255 || this.currentBlueColor <= 0) {
      // flip our color direction so that we get darker on our next iteration
      this.colorDirectionBlue *= -1;
    }
  }

}
