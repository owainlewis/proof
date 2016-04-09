package io.forward.proof.std

import io.forward.proof.Implicits
import io.forward.proof.std.typeclass.Len

trait LengthValidations {

  import Implicits._

  def lengthIs[T](n: Int, x: T)(implicit ev: Len[T]) =
    if (ev.length(x) == n) x.valid else s"Expected length $n".invalid

}
