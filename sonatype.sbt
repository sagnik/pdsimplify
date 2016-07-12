organization := "edu.psu.sagnik.research"

sonatypeProfileName := "edu.psu.sagnik.research"

// To sync with Maven central, you need to supply the following information:
pomExtra in Global := {
  <url>https://github.com/sagnik/pdsimplify</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
    </license>
  </licenses>
  <scm>
    <connection>scm:git:github.com/sagnik/pdsimplify.git</connection>
    <developerConnection>scm:git:git@github.com:sagnik/pdsimplify.git</developerConnection>
    <url>https://github.com/sagnik/pdsimplify</url>
  </scm>
  <developers>
    <developer>
      <id>sagnik</id>
      <name>sagnik ray choudhury</name>
      <url>http://personal.psu.edu/szr163</url>
    </developer>
  </developers>
}
