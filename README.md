##pdSimplify 
---------------------------------

###Goal: 
----------

Produce a simplified flat representation of a PDF: A sequence of pages where each page is a collection of three objects: graphics paths, images and text. 

###Data Models
---------------
Data models for `text`,`raster` and `path` are explained in respective packages, but are discussed here for clarity.

1. A `path` object is equivalent to a graphics path in a PDF. It is a _sequence_ of `subpath` objects. A `subpath` object can be a `rectangle` or any other `shape` that is a sequence of `segments`. A `segment` object is either a `line` or a `curve`. A `line` object has two end points. A `curve` object has the end points and two control points. A `segment` has a `style` object that pulls in multiple attributes (stroke/ fill color, stroke width etc.) from the _current graphics state_ (the graphics state when the path was drawn). Each such `segment` has a `bounding box`. You could convert a PDF path to an _SVG path_ using this representation. 

2. A `text` object is a _hierarchical_ collection of four objects: paragraph -> line -> word -> character. Each object has a `content` and `bounding box` variable. PDF just has the concept of `glyphs`. Therefore, all of these higher constructs are heuristic based approximations (from `PDFBox` and my code). For characters, you can have the font information and if the font is not sufficiently weird (most of the times they are), some other style information such as whether the font was **bold** or _italicized_ . 

3. An `image` object has the image data as both `BufferedImage` and `String` and the `bounding box`. This is again helpful if you want to convert the PDF page to an SVG.  

A bounding box is a rectangle (`x1,y1,x2,y2`) defined in the `user space` and uses the PDF coordinate system, i.e. the origin is `bottom left`.

###Testing
-----------
The main class for the purpose of testing is `edu.psu.sagnik.research.pdwriters.impl.ShowResults`. Run it with two arguments: 

1. The location of the PDF file. (default is `src/test/resources/LoremIpsum.pdf`)
  
2. The page number you want to process. (default is `0`)
 
This will produce five PDFs of the form `<*-page-*>-chars,words,lines,paragraphs,rasters and paths.pdf` in the directory of the input PDF. These PDFs are marked with rectangles and self explanatory.


###How to Use
------------
Clone this repo and do `publishLocal`. Put the line `"edu.psu.sagnik.research" %% "pdsimplifyparser" % "0.0.1"` in the `libraryDependencies` variable in your `build.sbt` to pull in the dependency. 
  
###TODO
------------

Serialized output as JSON, avro etc.

###Known Limitations
--------------------
1. We don't handle `clipping paths` or `crop box`. A clipping path (and a crop box) defines a region on the page. Any text or path within that region is painted, nothing else. If a PDF has contents that are not painted because they are outside these boxes, our code **will** extract them.
  
2. We don't handle text rendering modes. You can set text rendering mode for a text to 3 hence making it invisible. We will extract that text.
   
    