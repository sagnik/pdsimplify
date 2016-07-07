package edu.ist.psu.sagnik.research.pdsimplify.path.impl

import java.awt.geom.Point2D

import edu.ist.psu.sagnik.research.pdsimplify.model.Rectangle
import sun.java2d.loops.ProcessPath

/**
  * Created by schoudhury on 6/28/16.
  */

object BB {

  def Line(p0:Point2D.Float,p1:Point2D.Float)={
      val xs=List(p0.x,p1.x)
      val ys=List(p0.y,p1.y)
      Rectangle(
        xs.min,
        ys.min,
        xs.max,
        ys.max
      )
    }

  def Curve(start:Point2D.Float,end:Point2D.Float,cp1:Point2D.Float,cp2:Point2D.Float)={
    val xs=List(start.x,end.x,cp1.x,cp2.x)
    val ys=List(start.y,end.y,cp1.y,cp2.y)
    Rectangle(
      xs.min,
      ys.min,
      xs.max,
      ys.max
    )
  }


}
