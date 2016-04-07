package io.forward.validates

import cats.data.Xor

object Validates {

  import Implicits._

  /**
    * Wraps a default validation with an additional field name if an error is thrown.
    */
  def withFieldName[A,B](fieldName: String, xor: Xor[A,B]) =
    xor.leftMap(msg => s"$fieldName: $msg")

  /**
    * Validate that a string is exactly some length
    *
    * @param input An input string
    * @param n The number of chars expected
    */
  def validateLength(input: String, n: Int): Xor[String, String] =
    if (input.length == n) Xor.right(input) else Xor.left(s"Expected string with length $n")

  /**
    * Validate that a string is less than n chars
    *
    * @param input An input string
    * @param n The maximum number of chars
    */
  def validateLessThan(input: String, n: Int): Xor[String, String] =
    if (input.length < n) Xor.right(input) else Xor.left(s"Expected string with length less than $n")
    
  /**
    * Validate that a string is greater than n chars
    *
    * @param input An input string
    * @param n The min number of chars
    */
  def validateGreaterThan(input: String, n: Int): Xor[String, String] =
    if (input.length > n) Xor.right(input) else Xor.left(s"Expected string with length greater than $n")
}
