package demo.http

import org.apache.camel.{CamelContext, Exchange}
import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder

/**
  * todo
  */
class HttpPostRoute(ctx: CamelContext) extends ScalaRouteBuilder(ctx){

    "direct:restRoute" ==> {
    //process((e: Exchange) => e.getIn.setHeader(Exchange.HTTP_METHOD,"POST"))
    to ("http://localhost:3000/posts")
    to("log:demo?showAll=true")
  }
  /*
  http://jsonplaceholder.typicode.com/posts



   */

}
