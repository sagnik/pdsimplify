// shared settings across root & all subprojects

version in ThisBuild := {
  val major = 0
  val minor = 0
  val patch = 1
  s"$major.$minor.$patch"
}

scalaVersion := "2.11.7"

javacOptions += "-Xlint:unchecked"

organization := "edu.ist.psu.sagnik.research"

name := "pdsimplify"

lazy val root = project
  .in(file("."))
  .aggregate(
    parser, 
    writers
 )
  .settings(publishArtifact := false)

lazy val parser = project
  .in(file("parser"))
  .settings(publishArtifact := true)

lazy val writers = project
  .in(file("writers"))
  .dependsOn(parser)
  .settings(publishArtifact := false)

lazy val subprojects: Seq[ProjectReference] = root.aggregate
lazy val publishTasks = subprojects.map{ r => publish.in(r) }


