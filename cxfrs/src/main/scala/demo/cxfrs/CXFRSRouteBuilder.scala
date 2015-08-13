package demo.cxfrs

import org.apache.camel.Exchange
import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo  
 */
class CXFRSRouteBuilder extends RouteBuilder {

  "cxfrs:bean:rsServer?bindingStyle=SimpleConsumer" ==> {
      --> ("log:demo.cxfrs")

      // Fake an external service:
      process ((e: Exchange) => e.out=e.in[Registration].genActivationCode)

  }

}
