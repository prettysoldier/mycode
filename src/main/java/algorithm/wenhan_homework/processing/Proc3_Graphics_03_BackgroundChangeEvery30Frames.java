package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_03_BackgroundChangeEvery30Frames extends PApplet {
  // instance vars
  float bgRed = 0;
  float bgGreen = 0;
  float bgBlue = 0;

  int bgColorCounter = 0;

  public static void main(String[] args){
      PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_03_BackgroundChangeEvery30Frames");
  }
  
  public void settings() {
      size(500, 500);
  }
  
  // setup runs one time at
  // the beginning of your program
  public void setup() {
    background(this.bgRed, this.bgGreen, this.bgBlue);
  }

  // draw is an infinite loop
  // it keeps running about 60 times a second
  public void draw() {
    // increase bgColorCounter
    this.bgColorCounter++;

    // are we at 10 frames?
    if (this.bgColorCounter == 30) {
      // pick new backgorund colors
      this.bgRed = random(255);
      this.bgGreen = random(255);
      this.bgBlue = random(255);

      // recolor the background
      background(this.bgRed, this.bgGreen, this.bgBlue);

      // reset our counter
      this.bgColorCounter = 0;
    }
  }
}
