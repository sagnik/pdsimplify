package edu.psu.sagnik.research.pdsimplify.rectanglehelpers

import edu.psu.sagnik.research.data.RectangleOTL

/**
  * Created by schoudhury on 8/18/16.
  */
object RectangleHelpers {

  def round(x:Float)=scala.math.round(x)

  /**
    * Evaluates to a string that contains the Rectangle's
    * point information as x1,y2,x2,y2 .
    */
  def asCoordinatesStr(r: RectangleOTL): String =
    s"[topLeftX]:${r.xTopLeft},[topLeftY]:${r.yTopLeft},[width]:${r.widthRight},[height]:${r.heightDown}"


  //a bit of caution: this works only for axes parallel rectangles.
  // That suffice for our purpose, but this isn't a generic method.
  def rectDistance(r1:RectangleOTL,r2:RectangleOTL):Float={
    val dy1=if (r1.y2<r2.y1) r2.y1-r1.y2 else 0
    val dy2=if (r1.y1>r2.y2) r1.y1-r2.y2 else 0
    val dx1=if (r1.x2<r2.x1) r2.x1-r1.x2 else 0
    val dx2=if (r1.x1>r2.x2) r1.x1-r2.x2 else 0
    dx1+dx2+dy1+dy2
  }

  def rectExtend(r:Rectangle,rs:Seq[Rectangle],checkAgainstRS:Seq[Rectangle],pageBB:Rectangle,direction:String):Option[Rectangle]=
    if (
      !rs.exists(a=>rectInterSects(a,r)) && //haven't found any intersection with body or caption text boxes
        (r.x1>pageBB.x1 && r.x2<pageBB.x2 && r.y1>pageBB.y1 && r.y2 < pageBB.y2) // all coordinates OK with pageBB
    )
      rectExtend(changeRect(r,1f,direction),rs,checkAgainstRS,pageBB,direction)
    else {
      if (checkAgainstRS.filter(a=>rectInterSects(Rectangle(r.x1+2,r.y1+2,r.x2-2,r.y2-2),a)).length>2 //we have got an extended rectangle.
        // Need to see if this is empty, or has a very small size
        && (r.x2-r.x1>10f && r.y2-r.y1> 10f)
      )
        Some(changeRect(r,-1f,direction))
      else
        None
    }

  def changeRect(r:Rectangle,changeparam:Float,direction:String):Rectangle=
    direction match {
      case "left" => r.copy(x1 = r.x1 - changeparam)
      case "right" => r.copy(x2 = r.x2 + changeparam)
      case "top" => r.copy(y2 = r.y2 + changeparam)
      case "down" => r.copy(y1 = r.y1 - changeparam)
      case _ => r
    }


  /*
  TODO: test these methods
  * */
  def rectVerticalDistance(r1:Rectangle,r2:Rectangle):Float={
    val dy1=if (r1.y2<r2.y1) r2.y1-r1.y2 else 0
    val dy2=if (r1.y1>r2.y2) r1.y1-r2.y2 else 0
    dy1+dy2
  }

  def rectHorizontalDistance(r1:Rectangle,r2:Rectangle):Float={
    val dx1=if (r1.x2<r2.x1) r2.x1-r1.x2 else 0
    val dx2=if (r1.x1>r2.x2) r1.x1-r2.x2 else 0
    dx1+dx2
  }

}
*/
