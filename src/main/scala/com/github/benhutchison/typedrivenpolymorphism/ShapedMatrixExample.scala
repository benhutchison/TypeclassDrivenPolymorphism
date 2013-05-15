package com.github.benhutchison.typedrivenpolymorphism

import shapeless._
import shapeless.Nat._


object ShapedMatrixExample extends App {

  type M[R <: Nat, C <: Nat] = {type Rows = R; type Cols = C}
  type Matrix[R <: Nat, C <: Nat] = Vector[Double] with M[R, C]

  implicit def matrixMultRowMajorArray[R1 <: Nat, X <: Nat, C2 <: Nat]
    (implicit r1x: ToInt[R1], x: ToInt[X], c2x: ToInt[C2]) = {

    new Multiplicative[Matrix[R1, X], Matrix[X, C2], Matrix[R1, C2]] {

      def times(a: Matrix[R1, X], b: Matrix[X, C2]): Matrix[R1, C2] =
      {
        val r1 = r1x()
        val c1 = x()
        val r2 = x()
        val c2 = c2x()

        var result = Vector.fill(r1 * c2)(0d)

        for (i <- 0 until r1) {
          for (j <- 0 until c2) {
            var sum = 0d
            for (k <- 0 until r2) {
              sum = sum + a(i * c1 + k) * b(k * c2 + j)
            }
            result = result.updated(i * c2 + j, sum)
          }
        }
        result.asInstanceOf[Matrix[R1, C2]]
      }
    }
  }

  def matrix[R <: Nat, C <: Nat] = new MatrixBuilder[R, C]

  class MatrixBuilder[R <: Nat, C <: Nat] {
    def fromRowMajor(ary: Vector[Double]): Matrix[R, C] = {
      ary.asInstanceOf[Matrix[R, C]]
    }
  }


  val m2x3 = matrix[_2, _3].fromRowMajor(Vector(1, 2, 3, 4, 5, 6))
  val m3x2 = matrix[_3, _2].fromRowMajor(Vector(1, 2, 3, 4, 5, 6))
  val m1x3 = matrix[_1, _3].fromRowMajor(Vector(1, 2, 3))
  
  val mb = matrix[_3, _2].fromRowMajor(Vector(7, 8, 9, 10, 11, 12))


  val m2x2: Matrix[_2, _2] = m2x3 *# m3x2

  println(s"m2x3 *# m3x2 => $m2x2")

//  val mShapeError = m2x2 *# m1x3

}
