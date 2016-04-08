package io.forward.validates

import cats.data._

class Scratch {

  def withMessage[A,B](xor: Xor[A,B], msg: String): Xor[String, B] = xor.leftMap(_ => msg)

  def liftXorNel[A,B](xor: Xor[A,B]): Validated[NonEmptyList[A], B] = xor.toValidated.leftMap(NonEmptyList(_))

  def isValid[A,B](xor: Xor[A,B]) = xor.isRight
 
 /**
  * Combine multiple singular validations for some type T into a validation of type Validation[List[S],T]
  * 
  * This is used to combine multiple error outcomes into a single validation that either succeeds or contains
  * a list of validation errors
  * 
  */
  def combine[S,T](obj: T, validations: List[Validation[S, T]]): Validation[List[S], T] = {
    val errors = validations.foldLeft(List.empty[S]) { case (xs, validation) =>
      validation match {
        case Valid(_) => xs
        case Invalid(error) => error :: xs
      }
    }
    if (errors.isEmpty) Valid(obj) else Invalid(errors)
  }
}
