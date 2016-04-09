package io.forward.proof.std.typeclass

/**
  * A type class for things that have length i.e String, List etc
  *
  */
trait Len[T] {
  def length(x: T): Int
}

object Len {
  implicit def listLength[A]: Len[List[A]] = new Len[List[A]] {
    def length(x: List[A]) = x.length
  }

  implicit def stringLength[A]: Len[String] = new Len[String] {
    def length(x: String) = x.length
  }
}