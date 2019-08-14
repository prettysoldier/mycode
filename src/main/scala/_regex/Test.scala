package _regex

import scala.util.matching.Regex


object Test {
  def main(args: Array[String]) {
    // 使用 String 类的 r() 方法构造了一个Regex对象。
    val pattern = "Scala".r
    val str = "Scala is scalable and cool"

    println(pattern findFirstIn str)

    val p1 = new Regex("(S|s)cala")
    println((p1 findAllIn str).mkString(","))

    // 不会改变之前的变量str
    println((p1 replaceFirstIn(str, "Java")))
    println(str)

    val s = "我 is a good man"
    var p2 = new Regex("\\w")
    // \w 匹配数字、字母和下划线
    println(p2 findFirstIn s)
    var p3 = new Regex("\\G我")
    println(p3 findFirstIn s)
  }
}