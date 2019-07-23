package it.jesinity.playground.shapeless.chap03

import it.jesinity.playground.shapeless.chap0102.IceCream

object ConcreteClassEncoders {

  /**
    * initialized with the Single Abstract Method semantics
    */
  implicit val iceCreamEncoder: CsvEncoder[IceCream] =
    (i: IceCream) =>
      i.name + i.numCherries.toString + { if (i.inCone) "yes" else "no" }

  implicit def pairEncoder[A, B](
      implicit
      aEncoder: CsvEncoder[A],
      bEncoder: CsvEncoder[B]
  ): CsvEncoder[(A, B)] =
    (pair: (A, B)) => {
      val (a, b) = pair
      aEncoder.encode(a) ++ bEncoder.encode(b)
    }

}

object UseConcreteClassEncoders {
  def main(args: Array[String]): Unit = {
    import ConcreteClassEncoders._

    val encoder = CsvEncoder[IceCream]
    val encoded = encoder.encode(IceCream("cornetto", 4, true))

    println(encoded)
  }
}
