package demo.http

import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder
import org.apache.camel.{CamelContext, Exchange}

/**
 * todo
 */
class HttpRoute(ctx: CamelContext) extends ScalaRouteBuilder(ctx){
  
  
  "jetty:http://localhost:9090/myapp/myservice"
    .process((e: Exchange) => e.out = e.in[String].toUpperCase)

}
