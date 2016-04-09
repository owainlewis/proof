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
}
