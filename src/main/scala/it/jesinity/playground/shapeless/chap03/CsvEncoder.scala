package it.jesinity.playground.shapeless.chap03

import shapeless.{::, Generic, HList, HNil}

trait CsvEncoder[A] {
  def encode(value: A): String
}

object CsvEncoder {

  // "Summoner" method
  def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] = enc
  // "Constructor" method
  def instance[A](func: A => String): CsvEncoder[A] =
    (value: A) => func(value)

  def createEncoder[A](func: A => String): CsvEncoder[A] =
    (value: A) => func(value)

  implicit val stringEncoder: CsvEncoder[String] =
    createEncoder(str => str)
  implicit val intEncoder: CsvEncoder[Int] =
    createEncoder(num => num.toString)
  implicit val booleanEncoder: CsvEncoder[Boolean] =
    createEncoder(bool => if (bool) "yes" else "no")

  implicit def listEncoder[A](
      implicit concreteEncoder: CsvEncoder[A]): CsvEncoder[List[A]] =
    (value: List[A]) => {
      value.map(concreteEncoder.encode).mkString("\n")
    }

  implicit val hnilEncoder: CsvEncoder[HNil] =
    createEncoder(hnil => "")

  implicit def hlistEncoder[H, T <: HList](
      implicit
      hEncoder: CsvEncoder[H],
      tEncoder: CsvEncoder[T]
  ): CsvEncoder[H :: T] = createEncoder {
    case h :: HNil =>
      hEncoder.encode(h)
    case h :: t =>
      hEncoder.encode(h) + "," ++ tEncoder.encode(t)
  }

  implicit def genericEncoder[A, R](
      implicit
      gen: Generic.Aux[A, R],
      env: CsvEncoder[R]
  ): CsvEncoder[A] =
    createEncoder(a => env.encode(gen.to(a)))
}

object CsvEncoderOps {

  implicit class PimpMyTypeWithCSV[A](clazz: A) {

    def asCsv(implicit enc: CsvEncoder[A]): String =
      enc.encode(clazz)

  }
}
