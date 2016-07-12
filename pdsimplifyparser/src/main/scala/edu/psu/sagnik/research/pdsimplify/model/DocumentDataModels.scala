package edu.psu.sagnik.research.pdsimplify.model

import edu.psu.sagnik.research.pdsimplify.path.model.PDPath
import edu.psu.sagnik.research.pdsimplify.raster.model.PDRasterImage
import edu.psu.sagnik.research.pdsimplify.text.model.PDParagraph

/**
  * Created by schoudhury on 7/1/16.
  */

case class PDPageSimple(pNum:Int,
                        paragraphs:List[PDParagraph],
                        gPaths:List[PDPath],
                        rasters:List[PDRasterImage],
                        bb:Rectangle
                       )

case class PDDocumentSimple(pages:List[PDPageSimple])