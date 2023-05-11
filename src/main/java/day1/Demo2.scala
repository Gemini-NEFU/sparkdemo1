package day1

import org.apache.spark.{SparkConf, SparkContext}

object Demo2 {
  def main(args: Array[String]): Unit = {
    val logFile = "data/words.txt"
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    println("loglog;" + logData)
    val num = logData.flatMap(x => x.split(" ")).filter(_.contains("a")).count()
    println("Words with a : %s".format(num))
    sc.stop()
  }
}
