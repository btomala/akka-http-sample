net.virtualvoid.sbt.graph.Plugin.graphSettings

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

name := "akka-http-sample"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7"

val akkaHttpV  = "1.0-RC3"
val scalaTestV = "2.2.4"
val playTwirlV = "1.1.1"

lazy val `akka-http-sample` = (project in file(".")).enablePlugins(SbtTwirl)

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http-experimental"          % akkaHttpV,
    "com.typesafe.play" %%  "twirl-api"                      % playTwirlV
  ) ++ Seq(
    "com.typesafe.akka" %% "akka-http-testkit-experimental"  % akkaHttpV % "test",
    "org.scalatest"     %% "scalatest"                       % scalaTestV % "test"
  )
