package demo.core

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo  
 */
class CoreDemoRouteBuilder extends RouteBuilder {

  "file:./target/classes/camel/in" ==> {
      log("received file ${file:name}")
      --> ("class:demo.core.HeavyBean?method=workHard")

  }







}
