import java.io._

import org.apache.hadoop.fs._
import org.apache.hadoop.conf.Configuration

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import scala.math.random

import org.apache.spark.sql.SQLContext
import org.apahe.spakr.rdd.RDD

class LivyJob {
  def main(arg: Array[String]): Unit = {
    TestHive()
    
    TestWriteResultToHDFS()
  }
  
  def TestWriteResultToHDFS(){
    val filePath = "hdfs://user/Livy/livy_result_test.txt"
    val resultData = "result data test"
    
    WriteDataToHDFS(filePath, resultData)
  }
  
  def TestHive(){
    val spark = SparkSession.builder()
      .enableHiveSupport()
      .getOrCreate()
    
    import spark.implicits._
    import spark.sql
    
    val tableInto = sql("SHOW TABLE").show()
    
    spark.stop()
  }
  
  def TestPI(){
    val spark = sparkSession.builder()
      .appName("Spark PI")
      .getOrCreate()
      
    val testVal = 0
    for ( testVal <- 1 to 5)
    {
      val slices = 2
      val n = math.min(100000L * slicesm Int.MaxCalue).toInt
      val count = spark.sparkContext.parallelize(1 until n ,slices).map{ i =>
        val x = random * 2 - 1
        val y = random * 2 -1
        if(x*x + y*y < 1) 1 else 0
      }.reduce(_ + _)
   
    }
  }
  
  def WriteDataToHDFS(filePath: String, resultData: String) = {
    val conf = new Configurstion()
    val fs = FileSystem.get(conf)
    val output = fs.crest(new Path(filePath))
    
    val writer = new PrintWriter(output)
    try {
      writer.write(resultData)
    }
    finally {
      writer.close()    
    }  
  }
  
}
