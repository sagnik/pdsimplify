package edu.psu.sagnik.research.pdwriters.writers.json

import java.awt.geom.Point2D

import edu.psu.sagnik.research.data.RectangleOTL
import edu.psu.sagnik.research.pdsimplify.model.PDDocumentSimple
import edu.psu.sagnik.research.pdsimplify.path.model.PDLine
import org.json4s.JsonDSL._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

/**
 * Created by schoudhury on 7/6/16.
 */
object CreateTextLinesJSON {

  def precReduce(d: Float): Float = BigDecimal(d).setScale(2, BigDecimal.RoundingMode.HALF_UP).toFloat

  def precReduce(point: Point2D.Float): List[Float] = List(precReduce(point.x), precReduce(point.y))

  def precReduce(r: RectangleOTL): List[Float] = List(precReduce(r.xTopLeft), precReduce(r.yTopLeft), precReduce(r.widthRight), precReduce(r.heightDown))

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

  def apply(pDS: PDDocumentSimple): String = {
    val jsonContent =
      "pages" ->
        pDS.pages.map {
          p =>
            (
              ("pageNumber" -> p.pNum) ~
              ("pageBB" -> precReduce(p.bb)) ~
              ("textparagraphs" -> p.paragraphs.map {
                pp =>
                  (
                    ("bb" -> precReduce(pp.bb)) ~
                    ("textlines" -> pp.tLines.map {
                      tl =>
                        (
                          ("bb" -> precReduce(tl.bb)) ~
                          ("content" -> tl.content)
                        )
                    })
                  )
              })

            )
        }
    pretty(render(jsonContent))
  }

}
