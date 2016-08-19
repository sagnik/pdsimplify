package edu.psu.sagnik.research.pdwriters.impl

import java.awt.Color
import java.io.File

import edu.psu.sagnik.research.pdsimplify.model.Rectangle
import org.apache.pdfbox.pdmodel.PDDocument
import java.util.logging.{Level, Logger}

import edu.psu.sagnik.research.pdsimplify.impl.ProcessDocument
import edu.psu.sagnik.research.pdsimplify.path.model.PDPath
import edu.psu.sagnik.research.pdsimplify.raster.model.PDRasterImage
import edu.psu.sagnik.research.pdsimplify.text.model.PDParagraph
import edu.psu.sagnik.research.pdwriters.writers.image.CreateMarkedPNG
import edu.psu.sagnik.research.pdwriters.writers.pdf.CreateMarkedPDF

import scala.util.{Failure, Success}

/**
 * Created by schoudhury on 6/27/16.
 */
object ShowResults {

  lazy val logger = Logger.getLogger("pdwriters.writer.ShowResults")
  logger.setLevel(Level.ALL)

  def printExtractionResult(pdLoc:String,pageNum:Int,bbs:List[Rectangle],c:Color,qualifier:String)={
    val document = PDDocument.load(new File(pdLoc))
    val page = document.getPage(pageNum)
    CreateMarkedPDF(pdLoc,document,pageNum,page,bbs,c,qualifier)
    logger.fine(s"created ${qualifier.substring(0,qualifier.length-1)} marked PDF")
  }

  def main(args:Array[String]):Unit={
    val DEFAULT_LOC="/Users/schoudhury/codes/res-doc-sci/res-app-cmd/src/test/resources/008baad5-3fdb-4aea-9311-3ef7499e3f1f.pdf"
      //"/Users/schoudhury/hassan/C10-2042.pdf"
    val DEFAULT_PAGE_NUM=3

    val pdLoc=if (args.length >1 ) args(0) else DEFAULT_LOC
    val pageNum=if (args.length ==2 ) args(1).toInt else DEFAULT_PAGE_NUM


    val document = PDDocument.load(new File(pdLoc))
    val page = document.getPage(pageNum)

    val SimplifiedDocument=ProcessDocument(document)

    val paragraphs=SimplifiedDocument.pages(pageNum).paragraphs
    val rasters=SimplifiedDocument.pages(pageNum).rasters
    val graphicsPaths=SimplifiedDocument.pages(pageNum).gPaths


    val segments=graphicsPaths
      .filter(x=> x.doPaint)
      .flatMap(x=>x.subPaths)
      .flatMap(x=>x.segments)

    //TODO: check for comprehensions.
    val chars=paragraphs.flatMap(_.tLines).flatMap(_.tWords).flatMap(_.chars)
    val words=paragraphs.flatMap(_.tLines).flatMap(_.tWords)
    val lines=paragraphs.flatMap(_.tLines)

    //words.foreach(x=>println(x.content,x.bb))
    //create SVG here

//    new CreateSVG().apply(
//      chars,
//      pdLoc.substring(0,pdLoc.lastIndexOf("."))+"-page-chars-"+pageNum+".svg",
//      page.getBBox.getWidth,
//      page.getBBox.getHeight
//    )
//    logger.info("created char SVG")



    printExtractionResult(pdLoc,pageNum,chars.map(_.bb),Color.BLUE,"chars")

    printExtractionResult(pdLoc,pageNum,words.map(_.bb),Color.GREEN,"words")

    printExtractionResult(pdLoc,pageNum,lines.map(_.bb),Color.RED,"lines")

    printExtractionResult(pdLoc,pageNum,paragraphs.map(_.bb),Color.CYAN,"paragraphs")

    printExtractionResult(pdLoc,pageNum,rasters.map(_.bb),Color.MAGENTA,"rasters")

    printExtractionResult(pdLoc,pageNum,segments.map(_.bb),Color.PINK,"paths")

    document.close()

  }
}
