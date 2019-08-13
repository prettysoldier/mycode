package closure

/**
  * @author shuaijunhe
  * @create 2019/8/13
  * @description
  */
object Test {

  // 使用和定义不在一个地方的变量，称为自由变量
  var factor = 3
  // 这样定义的函数变量 multiplier 成为一个"闭包"，因为它引用到函数外面定义的变量，定义这个函数的过程是将这个自由变量捕获而构成一个封闭的函数。
  val multiplier = (i: Int) => i * factor

  var x = 3
  val multiplier2 = (i: Int) => x = x + i

  // 模拟python的闭包
  def outer(step: Int) : ()=>Int = {

    var freeVariable = 1

    def inner() = {

      freeVariable += step
      println(freeVariable)
      freeVariable
    }
    inner
  }



  def main(args: Array[String]) {
    println( "muliplier(1) value = " +  multiplier(1) )
    println( "muliplier(2) value = " +  multiplier(2) )

    multiplier2(1)
    multiplier2(1)
    multiplier2(1)
    println(x)

    println("----下面模拟python的闭包")
    val f = outer(3)
    f()
    f()
    f()
  }

}
