package com.github.benhutchison

package object typedrivenpolymorphism {

  implicit class RichMultiplicative[A](a: A) {

    def *#[B, R](b: B)(implicit op: Multiplicative[A, B, R]): R = op.times(a, b)

  }
}
