package edu.psu.sagnik.research.pdwriters.writers.pdf

import java.awt.Color
import java.io.IOException

import edu.psu.sagnik.research.pdsimplify.model.Rectangle
import org.apache.pdfbox.pdmodel.{PDDocument, PDPage, PDPageContentStream}

/**
  * Created by schoudhury on 6/27/16.
  */


object CreateMarkedPDF {
  @throws[IOException]
  private def drawRect(content: PDPageContentStream, color: Color, rect: Rectangle, fill: Boolean) {
   //println(rect+" : "+color.)
   content.addRect(rect.x1, rect.y1, rect.x2-rect.x1,rect.y1-rect.y2)
    if (fill) {
      content.setNonStrokingColor(color)
      content.fill()
    }
    else {
      content.setStrokingColor(color)
      content.stroke()
    }
  }

  def apply(docLoc:String,document:PDDocument,pageNum:Int,page:PDPage,bbs:List[Rectangle],color:Color,tElemType:String):Unit={
    val content = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false)
    bbs.foreach (bb => drawRect (content, color, bb, fill=false))

    content.close()
    document.save(docLoc.substring(0, docLoc.length - 4) + "-page-"+pageNum+"-"+tElemType+".pdf")
    println(s"[created]: ${docLoc.substring(0, docLoc.length - 4) + "-page-"+pageNum+"-"+tElemType+".pdf"}")
  }

}
