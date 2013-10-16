package demo.multicast.parallel

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo  
 */
class ParallelMulticastRoute extends RouteBuilder {


  "direct:foo" ==> {
    log("received")

    --> ( "class:demo.multicast.parallel.SomeBean?method=baz",
          "class:demo.multicast.parallel.SomeBean?method=bar"
        )


    --> ( "jms:C" )
  }

  "jms:C" log "At C"
}
