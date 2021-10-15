package com.dleal.sparkHbaseApplications.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Table}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}

object HBaseConnectorSerializable extends Serializable {
  val conf: Configuration = HBaseConfiguration.create()

  //val connection = ConnectionFactory.createConnection(conf)
  def connection: Connection = {
    //println("*******SET CONF**********")
    ConnectionFactory.createConnection(conf)
  }

  def zkPort: String = connection.getConfiguration.get("hbase.zookeeper.property.clientPort")

  def zkQuorum: String = connection.getConfiguration.get("hbase.zookeeper.quorum")

  def printHBaseConnection(): Unit = println(s"Connected to HBase [$zkQuorum]:[$zkPort]")

  def setZkQuorum(host: String): Unit = conf.set("hbase.zookeeper.quorum", host)

  def setZkPort(port: String): Unit = conf.set("hbase.zookeeper.property.clientPort", port)

  def getTable(table: String): Table = connection.getTable(TableName.valueOf(table))
}