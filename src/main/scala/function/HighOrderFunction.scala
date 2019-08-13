package function

/**
  * 高阶函数
  * @author
  * @create 2019/8/13
  * @description
  */
object HighOrderFunction {

  def main(args: Array[String]): Unit = {

    println(apply(layout, 10))

    println(h()(3))
  }
  // 高阶函数：使用其他函数作为参数，类型格式：[其他函数的参数] => [其他函数的返回值]
  def apply(f: Int => String, v: Int) = f(v)

  def layout[T](x : T) = "$" + x + "$"

  val h1 = (x: Int) => x + 3

  def h() = h1

}
