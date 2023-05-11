package day4

import org.apache.spark.sql.SparkSession

object TestSession {
  def main(array: Array[String]): Unit ={
    val spark =SparkSession.builder()
      .appName("SparkSQLDataSource")
      .config("spark.sql.parquet.mergeSchma",true)
      .master("local[*]")
      .getOrCreate()
    val peopleDFCsv=spark.read.format("csv").load("hdfs://hadoop101:8020/landuse.csv")
    val jdbcDF=spark.read.format("jdbc")
      .option("url","jdbc:mysql://localhost:3306/spark")
      .option("driver","com.mysql.cj.jdbc.Driver")
      .option("dbtable","emp")
      .option("user","root")
      .option("password","3W9hyq9wl9com")
      .load()
    jdbcDF.collect().foreach(println)
  }
}
