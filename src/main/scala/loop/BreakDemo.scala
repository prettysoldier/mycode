package loop

import scala.util.control.Breaks

/**
  * @author
  * @create 2019/8/13
  * @description
  */
object BreakDemo {

  val loop = new Breaks

  def main(args: Array[String]): Unit = {

    var i = 0
    loop.breakable(while(true){
      println(i)
      if(i > 5){
        loop.break()
      }
      i += 1
    })
  }


}
