package demo.splitter 

import org.apache.camel.scala.dsl.builder.RouteBuilder

class SplitterRoute extends RouteBuilder {

      "file://./target/scala-2.10/classes/camel/in" log "received ${body}" split xpath("/staff/employee") to "direct:next"

      "direct:next" log "received ${body}"

       


}