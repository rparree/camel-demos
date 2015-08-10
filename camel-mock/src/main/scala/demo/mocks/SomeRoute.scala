package demo.mocks

import org.apache.camel.CamelContext
import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder

/**
 * todo
 */
class SomeRoute(ctx: CamelContext) extends ScalaRouteBuilder(ctx) {
  
  "file:/tmp/camel/mocks/in" ==> {
    routeId("camel-mocks-route")
    log("received ${file:name}")
    process(e=>e.getIn.setBody(e.getIn.getBody(classOf[String]).reverse)) id "reverse"
    to("file:/tmp/camel/mocks/out?flatten=true") // param just used to illustrate wildcards and naming of mock endpoints
  }
  
  
}


