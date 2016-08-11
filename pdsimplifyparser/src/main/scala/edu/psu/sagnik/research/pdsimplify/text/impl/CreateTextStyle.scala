package edu.psu.sagnik.research.pdsimplify.text.impl

import java.util.logging.{ Level, Logger }

import edu.psu.sagnik.research.pdsimplify.path.impl.CreatePathStyle
import edu.psu.sagnik.research.pdsimplify.text.model.{ PDChar, PDCharStyle, PDFontInfo }
import org.apache.pdfbox.pdmodel.graphics.state.PDGraphicsState
import org.apache.pdfbox.text.TextPosition

import scala.util.{ Failure, Success, Try }

/**
 * Created by schoudhury on 6/30/16.
 */
object CreateTextStyle {
  lazy val logger = Logger.getLogger("pdsimplify.text.impl.CreateTextStyle")
  logger.setLevel(Level.ALL)
  //TODO: change these to proper default values
  val DEFAULT_FONT_NAME = "times new roman"
  val DEFAULT_FONT_SIZE = 10f
  val DEFAULT_FONT_FAMILY = "times"
  val DEFAULT_IS_BOLD = false
  val DEFAULT_IS_ITALIC = false
  val DEFAULT_FONT_WEIGHT = 100f
  val DEFAULT_ROTATION = 0f

  def apply(x: TextPosition, gs: PDGraphicsState): PDCharStyle = PDCharStyle(
    font = PDFontInfo(
      fontName = Try(x.getFont.getName) match { case Success(v) => v; case Failure(e) => logger.warning(e.getMessage); DEFAULT_FONT_NAME },
      fontSize = Try(x.getFontSizeInPt) match { case Success(v) => v; case Failure(e) => logger.warning(e.getMessage); DEFAULT_FONT_SIZE },
      fontFamily = Try(x.getFont.getFontDescriptor.getFontFamily) match { case Success(v) => v; case Failure(e) => logger.warning(e.getMessage); DEFAULT_FONT_FAMILY },
      isBold = Try(x.getFont.getFontDescriptor.isForceBold) match { case Success(v) => v; case Failure(e) => logger.warning(e.getMessage); DEFAULT_IS_BOLD }, //TODO: text can be made look bold or italic by modifying the text or the text line matrix
      isItalic = Try(x.getFont.getFontDescriptor.isItalic) match { case Success(v) => v; case Failure(e) => logger.warning(e.getMessage); DEFAULT_IS_ITALIC },
      fontWeight = Try(x.getFont.getFontDescriptor.getFontWeight) match { case Success(v) => v; case Failure(e) => logger.warning(e.getMessage); DEFAULT_FONT_WEIGHT }
    ),
    fill = if (gs.getTextState.getRenderingMode.isFill)
      CreatePathStyle.getHexRGB(gs.getNonStrokingColor)
    else
      "none",
    fillRule = "nonzero", //an approximation here. TODO: see how this can be fixed
    fillOpacity = "1", //a default value because I haven't read about the opacity model in PDF yet.
    stroke = if (gs.getTextState.getRenderingMode.isStroke)
      CreatePathStyle.getHexRGB(gs.getStrokingColor)
    else
      "none",
    rotation = x.getDir
  )
}
