package class_object

/**
  * 伴生类 与 伴生对象
  * @author
  * @create 2019/8/14
  * @description
  */
class Person private (val id: String){

  println("创建" + this)
  var name = "张三"
  var age = 10

  override def toString: String = "身份：" + id


}
/*
伴生对象
 */
object Person {


  private val persons: Map[String, Person] = Map("1" -> new Person("1"), "2" -> new Person("2"))

  def getPerson(id: String) = {
    if (persons.contains(id) ){
      persons(id)
    }else{
      null
    }
  }

  def main(args: Array[String]): Unit = {

//    println(Person("1"))
    println(Person getPerson  "1")
  }
}