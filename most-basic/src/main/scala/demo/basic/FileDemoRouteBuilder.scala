package demo.basic

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo  
 */
class FileDemoRouteBuilder extends RouteBuilder {

  "file:./target/scala-2.10/classes/camel/in"  log "received file ${file:name}"


}
