package demo.core

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
  * todo
  */
class CoreDemoRouteBuilder extends RouteBuilder {

  "file:/tmp/camel/in" ==> {
    log("received file ${file:name}")
    threads(10) {
      -->("class:demo.core.HeavyBean?method=workHard")
    }
  }


}
