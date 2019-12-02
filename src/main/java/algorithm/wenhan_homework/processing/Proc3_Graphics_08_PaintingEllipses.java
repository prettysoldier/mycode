package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_08_PaintingEllipses extends PApplet {

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_08_PaintingEllipses");
    }
    
    public void settings() {
    size(500, 500);
    smooth();
  }

  // draw is an infinite loop
  // it keeps running about 60 times a second
  public void draw() {
    if (this.mousePressed) {
      // fill with a random color
      fill(random(255), random(255), random(255));

      // pick a random size
      float size = random(10, 30);

      // draw an ellipse where the mouse is
      ellipse(mouseX, mouseY, size, size);
    }
  }
}
