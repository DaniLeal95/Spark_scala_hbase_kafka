package com.dleal.sparkHbaseApplications.bdao

import com.dleal.sparkHbaseApplications.parameters.PersonParameters
import com.dleal.sparkHbaseApplications.model.PersonModel
import com.dleal.sparkHbaseApplications.util.HBaseConnectorSerializable
import org.apache.hadoop.hbase.{CellUtil, TableName}
import org.apache.hadoop.hbase.client.{Connection, Get, Put, Result, Scan, Table}
import org.apache.hadoop.hbase.util.Bytes
import collection.JavaConverters._

class PersonBDAO extends Serializable {

  @transient
  private lazy val conexion: Connection = HBaseConnectorSerializable.connection
  @transient
  private lazy val table: Table = conexion.getTable(TableName.valueOf(PersonParameters.tableName))

  def put(person: PersonModel): Unit = {

    val put = new Put(Bytes.toBytes(person.rowKey.get))
    if (person.Country.isDefined) put.addColumn(Bytes.toBytes(PersonParameters.COLUMN_FAMILY_ADDRESS), Bytes.toBytes(PersonParameters.Country), Bytes.toBytes(person.Country.get))
    if (person.State.isDefined) put.addColumn(Bytes.toBytes(PersonParameters.COLUMN_FAMILY_ADDRESS), Bytes.toBytes(PersonParameters.State), Bytes.toBytes(person.State.get))
    if (person.First.isDefined) put.addColumn(Bytes.toBytes(PersonParameters.COLUMN_FAMILY_NAME), Bytes.toBytes(PersonParameters.First), Bytes.toBytes(person.First.get))
    if (person.Last.isDefined) put.addColumn(Bytes.toBytes(PersonParameters.COLUMN_FAMILY_NAME), Bytes.toBytes(PersonParameters.Last), Bytes.toBytes(person.Last.get))



  }


  def getRange(keyIni: String, keyEnd: String): Seq[PersonModel] = {

    val scanner = new Scan
    scanner.withStartRow(Bytes.toBytes(keyIni),true)
    scanner.withStopRow(Bytes.toBytes(keyEnd),true)

    val iterator = table.getScanner(scanner).iterator().asScala.toSeq
      .map( formatObject )

    iterator
  }


  def get(key: String): PersonModel = {

    var person: PersonModel = PersonModel.apply()

    val get : Get = new Get(Bytes.toBytes(key))
    val result =  table.get(get)

    if ( !result.isEmpty )
      person = formatObject(result)

    person
  }


  /******************************
   * Format Object from Result   *
   *******************************/
  def formatObject (result: Result): PersonModel = {

    val cells = result.rawCells()

    //KEY
    val keysplit = Bytes.toString(result.getRow)
    var personAux : PersonModel = PersonModel.apply(keysplit)


    //VALUES
    for (cell <- cells) {
      personAux = Bytes.toString(CellUtil.cloneQualifier(cell)) match {
        case PersonParameters.State => personAux.copy(State = Some(Bytes.toString(CellUtil.cloneValue(cell))))
        case PersonParameters.Country => personAux.copy(Country = Some(Bytes.toString(CellUtil.cloneValue(cell))))
        case PersonParameters.Last => personAux.copy(Last = Some(Bytes.toString(CellUtil.cloneValue(cell))))
        case PersonParameters.First => personAux.copy(First = Some(Bytes.toString(CellUtil.cloneValue(cell))))
      }
    }

    personAux
  }


}
