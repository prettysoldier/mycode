package _trait

/**
  * @author
  * @create 2019/8/14
  * @description
  */
trait Equal {

  def isEqual(x: Any): Boolean

  def isNotEqual(x: Any): Boolean = !isEqual(x)

}


class Point(xc: Int, yc: Int) extends Equal {
  var x = xc
  var y = yc

  override def isEqual(obj: Any): Boolean = obj.isInstanceOf[Point] && obj.asInstanceOf[Point].x == x && obj.asInstanceOf[Point].y == y

}

object Test {
  def main(args: Array[String]): Unit = {
    val p1 = new Point(1, 2)
    val p2 = new Point(1, 2)
    val p3 = new Point(1, 3)
    val p4 = new Point(2, 3)

    println(p1.isEqual(p1))
    println(p1.isEqual(p2))
    println(p1.isEqual(p3))
    println(p1.isEqual(p4))
    println(p1.isEqual(1))
  }
}