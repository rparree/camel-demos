package demo.cxfbean

import org.apache.camel.CamelContext
import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder

/**
 * todo
 */
class RsRoute(ctx : CamelContext) extends ScalaRouteBuilder(ctx) {
  "jetty:http://localhost:9090?matchOnUriPrefix=true" to "cxfbean:demoResource"
}
