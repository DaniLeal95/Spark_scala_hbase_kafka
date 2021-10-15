package com.dleal.sparkHbaseApplications.runner

import com.dleal.sparkHbaseApplications.bdao.PersonBDAO
import com.dleal.sparkHbaseApplications.model.PersonModel
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.spark.sql.SparkSession

object RUN {

  def main(args: Array[String]): Unit = {


    val bdao = new PersonBDAO


    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .master("local[4]")
      .getOrCreate()

    import spark.implicits._

//



    val range = bdao.getRange("1","100")

    range.foreach(println)

    val list = Seq("1")

    val ds = spark.createDataset(list)

    val e = ds.mapPartitions( partition => {


      val a = partition.map( f => {

        val y = bdao.get(f)

        y
      })

      a
    })

    e.show(false)


  }
}
