package loop

/**
  * @author shuaijunhe
  * @create 2019/8/13
  * @description
  */
object ForLoop {

  def main(args: Array[String]) {
//    var x = 0;
//    // for 循环
//    for( x <- 1 to 10){
//      println( "Value of a: " + x );
//    }
    // for 循环
//    for( a <- 1 until 10){
//      println( "Value of a: " + a );
//    }
    var a = 0;
    var b = 0;
    // for 循环
    for( a <- 1 to 3; b <- 1 to 4){
      println( "Value of a: " + a );
      println( "Value of b: " + b );
    }

//    val numList = List(1,2,3,4,5,6);
//    for( a <- numList ){
//      println( "Value of a: " + a );
//    }

//    val numList = List(1,2,3,4,5,6,7,8,9,10);
//    for( a <- numList if a != 3; if a < 8 ){
//      println( "Value of a: " + a );
//    }

//    val numList = List(1,2,3,4,5,6,7,8,9,10);
//    var retVal = for{ a <- numList
//                      if a != 3; if a < 8
//    }yield a
//    for( a <- retVal){
//      println( "Value of a: " + a );
//    }
  }
}
