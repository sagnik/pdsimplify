package edu.psu.sagnik.research.pdwriters.impl

import java.io.{ BufferedWriter, File, FileWriter }

import edu.psu.sagnik.research.pdsimplify.impl.ProcessDocument
import edu.psu.sagnik.research.pdsimplify.model.PDDocumentSimple
import edu.psu.sagnik.research.pdwriters.writers.json.CreateTextWordsJSON
import org.apache.pdfbox.pdmodel.PDDocument

/**
 * Created by sagnik on 2/23/18.
 */
object CreateResultsOneFile {
  def writeTextWordsJson(fileName: String, simplifiedDocument: PDDocumentSimple) = {
    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(CreateTextWordsJSON(simplifiedDocument))
    println(s"JSON file written at: ${fileName}")
    bw.close()
  }

  def main(args: Array[String]): Unit = {
    val pdfLoc = "/home/sagnik/aida-data/testing/test-2/test-2_ocr.pdf"
    val jsonLoc = "/home/sagnik/aida-data/testing/test-2/test-2_ocr.json"
    val document = PDDocument.load(new File(pdfLoc))
    val simplifiedDocument = ProcessDocument(document)
    writeTextWordsJson(fileName = jsonLoc, simplifiedDocument = simplifiedDocument)
  }

}
