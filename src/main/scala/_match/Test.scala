package _match

/**
  * @author shuaijunhe
  * @create 2019/8/14
  * @description
  */
object Test {

  def main(args: Array[String]) {

    val alice = Person("Alice", 25)
    val alice2 = new Person("Alice", 26)
    val bob = new Person("Bob", 32)
    val charlie = new Person("Charlie", 32)

    for (person <- List(alice, alice2, bob, charlie)) {
      person match {
        case Person("Alice", 25) => println("Hi Alice!")
        case Person("Bob", 32) => println("Hi Bob!")
        case Person(name, age) =>
          println("Age: " + age + " year, name: " + name + "?")
      }
    }


    def matchTest(x: Any): String = x match {
      case 1 => "one"
      case 2 => "two"
      case _: Int => "是整数" // 判断是否是Int类型
      case _ => "many" // 通配符
    }
    println(matchTest(3))
  }
  // 样例类
  case class Person(name: String, age: Int)

}
