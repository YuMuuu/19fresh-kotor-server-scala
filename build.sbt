import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.kotor"
ThisBuild / organizationName := "kotor"


val scalazVersion = "7.2.27"
val akkaVersion = "2.5.18"
val akkaHttpVersion = "10.1.5"

val scalatest = "org.scalatest" %% "scalatest" % "3.0.5" % Test
val mockkitoCore = "org.mockito" % "mockito-core" % "2.13.0" % Test

val scalazCore = "org.scalaz" %% "scalaz-core" % scalazVersion
val scalazEffect = "org.scalaz" %% "scalaz-effect" % scalazVersion

val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
val akkaHttpCore = "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion
val akkaHttpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test

val config = "com.typesafe" % "config" % "1.3.2"
val logback = "ch.qos.logback" %% "logback-classic" % "1.2.3"
val lazyLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")

lazy val commonSettings = Seq(
  target := {
    baseDirectory.value / "target"
  },
  libraryDependencies ++= Seq(
    akkaActor, akkaStream, akkaHttp, akkaHttpSprayJson, scalazCore, scalazEffect, config, lazyLogging,
    scalatest, mockkitoCore, akkaHttpTestkit
  )
)





lazy val root = (project in file("."))
  .settings(
    name := "Kotor Project",
    commonSettings,
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
