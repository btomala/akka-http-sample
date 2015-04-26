net.virtualvoid.sbt.graph.Plugin.graphSettings

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

name := "akka-http-sample"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

val akkaVersion = "2.4-SNAPSHOT"
val akkaHttpVersion = "1.0-M5"
val scalaTestVersion = "2.2.4"
val exp = "-experimental"

lazy val `akka-http-sample` = (project in file(".")).enablePlugins(SbtTwirl)

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor"                % akkaVersion,
    "com.typesafe.akka" %% "akka-kernel"               % akkaVersion,
    "com.typesafe.akka" %% s"akka-http-core$exp"       % akkaHttpVersion,
    "com.typesafe.akka" %% s"akka-http$exp"            % akkaHttpVersion,
    "com.typesafe.akka" %% s"akka-http-testkit$exp"    % akkaHttpVersion,
    "org.scalatest"     %% "scalatest"                 % scalaTestVersion % "test"
  )
