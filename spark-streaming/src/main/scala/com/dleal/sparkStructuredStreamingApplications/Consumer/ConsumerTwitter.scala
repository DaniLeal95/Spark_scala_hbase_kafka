package com.dleal.sparkStructuredStreamingApplications.Consumer

import com.dleal.spark.common.SparkSessionIni
import com.dleal.sparkStructuredStreamingApplications.conf.ConfigureTwitter
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ConsumerTwitter {

  def main(args: Array[String]): Unit = {
    //Create Spark Session
    val spark = SparkSessionIni.createSparkSession()
    //Configure Logger
    SparkSessionIni.setupLogging()
    //Configure twitter credentials
    ConfigureTwitter.setupTwitter()
    //Crete Streaming Context
    val ssc: StreamingContext = new StreamingContext(spark.sparkContext,Seconds(1))

    val tweets = TwitterUtils.createStream(ssc,None,Array("#WeAreSevilla"))

    val statuses = tweets.map( status => status.getText)
    statuses.print()

    ssc.start()
    ssc.awaitTermination()

  }
}
