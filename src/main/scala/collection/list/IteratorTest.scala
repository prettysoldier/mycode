package collection.list

/**
  * @author shuaijunhe
  * @create 2019/8/14
  * @description
  */
object IteratorTest {
  def main(args: Array[String]) {
    val it = Iterator("Baidu", "Google", "Runoob", "Taobao")

    while (it.hasNext){
      println(it.next())
    }

    val it1 = Iterator(1, 2, 3)
    println("it1.size 的值: " + it1.size )
    println("it1.length 的值: " + it1.length )
//    println("最大元素是：" + it1.max )
    //    println("最小元素是：" + it1.min )
    val it2 = Iterator(1, 2, 3)
    val merge = it ++ it2
    while (merge.hasNext){
      println(merge.next())
    }

  }
}
