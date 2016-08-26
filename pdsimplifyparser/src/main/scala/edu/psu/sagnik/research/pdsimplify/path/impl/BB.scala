package edu.psu.sagnik.research.pdsimplify.path.impl

import java.awt.geom.Point2D

import edu.psu.sagnik.research.data.RectangleOTL

/**
 * Created by schoudhury on 6/28/16.
 */

object BB {
  def Line(p0: Point2D.Float, p1: Point2D.Float, pageHeight: Float) = {
    val xMin = scala.math.min(p0.x, p1.x)
    val yMin = scala.math.min(p0.y, p1.y)
    val xMax = scala.math.max(p0.x, p1.x)
    val yMax = scala.math.max(p0.y, p1.y)
    RectangleOTL(
      xTopLeft = xMin,
      yTopLeft = pageHeight - yMax,
      widthRight = xMax - xMin,
      heightDown = yMax - yMin
    )
  }

  def Curve(start: Point2D.Float, end: Point2D.Float, cp1: Point2D.Float, cp2: Point2D.Float, pageHeight: Float) = {
    val xs = List(start.x, end.x, cp1.x, cp2.x)
    val ys = List(start.y, end.y, cp1.y, cp2.y)
    val xTopLeft = xs.min
    RectangleOTL(
      xTopLeft = xTopLeft,
      yTopLeft = pageHeight - ys.max,
      widthRight = xs.max - xs.min,
      heightDown = ys.max - ys.min
    )
  }

}
