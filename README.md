# Proof

A generic validation library for Scala.

Although there are other libraries for validation in Scala (Scalaz, Cats and Scalactic) this library offers a much smaller footprint and less complexity. 

Proof should be very easy to use for typical validation tasks but, conversely, will not be as powerful or generic as something like Scalaz/Cats.

## Overview

This library provides a simpe type for representing validation along with some helper functions for cominbing multiple validations into one. It also provides validation functions for some of the most common domain validation tasks.

## Examples

Let's write a function to validate a user

```scala
case class User(name: String, age: Int, email: String)
```

Next we'll write our validation functions

```scala
import io.forward.validates.Validation._

object UserValidator {

  def ageValid(user: User): Validation[String, User] = 
    if (user.age > 18) Valid(user) else Invalid("User must be over 18")

  def emailValid(user: User): Validation[String, User] = 
    if (user.email.contains("@")) Valid(user) else Invalid("User email invalid")
}
```

### Combine validations

Perhaps the most common requirement for validation is the ability to combine multiple validations into a list. For examples we will
validate a users age and email and return either a list of validation errors of a user if valid.

This logic is achieved using the validateWith method.


```scala
import io.forward.validates.Validation._

object UserValidator {
  /**
   * Check if a user is valid. Return either a list of validation errors or a user
   *
   */
  def validate(user: User): Validation[List[String], User] = 
    validateWith(user, ageValid, emailValid)

  private def ageValid(user: User): Validation[String, User] = 
    if (user.age > 18) Valid(user) else Invalid("User must be over 18")

  private def emailValid(user: User): Validation[String, User] = 
    if (user.email.contains("@")) Valid(user) else Invalid("User email invalid")
}

```

## Implicits

Implicits are available for a nicer syntax over validations

```
scala> import io.forward.proof.Implicits._
import io.forward.proof.Implicits._

scala> 1.valid[String]
res1: io.forward.proof.Validation[String,Int] = Valid(1)

scala> "Must be valid".invalid[Int]
res2: io.forward.proof.Validation[String,Int] = Invalid(Must be valid)

```

## Validations

Proof provides validation functions for some of the most common validation tasks:

### String

+ Max length
+ Min length
+ Regex
+ Email
+ Alpha
+ Numeric
+ AlphaNumeric

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

