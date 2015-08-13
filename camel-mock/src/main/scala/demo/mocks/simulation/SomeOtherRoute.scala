package demo.mocks.simulation

import org.apache.camel.CamelContext
import org.apache.camel.model.dataformat.XmlJsonDataFormat
import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder

/**
 * todo
 */
class SomeOtherRoute(ctx: CamelContext) extends ScalaRouteBuilder(ctx) {

  val format = new XmlJsonDataFormat()
  format.setRootName("order")
  format.setForceTopLevelObject(true)
  
  
  "jetty:http://localhost:17171/order" ==>{
    routeId("camel-simulation-route")
    unmarshal(format)
    to("netty:tcp://tso92b:7777")
    marshal(format)
  }
  
  
}
