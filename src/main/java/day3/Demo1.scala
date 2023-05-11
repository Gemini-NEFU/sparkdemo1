package day3

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object Demo1 {
  def main(array: Array[String]): Unit ={
    var conf=new SparkConf().setAppName("myApp").setMaster("local")
    var sc=new SparkContext(conf)

    var rdd1=sc.parallelize(Array(
      ("2023-05-09","user1"),("2023-05-09","user2"),
      ("2023-05-09","user3"),("2023-05-08","user2"),
      ("2023-05-08","user2"),("2023-05-07","user2"),
      ("2023-05-07","user1"),("2023-05-07","user2")
    ))

    var rdd2=rdd1.map(kv=>(kv._2,kv._1))
    var rdd3=rdd2.groupByKey()
    var rdd4=rdd3.map(kv=>(kv._2.min,1))
    rdd4.countByKey.foreach(println)
  }
}
