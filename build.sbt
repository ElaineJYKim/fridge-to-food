enablePlugins(JavaServerAppPackaging)

scalaVersion := "2.13.3"
name := "myfridge"
version := "1.0"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"

libraryDependencies ++= {
  val akkaHttpV   = "10.2.1"
  val akkaV       = "2.6.10"
  val scalaTestV  = "3.2.3"
  Seq(
    "net.ruippeixotog" %% "scala-scraper" % "2.2.0",
    "de.heikoseeberger" %% "akka-http-jackson" % "1.35.2",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,
    "org.scalatest"     %% "scalatest" % scalaTestV % "test"
  )
}

