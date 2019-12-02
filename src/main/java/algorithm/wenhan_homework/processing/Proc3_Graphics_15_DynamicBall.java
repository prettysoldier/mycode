package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_15_DynamicBall extends PApplet {
    // keep track of ball's x and y position
    float x = 300;
    float y = 250;
    float xSpeed = 15;
    float ySpeed = 10;

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_15_DynamicBall");
    }

    // setup runs one time at the beginning of your program
    public void settings() {
        size(500, 500);
        smooth();
    }

    // draw is an infinite loop it keeps running about 60 times a second
    public void draw() {
        // erase the background
        background(0);

        // move the ball
        moveBall();

        // draw the ball
        drawBall();
    }

    public void moveBall() {
        // move the ball based on its speed
        this.x += this.xSpeed;

        if (this.x > this.width) {
            this.x = this.width - 10;
            this.xSpeed = -10;
        }

        if (this.x < 0) {
            this.x = 10;
            this.xSpeed = 10;
        }

        // move the ball based on its speed
        this.y += this.ySpeed;

        if (this.y > this.width) {
            this.y = this.width - 5;
            this.ySpeed = -10;
        }

        if (this.y < 0) {
            this.y = 10;
            this.ySpeed = 10;
        }

    }

    public void drawBall() {
        // draw the ball at its current location
        ellipse(this.x, this.y, 25, 25);
    }

}
