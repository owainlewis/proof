# Proof

A generic validation library for Scala.

Although there are other libraries for validation in Scala (Scalaz, Cats and Scalactic) this library offers a much smaller footprint and less complexity. 

Proof should be very easy to use for typical validation tasks but, conversely, will not be as powerful or generic as something like Scalaz/Cats.

## Example

This example demonstrates how you might write a user validation object using this library

```scala
import io.forward.proof._
import io.forward.proof.Validation._
import io.forward.proof.Implicits._
import io.forward.proof.std.StringValidations._

case class User(name: String, age: Int, email: String)

object UserValidation extends Validator[User] {
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
    * Validate a user email contains an @ char. The leftMap function here is used to set the
    * error message to something custom in the case of failure. The constant method changes the return
    * type of the success case to always return a User type. This is helpful for turning a validation
    * of some type [A,B] into a validation of [A,C]
    */
  private def validateEmail(user: User): Validation[String, User] =
    stringMatches(".*[@].*", user.email).constant(user).leftMap(_ => "Invalid email")
}
```

We can use pattern matching on the Validation types (Valid or Invalid) to handle the success and failure cases easily.

```scala

UserValidation.validate(User("Mary Jane", 14, "l33tHak0r")) match {
  case Invalid(errors) => println(errors.mkString(", "))
  case Valid(_) => println("Valid")
}

// => Must be 18 or over, Invalid email

```

A *valid* and *invalid* method is available on every validation to check for success and failure

```scala
scala> import io.forward.proof.Implicits._
import io.forward.proof.Implicits._

scala> "Hello World".invalid[String].valid
res2: Boolean = false

scala> "Hello World".invalid[String].invalid
res3: Boolean = true
```

## In Depth

This library provides a simple type for representing validation along with some helper functions
for combining multiple validations into a list of validation errors.
It also provides validation functions for some of the most common domain validation tasks.

### Combining validations

Use the *runValidations* method to combine multiple singular validations into a success case or a list of validation errors.

## Implicits

Implicits are available for a nicer syntax when creating validations

```scala
scala> import io.forward.proof.Implicits._
import io.forward.proof.Implicits._

scala> 1.valid[String]
res1: io.forward.proof.Validation[String,Int] = Valid(1)

scala> "Must be valid".invalid[Int]
res2: io.forward.proof.Validation[String,Int] = Invalid("Must be valid")

```

## Validations

Proof provides validation functions for some of the most common validation tasks:

### String

+ Length equals
+ Max length
+ Min length
+ Contains
+ Matches
+ Alpha

## Roadmap

The following validations may be added at some piont

### Credit Card

+ Luhn Check

### Numbers

+ Less than
+ Greater than
+ Between

### Dates

+ Before
+ After
+ Within (e.g 2 hours of)

