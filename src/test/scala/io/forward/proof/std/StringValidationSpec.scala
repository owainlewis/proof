package io.forward.proof.std

import org.scalatest._
import io.forward.proof.Implicits._

class StringValidationSpec extends WordSpecLike with Matchers {

  "The stringLengthIs function" should {
    "be valid" when {
      "given valid input" in {
        StringValidations.stringLengthIs(3, "foo").valid shouldBe true
      }
    }
    "be invalid" when {
      "given invalid input" in {
        StringValidations.stringLengthIs(3, "foobar").valid shouldBe false
      }
    }
  }
}
