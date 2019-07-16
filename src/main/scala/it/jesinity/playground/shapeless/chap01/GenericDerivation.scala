package it.jesinity.playground.shapeless.chap01

import shapeless._

object GenericDerivation {

  val recGen       = Generic[Rectangle]
  val rectTupleGen = Generic[(Double, Double)]
  val iceCreamGen  = Generic[IceCream]
  val employeeGen  = Generic[Employee]

  def main(args: Array[String]): Unit = {

    val rect: Rectangle                 = Rectangle(2.0, 4.0)
    val hList: Double :: Double :: HNil = recGen.to(rect)
    val tupled                          = rectTupleGen.from(hList)
    println(tupled)

    val iceCream = IceCream("Sundae", 1, false)

    val employee = Generic[Employee].from(Generic[IceCream].to(iceCream))

    println(employee)

  }

}
