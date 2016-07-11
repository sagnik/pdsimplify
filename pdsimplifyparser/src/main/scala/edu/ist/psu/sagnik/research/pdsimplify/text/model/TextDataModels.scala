package edu.psu.sagnik.research.pdsimplify.text.model

import edu.psu.sagnik.research.pdsimplify.model.Rectangle
import org.apache.pdfbox.pdmodel.font.PDFont
import edu.psu.sagnik.research.pdsimplify.text.impl.CalculateBB
import org.apache.pdfbox.util.Matrix

/**
  * Created by schoudhury on 6/27/16.
  */
//why not PDChar.content is a String and not a char? PDChar is created by overriding the writeText method
//of the TextStripper, which processes the TextPosition object. TextPosition might
//contain a `string` instead of a char.

sealed trait TextSegment {
  def content: String
  def bb: Rectangle
}

case class PDFontInfo(fontName:String,
                      fontFamily:String,
                      fontSize:Float,
                      fontWeight:Float,
                      isBold:Boolean,
                      isItalic:Boolean)
// There is no easy way of knowing if a font is bold or Italic. But, some fonts do set a flag.
//See http://stackoverflow.com/questions/21561298/pdfbox-same-stream-with-bold-and-normal-text
// and http://developers.itextpdf.com/question/how-check-if-font-bold for discussions.
//To follow more about font, see org.apache.pdfbox.pdmodel.font.PDFontDescriptor.java

case class PDCharStyle(font:PDFontInfo,
                       fill:String,
                       fillOpacity:String,
                       fillRule:String,
                       stroke:String,
                       rotation:Float
                       )

case class PDChar(content:String,bb:Rectangle,glyphBB:Option[Rectangle],style:PDCharStyle) extends TextSegment

case class PDWord(content:String,bb:Rectangle,chars:List[PDChar]) extends TextSegment

case class PDTextLine(content:String,bb:Rectangle,tWords:List[PDWord]) extends TextSegment

case class PDParagraph(content:String,bb:Rectangle,tLines:List[PDTextLine]) extends TextSegment

