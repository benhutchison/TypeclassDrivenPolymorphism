package com.github.benhutchison.typedrivenpolymorphism

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "Cannot find typeclass to multiply types ${A} and ${B}")
trait Multiplicative[-A, -B, +R] {

  def times(a: A, b: B): R

}
