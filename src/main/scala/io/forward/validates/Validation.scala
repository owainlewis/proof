package io.forward.validates

/**
  * Type representing the outcome of a validation.
  *
  * @tparam A
  * @tparam B
  */
sealed abstract class Validation[+A, +B] extends Product with Serializable {

  def fold[C](fa: A => C, fb: B => C): C = this match {
    case Validation.Invalid(a) => fa(a)
    case Validation.Valid(b) => fb(b)
  }

  def valid: Boolean = fold(_ => false, _ => true)

  def invalid: Boolean = fold(_ => true, _ => false)

  def toEither: Either[A, B] = fold(Left(_), Right(_))
}

object Validation  {

  final case class Valid[+A](a: A) extends Validation[Nothing, A]

  final case class Invalid[+B](b: B) extends Validation[B, Nothing]
}