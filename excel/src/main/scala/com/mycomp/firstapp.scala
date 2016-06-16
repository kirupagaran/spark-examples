/* WordCount.scala */

import org.apache.spark.SparkContext

import org.apache.spark.SparkContext._

import org.apache.spark.SparkConf

import java.util.Date

object WordCount {

  def main(args: Array[String]) {

    val logFile = "/home/cloudera/kirupa/spark/sample.txt" // Should be some file on your hdfs

    val conf = new SparkConf().setAppName("First Application").setMaster("local[2]")

    val sc = new SparkContext(conf)

    val logData = sc.textFile(logFile, 2).cache()

    val numAs = logData.flatMap(x => x.split(" ")).map(x=>(x,1)).reduceByKey((a,b)=>(a+b))

    val dateObj = new Date()
    
    val dateInfo = dateObj.getSeconds
    
    numAs.collect.foreach(println)

    //numAs.saveAsTextFile("/user/cloudera/kirupa/spark/sample_"+dateInfo);

  }

}
