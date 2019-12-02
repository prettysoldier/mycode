package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_10_Kaleidoscope extends PApplet {
  // sizing for our ellipse
  int ellipseSize = 30;
  int ellipseSizeDirection = 1;
  
  public static void main(String[] args){
      PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_10_Kaleidoscope");
  }
  
  public void settings() {
    size(500, 500);
    smooth();
  }

  public void draw() {
    if (this.mousePressed) {
      // random color
      fill(random(255), random(255), random(255));

      // no stroke
      noStroke();

      // draw an ellipse where the mouse is
      ellipse(mouseX, mouseY, this.ellipseSize, this.ellipseSize);

      // also draw an ellipse on the other side of the canvas
      ellipse(this.width - mouseX, mouseY, this.ellipseSize, this.ellipseSize);

      // update size
      this.ellipseSize += this.ellipseSizeDirection;

      // do we need to flip direction (bigger / smaller?)
      if (this.ellipseSize > 50 || this.ellipseSize < 10) {
        this.ellipseSizeDirection *= -1;
      }
    }
  }

}
