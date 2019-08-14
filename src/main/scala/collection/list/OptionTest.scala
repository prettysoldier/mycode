package collection.list

/**
  * @author shuaijunhe
  * @create 2019/8/14
  * @description
  */
object OptionTest {

  def main(args: Array[String]): Unit = {
    val map = Map("key1" -> "value1")
    val value:Option[String] = map.get("key1")
    println(value)
    println(value.get)
    println(map.get("k"))
//    println(map.get("k").get)

    //你也可以通过模式匹配来输出匹配值。实例如下：

    val sites = Map("runoob" -> "www.runoob.com", "google" -> "www.google.com")

    println("show(sites.get( \"runoob\")) : " + show(sites.get( "runoob")) )
    println("show(sites.get( \"baidu\")) : " + show(sites.get( "baidu")) )
    println(sites.get( "runoob").getOrElse("lalala "))
    println(sites.get( "baidu").getOrElse("hahaha"))

  }

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

}
