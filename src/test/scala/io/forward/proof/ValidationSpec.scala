package io.forward.proof

import org.scalatest._
import io.forward.proof.Validation._

class ValidationSpec extends WordSpecLike with Matchers {

  "The Valid type" should {
    "have a valid method" in {
      Valid("Hello").valid shouldBe true
    }
    "have an invalid method" in {
      Valid("Hello").invalid shouldBe false
    }
  }

  "The Invalid type" should {
    "have a valid method" in {
      Invalid("Hello").valid shouldBe false
    }
    "have an invalid method" in {
      Invalid("Hello").invalid shouldBe true
    }
  }

  "Validating types" should {

    case class User(name: String, age: Int, email:  String)

    import Implicits._

    val ageValid: (User) => Validation[String, User] = (user: User) =>
      if (user.age > 18) user.valid else "Must be over 18".invalid

    val emailValid: (User) => Validation[String, User] = (user: User) =>
      if (user.email.contains("@")) user.valid else "Email must be valid".invalid

    val validUser = User("Jack", 25, "jack@twitter.com")

    val invalidUser = User("Jill", 17, "foobar")

    "check if an age is valid" in {
      ageValid(validUser) shouldBe Valid(validUser)
    }
    "check if an age is invalid" in {
      ageValid(invalidUser) shouldBe Invalid("Must be over 18")
    }
    "check if an email is valid" in {
      emailValid(validUser) shouldBe Valid(validUser)
    }
    "check if an email is invalid" in {
      emailValid(invalidUser) shouldBe Invalid("Email must be valid")
    }
    "combine validations" when {

      "given a valid user" in {
        validate(validUser, ageValid, emailValid) shouldBe Valid(validUser)
      }
      "given an invalid user" in {
        validate(invalidUser, ageValid, emailValid) shouldBe Invalid(List("Must be over 18", "Email must be valid"))
      }
    }
  }
}
