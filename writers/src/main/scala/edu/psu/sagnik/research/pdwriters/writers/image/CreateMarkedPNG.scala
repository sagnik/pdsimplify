package edu.psu.sagnik.research.pdwriters.writers.image

import java.awt.geom.Rectangle2D
import java.awt.{BasicStroke, Color}
import java.io.{File}
import java.util.logging.{Level, Logger}
import javax.imageio.ImageIO

import edu.psu.sagnik.research.data.RectangleOTL
import org.apache.pdfbox.pdmodel.{PDDocument, PDPage}
import org.apache.pdfbox.rendering.PDFRenderer

/**
 * Created by schoudhury on 6/27/16.
 */

object CreateMarkedPNG {

  lazy val logger = Logger.getLogger("pdwriters.writers.image.CreateMarkedPNG")
  logger.setLevel(Level.ALL)

  def apply(docLoc: String, document: PDDocument, pageNum: Int, page: PDPage, bbs: List[RectangleOTL], color: Color, tElemType: String): Unit = {
    val SCALE = 1
    val image = new PDFRenderer(document).renderImage(pageNum, SCALE)
    val g2d = image.createGraphics
    g2d.setStroke(new BasicStroke(0.1f))
    g2d.scale(SCALE, SCALE)
    g2d.setColor(color)
    document.close()

    val pH = page.getBBox.getHeight

    for (bb <- bbs) {
      logger.info(s"[$tElemType] ${(bb.xTopLeft, bb.yTopLeft, bb.widthRight, bb.heightDown)}")
      g2d.draw(new Rectangle2D.Float(bb.xTopLeft, bb.yTopLeft, bb.widthRight, bb.heightDown))
    }

    g2d.dispose()

    ImageIO.write(image, "png", new File(docLoc.substring(0, docLoc.length - 4) + "-page-" + pageNum + "-" + tElemType + ".png"))
    println(s"[created]: ${docLoc.substring(0, docLoc.length - 4) + "-page-" + pageNum + "-" + tElemType + ".png"}")
  }

}
