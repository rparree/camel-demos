import sbt._

object Dependencies {

  val camelArtifact = (artifact: String) => "org.apache.camel" % artifact % "2.12.2"

  // Camel
  val camelCore= camelArtifact("camel-core")
  val camelScala= camelArtifact("camel-scala")
  val camelJms= camelArtifact("camel-jms")
  val camelSpring= camelArtifact("camel-spring")
  val camelFtp= camelArtifact("camel-ftp")
  val camelNetty= camelArtifact("camel-netty")
  val camelCxf= camelArtifact("camel-cxf")
  val camelJetty = camelArtifact("camel-jetty")
  val camelJackson = camelArtifact("camel-jackson")
  
  // Camel Test
  val camelTest= camelArtifact("camel-test")
  val camelTestBlueprint = camelArtifact("camel-test-blueprint")
  val camelTestSpring = camelArtifact("camel-test-spring")
  

  // CXF
  val cxfBundleJaxrs = "org.apache.cxf" % "cxf-bundle-jaxrs" % "2.7.6"
  val cxfTransportHttpJetty = "org.apache.cxf" % "cxf-rt-transports-http-jetty" % "2.7.6"
  val jettison = "org.codehaus.jettison" % "jettison" % "1.3.4"
  
  // ActiveMQ
  val activeMQSeq = Seq("activemq-core", "activemq-spring").map("org.apache.activemq" % _ % "5.7.0") ++ Seq("org.apache.xbean" % "xbean-spring" % "3.7")

  // Logging
  val log4j = "log4j" % "log4j" % "1.2.16"
  val slf4j = "org.slf4j" % "slf4j-api" % "1.6.1"
  val slf4jLog4j = "org.slf4j" % "slf4j-log4j12" % "1.6.1"
//  val slf4jSeq = Seq("slf4j-api", "slf4j-log4j12").map("org.slf4j" % _ % "1.6.1")
  val grizzled = "org.clapper" %% "grizzled-slf4j" % "1.0.1"


  val osgi = "org.osgi" % "org.osgi.core" % "4.3.0"

  def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")

  def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

  def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

  def runtime(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")

  def container(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")
}
