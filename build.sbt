name := "akka-artery-example"

organization := "me.albinus"

scalaVersion := "2.12.4"

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

dockerBaseImage := "openjdk:8"
packageName in Docker := "albinusco/akka-artery-example"
version in Docker := (version in ThisBuild).value
bashScriptExtraDefines += """addJava "-Dlogback.configurationFile=${app_home}/../conf/logback.xml""""

resolvers += Resolver.bintrayRepo("albinusco", "maven")
resolvers += Resolver.bintrayRepo("beyondthelines", "maven")

lazy val akkaVersion = "2.5.22"
lazy val akka = Seq(
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion,
  "com.typesafe.akka" %% "akka-distributed-data" % akkaVersion
)

lazy val scalaPb = Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
  "beyondthelines" %% "grpcgatewayruntime" % "0.0.9" % "compile,protobuf"
)

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "biz.paluch.logging" % "logstash-gelf" % "1.8.0",
  "me.albinus" %% "albinus-commons" % "1.0.8",
  "me.albinus" %% "akka-scalapb-serialization" % "1.0.2"
) ++ akka ++ scalaPb

libraryDependencies ++= Seq(
  "com.lightbend.akka.management" %% "akka-management" % "1.0.0",
  "com.lightbend.akka.management" %% "akka-management-cluster-http" % "1.0.0"
)

licenses := Seq(
  (
    "Apache-2.0",
    url("https://www.apache.org/licenses/LICENSE-2.0.html")
  )
)