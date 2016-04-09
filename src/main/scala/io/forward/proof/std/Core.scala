package io.forward.proof.std

import io.forward.proof.Validation
import io.forward.proof.Validation._

object Core {
  /**
    * Validate that a string is exactly some length
    *
    * @param input An input string
    * @param n The number of chars expected
    */
  def validateLength(input: String, n: Int): Validation[String, String] =
    if (input.length == n) Valid(input) else Invalid(s"Expected string with length $n")

  /**
    * Validate that a string is less than n chars
    *
    * @param input An input string
    * @param n The maximum number of chars
    */
  def validateLessThan(input: String, n: Int): Validation[String, String] =
    if (input.length < n) Valid(input) else Invalid(s"Expected string with length less than $n")
    
  /**
    * Validate that a string is greater than n chars
    *
    * @param input An input string
    * @param n The min number of chars
    */
  def validateGreaterThan(input: String, n: Int): Validation[String, String] =
    if (input.length > n) Valid(input) else Invalid(s"Expected string with length greater than $n")
}
