organization := "edu.psu.sagnik.research"

name := "pdsimplifyparser"

resolvers ++= Seq(
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Sonatype Shapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "JAI releases" at "http://maven.geotoolkit.org/"
)

libraryDependencies ++= Seq(
 // pdf parsing libraries
  "org.apache.pdfbox"    %  "pdfbox"          %  "2.0.2" exclude("javax.jms", "jms") exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri"),
  "org.apache.pdfbox"    %   "pdfbox-tools"   %  "2.0.2" exclude("javax.jms", "jms") exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri"),
 // testing
  "org.scalatest"        %% "scalatest"  %  "2.2.4"
 )

fork := true

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

testOptions in Test += Tests.Argument("-oF")

fork in Test := false

parallelExecution in Test := false

