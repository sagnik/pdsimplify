package edu.psu.sagnik.research.pdsimplify.rectanglehelpers

import edu.psu.sagnik.research.data.RectangleOTL

/**
 * Created by schoudhury on 8/18/16.
 */
object RectangleHelpers {

  def round(x: Float) = scala.math.round(x)

  /**
   * Evaluates to a string that contains the Rectangle's
   * point information as x1,y2,x2,y2 .
   */
  def asCoordinatesStr(r: RectangleOTL): String =
    s"[topLeftX]:${r.xTopLeft},[topLeftY]:${r.yTopLeft},[width]:${r.widthRight},[height]:${r.heightDown}"

  //a bit of caution: this works only for axes parallel rectangles.
  // That suffice for our purpose, but this isn't a generic method.
  def rectDistance(r1: RectangleOTL, r2: RectangleOTL): Float = {
    val r1x1 = r1.xTopLeft
    val r1y1 = r1.yTopLeft
    val r1x2 = r1.xTopLeft + r1.widthRight
    val r1y2 = r1.yTopLeft + r1.heightDown
    val r2x1 = r2.xTopLeft
    val r2y1 = r2.yTopLeft
    val r2x2 = r2.xTopLeft + r2.widthRight
    val r2y2 = r2.yTopLeft + r2.heightDown

    val dy1 = if (r1y2 < r2y1) r2y1 - r1y2 else 0
    val dy2 = if (r1y1 > r2y2) r1y1 - r2y2 else 0
    val dx1 = if (r1x2 < r2x1) r2x1 - r1x2 else 0
    val dx2 = if (r1x1 > r2x2) r1x1 - r2x2 else 0
    dx1 + dx2 + dy1 + dy2

    dx1 + dx2 + dy1 + dy2
  }
}

