package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Proc3_Graphics_06_SimpleShapes extends PApplet {

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_06_SimpleShapes");
    }

    public void settings() {
        size(640, 480);
    }
    
    public void setup() {

        // you can draw lots of things using Processing. Let's start
        // with some simple shapes.

        // ellipses can be used to draw circular shapes to the screen.
        // syntax: ellipse( xPosition, yPosition, width, height)
        // ellipses draw from their center point by default
        ellipse(25, 25, 10, 10);
        ellipse(50, 25, 30, 30);
        ellipse(75, 25, 10, 30);
        ellipse(100, 25, 30, 10);
        ellipse(135, 25, 30, 10);
        ellipse(135, 25, 10, 30);

        // you can ask Java to smooth out your shapes by calling the
        // smooth() method. you only have to call smooth() once and
        // all shapes drawn after this method call will be smoothed
        // prior to being drawn on the screen
        smooth();
        ellipse(25, 75, 10, 10);
        ellipse(50, 75, 30, 30);
        ellipse(75, 75, 10, 30);
        ellipse(100, 75, 30, 10);
        ellipse(135, 75, 30, 10);
        ellipse(135, 75, 10, 30);

        // you can specify no fill color for your shapes by using the noFill() method.
        // call this method once and all shapes after it will not receive a fill
        noFill();
        ellipse(25, 125, 10, 10);
        ellipse(50, 125, 30, 30);
        ellipse(75, 125, 10, 30);
        ellipse(100, 125, 30, 10);
        ellipse(135, 125, 30, 10);
        ellipse(135, 125, 10, 30);

        // you can color the fill of an ellipse by calling the fill() method
        // prior to drawing it to the screen. you can pass fill up to 4 arguments 
        // each argument is an integer ranging from 0 to 255. Here's the general usage:
        // fill(greyscale)
        // fill(red, green, blue)
        // fill(red, green, blue, alpha) // alpha is transparency
        fill(150);
        ellipse(25, 175, 10, 10);

        fill(255, 0, 0);
        ellipse(50, 175, 30, 30);

        fill(0, 255, 0);
        ellipse(75, 175, 10, 30);

        fill(0, 0, 255);
        ellipse(100, 175, 30, 10);

        fill(0, 0, 255, 90);
        ellipse(135, 175, 30, 10);
        ellipse(135, 175, 10, 30);

        // you can turn off the outline for a shape by calling noStroke()
        // this will turn off the outline for all shapes drawn afterwards until you
        // call the stroke() method to set up a new stroke color.
        noStroke();

        fill(150);
        ellipse(25, 225, 10, 10);

        fill(255, 0, 0);
        ellipse(50, 225, 30, 30);

        fill(0, 255, 0);
        ellipse(75, 225, 10, 30);

        fill(0, 0, 255);
        ellipse(100, 225, 30, 10);

        fill(0, 0, 255, 90);
        ellipse(135, 225, 30, 10);
        ellipse(135, 225, 10, 30);

        // you can change the color of the outline used for shapes by calling the
        // stroke() method. stroke() accepts the same arguments as the fill method()
        // you can also alter the stroke weight by using the strokeWeight() method,
        // which accepts an integer representing the weight of the line to be used
        fill(0);

        stroke(150);
        ellipse(25, 275, 10, 10);

        stroke(255, 0, 0);
        strokeWeight(2);
        ellipse(50, 275, 30, 30);

        stroke(0, 255, 0);
        strokeWeight(3);
        ellipse(75, 275, 10, 30);

        stroke(0, 0, 255);
        strokeWeight(4);
        ellipse(100, 275, 30, 10);

        stroke(0, 0, 255, 90);
        strokeWeight(5);
        ellipse(135, 275, 30, 10);
        ellipse(135, 275, 10, 30);

        // rectangles can be used to draw rectangular shapes to the screen.
        // syntax: rect( xPosition, yPosition, width, height)
        // rectangles draw from their top left point by default
        // you can change this by calling rectMode(CENTER);
        fill(255);
        stroke(0);
        strokeWeight(1);
        rectMode(CENTER);
        rect(225, 25, 10, 10);
        rect(250, 25, 30, 30);
        rect(275, 25, 10, 30);
        rect(300, 25, 30, 10);
        rect(335, 25, 30, 10);
        rect(335, 25, 10, 30);

        // lines can be used to connect points on the screen
        // syntax: line(x1,y1, x2,y2)
        // note that lines are affected by stroke() and strokeWeight()
        stroke(0);
        strokeWeight(3);
        line(450, 25, 500, 25);

        stroke(255, 0, 0);
        strokeWeight(5);
        line(450, 50, 500, 75);

        stroke(0, 0, 255);
        strokeWeight(1);
        line(450, 75, 500, 300);
    }

    // draw is an infinite loop - it keeps running about 60 times a second
    public void draw() {

    }
}