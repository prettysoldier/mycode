package algorithm.wenhan_homework.processing;

import processing.core.PApplet;

public class Proc3_Graphics_16_DynamicBallWithClasses extends PApplet {
  private Ball[] theBalls;

  public static void main(String[] args){
      PApplet.main("algorithm.wenhan_homework.processing.Proc3_Graphics_16_DynamicBallWithClasses");
  }
  
  public void settings() {
      size(500, 500);
  }
  
  public void setup() {
    // tell the Ball class about the size of the world
    Ball.width = 500;
    Ball.height = 500;

    // create an array of Ball objects
    this.theBalls = new Ball[50];

    // create the ball objects
    for (int i = 0; i < this.theBalls.length; i++) {
      this.theBalls[i] = new Ball(random(0, width), random(0, height));
    }
  }

  public void draw() {
    // erase the background
    background(0);
    
    // move all the balls and draw them to the screen
    for (int i = 0; i < this.theBalls.length; i++) {
      this.theBalls[i].move();

      ellipse(this.theBalls[i].x, this.theBalls[i].y, 15, 15);
    }
  }
}

// class ball
// (I just put it here so that you wouldn't have to open up a new file)
class Ball {
  // all balls should keep track of their x and y positions
  public float x;
  public float y;

  // all balls should have an x speed and y speed
  private float xSpeed;
  private float ySpeed;

  // the Ball class should keep track of the size of the world the balls live in
  public static int width;
  public static int height;

  // constructor
  public Ball(float x, float y) {
    
    // store my position
    this.x = x;
    this.y = y;

    // give me a random speed
    this.xSpeed = (float) (Math.random() * 6) - 3;
    this.ySpeed = (float) (Math.random() * 6) - 3;
  }

  // move a ball
  public void move() {
    this.x += this.xSpeed;
    this.y += this.ySpeed;

    // bounce!
    if (this.x > width) {
      this.x = width;
      this.xSpeed *= -1;
    }
    if (this.x < 0) {
      this.x = 0;
      this.xSpeed *= -1;
    }
    if (this.y > height) {
      this.y = height;
      this.ySpeed *= -1;
    }
    if (this.y < 0) {
      this.y = 0;
      this.ySpeed *= -1;
    }
  }

}
