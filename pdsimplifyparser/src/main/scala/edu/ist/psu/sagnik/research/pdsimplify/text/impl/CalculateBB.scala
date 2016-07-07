package edu.ist.psu.sagnik.research.pdsimplify.text.impl

import edu.ist.psu.sagnik.research.pdsimplify.model.{Rectangle}
import edu.ist.psu.sagnik.research.pdsimplify.text.model._

/**
  * Created by schoudhury on 6/27/16.
  */
object CalculateBB {

  def apply(texts:List[TextSegment]):Option[Rectangle]=
    if (texts.nonEmpty)
      Some(
        Rectangle(
          texts.map(x=>x.bb.x1).min,
          texts.map(x=>x.bb.y1).min,
          texts.map(x=>x.bb.x2).max,
          texts.map(x=>x.bb.y2).max
        )
      )
    else
      None


}
