package function

/**
  * @author shuaijunhe
  * @create 2019/8/13
  * @description
  */
object CallByName {

  def main(args: Array[String]) {
    delayed(time());
  }

  def time() = {
    println("获取时间，单位为纳秒")
    System.nanoTime
  }
  def delayed( t: => Long ) = {
    println("在 delayed 方法内")
    println("参数： " + t)
    println("参数： " + t)
    t
    t
  }
}
