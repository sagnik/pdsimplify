package edu.psu.sagnik.research.pdsimplify.text.impl

import edu.psu.sagnik.research.data.RectangleOTL
import edu.psu.sagnik.research.pdsimplify.text.model._

/**
 * Created by schoudhury on 6/27/16.
 */
object CalculateBB {

  def apply(texts: List[TextSegment]): Option[RectangleOTL] =
    if (texts.nonEmpty) {
      val xTopLeft = texts.map(x => x.bb.xTopLeft).min
      val yTopLeft = texts.map(x => x.bb.yTopLeft).min
      val width = texts.map(t => t.bb.xTopLeft + t.bb.widthRight).max - xTopLeft
      val height = texts.map(t => t.bb.yTopLeft + t.bb.heightDown).max - xTopLeft
      Some(
        RectangleOTL(
          xTopLeft = xTopLeft,
          yTopLeft = yTopLeft,
          widthRight = width,
          heightDown = height
        )
      )
    } else
      None

}
