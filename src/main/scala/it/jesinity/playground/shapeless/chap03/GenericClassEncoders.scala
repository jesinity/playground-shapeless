package it.jesinity.playground.shapeless.chap03

import CsvEncoder._
import CsvEncoderOps._

import it.jesinity.playground.shapeless.chap0102.Employee

object GenericClassEncoders {

  def main(args: Array[String]): Unit = {

    val inCsv =
      List(Employee("franco", 3, true), Employee("davide", 3, false)).asCsv

    println(inCsv)
    println(Employee("franco", 3, true).asCsv)
  }

}
