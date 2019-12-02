package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_13_RandomShapeInstanceMethod extends PApplet {

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_13_RandomShapeInstanceMethod");
    }

    public void settings() {
        size(500, 500);
        smooth();
    }

    // draw is an infinite loop it keeps running about 60 times a second
    public void draw() {
        // only paint if the mouse is down
        if (this.mousePressed == true) {
            // invoke our randomShape method
            randomShape(this.mouseX, this.mouseY);
        }

    }

    // draw a random shape
    public void randomShape(int x, int y) {
        // is this an ellipse or a rectangle?
        if (Math.random() > 0.5) {
            System.out.println("hi");
            // ellipse!
            fill(random(255), random(255), random(255));
            ellipse(x, y, random(15, 40), random(15, 40));
        } else {
            // rectangle!
            rectMode(CENTER);
            fill(random(255), random(255), random(255));
            rect(x, y, random(15, 40), random(15, 40));
        }
    }

}
