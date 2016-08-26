package edu.psu.sagnik.research.pdsimplify.path.model

import java.awt.geom.Point2D

import edu.psu.sagnik.research.data.RectangleOTL

/**
 * Created by schoudhury on 6/15/16.
 */

/*
From PDF Spec 1.7, Page 225
---------------------------
A path is composed of straight and curved line segments, which may connect to
one another or may be disconnected. A pair of segments are said to connect only
if they are defined consecutively, with the second segment starting where the first
one ends. Thus, the order in which the segments of a path are defined is significant.
Nonconsecutive segments that meet or intersect fortuitously are not considered
to connect.
A path is made up of one or more disconnected subpaths, each comprising a sequence
of connected segments. The topology of the path is unrestricted: it may be
concave or convex, may contain multiple subpaths representing disjoint areas,
and may intersect itself in arbitrary ways. The h operator explicitly connects the
end of a subpath back to its starting point; such a subpath is said to be closed. A
subpath that has not been explicitly closed is open.
As discussed in Section 4.1, “Graphics Objects,” a path object is defined by a sequence
of operators to construct the path, followed by one or more operators to
paint the path or to use it as a clipping boundary.
 */

trait PDSegment {
  def startPoint: Point2D.Float
  def endPoint: Point2D.Float
  def bb: RectangleOTL
}

case class PDLine(startPoint: Point2D.Float, endPoint: Point2D.Float, bb: RectangleOTL) extends PDSegment
case class PDCurve(startPoint: Point2D.Float, endPoint: Point2D.Float, controlPoint1: Point2D.Float, controlPoint2: Point2D.Float, bb: RectangleOTL) extends PDSegment

//a PDShape is a subpath actually
case class PDShape(segments: List[PDSegment], fromReCommand: Boolean)

case class PDPath(subPaths: List[PDShape], isClip: Boolean, doPaint: Boolean, windingRule: Int, pathStyle: PathStyle)

//this comes from the graphics state
case class PathStyle(
  fill: Option[String],
  fillRule: Option[String],
  fillOpacity: Option[String],
  stroke: Option[String],
  strokeWidth: Option[String],
  strokeLineCap: Option[String],
  strokeLineJoin: Option[String],
  strokeMiterLimit: Option[String],
  strokeDashArray: Option[String],
  strokeDashOffset: Option[String],
  strokeOpacity: Option[String]
)

