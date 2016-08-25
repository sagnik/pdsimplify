package edu.psu.sagnik.research.pdsimplify.raster.model

import java.awt.image.BufferedImage

import edu.psu.sagnik.research.data.RectangleOTL

/**
 * Created by sagnik on 6/29/16.
 */
case class PDRasterImage(image: BufferedImage, imageDataString: String, bb: RectangleOTL)