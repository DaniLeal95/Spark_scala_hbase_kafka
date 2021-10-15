package com.dleal.spark

//import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.spark.HBaseContext
//import com.dleal.sparkHbaseApplications.PruebaHBASE2
import org.apache.spark.sql.SparkSession
import org.apache.hadoop.hbase.spark.datasources.HBaseTableCatalog


object Prueba1{


    def main(args: Array[String]): Unit = {

        val s = "HOLA"
        println(s)

        val spark = SparkSession
          .builder()
          .appName("Spark SQL basic example")
          .config("spark.some.config.option", "some-value")
          .master("local[4]")
          .getOrCreate()
        val range100 = spark.range(100)

        range100.show(false)

//        PruebaHBASE2.callHbase(spark)



//         Instantiate HBaseContext that will be used by the following code


    }



}
