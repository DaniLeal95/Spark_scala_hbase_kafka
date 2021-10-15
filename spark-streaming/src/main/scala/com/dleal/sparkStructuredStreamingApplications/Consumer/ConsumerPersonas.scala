package com.dleal.sparkStructuredStreamingApplications.Consumer

import org.apache.spark.sql.SparkSession

object ConsumerPersonas {

  def main(args: Array[String]): Unit = {


    val spark = SparkSession
      .builder()
      .appName("Spark Structured Streaming example")
      .config("spark.some.config.option", "some-value")
      .master("local[4]")
      .getOrCreate()

    import spark.sqlContext.implicits._



    // Subscribe to 1 topic
    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "CreatePersonas")
      .load()

    val query = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)").writeStream.format("console").outputMode("update").start()

    query.awaitTermination()
  }

}
