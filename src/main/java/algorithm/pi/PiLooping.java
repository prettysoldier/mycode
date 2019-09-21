package algorithm.pi;

/**
 * π=4*(1-1/3+1/5-1/7+1/9-1/11+....)
 */
public class PiLooping {

  public static void main(String[] args) {
		
    // Declare and initialize piAppx
    double piAppx = 1.0 - 1.0 / 3 + 1.0 / 5;
		
    // What is the max value for i
    // Use 407 in the first run, then use 100000000
    int MAX_I_VALUE = 407;
    double sign = -1;

    for (int i = 7; i <= MAX_I_VALUE + 2; i += 2) {

      piAppx = piAppx + sign * (1 / (double)i);
      sign = - sign;
    }
		
    double finalPi = 4.0 * piAppx;

    // Output the approximation you computed for Pi
    System.out.println("Pi approximation =\t" + finalPi);
		
    // Output the Math library value of Pi
    System.out.println("Math.PI =\t\t" + Math.PI);
		
    // Output the difference between Math.PI and the approximation 
    // for Pi that you computed
    System.out.println("Math.PI - piAppx =\t" + (Math.PI - finalPi));
  }
}
