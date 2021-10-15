package com.dleal.sparkHbaseApplications.util

import org.apache.hadoop.hbase.CellUtil
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.util.Bytes

object HBaseUtils {
  def printRow(result: Result): Unit = {
    val cells = result.rawCells()
    print(Bytes.toString(result.getRow) + " : ")
    for (cell <- cells) {
      try{
        val col_name = Bytes.toString(CellUtil.cloneQualifier(cell))
        val col_value = Bytes.toString(CellUtil.cloneValue(cell))
        print("(%s,%s) ".format(col_name, col_value))
      }
      catch {
        case t: Throwable => print("Cast error ")
      }
    }
    println()
  }
}
