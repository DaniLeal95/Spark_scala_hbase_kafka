package com.dleal.sparkStructuredStreamingApplications.Producer


import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties
import scala.util.Random

object ProducerPersonas {

  def main(args: Array[String]): Unit = {

    val kafkaProducerProps: Properties = {
      val props = new Properties()
      props.put("bootstrap.servers", "localhost:9092")
      props.put("key.serializer", classOf[StringSerializer].getName)
      props.put("value.serializer", classOf[StringSerializer].getName)
      props
    }





    val producer = new KafkaProducer[String, String](kafkaProducerProps)

//    for( i <- 1 to 1000000){

      producer.send(new ProducerRecord[String, String]("CreatePersonas", "PERSONA_"+1))

//    }


  }
}
