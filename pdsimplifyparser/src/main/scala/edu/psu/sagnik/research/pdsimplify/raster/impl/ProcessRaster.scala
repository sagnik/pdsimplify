package edu.psu.sagnik.research.pdsimplify.raster.impl

import java.awt.geom.Point2D
import java.awt.image.BufferedImage
import java.io.{ ByteArrayOutputStream, IOException }
import javax.imageio.ImageIO
import javax.xml.bind.DatatypeConverter

import edu.psu.sagnik.research.data.RectangleOTL
import edu.psu.sagnik.research.pdsimplify.raster.model.PDRasterImage
import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine
import org.apache.pdfbox.cos.COSName
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.graphics.image.{ PDImage, PDImageXObject }

/**
 * Created by sagnik on 6/29/16.
 */
class ProcessRaster(page: PDPage) extends PDFGraphicsStreamEngine(page: PDPage) {
  var currentPoint = new Point2D.Float(0f, 0f)
  var rasterImages = List.empty[PDRasterImage]

  def getImages(): Unit = processPage(getPage)

  def getCTM = getGraphicsState.getCurrentTransformationMatrix

  @Override @throws[IOException]
  def appendRectangle(p0: Point2D, p1: Point2D, p2: Point2D, p3: Point2D) = {}

  @Override @throws[IOException]
  def clip(windingRule: Int) = {}

  @Override @throws[IOException]
  def moveTo(x: Float, y: Float) = {}

  @Override @throws[IOException]
  def lineTo(x: Float, y: Float) = {}

  @Override @throws[IOException]
  def curveTo(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float) = {}

  @Override @throws[IOException]
  def getCurrentPoint(): Point2D = currentPoint

  @Override @throws[IOException]
  def closePath() = {}

  @Override @throws[IOException]
  def endPath() = {}

  @Override @throws[IOException]
  def strokePath = {}

  @Override @throws[IOException]
  def fillPath(windingRule: Int) = {}

  @Override @throws[IOException]
  def fillAndStrokePath(windingRule: Int) = {}

  @Override @throws[IOException]
  def shadingFill(shadingName: COSName) = {}

  //see http://www.mkyong.com/java/how-to-convert-bufferedimage-to-byte-in-java/

  def getByteArray(img: BufferedImage): Array[Byte] = {
    val b = new ByteArrayOutputStream()
    ImageIO.write(img, "png", b)
    b.toByteArray
  }

  @Override @throws[IOException]
  def drawImage(pdImage: PDImage): Unit = rasterImages = {
    val image = pdImage.getImage
    rasterImages :+ PDRasterImage(
      image = image,
      imageDataString = DatatypeConverter.printBase64Binary(getByteArray(image)),

      bb = RectangleOTL(
        xTopLeft = getCTM.getTranslateX - page.getCropBox.getLowerLeftX,
        yTopLeft = getCTM.getTranslateY - page.getCropBox.getLowerLeftY,
        widthRight = getCTM.getScaleX,
        heightDown = getCTM.getScaleY
      )
    )
  }

}
