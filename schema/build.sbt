name := "schema"

import sbt._
import Keys._

lazy val scalaMacros = "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full
addCompilerPlugin(scalaMacros)

lazy val nitroOss = "com.gonitro"
lazy val avroCodegen = nitroOss          %% "avro-codegen-runtime" % "0.3.5"
lazy val shapeless   = "com.chuusai" %% "shapeless"            % "2.2.5"

libraryDependencies ++= Seq(
  shapeless,
  avroCodegen
)
