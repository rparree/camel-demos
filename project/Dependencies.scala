import sbt._

object Dependencies {

  val camelArtifact = (artifact: String) => "org.apache.camel" % artifact % "2.17.2"
  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.5"

  // Camel
  val camelCore= camelArtifact("camel-core")
  val camelScala= camelArtifact("camel-scala")
  val camelJms= camelArtifact("camel-jms")
  val camelSpring= camelArtifact("camel-spring")
  val camelFtp= camelArtifact("camel-ftp")
  val camelNetty= camelArtifact("camel-netty")
  val camelCxf= camelArtifact("camel-cxf")
  val camelJetty = camelArtifact("camel-jetty")
  val camelHttp = camelArtifact("camel-http")
  val camelJackson = camelArtifact("camel-jackson")
  val camelRx = camelArtifact("camel-rx")
  val camelXmlJson= camelArtifact("camel-xmljson")
  val camelSql= camelArtifact("camel-sql")
  val camelJbpm = camelArtifact("camel-jbpm")
  val jbpmnSeq = Seq("kie-remote-client").map("org.kie.remote" % _ % "6.3.0.Final") // make sure this version matches version jbpm-verion within parent pom of camel

  val xom = "xom" % "xom" % "1.2.5"
  
  // Camel Test
  val camelTest= camelArtifact("camel-test")
  val camelTestBlueprint = camelArtifact("camel-test-blueprint")
  val camelTestSpring = camelArtifact("camel-test-spring")
  

  // CXF (see https://github.com/apache/camel/blob/master/parent/pom.xml)
  val cxfBundleJaxrs = "org.apache.cxf" % "cxf-rt-frontend-jaxrs" % "3.1.2"
  val cxfTransportHttpJetty = "org.apache.cxf" % "cxf-rt-transports-http-jetty" % "3.1.2"
  val csfrsExtProv = "org.apache.cxf" % "cxf-rt-rs-extension-providers" % "3.1.2"
  val jettison = "org.codehaus.jettison" % "jettison" % "1.2"
  
  // ActiveMQ
  val activeMQSeq = Seq("activemq-core", "activemq-spring").map("org.apache.activemq" % _ % "5.7.0") ++ Seq("org.apache.xbean" % "xbean-spring" % "3.7")

  // Logging
  val log4j = "log4j" % "log4j" % "1.2.16"
  val slf4j = "org.slf4j" % "slf4j-api" % "1.6.1"
  val slf4jLog4j = "org.slf4j" % "slf4j-log4j12" % "1.6.1"
//  val slf4jSeq = Seq("slf4j-api", "slf4j-log4j12").map("org.slf4j" % _ % "1.6.1")

  // Logging
  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

  
 
  val junitInterface = "com.novocode" % "junit-interface" % "0.11"
  val hamcrestJava = "org.hamcrest" % "java-hamcrest" % "2.0.0.0"
  val osgi = "org.osgi" % "org.osgi.core" % "4.3.0"

  def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")

  def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

  def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

  def runtime(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")

  def container(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")
}
