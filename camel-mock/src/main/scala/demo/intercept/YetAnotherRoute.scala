package demo.intercept

import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder
import org.apache.camel.{CamelContext, Exchange}

/**
 * todo
 */


// add "with InterceptDemo" for Intercept demo 
class YetAnotherRoute(ctx: CamelContext) extends ScalaRouteBuilder(ctx)  {

  "direct:orderProcessor" ==> {
    routeId("camel-intercept-route")
    validate(_.in[String].matches("^\\d{2}$"))
    transform(simple("<request>${in.body}</request>")) id "transform-to-request"
    to("direct:xmlsubprocess")
    transform(xpath("/response/text()")) id "transform-from-response"
  }

  "direct:xmlsubprocess" ==> {
    routeId("camel-intercept-sub-route")
    transform(xpath("/request/text()")) id "transform-extract-value"
    process((e: Exchange) => e.out = e.in[Int] * 2)
    transform(simple("<response>${in.body}</response>")) id "transform-box-value"
  }


}



trait InterceptDemo {
  self : ScalaRouteBuilder =>
  
  interceptFrom().log("intercepted froms")
  interceptSendTo("*").log("intercepted sendTos") 
  intercept().when(_.in[String].matches("^\\d{2}$")).log("intercepted ${body}")
}
