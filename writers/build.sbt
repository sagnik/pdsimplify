organization := "edu.psu.sagnik.research"

libraryDependencies ++= Seq(
   //jackson for json
  "org.json4s" %% "json4s-native" % "3.2.11",
  "org.json4s" %% "json4s-jackson" % "3.2.10",
  // testing
  "org.scalatest"        %% "scalatest"  %  "2.2.4"
 )

fork := true

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

testOptions in Test += Tests.Argument("-oF")

fork in Test := false

parallelExecution in Test := false

