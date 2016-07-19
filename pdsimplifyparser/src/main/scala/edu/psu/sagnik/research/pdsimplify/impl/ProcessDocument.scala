package edu.psu.sagnik.research.pdsimplify.impl

import java.io.File

import edu.psu.sagnik.research.pdsimplify.model.{PDDocumentSimple, PDPageSimple, Rectangle}
import edu.psu.sagnik.research.pdsimplify.path.impl.ProcessPaths
import edu.psu.sagnik.research.pdsimplify.raster.impl.ProcessRaster
import edu.psu.sagnik.research.pdsimplify.text.impl.ProcessText
import org.apache.pdfbox.pdmodel.{PDDocument, PDPage}

/**
  * Created by schoudhury on 7/1/16.
  */
object ProcessDocument {

  def fromPage(page:PDPage,document:PDDocument,pNum:Int):PDPageSimple={
    val paragraphs=new ProcessText(page).stripPage(pNum,document)

    val imFinder=new ProcessRaster(page)
    imFinder.getImages()
    val rasters=imFinder.rasterImages

    val pathFinder=new ProcessPaths(page)
    pathFinder.getPaths()
    val pdGraphicsPaths=pathFinder.paths.filter(!_.isClip)

    PDPageSimple(
      pNum = pNum,
      paragraphs=paragraphs,
      gPaths=pdGraphicsPaths,
      rasters=rasters,
      bb=Rectangle(
        page.getBBox.getLowerLeftX,
        page.getBBox.getHeight-page.getBBox.getUpperRightY,
        page.getBBox.getUpperRightX,
        page.getBBox.getUpperRightY
      )
    )
  }

  def fromPDLocByPage(pdLoc:String,pNum:Int):PDPageSimple={
    val document = PDDocument.load(new File(pdLoc))
    val page = document.getPage(pNum)
    fromPage(page,document,pNum)
  }

  def fromPDLoc(pdLoc:String):List[PDPageSimple]={
    val document = PDDocument.load(new File(pdLoc))
    (0 until document.getNumberOfPages).toList.map(x=>fromPDLocByPage(pdLoc,x))
  }

  def fromPDDoc(document:PDDocument):List[PDPageSimple]=
    (0 until document.getNumberOfPages).toList.map(x=>fromPage(document.getPage(x),document,x))

  def apply(pdLoc:String):PDDocumentSimple=PDDocumentSimple(fromPDLoc(pdLoc))

  def apply(pdDoc:PDDocument):PDDocumentSimple=PDDocumentSimple(fromPDDoc(pdDoc))

}
