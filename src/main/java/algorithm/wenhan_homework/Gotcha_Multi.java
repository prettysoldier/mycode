package algorithm.wenhan_homework;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

// import processing.core.PImage;   // For background image


public class Gotcha_Multi extends PApplet {

    // Keep track of current score
    int score = 0;

    // Canvas size
    final int canvasWidth  = 500;
    final int canvasHeight = 500;

    // Declare disk
    List<Disk> diskList;

    long start = System.currentTimeMillis();
    boolean stop = false;

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.Gotcha_Multi");
    }


    @Override
    public void settings() {
        size(canvasWidth, canvasHeight);
        smooth();

    }

    // setup() runs one time at the beginning of your program
    @Override
    public void setup() {
        // Create a disk
        diskList = new ArrayList<>(3);
        diskList.add(new Disk(10, 2, 0.2f, 0, 0, 255, 200, 60));
        diskList.add(new Disk(20, 1, 2, 255, 0, 0, 100, 40));
        diskList.add(new Disk(30, 2, 1, 0, 255, 0, 50, 70));
        this.draw();
    }

    // draw() is called repeatedly in an infinite loop.
    // By default, it is called about 60 times per second.
    @Override
    public void draw() {
        if(stop){
            stop();
        }
        long second = (System.currentTimeMillis() - start) / 1000;
        if(second > 20){

            eraseBackground();
            fill(0, 0, 0);
            textAlign(CENTER, CENTER);
            text("Final Score: " + this.score, canvasWidth / 2, canvasHeight / 2);
            System.out.println("Time is up!");
            this.stop = true;
            return;
        }
        // Erase the background, if you don't, the previous shape(s) will 
        // still be displayed
        eraseBackground();
        // Move the shape, i.e. calculate the next x and y position
        // where the shape will be drawn.
        for(Disk d : diskList){
            d.drawShape();
            d.calcCoords();
            d.displayPointValue();
        }
        // Display point value on the shape
        // Display player's score
        fill(255, 0, 0);
        textAlign(LEFT, TOP);
        text("Score: " + this.score, 10, 10);
    }

    public void eraseBackground() {      
        // White background:
        background(255);
        
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
           | Use the following lines to display an image in the background. |
           | You will need to bring a .png file into your package. The path |
           | given below should be replaced by your path and png file name. |
           |                                                                |
           |   PImage bg = loadImage("hw_pkg/moon_walk.png");               |
           |   background(bg);                                              |
           ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    }

    // mousePressed() is a PApplet method that you will override.
    // This method is called from PApplet one time when the mouse is pressed.
    @Override
    public void mousePressed() {
        // Draw a circle wherever the mouse is
        int mouseWidth  = 20;
        int mouseHeight = 20;
        fill(0, 255, 0);
        ellipse(mouseX, mouseY, mouseWidth, mouseHeight);

        // Check whether the click occurred within range of the shape
        for(Disk d1 : diskList){
            if ((this.mouseX >= d1.x - d1.targetRange) && (this.mouseX <= d1.x + d1.targetRange) &&
                (this.mouseY >= d1.y - d1.targetRange) && (this.mouseY <= d1.y + d1.targetRange)) {
                score = score + d1.pointValue;
                System.out.println("DBG:  HIT!");
            }
        }
    }

    // Create a Disk class that you will use to create one or more disks with each
    // disk having a color, speed, position, etc.
    class Disk {
        // Size of disk
        int shapeWidth;
        int shapeHeight;

        // Point value of disk
        int pointValue;

        // Position of disk - keep track of x and y position of disk
        float x = 250;
        float y = 250;

        // Horizontal speed of disk
        float xSpeed = 2;
        float ySpeed = 2;
        // It's hard to click a precise pixel on a disk, to make it easier we can
        // allow the user to click somewhere on the disk.
        // You might want to make the scoring space be a rectangle fitted tightly
        // to the disk - it's easier than calculating a rounded boundary.
        int targetRange = 30;

        float red;
        float green;
        float blue;

        // The constructor could be extended to accept other disk characteristics
        Disk(float red, float green, float blue) {
            this.red   = red;
            this.green = green;
            this.blue  = blue;
        }

        Disk() {
            this(0, 0, 255);
        }
        Disk(int pointValue) {
            this(0, 0, 255);
            this.pointValue = pointValue;
        }

        Disk (int pointValue, float red, float green, float blue) {
            this(red, green, blue);
            this.pointValue = pointValue;
        }

        private Disk (int pointValue, float xSpeed, float ySpeed, float red,
                      float green, float blue,int shapeWidth, int shapeHeight) {
            this(red, green, blue);
            this.pointValue = pointValue;
            this.xSpeed = xSpeed;
            this.ySpeed = ySpeed;
            this.shapeWidth = shapeWidth;
            this.shapeHeight = shapeHeight;
        }

        public void calcCoords() {
            // Compute the x position where the shape will be drawn
            this.x += this.xSpeed;
            this.y += this.ySpeed;
            // If the x position is off right side of the canvas, reverse direction of 
            // movement:
            if(this.x >= canvasWidth - this.shapeWidth / 2 ){
                // Log a debug message:
                System.out.println("DBG:  <---  Change direction, go left because x = " + this.x);
                // Recalculate:
                this.xSpeed = (-1) * this.xSpeed;
            }
            // If the x position is off left side of the canvas, reverse direction of
            // movement:
            if (this.x <= this.shapeWidth / 2) {
                // Log a debug message:
                System.out.println("DBG:      ---> Change direction, go right because x = " + this.x + "\n");
                // Recalculate:
                this.xSpeed = (-1) * this.xSpeed;
            }
            if(this.y >= canvasHeight - this.shapeHeight / 2 ){
                // Log a debug message:
                System.out.println("DBG:  <---  Change direction, go down because y = " + this.y);
                // Recalculate:
                this.ySpeed = (-1) * this.ySpeed;
            }
            if (this.y <= this.shapeHeight / 2) {
                // Log a debug message:
                System.out.println("DBG:      ---> Change direction, go right because x = " + this.x + "\n");
                // Recalculate:
                this.ySpeed = (-1) * this.ySpeed;
            }
        }

        public void drawShape() {
            // Select color, then draw the shape at computed x, y location
            fill(this.red, this.green, this.blue);
            ellipse(x, y, shapeWidth, shapeHeight);
        }

        public void displayPointValue() {
            // Draw the text at computed x, y location
            //TODO this.pointValue = ...
            textSize(20);
            fill(255, 255, 255);
            textAlign(CENTER, CENTER);
            text(this.pointValue, x, y);
        }
    }
}
