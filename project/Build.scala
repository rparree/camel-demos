import sbt._
import Keys._

object SbtMultiBuild extends Build {
  val camelArtifact = (artifact: String) => "org.apache.camel" % artifact % "2.12.2"

  val log4j = "log4j" % "log4j" % "1.2.16"
  val camel = Seq("camel-core", "camel-scala", "camel-test","camel-test-blueprint","camel-test-spring", 
    "camel-jms", "camel-spring", "camel-ftp", "camel-netty", "camel-cxf").map(camelArtifact)

  val slf4j = Seq("slf4j-api", "slf4j-log4j12").map("org.slf4j" % _ % "1.6.1")
  val activeMQ = Seq("activemq-core", "activemq-spring").map("org.apache.activemq" % _ % "5.7.0") ++ Seq("org.apache.xbean" % "xbean-spring" % "3.7")
  val grizzled = "org.clapper" %% "grizzled-slf4j" % "1.0.1"

  val libraries = Seq(log4j) ++ camel ++ slf4j ++ activeMQ ++ Seq(grizzled)

  val cxfExtra = "org.apache.cxf" % "cxf-rt-transports-http-jetty" % "2.7.6"
  val camelJetty = camelArtifact("camel-jetty")
  val xmljson = camelArtifact("camel-jackson")


  override lazy val settings = super.settings ++
    Seq(
      fork in run := true,
      scalaVersion := "2.10.3",
      version := "1.1",
      publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository"))),
      organization := "com.edc4it.demos.camel"
    )

  lazy val parent = Project(id = "camel-demos-scala-parent",
    base = file(".")
  ) aggregate(common, simpleFile, coreCamel, simpleSpring, simpleFTP, splitter, simpleJMS, springBean, sedaInOut,
    springJMS, simpleJMS, aggregator, parallelMulticast, osgiSpring, http, pipelineProcessorsInout,
    cxfJAXWSSpring, cxfbeanJAXRS, cxfrs  )

  lazy val common=  Project(id = "common",
    base = file("common"),
    settings = Project.defaultSettings 
  ) 
    
  lazy val simpleFile = addProject("most-basic") dependsOn common
  lazy val coreCamel = addProject("core-camel") dependsOn  common
  lazy val simpleSpring = addProject("simple-spring") dependsOn  common
  lazy val simpleFTP = addProject("simple-ftp") dependsOn  common
  lazy val splitter = addProject("splitter") dependsOn  common
  lazy val simpleJMS = addProject("simple-jms") dependsOn  common
  lazy val springBean= addProject("spring-bean") dependsOn  common
  lazy val sedaInOut= addProject("seda-inout") dependsOn  common
  lazy val springJMS = addProject("spring-jms") dependsOn   common
  lazy val aggregator = addProject("aggregator") dependsOn  common
  lazy val parallelMulticast= addProject("parallelMulticast") dependsOn   common
  lazy val osgiSpring = addProject("osgi-spring")
  lazy val http = addProject("jetty-http") settings(libraryDependencies += camelJetty)  dependsOn  common
  lazy val pipelineProcessorsInout = addProject("pipeline-processors-inout") settings(libraryDependencies += camelJetty) dependsOn  common
  lazy val cxfJAXWSSpring = addProject("cxf-spring") settings(libraryDependencies ++= Seq(cxfExtra,xmljson)) dependsOn  common
  lazy val cxfbeanJAXRS = addProject("cxfbean-jaxrs") settings(libraryDependencies += camelJetty) dependsOn  common
  lazy val cxfrs = addProject("cxfrs") settings(libraryDependencies ++= Seq(cxfExtra, "org.apache.cxf" % "cxf-bundle-jaxrs" % "2.7.6", "org.codehaus.jettison" % "jettison" % "1.3.4") ) dependsOn  common
  lazy val blueprint = Project(id = "blueprint",
    base = file("blueprint"),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies ++= Seq(log4j)++ Seq("camel-core","camel-scala", "camel-test-blueprint","camel-jms").map(camelArtifact)  ++activeMQ  ++ Seq(camelJetty) ++  slf4j,
      fork in run := true
    )  ) dependsOn common



  def addProject(dirName : String) = Project(id = dirName,
    base = file(dirName),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies ++= libraries,
      fork in run := true
    )
  )
}
