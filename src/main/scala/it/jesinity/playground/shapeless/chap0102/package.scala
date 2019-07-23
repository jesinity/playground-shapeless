package it.jesinity.playground.shapeless
import shapeless.{:+:, CNil, Generic}

package object chap0102 {

  type Light = Red :+: Amber :+: Green :+: CNil

  val shapeGen     = Generic[Shape]
  val recGen       = Generic[Rectangle]
  val rectTupleGen = Generic[(Double, Double)]
  val iceCreamGen  = Generic[IceCream]
  val employeeGen  = Generic[Employee]

}
