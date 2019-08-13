package array

import Array._
/**
  * @author shuaijunhe
  * @create 2019/8/13
  * @description
  */
object Test {

  def main(args: Array[String]) {
//    var myMatrix = ofDim[Int](3,3)
//
//    // 创建矩阵
//    for (i <- 0 to 2) {
//      for ( j <- 0 to 2) {
//        myMatrix(i)(j) = j;
//      }
//    }
//
//    // 打印二维阵列
//    for (i <- 0 to 2) {
//      for ( j <- 0 to 2) {
//        print(" " + myMatrix(i)(j));
//      }
//      println()
//    }
//
//    // 合并
//    var myList1 = Array(1.9, 2.9, 3.4, 3.5)
//    var myList2 = Array(8.9, 7.9, 0.4, 1.5)
//    var myList3 = Array.concat( myList1, myList2)
//    // 输出所有数组元素
//    for ( x <- myList3 ) {
//      print(x + " " )
//    }
//    println()
//
//
//    // 创建区间数组
//    var myArr1 = range(10, 20, 2)
//    var myArr2 = range(10, 20)
//    // 输出所有数组元素
//    for ( x <- myArr1 ) {
//      print(x + " " )
//    }
//    println()
//    for ( x <- myArr2 ) {
//      print( x + " ")
//    }
//    println()


//    var arr = Array.iterate(0, 3)(a => a+1)
//    var arr = Array.apply(1)
//    def f(x: Int) = x + 1
//    var arr = Array.fill(5)(f(1))

//    var arr = Array.tabulate(5)(s => s + 10)
//    for ( x <- arr ) {
//      print( x + " ")
//    }
//    println()

    var arr = Array.tabulate(5, 2)((x, y) => (x + 10, y + 20))
    println(arr)
    for ( x <- arr ) {
      for ( y <- x){
        print( y + " ")
      }
      println()
    }
    println()




  }

}
