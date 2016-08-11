// shared settings across root & all subprojects

version in ThisBuild := {
  val major = 0
  val minor = 0
  val patch = 11
  s"$major.$minor.$patch"
} //page size now conforms to our rectangle structure.

scalaVersion in ThisBuild := "2.11.8"

javacOptions += "-Xlint:unchecked"

organization := "edu.psu.sagnik.research"

name := "pdsimplify"

lazy val root = project
  .in(file("."))
  .aggregate(
    schema,
    pdsimplifyparser,
    writers
 )
  .settings(publishArtifact := false)

lazy val schema = project
  .in(file("schema"))
  .settings(publishArtifact := false)

lazy val pdsimplifyparser = project
  .in(file("pdsimplifyparser"))
  .dependsOn(schema)
  .settings(publishArtifact := true)

lazy val writers = project
  .in(file("writers"))
  .dependsOn(pdsimplifyparser)
  .settings(publishArtifact := false)

lazy val subprojects: Seq[ProjectReference] = root.aggregate
lazy val publishTasks = subprojects.map{ r => publish.in(r) }

