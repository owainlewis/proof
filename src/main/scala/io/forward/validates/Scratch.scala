package io.forward.validates

import cats.data._

class Scratch {

  def withMessage[A,B](xor: Xor[A,B], msg: String): Xor[String, B] = xor.leftMap(_ => msg)

  def liftXorNel[A,B](xor: Xor[A,B]): Validated[NonEmptyList[A], B] = xor.toValidated.leftMap(NonEmptyList(_))

  def isValid[A,B](xor: Xor[A,B]) = xor.isRight

  def combine[S,T](obj: T, validations: List[Validation[S, T]]): Validation[List[S], T] = {
    val errors = validations.foldLeft(List.empty[String]) { case (xs, validation) =>
      validation match {
        case Valid(_) => xs
        case Invalid(error) => error :: xs
      }
    }
    if (errors.isEmpty) Valid(obj) else Invalid(obj)
  }
}
