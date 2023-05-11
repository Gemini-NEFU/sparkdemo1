package day2

import org.apache.spark.{SparkConf, SparkContext}

//案例2：输出学生平均成绩段，60以下“不及格”，60以上“及格”
object Example2 {
  def main(array: Array[String]): Unit = {
    var conf = new SparkConf().setAppName("myApp").setMaster("local")
    var sc = new SparkContext(conf)
    var file = "data/grade.txt"
    var logData = sc.textFile(file, 2).cache()


    var tuples = logData.map(x => (x.split(" ")(0), x.split(" ")(1).toInt))


    var pass = tuples.filter(x => x._2 >= 60).map(x => (x._1, x._2, "合格"))
    var unPass = tuples.filter(x => x._2 < 60).map(x => (x._1, x._2, "不合格"))

    //输出结果 并按照成绩降序排序
    var ans = pass.union(unPass).sortBy(x => x._2, false)
    ans.collect().foreach(println)
  }
}
