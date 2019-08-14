package extractor

/**
  * @author
  * @create 2019/8/14
  * @description
  */
object ExtractorWithMatch {

  def main(args: Array[String]) {

    val x = ExtractorWithMatch(5)
    println(x)

    x match
    {
      // 这里自动调用unapply方法，提取参数num的值
      case ExtractorWithMatch(num) => println(x + " 是 " + num + " 的两倍！")
      //unapply 被调用
      case _ => println("无法计算")
    }

  }

  def apply(x: Int) = x*2
  def unapply(z: Int): Option[Int] = if (z % 2 == 0) Some( z / 2) else None
}
