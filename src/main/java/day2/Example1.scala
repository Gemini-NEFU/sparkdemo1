package day2

import org.apache.spark.{SparkConf, SparkContext}

//案例1：统计文档中单词出现的个数
object Example1 {
  def main(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var file = "data/wordscount.txt"
    var logData = sc.textFile(file, 2).cache()

    var words = logData.flatMap(x => x.toLowerCase()
      .replaceAll("\\pP", "")
      .split(" "))
    var count = words.map(x => (x, 1))
    //统计个数
    var new_count = count.reduceByKey((x, y) => x + y)
    //按词频降序排序
    new_count.sortBy(x => x._2, false).collect().foreach(println)

  }
}
