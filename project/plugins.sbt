logLevel := Level.Warn


resolvers += Resolver.typesafeRepo("releases")
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.10")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")
