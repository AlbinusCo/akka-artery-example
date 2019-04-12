resolvers += Resolver.bintrayRepo("albinusco", "sbt-plugins")

resolvers += Resolver.bintrayRepo("thesamet", "sbt-plugins")

resolvers += Resolver.bintrayRepo("beyondthelines", "maven")

addSbtPlugin("me.albinus" % "albinus-code-style" % "1.1.4")

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.10")

addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.18")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.10")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.7.1"

libraryDependencies += "beyondthelines" %% "grpcgatewaygenerator" % "0.0.9"