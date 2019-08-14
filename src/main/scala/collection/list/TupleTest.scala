package collection.list

/**
  * @author shuaijunhe
  * @create 2019/8/14
  * @description
  */
object TupleTest {

  def main(args: Array[String]): Unit = {

    val t = (4,3,2,1)

    t.productIterator.foreach{ i =>println("Value = " + i )}
    t.productIterator.foreach( i =>println("Value = " + i ))

    val t1 = new Tuple2("www.google.com", "www.baidu.com")
    println("交换后的元组: " + t1.swap )
  }
}
