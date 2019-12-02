package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_07_RandomEllipses extends PApplet {

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_07_RandomEllipses");
    }

    public void settings() {
        size(500, 500);
        smooth();
    }

    // draw is an infinite loop
    // it keeps running about 60 times a second
    public void draw() {
        // randomize color
        fill(random(255), random(255), random(255), random(255));
        //  red,         green,       blue         transparency

        // turn off the stroke (outline) of the shape
        noStroke();

        // draw a random ellipse on the screen
        ellipse(random(0, this.width), random(0, this.height), random(50),  random(50));
    }
}
