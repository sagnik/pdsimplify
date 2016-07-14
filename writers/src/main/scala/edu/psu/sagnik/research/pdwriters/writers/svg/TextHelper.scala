package edu.psu.sagnik.research.pdwriters.writers.svg

import edu.psu.sagnik.research.pdsimplify.text.model.PDChar

/**
  * Created by schoudhury on 6/30/16.
  */
object TextHelper {

  def italicNormal(f:Boolean)=if (f) "italic" else "normal"

  def getStyleString(c:PDChar):String="style=\""+
    "font-variant:" +
    "normal" + ";" +
    "font-style:" +
    italicNormal(c.style.font.isItalic) + ";" +
    "font-weight:" +
    c.style.font.fontWeight + ";" +
    "font-size:" +
    c.style.font.fontSize + "px;" +
    "font-family:" +
    c.style.font.fontFamily + ";" +
    "-inkscape-font-specification:" +
    c.style.font.fontName + ";" +
    "writing-mode:lr-tb;" +
    "fill:" +
    c.style.fill + ";" +
    "fill-opacity:" +
    c.style.fillOpacity + ";" +
    "fill-rule:" +
    c.style.fillRule + ";" +
    "stroke:" +
    c.style.stroke +
    "\""

  def getLocationString(c:PDChar,h:Float):String="y=\"" +
    (c.bb.y2) +
    "\" x=\"" +
    c.bb.x1 +
    "\">"

  def getTransformString(c:PDChar):String= "transform=\"rotate("+c.style.rotation.toString+")\""

  def replaceSpecialChars(c:String)={
    //see http://stackoverflow.com/questions/4237625/removing-invalid-xml-characters-from-a-string-in-java
    val xml10pattern = "[^" + "\u0009\r\n" + "\u0020-\uD7FF" + "\uE000-\uFFFD" + "\ud800\udc00-\udbff\udfff" + "]"
    c.replaceAll(xml10pattern, "")
      .replaceAll("&","&amp;")
      .replaceAll("<","&lt;")
      .replaceAll(">","&gt;")
      .replaceAll("\'","&apos;")
      .replaceAll("\"","&quot;")
  }

}
