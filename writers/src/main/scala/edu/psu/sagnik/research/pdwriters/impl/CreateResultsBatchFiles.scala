package edu.psu.sagnik.research.pdwriters.impl

import java.io.{ BufferedWriter, File, FileWriter }

import edu.psu.sagnik.research.pdsimplify.impl.ProcessDocument
import edu.psu.sagnik.research.pdsimplify.model.PDDocumentSimple
import edu.psu.sagnik.research.pdwriters.writers.json.CreateTextLinesJSON
import org.apache.pdfbox.pdmodel.PDDocument

/**
 * Created by sagnik on 7/7/17.
 */

object CreateResultsBatchFiles {

  def writeTextLinesJson(fileName: String, simplifiedDocument: PDDocumentSimple) = {
    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(CreateTextLinesJSON(simplifiedDocument))
    println(s"JSON file written at: ${fileName}")
    bw.close()
  }

  import scala.util.matching.Regex
  def recursiveListFiles(f: File, r: Regex): Array[File] = {
    val these = f.listFiles
    val good = these.filter(f => r.findFirstIn(f.getName).isDefined)
    good ++ these.filter(_.isDirectory).flatMap(recursiveListFiles(_, r))
  }

  /*
  def main(args: Array[String]): Unit = {
    val DEFAULT_DIR = "/media/sagnik/OS_Install/data/pdfsamples/nuance/nuancepdf/"
    //"/home/szr163/Downloads/f706gsd1.pdf"
    //"/Users/schoudhury/hassan/C10-2042.pdf"
    val pdDir = if (args.length > 1) args(0) else DEFAULT_DIR

    val docs = recursiveListFiles(new File(pdDir), "pdf".r).map(_.getAbsolutePath)
    docs.foreach { x =>
      val document = PDDocument.load(new File(x))
      val jsonLoc = s"${x.substring(0, x.length - 3)}json"
      val simplifiedDocument = ProcessDocument(document)
      writeTextLinesJson(jsonLoc, simplifiedDocument)
      document.close()
    }
  }
*/

  def main(args: Array[String]): Unit = {
    val DEFAULT_DIR = "/media/sagnik/OS_Install/data/pdfsamples/nuance/nuancepdf/"
    //"/home/szr163/Downloads/f706gsd1.pdf"
    //"/Users/schoudhury/hassan/C10-2042.pdf"
    val pdDir = if (args.length > 1) args(0) else DEFAULT_DIR

    val docs = recursiveListFiles(new File(pdDir), "pdf".r).map(_.getAbsolutePath)
    docs.foreach { x =>
      var document = PDDocument.load(new File(x))
      val simplifiedDocument = ProcessDocument(document)
      (0 until simplifiedDocument.pages.size).foreach(pageNum =>
        document = CreateResultsBatch.createMarkedResult(
          document,
          simplifiedDocument,
          pageNum = pageNum,
          pdLoc = x
        ))
      document.save(s"${x.dropRight(4)}-marked.pdf")
      println(s"[marked document written to]: ${x.dropRight(4)}-marked.pdf")
      document.close()
      document.close()
    }
  }
}
