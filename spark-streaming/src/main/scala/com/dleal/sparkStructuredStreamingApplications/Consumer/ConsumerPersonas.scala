package com.dleal.sparkStructuredStreamingApplications.Consumer

import com.dleal.spark.common.SparkSessionIni
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession

object ConsumerPersonas {

  def main(args: Array[String]): Unit = {

    val spark = SparkSessionIni.createSparkSession()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.sqlContext.implicits._

    val logger = Logger.getLogger(this.getClass)

    // Subscribe to 1 topic
    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "CreatePersonas")
      .option(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
      .load()

    val query = /*df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")*/df.writeStream.format("console").outputMode("update").start()

    query.awaitTermination()
  }

}
