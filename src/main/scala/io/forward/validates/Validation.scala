package io.forward.validates

import cats.data.{NonEmptyList, Validated, Xor}

object Validation {

  def withMessage[A,B](xor: Xor[A,B], msg: String): Xor[String, B] = xor.leftMap(_ => msg)

  def liftXorNel[A,B](xor: Xor[A,B]): Validated[NonEmptyList[A], B] = xor.toValidated.leftMap(NonEmptyList(_))

  def isValid[A,B](xor: Xor[A,B]) = xor.isRight
}
