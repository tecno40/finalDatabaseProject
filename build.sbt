name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.avaje" % "ebean" % "2.7.3",
  "javax.persistence" % "persistence-api" % "1.0.2"
)
libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1212.jre7"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.35"
fork in run := true

fork in run := true
