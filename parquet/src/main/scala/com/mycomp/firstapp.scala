/* WordCount.scala */

import org.apache.spark.SparkContext

import org.apache.spark.SparkContext._

import org.apache.spark.SparkConf

import java.util.Date

import org.apache.spark.sql.SQLContext

import java.util.Date;

object Parquet {

  def main(args: Array[String]) {

    val logFile = "/home/cloudera/kirupa/spark/sample.txt" // Should be some file on your hdfs

    val conf = new SparkConf().setAppName("Parquet Read Write").setMaster("local[2]")

    val sc = new SparkContext(conf)
    
    val dt = new Date().getDate+"_"+new Date().getTime()
    
    
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    
    import sqlContext.implicits._
    
    val data:List[Int] = List(1,2,3,4,5)                     // create List of Integers
    
    val dataRDD = sc.parallelize(data)
    
    val dataDF = dataRDD.toDF()
    
    dataDF.write.parquet("hdfs://localhost:9000/user/kirupa/parquet/parquet_"+dt)
    
    val newDataDF = sqlContext.read.parquet("hdfs://localhost:9000/user/kirupa/parquet/parquet_"+dt)        // read back parquet to DF
    
    newDataDF.show() 

  }

}
