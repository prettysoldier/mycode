package algorithm.wenhan_homework.processing.app;
import java.math.BigInteger;

public class PrimitiveWrappers {
  // Java provides class wrappers for all primitive data types.
  // Why? Encapsulation! 
  // So why do we have primitives? performance
  
  public static void main(String[] args) {
    // ex. Integer 'wraps' an int
    Integer x = new Integer(1);
    Integer y = 1; // This works too, don't need 'new' in newer versions of Java

    // Note that every wrapper type has a method 
    // to convert to a primitive type
    int primitive = x.intValue();
    double d = x.doubleValue();
    
    // Lots of convenient methods 
    String s = x.toString();
    System.out.println(s);
    
    // Some static methods as well...
    Integer max = Integer.max(x, 100);
    
    // What does this line do?
    max = Integer.parseInt(max.toString());
    
    // As well as some useful constants..
    System.out.println("Max int = " + Integer.MAX_VALUE); // max int before overflow
    System.out.println("Num bytes for int " + Integer.BYTES); // how many bytes
    
    // Java also does what is called 'auto-boxing'
    // where it will implicitly convert between
    // the primitive type and its class where it can.
    // For example....
    Character someChar = 'z';
    System.out.println("Boxed char:   " + someChar);
    char j = someChar;  // auto-boxing happens here - Character value assigned to char variable
    System.out.println("Unboxed char: " + j);
    
    // There is also BigInteger, which will never overflow.
    BigInteger intMax = BigInteger.valueOf(Integer.MAX_VALUE);
    BigInteger b = intMax.add(BigInteger.ONE);
    System.out.println("Int max + 1 = " + b.toString()); 
  }
}




