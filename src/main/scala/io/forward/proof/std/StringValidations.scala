package io.forward.proof.std

import io.forward.proof.Validation

trait StringValidations {

  val stringLengthIs = (n: Int) => validateLength.curried(n)
  
  val stringLengthIsLessThan = (n: Int) => validateLengthLessThan.curried(n)
  
  val stringLengthIsMoreThan = (n: Int) => validateLengthMoreThan.curried(n)
  
  val stringContains = (sub: String) => validateContains.curried(sub)
  
  val validateLength: (Int, String) => Validation[String, String] = (n: Int, input: String) =>
    liftBool({ s: String => s.length == n }, input, s"Expected string with length $n")

  val validateLengthLessThan: (Int, String) => Validation[String, String] = (n: Int, input: String) =>
    liftBool({ s: String => s.length < n }, input, s"Expected string with length less than $n")

  val stringLengthIsLessThan = (n: Int) => validateLengthLessThan.curried(n)

  val validateLengthMoreThan: (Int, String) => Validation[String, String] = (n: Int, input: String) =>
    liftBool({ s: String => s.length > n }, input, s"Expected string with length greater than $n")

  val validateContains: (String, String) => Validation[String, String] = (sub: String, input: String) =>
    liftBool({ s: String => s.contains(sub) }, input, s"Expected string to contain $sub")
}
