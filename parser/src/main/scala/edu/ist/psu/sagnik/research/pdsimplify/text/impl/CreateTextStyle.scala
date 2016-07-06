package edu.ist.psu.sagnik.research.pdsimplify.text.impl

import edu.ist.psu.sagnik.research.pdsimplify.path.impl.CreatePathStyle
import edu.ist.psu.sagnik.research.pdsimplify.text.model.{PDChar, PDCharStyle, PDFontInfo}
import org.apache.pdfbox.pdmodel.graphics.state.PDGraphicsState
import org.apache.pdfbox.text.TextPosition

/**
  * Created by schoudhury on 6/30/16.
  */
object CreateTextStyle {

  def apply(x:TextPosition,gs:PDGraphicsState):PDCharStyle=PDCharStyle(
    font=PDFontInfo(
      fontName= x.getFont.getName,
      fontSize = x.getFontSizeInPt,
      fontFamily = x.getFont.getFontDescriptor.getFontFamily,
      isBold=x.getFont.getFontDescriptor.isForceBold, //TODO: text can be made look bold or italic by modifying the text or the text line matrix
      isItalic=x.getFont.getFontDescriptor.isItalic,
      fontWeight=x.getFont.getFontDescriptor.getFontWeight
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
