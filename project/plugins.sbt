resolvers += Resolver.typesafeRepo("releases")


addSbtPlugin("org.xerial.sbt"  % "sbt-sonatype" % "1.1")
addSbtPlugin("com.jsuereth"    % "sbt-pgp" % "1.0.0")
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")
addSbtPlugin("com.gonitro"     % "avro-codegen-compiler" % "0.3.4")
