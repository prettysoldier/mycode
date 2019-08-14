package collection.list
/**
  * @author shuaijunhe
  * @create 2019/8/14
  * @description
  */
object SetTest {


  def main(args: Array[String]): Unit = {

    val set = Set(1, 2, 3)
    println(set.getClass.getName)
    println(set.exists(_ % 2 == 0))

    val mutableSet = scala.collection.mutable.Set(1, 2, 3)
    println(mutableSet.getClass.getName)
    mutableSet.add(4)
    println(mutableSet)

    val another = mutableSet.toSet
    println(another.getClass.getName) // scala.collection.immutable.Set
  }
}
