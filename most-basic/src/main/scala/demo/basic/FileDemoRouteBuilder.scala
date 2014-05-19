package demo.basic

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo  
 */
class FileDemoRouteBuilder extends RouteBuilder {

  "file:target/scala-2.10/classes/camel/in?move=processed"  ==>{
    log ("received file ${file:name}") 
    transform( xpath("/employee/email/text()") )
    --> ("file:target/scala-2.10/classes/camel/out")
  } 


}
