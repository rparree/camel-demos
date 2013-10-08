import sbt._
import Keys._

object SbtMultiBuild extends Build {

  val log4j = "log4j" % "log4j" % "1.2.16"
  val camel = Seq("camel-core", "camel-scala", "camel-test", "camel-jms", "camel-spring").map("org.apache.camel" % _ % "2.12.0")
  val slf4j = Seq("slf4j-api", "slf4j-log4j12").map("org.slf4j" % _ % "1.6.1")
  val activeMQ = Seq("activemq-core", "activemq-spring").map("org.apache.activemq" % _ % "5.7.0") ++ Seq("org.apache.xbean" % "xbean-spring" % "3.7")
  val grizzled = "org.clapper" %% "grizzled-slf4j" % "1.0.1"

  val libraries = Seq(log4j) ++ camel ++ slf4j ++ activeMQ ++ Seq(grizzled)

  override lazy val settings = super.settings ++
    Seq(
      fork in run := true,
      scalaVersion := "2.10.3",
      version := "1.1"

    )

  lazy val parent = Project(id = "camel-demos-scala-parent",
    base = file(".")
  ) aggregate(simpleJMS, springJMS, aggregator)

  lazy val simpleJMS = Project(id = "simple-jms",
    base = file("simple-jms"),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies ++= libraries
    )
  )

  lazy val springJMS = Project(id = "spring-jms",
    base = file("spring-jms"),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies ++= libraries
    )
  )
  
  lazy val aggregator = Project(id = "aggregator",
    base = file("aggregator"),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies ++= libraries
    )
  )
}
