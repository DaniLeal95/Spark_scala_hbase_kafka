package com.dleal.spark.common

import org.apache.spark.sql.SparkSession


object SparkSessionIni {

  def createSparkSession(): SparkSession = {
    SparkSession
      .builder()
      .appName("Spark Structured Streaming example")
      .config("spark.some.config.option", "some-value")
      .master("local[4]")
      .getOrCreate()


  }
  def setupLogging(): Unit = {
    import org.apache.log4j.{Level, Logger}
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.ERROR)
  }
}
