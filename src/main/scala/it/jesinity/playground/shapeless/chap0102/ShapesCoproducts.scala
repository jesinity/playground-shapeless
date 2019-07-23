package it.jesinity.playground.shapeless.chap0102

import shapeless.{Inl, Inr}

object ShapesCoproducts {

  def main(args: Array[String]): Unit = {
    val red: Light   = Inl(Red())
    val green: Light = Inr(Inr(Inl(Green())))

    println(red)
    println(green)

    val rectangle = shapeGen.to(Rectangle(3.0, 4.0))
    val circle    = shapeGen.to(Circle(1.0))

    println(rectangle)
    println(circle)
  }

}
