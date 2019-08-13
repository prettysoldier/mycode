package function

/**
  * 函数柯里化
  * @author shuaijunhe
  * @create 2019/8/13
  * @description
  */
object FunctionCurrying {

  def add(x:Int,y:Int)= x + y

  def add2(x:Int)(y:Int) = x + y

  def add3(x:Int)= (y:Int) => x + y

  def ext(x: Int)(y: Int)(z: Int) = x + y +z

  def main(args: Array[String]): Unit = {
    println(add(1, 2))
    println(add2(1)(2))
    println(add3(1)(2))
    println(ext(1)(2)(3))
  }

}
