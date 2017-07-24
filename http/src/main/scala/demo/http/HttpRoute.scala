package demo.http

import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder
import org.apache.camel.{CamelContext, Exchange}

/**
 * todo
 */
class HttpRoute(ctx: CamelContext) extends ScalaRouteBuilder(ctx){
  
  
    "jms:queue/SomeQ" ==> {
        log("message is ${body}")
        convertBodyTo(classOf[String],"UTF-32" )
        process((e: Exchange) => e.in = e.in[String].toUpperCase + "!")
        process((e: Exchange) => e.getIn.setHeader(Exchange.HTTP_URI,"http://localhost:8111/foo"))
        to ("http:DUMMY")
    }

  "jetty:http://localhost:8111/foo" ==> {
    log("received on http 8111 endpoint ${body}")
  }

  "jetty:http://localhost:8112/foo" ==> {
    log("received on http 8112 endpoint ${body}")
  }

}
