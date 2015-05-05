net.virtualvoid.sbt.graph.Plugin.graphSettings

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

name := "akka-http-sample"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

val akkaV      = "2.4-SNAPSHOT"
val akkaHttpV  = "1.0-M5"
val scalaTestV = "2.2.4"
val json4sV    = "3.2.11"

lazy val `akka-http-sample` = (project in file(".")).enablePlugins(SbtTwirl)

libraryDependencies ++= Seq(
//    "com.typesafe.akka" %% "akka-actor"                     % akkaV,
    "com.typesafe.akka" %% "akka-kernel"                     % akkaV,
//    "com.typesafe.akka" %% "akka-http-core-experimental"     % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-experimental"          % akkaHttpV,
//    "com.typesafe.akka" %% "akka-http-testkit-experimental" % akkaHttpV,
//    "org.json4s"        %% "json4s-native"                   % json4sV,
//    "org.json4s"        %% "json4s-jackson"                  % json4sV,
//    "org.json4s"        %% "json4s-ext"                     % json4sV,
    "org.scalatest"     %% "scalatest"                       % scalaTestV % "test"
  )
