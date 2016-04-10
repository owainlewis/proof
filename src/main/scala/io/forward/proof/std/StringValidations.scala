package io.forward.proof.std

import io.forward.proof.Validation

/**
  * Curried versions of the standard string validation functions for a nicer DSL
  *
  * validate("http://google.com", lengthLessThan(20), startsWith("http"))
  */
trait StringValidations {

  import StringValidations._

  val lengthIs = (n: Int) => stringLengthIs.curried(n)

  val lengthLessThan = (n: Int) => stringLengthLessThan.curried(n)

  val lengthMoreThan = (n: Int) => stringLengthMoreThan.curried(n)

  val contains = (sub: String) => stringContains.curried(sub)

  val startsWith = (prefix: String) => stringStartsWith.curried(prefix)

  val endsWith = (suffix: String) => stringEndsWith.curried(suffix)

  val matches = (expr: String) => stringMatches.curried(expr)
}

object StringValidations {
  /**
    * Validates if a string is exactly some length n
    */
  val stringLengthIs: (Int, String) => Validation[String, String] = (n: Int, input: String) =>
    liftBool({ s: String => s.length == n }, input, s"Expected string with length $n")
  /**
    * Validates a string is less than n chars
    */
  val stringLengthLessThan: (Int, String) => Validation[String, String] = (n: Int, input: String) =>
    liftBool({ s: String => s.length < n }, input, s"Expected string with length less than $n")
  /**
    * Validates a string is longer than n chars
    */
  val stringLengthMoreThan: (Int, String) => Validation[String, String] = (n: Int, input: String) =>
    liftBool({ s: String => s.length > n }, input, s"Expected string with length greater than $n")
  /**
    * Validates a string contains a sub-sequence
    */
  val stringContains: (String, String) => Validation[String, String] = (sub: String, input: String) =>
    liftBool({ s: String => s.contains(sub) }, input, s"Expected string to contain $sub")
  /**
    * Validates a string starts with a given prefix
    */
  val stringStartsWith: (String, String) => Validation[String, String] = (prefix: String, input: String) =>
    liftBool({ s: String => s.startsWith(prefix) }, input, s"Expected string to start with $prefix")
  /**
    * Validates a string ends with a given suffix
    */
  val stringEndsWith: (String, String) => Validation[String, String] = (suffix: String, input: String) =>
    liftBool({ s: String => s.endsWith(suffix) }, input, s"Expected string to end with $suffix")
  /**
    *  Validates a string matches some regex pattern
    */
  val stringMatches: (String, String) => Validation[String, String] = (expr: String, input: String) =>
    liftBool({ s: String => s.matches(expr) }, input, s"Expected string to match expr $expr")
  /**
    * Validates a string is not empty i.e whitespace
    */
  val stringNonEmpty: (String) => Validation[String, String] = (input: String) =>
    liftBool({ s: String => s.trim.nonEmpty }, input, "Expected non empty string")
  /**
    * Validates a string only contains alpha chars
    */
  val stringIsAlpha: (String) => Validation[String, String] = (input: String) =>
    liftBool({ s: String => s.matches("[A-Za-z]") }, input, "Expected string containing only alpha chars")
}
