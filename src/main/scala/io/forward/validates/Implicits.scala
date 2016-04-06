package io.forward.validates

import cats.{Semigroup, _}
import cats.data.NonEmptyList
import cats.std.list._

object Implicits {

  implicit val nonEmptyListSemiGroup: Semigroup[NonEmptyList[String]] =
    SemigroupK[NonEmptyList].algebra[String]
}
