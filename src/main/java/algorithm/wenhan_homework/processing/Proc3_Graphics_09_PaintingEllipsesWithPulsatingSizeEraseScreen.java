package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_09_PaintingEllipsesWithPulsatingSizeEraseScreen extends PApplet {
    int size = 10;
    int sizeInc = 1;

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_09_PaintingEllipsesWithPulsatingSizeEraseScreen");
    }

    // setup runs one time at the beginning of your program
    public void settings() {
        size(500, 500);
        smooth();
    }

    // draw is an infinite loop it keeps running about 60 times a second
    public void draw() {
        // if any key on the keyboard is pressed we can erase the screen
        if (this.keyPressed) {
            // erase by simply drawing a new background
            background(200);
        }

        // if the mouse is down, draw an ellipse
        if (this.mousePressed) {
            // fill with a random color
            fill(random(255), random(255), random(255));

            // draw our ellipse
            ellipse(this.mouseX, this.mouseY, size, size);

            // adjust size
            this.size += this.sizeInc;

            // did we get too big?
            if (this.size > 50) {
                this.sizeInc = -1;
            }
            // did we get too small?
            if (this.size < 10) {
                this.sizeInc = 1;
            }
        }
    }
}
