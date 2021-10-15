package com.dleal.sparkHbaseApplications.model

case class PersonModel(rowKey: Option[String],Country: Option[String], State: Option[String], First: Option[String], Last: Option[String])
object PersonModel{
  def apply(): PersonModel = new PersonModel(None,None,None,None, None)
  def apply(key: String): PersonModel = new PersonModel(Some(key),None,None,None, None)
}
