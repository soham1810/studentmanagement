name := """studentmanagement"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.elasticsearch" % "elasticsearch" % "7.9.3"
libraryDependencies += "org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "7.9.3"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.32"
//libraryDependencies ++= Seq(
//  javaJpa,
//  "org.hibernate" % "hibernate-core" % "5.4.18.Final",
//  "mysql" % "mysql-connector-java" % "8.0.25"
libraryDependencies += jdbc

