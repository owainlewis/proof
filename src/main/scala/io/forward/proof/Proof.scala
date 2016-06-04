package io.forward.proof

trait Proof[T] {

  def validate(obj: T): Validation[List[String], T]
}
