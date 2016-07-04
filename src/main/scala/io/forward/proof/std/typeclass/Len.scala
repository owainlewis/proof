package io.forward.proof.std.typeclass

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
