import com.typesafe.sbt.osgi.OsgiKeys
import com.typesafe.sbt.osgi.SbtOsgi._
import sbt.Keys._
import sbt._

object CamelDemosBuild extends Build {


  override lazy val settings = super.settings ++
  Seq(
    fork in run := true,
    scalaVersion := "2.11.7",
    version := "1.0.0-SNAPSHOT",
    publishTo               := Some("Sonatype Snapshots Nexus" at "http://localhost:8081/content/repositories/snapshots/"),
    credentials             += Credentials("Sonatype Nexus Repository Manager", "localhost", "admin", "admin123"),
    organization := "com.edc4it",
    startYear := Some(2014),
    crossPaths := false,
    scalacOptions         ++= Seq("-target:jvm-1.7", "-deprecation","-feature","-language:implicitConversions"),
    javacOptions in compile ++= Seq("-source", "1.7", "-target", "1.7"),
    javacOptions in doc     ++= Seq("-source", "1.7"),
    javaOptions in run ++= Seq(s"-javaagent:../lib/jolokia-jvm-1.2.3-agent.jar"),
    shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " },
    scalacOptions += "-Xexperimental" // for SAM https://github.com/scala/scala/pull/3018
  )
  
  import Dependencies._

  lazy val parent = Project(id = "camel-demos-scala-parent",
    base = file(".")
  ) aggregate(common, camelProps, camelMock, simpleFile, coreCamel, simpleSpring, simpleFTP, splitter, simpleJMS, 
    springBean, sedaInOut,
    springJMS, simpleJMS, aggregator, parallelMulticast, osgiSpring, http, pipelineProcessorsInout,
    cxfJAXWSSpring, cxfbeanJAXRS, cxfrs, fabricJetty,fabricDemoClientClient )

  lazy val common=  Project(id = "common",
    base = file("common"),
    settings = Project.defaultSettings  ++ Seq(
      libraryDependencies += camelScala
    )
  ) 
    
  lazy val simpleFile = addProject("most-basic") dependsOn common
  lazy val coreCamel = addProject("core-camel") dependsOn  common
  lazy val camelProps = addProject("camel-properties") dependsOn common
  lazy val camelMock = addProject("camel-mock") settings (libraryDependencies ++= Seq(camelXmlJson, xom)) dependsOn 
    common 
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
  lazy val errorHandling= addProject("error-handling") settings(libraryDependencies += camelJetty) dependsOn  common
  lazy val cxfJAXWSSpring = addProject("cxf-spring") settings(libraryDependencies ++= Seq(cxfTransportHttpJetty,camelJackson)) dependsOn  common
  lazy val cxfbeanJAXRS = addProject("cxfbean-jaxrs") settings(libraryDependencies += camelJetty) dependsOn  common
  lazy val enrich = addProject("enrich")  dependsOn  common

  lazy val cxfrs = addProject("cxfrs")
    .settings(
      libraryDependencies ++= Seq(cxfTransportHttpJetty,cxfBundleJaxrs , csfrsExtProv,jettison) 
    ).dependsOn  (common)
 
  lazy val blueprint = Project(id = "blueprint",
    base = file("blueprint"),
    settings = Project.defaultSettings ++ Seq(
      libraryDependencies ++= Seq(camelCore,camelScala,camelTestBlueprint,camelJms),
      libraryDependencies += log4j,
      libraryDependencies += slf4j,
      libraryDependencies += slf4jLog4j,
      libraryDependencies ++= activeMQSeq,
      libraryDependencies += camelJetty,
      fork in run := true
    )  ) dependsOn common


  lazy val fabricJetty = Project(id = "fabric-jetty", base = file("fabric-demo/fabric-jetty"))
    .settings(Project.defaultSettings : _*)
    .settings(
      libraryDependencies ++= compile(osgi, slf4j)
        ++ compile(camelCore,camelJetty,camelScala)
        ++ test (camelTest ,slf4jLog4j)
    )

    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.privatePackage := Nil,
      OsgiKeys.importPackage := Seq("org.apache.camel.component.jetty;version=\"[2,3)\"","io.fabric8.zookeeper")
    )
  

  lazy val fabricDemoClientClient = Project(id = "fabric-demo-client", base = file("fabric-demo/fabric-demo-client"))
    .settings(Project.defaultSettings : _*)
    .settings(

      libraryDependencies ++= compile(osgi, slf4j)
        ++ compile(camelCore,camelJetty,camelScala)
        ++ test (camelTest, slf4jLog4j)
    )

    .settings(osgiSettings: _*)
    .settings(
      OsgiKeys.privatePackage := Nil,
      OsgiKeys.importPackage := Seq("org.apache.camel.component.jetty;version=\"[2,3)\"","io.fabric8.zookeeper")
    )


  def addProject(dirName : String) = Project(id = dirName,
    base = file(dirName),
    settings = Project.defaultSettings ++ Seq(
      
      libraryDependencies += log4j,
      libraryDependencies += scalaLogging,
      libraryDependencies += slf4j,
      libraryDependencies += slf4jLog4j,
      libraryDependencies ++= activeMQSeq,
      libraryDependencies ++= Seq(camelCore,camelScala,camelJms,camelSpring,camelFtp,camelNetty,camelCxf,camelJetty,camelRx),
      libraryDependencies ++= test(camelTest,camelTestSpring,junitInterface,hamcrestJava),
      fork in run := true
    )
  )
}
