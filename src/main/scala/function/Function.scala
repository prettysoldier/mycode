package function

/**
  * @author shuaijunhe
  * @create 2019/8/13
  * @description
  */
object Function {

  // 匿名函数
  var inc = (x:Int) => x+1
  // 上述定义的匿名函数，其实是下面这种写法的简写：
  def add2 = new Function1[Int,Int]{
    def apply(x:Int):Int = x+1;
  }

  inc = (a: Int) => a + 1


  def main(args: Array[String]): Unit = {
    println(inc(1))
  }
}
