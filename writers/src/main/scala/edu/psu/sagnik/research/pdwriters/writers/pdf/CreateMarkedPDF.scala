package edu.psu.sagnik.research.pdwriters.writers.pdf

import java.awt.Color
import java.io.IOException
import java.util.logging.{ Level, Logger }

import edu.psu.sagnik.research.data.RectangleOTL
import org.apache.pdfbox.pdmodel.{ PDDocument, PDPage, PDPageContentStream }
import org.apache.pdfbox.util.Matrix

/**
 * Created by schoudhury on 6/27/16.
 */

object CreateMarkedPDF {

  lazy val logger = Logger.getLogger("pdwriters.writers.image.CreateMarkedPDF")
  logger.setLevel(Level.ALL)

  @throws[IOException]
  private def drawRect(content: PDPageContentStream, color: Color, rect: RectangleOTL, page: PDPage, fill: Boolean) {
    content.addRect(
      rect.xTopLeft + page.getCropBox.getLowerLeftX,
      page.getCropBox.getHeight - rect.yTopLeft - rect.heightDown + page.getCropBox.getLowerLeftY,
      rect.widthRight,
      rect.heightDown
    )
    //remember the addRect is drawing a rectangle with x,y at bottom left. Also, we adjusted the rect for cropbox before. Since we are not changing the
    //content stream, that adjustment has to be _re_adjusted.
    if (fill) {
      content.setNonStrokingColor(color)
      content.fill()
    } else {
      content.setStrokingColor(color)
      content.stroke()
    }
  }

  def apply(docLoc: String, document: PDDocument, pageNum: Int, page: PDPage, bbs: List[RectangleOTL], color: Color, tElemType: String): Unit = {
    bbs.foreach(bb => {
      val content = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false)
      drawRect(content, color, bb, page, fill = false)
      content.close()
    })
    document.save(docLoc.substring(0, docLoc.length - 4) + "-page-" + pageNum + "-" + tElemType + ".pdf")
    println(s"[created]: ${docLoc.substring(0, docLoc.length - 4) + "-page-" + pageNum + "-" + tElemType + ".pdf"}")
    document.close()
  }

  def rectMarkedContent(document: PDDocument, page: PDPage, bbs: List[RectangleOTL], color: Color): PDDocument = {
    bbs.foreach(bb => {
      val content = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false)
      drawRect(content, color, bb, page, fill = false)
      content.close()
    })
    document
  }

}
