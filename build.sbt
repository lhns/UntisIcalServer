name := "UntisIcalServer"

mainClass := Some("org.lolhens.untisicalserver.Main")

lazy val settings = Seq(
  version := "1.4.0",

  scalaVersion := "2.11.8",

  resolvers := Seq("Artifactory" at "http://lolhens.no-ip.org/artifactory/libs-release/"),

  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % "2.11.8",
    "org.slf4j" % "slf4j-api" % "1.7.21",
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
    "org.typelevel" %% "cats" % "0.8.1",
    "com.chuusai" %% "shapeless" % "2.3.2",
    "com.github.mpilquist" %% "simulacrum" % "0.10.0",
    "io.monix" %% "monix" % "2.1.0",
    "io.monix" %% "monix-cats" % "2.1.0",
    "com.typesafe.akka" %% "akka-actor" % "2.4.12",
    "com.typesafe.akka" %% "akka-remote" % "2.4.12",
    "com.typesafe.akka" %% "akka-stream" % "2.4.12",
    "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11",
    "io.spray" %% "spray-json" % "1.3.2",
    "com.github.fommil" %% "spray-json-shapeless" % "1.2.0",
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.3",
    "org.mnode.ical4j" % "ical4j" % "2.0-beta1"
  ),

  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3"),

  scalacOptions ++= Seq("-Xmax-classfile-name", "254")
)

lazy val root = Project("untisicalserver", file("."))
  .enablePlugins(
    JavaAppPackaging,
    UniversalPlugin)
  .settings(settings: _*)
