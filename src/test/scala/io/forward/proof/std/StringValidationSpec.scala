package io.forward.proof.std

import org.scalatest._

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

  "The stringLengthLessThan function" should {
    "be valid" when {
      "given valid input" in {
        StringValidations.stringLengthLessThan(4, "foo").valid shouldBe true
      }
    }
    "be invalid" when {
      "given invalid input" in {
        StringValidations.stringLengthLessThan(3, "foo").valid shouldBe false
      }
    }
  }

  "The stringLengthMoreThan function" should {
    "be valid" when {
      "given valid input" in {
        StringValidations.stringLengthMoreThan(3, "hello").valid shouldBe true
      }
    }
    "be invalid" when {
      "given invalid input" in {
        StringValidations.stringLengthMoreThan(3, "foo").valid shouldBe false
      }
    }
  }

  "The stringContains function" should {
    "be valid" when {
      "given valid input" in {
        StringValidations.stringContains("llo", "hello").valid shouldBe true
      }
    }
    "be invalid" when {
      "given invalid input" in {
        StringValidations.stringContains("bar", "foo").valid shouldBe false
      }
    }
  }

  "The stringStartsWith function" should {
    "be valid" when {
      "given valid input" in {
        StringValidations.stringStartsWith("he", "hello").valid shouldBe true
      }
    }
    "be invalid" when {
      "given invalid input" in {
        StringValidations.stringStartsWith("bar", "foo").valid shouldBe false
      }
    }
  }

  "The stringEndsWith function" should {
    "be valid" when {
      "given valid input" in {
        StringValidations.stringEndsWith("oo", "foo").valid shouldBe true
      }
    }
    "be invalid" when {
      "given invalid input" in {
        StringValidations.stringEndsWith("bar", "foo").valid shouldBe false
      }
    }
  }

  "The stringMatches function" should {
    "be valid" when {
      "given valid input" in {
        StringValidations.stringMatches(".*[@].*", "foo@bar").valid shouldBe true
      }
    }
    "be invalid" when {
      "given invalid input" in {
        StringValidations.stringMatches("bar", "Hello World").valid shouldBe false
      }
    }
  }
}
