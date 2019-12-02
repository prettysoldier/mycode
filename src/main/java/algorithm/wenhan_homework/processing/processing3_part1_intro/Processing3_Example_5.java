package algorithm.wenhan_homework.processing.processing3_part1_intro;

import processing.core.PApplet;

public class Processing3_Example_5 extends PApplet {
    
    public static void main(String[] args){
        PApplet.main("processing3_part1_intro.Processing3_Example_5");
    }
    
    public void settings() {
        // set the size of the canvas for your "drawing"
        size(1000, 1000);
    }

    public void draw() {
        ellipse(random(0, 1000), random(0, 1000), 20, 50);
    }
}
