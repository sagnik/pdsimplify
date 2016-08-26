package edu.psu.sagnik.research.pdwriters.impl

import edu.psu.sagnik.research.pdsimplify.model.PDDocumentSimple

/**
 * Created by szr163 on 8/26/16.
 */
object CreateResultsBatch {

  import java.awt.Color
  import java.io.File

  import org.apache.pdfbox.pdmodel.PDDocument
  import java.util.logging.{ Level, Logger }

  import edu.psu.sagnik.research.data.RectangleOTL
  import edu.psu.sagnik.research.pdsimplify.impl.ProcessDocument
  import edu.psu.sagnik.research.pdwriters.writers.image.CreateMarkedPNG
  import edu.psu.sagnik.research.pdwriters.writers.pdf.CreateMarkedPDF

  import scala.util.{ Failure, Success }

  /**
   * Created by schoudhury on 6/27/16.
   */
  def createMarkedResult(document: PDDocument, SimplifiedDocument: PDDocumentSimple, pageNum: Int, pdLoc: String): PDDocument = {
    val paragraphs = SimplifiedDocument.pages(pageNum).paragraphs
    val rasters = SimplifiedDocument.pages(pageNum).rasters
    val graphicsPaths = SimplifiedDocument.pages(pageNum).gPaths

    val segments = graphicsPaths
      .filter(x => x.doPaint)
      .flatMap(x => x.subPaths)
      .flatMap(x => x.segments)

    //TODO: check for comprehensions.
    val chars = paragraphs.flatMap(_.tLines).flatMap(_.tWords).flatMap(_.chars)
    val words = paragraphs.flatMap(_.tLines).flatMap(_.tWords)
    val lines = paragraphs.flatMap(_.tLines)

    var doc = document

    doc = getExtractionResult(doc, pageNum, chars.map(_.bb), Color.BLUE)

    doc = getExtractionResult(doc, pageNum, words.map(_.bb), Color.GREEN)

    doc = getExtractionResult(doc, pageNum, lines.map(_.bb), Color.RED)

    doc = getExtractionResult(doc, pageNum, paragraphs.map(_.bb), Color.CYAN)

    doc = getExtractionResult(doc, pageNum, rasters.map(_.bb), Color.MAGENTA)

    doc = getExtractionResult(doc, pageNum, segments.map(_.bb), Color.PINK)

    doc
  }

  def getExtractionResult(document: PDDocument, pageNum: Int, bbs: List[RectangleOTL], c: Color): PDDocument = {
    val page = document.getPage(pageNum)
    CreateMarkedPDF.rectMarkedContent(document, page, bbs, c)
  }

  lazy val logger = Logger.getLogger("pdwriters.writer.ShowResults")
  logger.setLevel(Level.ALL)

  def printExtractionResult(pdLoc: String, pageNum: Int, bbs: List[RectangleOTL], c: Color, qualifier: String) = {
    val document = PDDocument.load(new File(pdLoc))
    val page = document.getPage(pageNum)
    CreateMarkedPDF(pdLoc, document, pageNum, page, bbs, c, qualifier)
    logger.fine(s"created ${qualifier.substring(0, qualifier.length - 1)} marked PDF")
  }

  def main(args: Array[String]): Unit = {
    val DEFAULT_LOC = "/home/szr163/Downloads/f706gsd1.pdf"
    //"/Users/schoudhury/hassan/C10-2042.pdf"
    val pdLoc = if (args.length > 1) args(0) else DEFAULT_LOC

    var document = PDDocument.load(new File(pdLoc))

    val simplifiedDocument = ProcessDocument(document)

    (0 until simplifiedDocument.pages.size).foreach(pageNum => document = createMarkedResult(document, simplifiedDocument, pageNum = pageNum, pdLoc = pdLoc))

    document.save(s"${pdLoc.dropRight(4)}-marked.pdf")
    println(s"[marked document written to]: ${pdLoc.dropRight(4)}-marked.pdf")
    document.close()

  }

}
