package io.forward.proof.examples

import io.forward.proof._
import io.forward.proof.Validation._
import io.forward.proof.Implicits._
import io.forward.proof.std.StringValidations._

case class User(name: String, age: Int, email: String)

object UserValidation extends Proof[User] {
  /**
    * Given a user to validate, return either a user or a list of errors
    */
  def validate(user: User): Validation[List[String], User] =
    runValidations(user, validateAge, validateEmail)

  /**
    * Validate a user is over 18
    */
  private def validateAge(user: User): Validation[String, User] =
    if (user.age >= 18) user.valid else "Must be 18 or over".invalid

  /**
    * Validate a user email contains an @ char.
    *
    * The leftMap function here is used to set the error message to something custom in the case of failure.
    *
    * The constant method changes the return type of a success case to always return some (other) type.
    * This is helpful for turning a validation of some type [A,B] into a validation of [A,C]
    * For example: Validation[String, String] => constant(user) => Validation[String, User]
    */
  private def validateEmail(user: User): Validation[String, User] =
    stringMatches(".*[@].*", user.email).constant(user).leftMap(_ => "Invalid email address")
}