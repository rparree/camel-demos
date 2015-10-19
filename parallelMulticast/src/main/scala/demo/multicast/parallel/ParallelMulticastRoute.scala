package demo.multicast.parallel

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
  * todo
  */
class ParallelMulticastRoute extends RouteBuilder {


  "direct:foo" ==> {
    log("received")

    // note if this was inout: would have to add strategy(...)

    multicast parallelProcessing {
      to("class:demo.multicast.parallel.SomeBean?method=baz") // 30s
      to("class:demo.multicast.parallel.SomeBean?method=bar") // 10s
    }
    -->("jms:C")
  }

  "jms:C" log "At C"
}
