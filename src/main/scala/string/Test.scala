package string

/**
  * @author shuaijunhe
  * @create 2019/8/13
  * @description
  */
object Test {

  val greeting: String = "Hello,World!"

  def main(args: Array[String]) {
    val buf = new StringBuilder;
    buf += 'a'
    buf ++= "bcdef"
    println(buf charAt 1)
    println( "buf is : " + buf.toString )
  }
}
