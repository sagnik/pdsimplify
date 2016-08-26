package edu.psu.sagnik.research.pdsimplify.path.impl

import edu.psu.sagnik.research.pdsimplify.path.model.PathStyle
import org.apache.pdfbox.pdmodel.graphics.color.PDColor
import org.apache.pdfbox.pdmodel.graphics.state.PDGraphicsState

/**
 * Created by sagnik on 6/28/16.
 */
object CreatePathStyle {

  //TODO:Possible exception?
  def getHexRGB(color: PDColor): String = {
    val rgb = color.toRGB //this is packed RGB
    //http://stackoverflow.com/questions/4801366/convert-rgb-values-into-integer-pixel
    val r = (rgb >> 16) & 0xFF
    val g = (rgb >> 8) & 0xFF
    val b = rgb & 0xFF

    f"#${r}%02x${g}%02x${b}%02x"
  }

  def apply(gs: PDGraphicsState): PathStyle = {
    //See https://www.w3.org/TR/SVG/painting.html#FillOpacityProperty for fillRule.
    PathStyle(
      fill = Some(getHexRGB(gs.getNonStrokingColor)),
      fillRule = None,
      fillOpacity = None, //TODO: we don't know what to do here, will use default value (1) while printing
      stroke = Some(getHexRGB(gs.getStrokingColor)),
      strokeWidth = Some((gs.getLineWidth * gs.getCurrentTransformationMatrix.getScaleX).toString), //TODO: this is kind of a hack. Need
      //to look more into how scaling affects line width
      strokeLineCap = gs.getLineCap match {
        case 0 => Some("butt")
        case 1 => Some("round")
        case 2 => Some("square")
        case _ => None
      },
      strokeLineJoin = gs.getLineJoin match {
        case 0 => Some("miter")
        case 1 => Some("round")
        case 2 => Some("bevel")
        case _ => None
      },
      strokeMiterLimit = Some(gs.getMiterLimit.toString),
      strokeDashArray = if (gs.getLineDashPattern.getDashArray().length == 2)
        Some(gs.getLineDashPattern.getDashArray().toList(0) + ", " + gs.getLineDashPattern.getDashArray().toList(1))
      else None,
      strokeDashOffset = Some(gs.getLineDashPattern.getPhase.toString), //TODO:check
      strokeOpacity = None
    )

  }
}
