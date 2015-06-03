net.virtualvoid.sbt.graph.Plugin.graphSettings

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

name := "akka-http-sample"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

val akkaV      = "2.4-SNAPSHOT"
val akkaHttpV  = "1.0-RC3"
val scalaTestV = "2.2.4"

lazy val `akka-http-sample` = (project in file(".")).enablePlugins(SbtTwirl)

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http-scala-experimental"          % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-testkit-scala-experimental"  % akkaHttpV % "test",
    "org.scalatest"     %% "scalatest"                             % scalaTestV % "test"
  )
