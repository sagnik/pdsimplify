// shared settings across root & all subprojects

version in ThisBuild := {
  val major = 0
  val minor = 0
  val patch = 1
  s"$major.$minor.$patch"
}

scalaVersion in ThisBuild := "2.11.8"

javacOptions += "-Xlint:unchecked"

organization := "edu.psu.sagnik.research"

name := "pdsimplify"

lazy val root = project
  .in(file("."))
  .aggregate(
    pdsimplifyparser, 
    writers
 )
  .settings(publishArtifact := false)

lazy val pdsimplifyparser = project
  .in(file("pdsimplifyparser"))
  .settings(publishArtifact := true)

lazy val writers = project
  .in(file("writers"))
  .dependsOn(pdsimplifyparser)
  .settings(publishArtifact := false)

lazy val subprojects: Seq[ProjectReference] = root.aggregate
lazy val publishTasks = subprojects.map{ r => publish.in(r) }

