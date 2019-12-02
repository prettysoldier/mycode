package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_14_MouseCursorSizeChange extends PApplet {
    // size of the cursor
    int cursorSize = 20;
    int cursorSizeDirection = 1;

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_14_MouseCursorSizeChange");
    }

    public void settings() {
        size(500, 500);
        smooth();
    }
    
    public void setup() {
        // turn off the default mouse cursor
        noCursor();
    }

    // draw is an infinite loop it keeps running about 60 times a second
    public void draw() {
        background(0);

        // draw our cursor
        drawCursor();
    }

    public void drawCursor() {
        // fill with a red color if the mouse is down
        if (this.mousePressed) {
            fill(255, 0, 0);
        }
        // otherwise fill with a white color
        else {
            fill(255);
        }

        // draw the cursor all the time (regardless of whether the mouse is down)
        ellipse(this.mouseX, this.mouseY, this.cursorSize, this.cursorSize);

        // update cursor size
        this.cursorSize += this.cursorSizeDirection;

        // do we need to pulsate?
        if (this.cursorSize > 30 || this.cursorSize < 10) {
            this.cursorSizeDirection *= -1;
        }
    }

}
