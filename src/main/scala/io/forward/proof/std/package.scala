package io.forward.proof

package object std {

  import Implicits._

  def liftBool[T](f: T => Boolean, obj: T, msg: String): Validation[String, T] =
    if (f(obj)) obj.valid else msg.invalid
}
