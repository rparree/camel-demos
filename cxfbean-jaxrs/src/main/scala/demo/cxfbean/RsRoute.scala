package demo.cxfbean

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo
 */
class RsRoute extends RouteBuilder {
  "jetty:http://localhost:9090?matchOnUriPrefix=true" to "cxfbean:demoResource"
}
