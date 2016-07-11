package edu.psu.sagnik.research.pdwriters.writers.svg

import java.util.logging.{Level, Logger}

import edu.psu.sagnik.research.pdsimplify.path.model._
import edu.psu.sagnik.research.pdsimplify.text.model.PDChar

import scala.reflect.io.File

/**
 * Created by schoudhury on 6/17/16.
 */
class CreateSVG[A] {

  lazy val logger = Logger.getLogger("ProcessPaths")
  logger.setLevel(Level.ALL)


  def getSvgString[A](p:A,w:Float,h:Float):String=p match{
    case p:PDPath => "<path "+PathHelper.getPathDString(p,h)+" "+PathHelper.getStyleString(p.pathStyle)+" />"
    case p:PDChar => "<text "+TextHelper.getStyleString(p)+" "+TextHelper.getTransformString(p)+" "+TextHelper.getLocationString(p,h)+ ">"+
      TextHelper.replaceSpecialChars(p.content)+"</text>"
  }

  def apply(sequence:List[A], svgLoc:String, width:Float, height:Float):Unit={
    val svgStart="<?xml version=\"1.0\" standalone=\"no\"?>\n\n<svg height=\"" +
      height +
      "\" width=\"" +
      width +
      "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">"+
      "\n"

    val first=sequence.headOption
    first match{
    case Some(first) => val content = first match {
      case first: PDPath => sequence.map(x => getSvgString(x, width, height)).foldLeft("")((a, b) => a + "\n" + b) + "\n"
      case first: PDChar => sequence.map(x => getSvgString(x, width, height)).foldLeft("")((a, b) => a + "\n" + b) + "\n"
      case _ => {logger.info(s"${first.getClass}");???}
    }
      val svgEnd = "\n</svg>"
      File(svgLoc).writeAll(svgStart + content + svgEnd)
    case _ => {}
    }
  }


}
