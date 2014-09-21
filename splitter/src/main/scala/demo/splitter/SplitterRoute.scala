package demo.splitter 

import org.apache.camel.scala.dsl.builder.RouteBuilder

class SplitterRoute extends RouteBuilder {

      "file://./target/classes/camel/in" log "received ${body}" split xpath("/staff/employee") to "direct:next"

      "direct:next" log "received ${body}"

       


}