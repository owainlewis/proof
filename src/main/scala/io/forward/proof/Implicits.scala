package io.forward.proof

import Validation._

object Implicits {

  class ValidType[T](t: T) {
    def valid[S]: Validation[S,T] = Valid(t)
  }

  implicit def toValidType[T](t: T): ValidType[T] = new ValidType(t)

  class InvalidType[S](t: S) {
    def invalid[T]: Validation[S,T] = Invalid(t)
  }

  implicit def toInvalidType[T](t: T): InvalidType[T] = new InvalidType(t)
}
