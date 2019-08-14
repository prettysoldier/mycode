package collection.list

import scala.collection.mutable.ListBuffer

/**
  * @author shuaijunhe
  * @create 2019/8/14
  * @description
  */
object ListTest {

  // 字符串列表
  val site: List[String] = List("Runoob", "Google", "Baidu")

  // 整型列表
  val nums: List[Int] = List(1, 2, 3, 4)

  // 空列表
  val empty: List[Nothing] = List()
  // 空列表
  val empty2: List[Nothing] = Nil

  // 二维列表
  val dim: List[List[Int]] =
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )

  // 字符串列表


  val site2 = "Runoob" :: ("Google" :: ("Baidu" :: Nil))
  val nums2 = 1 :: (2 :: (3 :: (4 :: Nil)))
  // 二维列表
  val dim2 = (1 :: (0 :: (0 :: Nil))) ::
    (0 :: (1 :: (0 :: Nil))) ::
    (0 :: (0 :: (1 :: Nil))) :: Nil

  def main(args: Array[String]): Unit = {
    println( "查看列表 site 是否为空 : " + site2.isEmpty )
    println( "查看 nums 是否为空 : " + empty2.isEmpty )

    for( x <- dim2) {
      println(x)
    }

    println( "第一网站是 : " + site2.head )
    println( "最后一个网站是 : " + site2.tail )

    var list1 = List(1, 2, 3)
    var list2 = List('a', 'b', 'c')
    println(list1 ::: list2)
    println(list1.:::(list2))

    // 通过给定的函数创建 5 个元素
    val squares = List.tabulate(6)(n => n * n)
    println( "一维 : " + squares  )
    // 创建二维列表
    val mul = List.tabulate( 4, 5 )( _ + _ )
    println( "多维 : " + mul  )

    println("new" +: list1)
    println(list1 :+ "new")
    println(list1.+:("new"))
    println(list1.:+("new"))
    //    println(site +: "new")

    println("asdf" :: site)
    println(site.::("new"))
    println(list1.apply(2))

    println(list1.getClass.getName)
//    val mutableList = scala.collection.mutable.L
    val mutableList = ListBuffer("a", "b")
    mutableList.insert(2, "c")
    println(mutableList)
  }


}
