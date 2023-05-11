package day2

import org.apache.spark.{SparkConf, SparkContext}

//四种创建rdd方式
object Demo3 {
  def main(array: Array[String]): Unit = {

    //创建RDD
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)

    var a1 = Array(2, 3, 4, 5, 6)
    var rdd = sc.parallelize(a1)
    print("打印内容rdd:" + rdd)

    var rdd1 = sc.makeRDD(a1)
    print("打印内容rdd1:" + rdd1)

    var rdd2 = sc.textFile("data/words.txt")
    print("打印内容rdd2:" + rdd2)

    print(rdd2.collect)
    rdd2.collect().foreach(println)


    var rdd3 = sc.textFile("hdfs://10.90.6.105:8020/word.txt")
    print(rdd3.collect)
    rdd3.collect().foreach(println)
    print("here!")
  }
}
