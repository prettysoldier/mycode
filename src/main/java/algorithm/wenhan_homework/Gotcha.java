package algorithm.wenhan_homework;

import processing.core.PApplet;

// import processing.core.PImage;   // For background image


public class Gotcha extends PApplet {

    // Keep track of current score
    int score = 0;

    // Canvas size
    final int canvasWidth  = 500;
    final int canvasHeight = 500;

    // Declare disk
    Disk d1;

    public static void main(String[] args){
        PApplet.main("algorithm.wenhan_homework.Gotcha");
    }
    
    public void settings() {
        size(canvasWidth, canvasHeight);
        smooth();
    }

    // setup() runs one time at the beginning of your program
    public void setup() {
        // Create a disk
        d1 = new Disk(10);
        this.draw();
    }

    // draw() is called repeatedly in an infinite loop.
    // By default, it is called about 60 times per second.
    public void draw() {
        // Erase the background, if you don't, the previous shape(s) will 
        // still be displayed
        eraseBackground();
        // Move the shape, i.e. calculate the next x and y position
        // where the shape will be drawn.
        d1.calcCoords();
        // Draw the shape
        d1.drawShape();
        // Display point value on the shape
        d1.displayPointValue();
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
    public void mousePressed() {
        // Draw a circle wherever the mouse is
        int mouseWidth  = 20;
        int mouseHeight = 20;
        fill(0, 255, 0);
        ellipse(mouseX, mouseY, mouseWidth, mouseHeight);

        // Check whether the click occurred within range of the shape
        if ((this.mouseX >= d1.x - d1.targetRange) && (this.mouseX <= d1.x + d1.targetRange) &&
            (this.mouseY >= d1.y - d1.targetRange) && (this.mouseY <= d1.y + d1.targetRange)) {
            score = score + d1.pointValue;
            System.out.println("DBG:  HIT!");
        }
    }

    // Create a Disk class that you will use to create one or more disks with each
    // disk having a color, speed, position, etc.
    class Disk {
        // Size of disk
        final int shapeWidth  = 60;
        final int shapeHeight = 80;

        // Point value of disk
        int pointValue;

        // Position of disk - keep track of x and y position of disk
        //TODO float x = ...
        //TODO float y = ...
        float x = 30;
        float y = 250;

        // Horizontal speed of disk
        //TODO float xSpeed = ...
        float xSpeed = 2;

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

        public void calcCoords() {
            // Compute the x position where the shape will be drawn
            this.x += this.xSpeed;

            // If the x position is off right side of the canvas, reverse direction of 
            // movement:
            if(this.x >= canvasWidth - shapeWidth / 2 ){
                // Log a debug message:
                System.out.println("DBG:  <---  Change direction, go left because x = " + this.x);
                // Recalculate:
                this.xSpeed = (-1) * this.xSpeed;
            }
            // If the x position is off left side of the canvas, reverse direction of
            // movement:
            if (this.x <= shapeWidth / 2) {
                // Log a debug message:
                System.out.println("DBG:      ---> Change direction, go right because x = " + this.x + "\n");
                // Recalculate:
                this.xSpeed = (-1) * this.xSpeed;
            }
        }

        public void drawShape() {
//            background(255, 255, 255);
            // Select color, then draw the shape at computed x, y location
            fill(0, 0, 255);
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
