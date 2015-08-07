package demo.properties

import org.apache.camel.CamelContext
import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder
import org.apache.camel.spring.Main

/**
 * todo
 */
class SomeRoute(ctx: CamelContext) extends ScalaRouteBuilder(ctx) {
  
  "{{demo.in.uri}}" ==>{
    routeId("camel-properties-demo-route")
    log("received ${file:name}")
    to("file:{{demo.out.dir}}")
  }
  
  
}

object DemoApp extends App{
  val main = new Main
  main.enableHangupSupport()
  main.run()
}
