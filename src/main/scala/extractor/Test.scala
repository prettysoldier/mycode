package extractor

import scala.io.StdIn

/**
  * @author shuaijunhe
  * @create 2019/8/14
  * @description
  */
object Test {
  def main(args: Array[String]) {

    println ("Apply 方法 : " + apply("Zara", "gmail.com"));
    println ("Unapply 方法 : " + unapply("Zara@gmail.com"));
    println ("Unapply 方法 : " + unapply("Zara Ali"));

    // 在object中定义apply方法，就可以不使用new操作就可以创建对象
    println(Test("asd", "zxcv"))

  }
  // 注入方法 (可选)
  def apply(user: String, domain: String) = {
    user +"@"+ domain
  }

  // 提取方法（必选）
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2){
      Some(parts(0), parts(1))
    }else{
      None
    }
  }
}