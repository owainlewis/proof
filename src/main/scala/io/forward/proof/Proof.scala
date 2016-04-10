package io.forward.proof

trait Proof[T] {

  type Outcome = Validation[List[String], T]

  def validate(obj: T): Outcome
}
