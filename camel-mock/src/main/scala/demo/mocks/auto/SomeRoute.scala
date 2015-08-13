package demo.mocks.auto

import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder
import org.apache.camel.{CamelContext, Exchange}

/**
 * todo
 */
class SomeRoute(ctx: CamelContext) extends ScalaRouteBuilder(ctx) {
  
  "file:/tmp/camel/mocks/in" ==> {
    routeId("camel-mocks-route")
    log("received ${file:name}")
    process((e : Exchange)=>e.in = e.in[String].reverse) id "reverse"
    to("file:/tmp/camel/mocks/out?flatten=true") // param just used to illustrate wildcards and naming of mock endpoints
  }
  
  
}


