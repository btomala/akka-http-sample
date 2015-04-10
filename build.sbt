name := "akka-http-sample"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

val akkaVersion = "2.3.9"
val akkaHttpVersion = "1.0-M5"
val scalaTestVersion = "2.2.4"

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor"                        % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core-experimental"       % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-experimental"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit-experimental"    % akkaHttpVersion,
    "org.scalatest"     %% "scalatest"                         % scalaTestVersion % "test"
  )

