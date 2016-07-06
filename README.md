##pdSimplify 
---------------------------------

###Goal: 
----------

Produce a simplified flat representation of a PDF: A sequence of pages where each page is a collection of three objects: graphics paths, images and text. A text object is a _hierarchical_ collection of four objects: paragraph -> line -> word -> character. Each object has a bounding box defined in the `user space` and uses the PDF coordinate system, i.e. the origin is `bottom left`.

------------

The main class for the purpose of testing is `edu.ist.psu.sagnik.research.pdwriters.impl.ShowResults`. Run it with two arguments: 

1. The location of the PDF file. (default is `src/test/resources/LoremIpsum.pdf`)
  
2. The page number you want to process. (default is `0`)
 
This will produce five PDFs of the form `<*-page-*>-chars,words,lines,paragraphs,rasters and paths.pdf` in the directory of the input PDF. These PDFs are marked with rectangles and self explanatory.

Data models for `text`,`raster` and `path` are explained in respective packages.

###TODO
------------

Serialized output as JSON, avro etc.