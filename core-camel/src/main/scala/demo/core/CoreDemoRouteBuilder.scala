package demo.core

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo  
 */
class CoreDemoRouteBuilder extends RouteBuilder {

  "file:./target/scala-2.10/classes/camel/in" ==> {
    threads (5){
      log("received file ${file:name}")
      --> ("class:demo.file.HeavyBean?method=workHard")
    }

  }







}
