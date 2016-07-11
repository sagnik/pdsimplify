organization := "edu.psu.sagnik.research"

name := "pdsimplifyparser"

resolvers ++= Seq(
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Sonatype Shapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "JAI releases" at "http://maven.geotoolkit.org/"
)

libraryDependencies ++= Seq(
 // pdf parsing libraries
  "org.apache.pdfbox"    %  "pdfbox"          %  "2.0.2",
  "org.apache.pdfbox"    %   "pdfbox-tools"   %  "2.0.2",
 // testing
  "org.scalatest"        %% "scalatest"  %  "2.2.4",
  //log4j
  "log4j" % "log4j" % "1.2.15" excludeAll(
    ExclusionRule(organization = "com.sun.jdmk"),
    ExclusionRule(organization = "com.sun.jmx"),
    ExclusionRule(organization = "javax.jms")
    )
  )

fork := true

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

testOptions in Test += Tests.Argument("-oF")

fork in Test := false

parallelExecution in Test := false

