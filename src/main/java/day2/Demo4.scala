package day2

import org.apache.spark.{SparkConf, SparkContext}

//rdd 算子
object Demo4 {
  def main(array: Array[String]): Unit = {
    //    demo_map(Array{"test"});
    //    demo_filter(Array{"test"});
    //    demo_flatmap(Array{"test"});
    //    demo_reduceByKey(Array{"test"});
    //    demo_union(Array{"test"});
    //    demo_sortBy(Array{"test"});
    //    demo_join(Array{"test"})
    //    demo_collect(Array{"test"})
    //    demo_reduce(Array{"test"})
    //    demo_count(Array{"test"})
    //    demo_take(Array{"test"})
    //    demo_countByKey(Array{"test"})
  }

  //1.转化算子
  def demo_map(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var rdd1 = sc.parallelize(List(3, 4, 5, 6, 7))

    var rdd2 = rdd1.map(x => x * x)
    var rdd3 = rdd1.map(x => x + 1)
    var rdd4 = rdd1.map(_ + 1)
    rdd2.collect().foreach(println)
    rdd3.collect().foreach(println)
    rdd4.collect().foreach(println)
  }

  def demo_filter(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var rdd1 = sc.parallelize(List(3, 4, 5, 6, 7, 8))

    var rdd2 = rdd1.filter(x => x > 5)
    var rdd3 = rdd1.filter(_ > 5)

    rdd2.collect().foreach(println)
    rdd3.collect().foreach(println)
  }

  def demo_flatmap(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var rdd1 = sc.parallelize(List("hello word welcome", "hello stu"))

    var rdd2 = rdd1.flatMap(x => x.split(" "))

    var rdd3 = sc.parallelize(List(1, 2, 3, 4, 5))
    var rdd4 = rdd3.flatMap(1 to _)
    rdd2.collect().foreach(println)
    rdd4.collect().foreach(println)
  }

  def demo_reduceByKey(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var arr1 = List(("zs", 23), ("ls", 34), ("zs", 45), ("ls", 55))
    var rdd1 = sc.parallelize(arr1)

    var rdd2 = rdd1.reduceByKey((x, y) => x + y)

    var rdd3 = rdd1.reduceByKey(_ + _)
    rdd2.collect().foreach(println)
    rdd3.collect().foreach(println)
  }

  def demo_union(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var rdd1 = sc.parallelize(List(3, 4, 5))
    var rdd2 = sc.parallelize(List(7, 8, 9))

    var rdd3 = rdd1.union(rdd2)

    var rdd4 = rdd2.union(rdd1)

    rdd3.collect().foreach(println)
    rdd4.collect().foreach(println)
    //观察区别
  }

  def demo_sortBy(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var arr1 = List(("zs", 34), ("ls", 44), ("ww", 45), ("lll", 66))
    var rdd1 = sc.parallelize(arr1)

    //观察区别
    var rdd2 = rdd1.sortBy(x => x._2, false)
    rdd2.collect().foreach(println)
    var rdd3 = rdd1.sortBy(x => x._2, true)
    rdd3.collect().foreach(println)
  }

  def demo_join(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var a1 = Array(("A", "a1"), ("B", "b1"), ("C", "c1"), ("D", "d1"), ("E", "e1"))
    var a2 = Array(("A", "A1"), ("B", "B1"), ("C", "C1"), ("C", "C2"), ("C", "C3"), ("E", "E1"))

    var rdd1 = sc.parallelize(a1)
    var rdd2 = sc.parallelize(a2)

    var rdd3 = rdd1.join(rdd2)
    var rdd4 = rdd2.join(rdd1)

    rdd3.collect().foreach(println)
    rdd4.collect().foreach(println)
  }

  //2.行动算子
  def demo_collect(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var rdd1 = sc.parallelize(List(1, 2, 3, 4, 5, 6))
    println(rdd1.collect())
    println("here")
    rdd1.collect().foreach(println)
    println("here")
  }

  def demo_reduce(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var rdd1 = sc.parallelize(List(2, 3, 4, 5, 6, 7, 8, 9))

    var result1 = rdd1.reduce((x, y) => x + y)
    var result2 = rdd1.reduce(_ + _)
    println((result1))
    println((result2))

  }

  def demo_count(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var rdd1 = sc.parallelize(List(2, 3, 4, 5, 6, 7, 8, 9))

    var num = rdd1.count()
    print("长度:" + num + "!!!!\n")
  }

  def demo_take(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var rdd1 = sc.parallelize(List(2, 3, 4, 5, 6, 7, 8, 9))

    var nums = rdd1.take(3)
    nums.foreach(println)
  }

  def demo_countByKey(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var arr1 = List(("zs", 34), ("ls", 44), ("ww", 45), ("lll", 66))
    var rdd1 = sc.parallelize(arr1)

    var result = rdd1.countByKey()
    for ((k, v) <- result) {
      println(k + " " + v)
    }
  }
}
