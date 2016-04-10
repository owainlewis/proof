package io.forward.proof

package object std {

  import Implicits._

  /**
    * Lifts a function of T => Boolean to a validation with some message
    */
  def liftBool[T](f: T => Boolean, obj: T, msg: String): Validation[String, T] =
    if (f(obj)) obj.valid else msg.invalid
}
