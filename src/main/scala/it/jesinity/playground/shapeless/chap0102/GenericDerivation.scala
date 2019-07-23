package it.jesinity.playground.shapeless.chap0102

import shapeless._

object GenericDerivation {

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
