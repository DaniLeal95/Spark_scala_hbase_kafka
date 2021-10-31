package com.dleal.sparkStructuredStreamingApplications.conf

import java.io.InputStream
import scala.io.Source


object ConfigureTwitter {

  def setupTwitter(): Unit = {
    val stream: InputStream = getClass.getResourceAsStream("/twitter.txt")

    for (line <- Source.fromInputStream(stream).getLines) {

      val fields = line.split(" ")
      if (fields.length == 2) {
        println(fields.mkString(" "))
        System.setProperty("twitter4j.oauth." + fields(0), fields(1))
      }
    }
  }


}
