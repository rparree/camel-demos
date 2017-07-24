package demo.basic

import org.apache.camel.{CamelContext, Exchange, Processor}
import org.apache.camel.scala.dsl.builder.{RouteBuilder, ScalaRouteBuilder}

/**
 * todo  
 */
class FileDemoRouteBuilder(ctx: CamelContext) extends ScalaRouteBuilder(ctx) {

  
  "file:/tmp/camel/in" ==> {
    log("received file ${file:name}")

    transform(  xpath("/employee/email/text()"))
    -->("file:/tmp/camel/out")
  }


}
