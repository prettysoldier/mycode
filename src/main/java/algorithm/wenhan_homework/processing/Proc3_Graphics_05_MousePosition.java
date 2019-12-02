package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_05_MousePosition extends PApplet {

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_05_MousePosition");
    }
    
    public void settings() {
        size(640, 480);
    }
    
    public void setup() {
        // note that PApplet has two instance variables that you can refer
        // to in order to get the screen size. They are called width and
        // height and can be referenced as follows:
        System.out.println("Stage width is: " + this.width);
        System.out.println("Stage height is: " + this.height);
  }

  // draw is an infinite loop - it keeps running about 60 times a second
  public void draw() {
    // The code below shows a few ways in which we can use the mouse
    // position when coding. You should uncomment one multi line
    // block at a time in order to see this code in action

    // Block #1
    // PApplet contains two instance variables named mouseX and mouseY
    // These values are continually updated after each cycle of your
    // draw loop -- they store integer values that represent where
    // the user's mouse is on the canvas
    // note that the origin of your project is the top left corner
    // of the graphics canvas that appears
    System.out.println(this.mouseX + ", " + this.mouseY);
   
    // Block #2 
    // let's change the background color based mouse location
    if (this.mouseX < this.width / 2) {
      background(0);
    } else {
      background(255);
    }
    
    // Block #3 
    // we can do better than that! Let's gradually adjust the color 
    // to do what we need to "map" the mouse's value to acceptable 
    // range for a color channel (0 - 255). the mouse in this case can 
    // be anything between 0 and 640 - well outside the acceptable range 
    // so let's figure out how far across the screen the mouse is 
  // float percentAcross = (float) this.mouseX / (float) this.width;
      
    // now we can use this percentage to multiply by 255 to 
    // get a nice  smooth transition 
 // background(255 * percentAcross);

    // Block #4
    // here's the easy way in processing -- just use the "map" method!
    // takes 4 arguments
    // (a) a number to examine
    // (b) the minimum value for that number
    // (c) the maximium value for that number
    // (d) the minimum value you want to convert the number into
    // (e) the maximum value you want to convert the number into
//    float mappedColor = map(this.mouseX, 0, this.width, 0, 255);
//    background(mappedColor);
  }
}