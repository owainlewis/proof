# Validates

## Examples

```scala
scala> import io.forward.validates._
import io.forward.validates._

scala> import Validates._
import Validates._

scala> validateLength("FooBar", 3)
res0: cats.data.Xor[String,String] = Left(Expected string with length 3)

scala> validateLength("Foo", 3)
res1: cats.data.Xor[String,String] = Right(Foo)

scala> Validation.liftXorNel(validateLength("Foo", 3))
res2: cats.data.Validated[cats.data.NonEmptyList[String],String] = Valid(Foo)
```

A library for generic validation

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