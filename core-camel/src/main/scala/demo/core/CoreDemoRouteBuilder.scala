package demo.core

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo  
 */
class CoreDemoRouteBuilder extends RouteBuilder {

  "file:./target/scala-2.10/classes/camel/in" ==> {
      log("received file ${file:name}")
      --> ("class:demo.core.HeavyBean?method=workHard")

  }







}
