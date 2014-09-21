package demo.basic

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo  
 */
class FileDemoRouteBuilder extends RouteBuilder {

  "file:target/classes/camel/in?move=processed"  ==>{
    log ("received file ${file:name}") 
    transform( xpath("/employee/email/text()") )
    --> ("file:target/classes/camel/out")
  } 


}
