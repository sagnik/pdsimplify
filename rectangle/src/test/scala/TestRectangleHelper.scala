package edu.psu.sagnik.research.pdsimplify.rectanglehelpers

import edu.psu.sagnik.research.data.RectangleOTL
import org.scalatest.FunSuite
/**
 * Created by schoudhury on 8/18/16.
 */

class TestRectangleHelper extends FunSuite {

  val centralRect = RectangleOTL(
    xTopLeft = 50, yTopLeft = 50,
    widthRight = 50, heightDown = 50
  )

  import edu.psu.sagnik.research.pdsimplify.rectanglehelpers.RectangleHelpers._
  test("rectangle distance, non spanning, non intersecting, top left") {
    val r = RectangleOTL(
      xTopLeft = 0, yTopLeft = 0,
      widthRight = 10, heightDown = 10
    )
    assert(rectDistance(centralRect, r) == 80f)
    assert(rectDistance(r, centralRect) == 80f)
  }

  test("rectangle distance, non spanning, non intersecting, top") {

    val r = RectangleOTL(
      xTopLeft = 50, yTopLeft = 0,
      widthRight = 50, heightDown = 10
    )

    assert(rectDistance(centralRect, r) == 40f)
    assert(rectDistance(r, centralRect) == 40f)
  }

  test("rectangle distance, non spanning, non intersecting, top right") {

    val r = RectangleOTL(
      xTopLeft = 140, yTopLeft = 0,
      widthRight = 10, heightDown = 10
    )

    assert(rectDistance(centralRect, r) == 80f)
    assert(rectDistance(r, centralRect) == 80f)
  }

  test("rectangle distance, non spanning, non intersecting, right") {

    val r = RectangleOTL(
      xTopLeft = 140, yTopLeft = 50,
      widthRight = 10, heightDown = 50
    )

    assert(rectDistance(centralRect, r) == 40f)
    assert(rectDistance(r, centralRect) == 40f)
  }

  test("rectangle distance, non spanning, non intersecting, right below") {

    val r = RectangleOTL(
      xTopLeft = 140, yTopLeft = 140,
      widthRight = 10, heightDown = 10
    )
    assert(rectDistance(centralRect, r) == 80f)
    assert(rectDistance(r, centralRect) == 80f)
  }

  test("rectangle distance, non spanning, non intersecting, below") {

    val r = RectangleOTL(
      xTopLeft = 50, yTopLeft = 140,
      widthRight = 10, heightDown = 10
    )

    assert(rectDistance(centralRect, r) == 40f)
    assert(rectDistance(r, centralRect) == 40f)
  }

  test("rectangle distance, non spanning, non intersecting, below left") {

    val r = RectangleOTL(
      xTopLeft = 0, yTopLeft = 140,
      widthRight = 10, heightDown = 10
    )

    assert(rectDistance(centralRect, r) == 80f)
    assert(rectDistance(r, centralRect) == 80f)
  }

  test("rectangle distance, non spanning, non intersecting, left") {

    val r = RectangleOTL(
      xTopLeft = 0, yTopLeft = 50,
      widthRight = 10, heightDown = 50
    )

    assert(rectDistance(centralRect, r) == 40f)
    assert(rectDistance(r, centralRect) == 40f)
  }

  test("rectangle distance, spanning, non intersecting, below") {

    val r = RectangleOTL(
      xTopLeft = 0, yTopLeft = 140,
      widthRight = 150, heightDown = 10
    )

    assert(rectDistance(centralRect, r) == 40f)
    assert(rectDistance(r, centralRect) == 40f)
  }

}
