package function

/**
  * @author shuaijunhe
  * @create 2019/8/13
  * @description
  */
abstract
class MethodAndFunction {

  // 这是函数
  val f = (x: Int) => x + 3
  // 这是方法
  def m(x: Int) = x + 3

  // 抽象方法
  def method(): Int


}
