package access

/**
  * Scala 中的 private 限定符，比 Java 更严格，在嵌套类情况下，外层类甚至不能访问被嵌套类的私有成员。
  * 在 scala 中，对保护（Protected）成员的访问比 java 更严格一些。因为它只允许保护成员在定义了该成员的的类的子类中被访问。
  *
  * @author
  * @create 2019/8/13
  * @description
  */
class Access {

  class Inner{
    private def f(){println("f")}

    def g(): Unit ={
      println("g")
    }
    class InnerIn{
      f()
    }

    f()
  }


  new Inner().g()
}
