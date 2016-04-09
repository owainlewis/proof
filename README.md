# Validates

A small library for simple and consistent domain validation.

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

## More Examples

```scala
scala> import io.forward.validates.Validation._
import io.forward.validates.Validation._

scala> case class User(name: String, age: Int)
defined class User

scala> val user = User("Jack", 25)
user: User = User(Jack,25)

scala> def validateAge(user: User) = if (user.age > 18) Valid(user) else Invalid("User must be over 18")
validateAge: (user: User)io.forward.validates.Validation[String,User]

scala> validateWith(user, validateAge)
res0: io.forward.validates.Validation[List[String],User] = Valid(User(Jack,25))

scala> def validateName(user: User) = if (user.name == "Jack") Valid(user) else Invalid("User name must be Jack")
validateName: (user: User)io.forward.validates.Validation[String,User]

scala> validateWith(user, validateAge, validateName)
res1: io.forward.validates.Validation[List[String],User] = Valid(User(Jack,25))

scala> validateWith(User("Mary", 14), validateAge, validateName)
res3: io.forward.validates.Validation[List[String],User] = Invalid(List(User name must be Jack, User must be over 18))

```

## Validations

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
