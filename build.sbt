scalaVersion := "2.12.3"

name := "hello-world"
organization := "ch.epfl.scala"
version := "1.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats" % "0.9.0",
  "com.github.tototoshi" %% "scala-csv" % "1.3.5",
  "org.json4s" %% "json4s-jackson" % "3.5.3"
)
