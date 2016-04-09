package io.forward.proof.std

import io.forward.proof.{Implicits, Validation}

trait StringValidations {

  import Implicits._

  val validateLength = (n: Int, input: String) =>
    if (input.length == n) input.valid else s"Expected string with length $n".invalid

  val lengthIs = (n: Int) => validateLength.curried(n)

  val validateLessThan = (n: Int, input: String) =>
    if (input.length < n) input.valid else s"Expected string with length less than $n".invalid

  val lengthLessThan = (n: Int) => validateLength.curried(n)

  val validateGreaterThan = (n: Int, input: String) =>
    if (input.length > n) input.valid else s"Expected string with length greater than $n".invalid

  val lengthGreaterThan = (n: Int) => validateGreaterThan.curried(n)
}
