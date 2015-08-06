package demo.seda

import java.nio.file.{FileSystems, Files}
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.main.Main


/**
 * todo
 */
object SedaDemo extends  App {


  val m = new Main
  m.enableTrace()

  m.addRouteBuilder(new org.apache.camel.scala.dsl.builder.RouteBuilder(){
    "netty:tcp://0.0.0.0:7090?textline=true&sync=true"  ==> {
      log ("received ${body}")
      to ("seda:a") 
      log ("after a1 ${body}") 
      to("seda:a") 
      to("log:demo")
      log ("after a2 ${body}")
    }
    
    "seda:a" log "received ${body}" process(e => e.getOut.setBody("Some Result from seda route\n" + System.currentTimeMillis()))
  }.builder)

  m.enableHangupSupport()
  m.run()

}
