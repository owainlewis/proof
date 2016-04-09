package io.forward.validates

/**
  * Type representing the outcome of a validation.
  *
  * @tparam A Type for Invalid
  * @tparam B Type for Valid
  */
sealed abstract class Validation[+A, +B] extends Product with Serializable {

  def fold[C](fa: A => C, fb: B => C): C = this match {
    case Validation.Invalid(a) => fa(a)
    case Validation.Valid(b) => fb(b)
  }

  def valid: Boolean = fold(_ => false, _ => true)

  def invalid: Boolean = fold(_ => true, _ => false)

  def toEither: Either[A, B] = fold(Left(_), Right(_))

  def toOption: Option[B] = fold(_ => None, Some(_))

  def leftMap[C](f: A => C): Validation[C,B] = this match {
    case Validation.Invalid(x) => Validation.Invalid(f(x))
    case r @ Validation.Valid(_) => r
  }

  def rightMap[C](f: B => C): Validation[A,C] = this match {
    case r @ Validation.Invalid(_)      => r
    case r @ Validation.Valid(x) => Validation.Valid(f(x))
  }

  def map[C](f: B => C): Validation[A,C] = rightMap(f)
}

object Validation  {

  final case class Valid[+A](a: A) extends Validation[Nothing, A]

  final case class Invalid[+B](b: B) extends Validation[B, Nothing]

  /**
    * Combine multiple singular validations for some type T into a validation of type Validation[List[S],T]
    *
    * This is used to combine multiple error outcomes into a single validation that either succeeds or contains a list of validation errors
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

  /**
    * Given an input type validate it with many validation functions. This can be used to perform
    * validation accumulation on a range of validation functions for a given type
    *
    * @param obj An object to validate i.e User(name = "Jack", age = 25)
    * @param validations Any number of validation functions in the form (T) => Validation[S,T]
    *
    * @tparam S Error type
    * @tparam T Validation object type
    */
  def validateWith[S,T](obj: T, validations: ((T) => Validation[S,T])*): Validation[List[S], T] =
    combine(obj, validations.toList.map(f => f(obj)))
}