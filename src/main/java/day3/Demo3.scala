package day3

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object Demo3 {
  def main(array: Array[String]): Unit ={
    var conf=new SparkConf().setAppName("myApp").setMaster("local")
    var sc=new SparkContext(conf)

    var a1=Array[Long](3,4,5,6,7)
    var sumAcc=sc.longAccumulator(name = "test")
    a1.foreach(sumAcc.add)
    println(sumAcc.value)
  }
}

