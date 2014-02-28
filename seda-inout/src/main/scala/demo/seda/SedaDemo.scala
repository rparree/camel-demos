package demo.seda

import java.nio.file.{FileSystems, Files}
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.main.Main


/**
 * todo
 */
object SedaDemo extends  App {


  val m = new Main

  m.addRouteBuilder(new org.apache.camel.scala.dsl.builder.RouteBuilder(){
    "netty:tcp://0.0.0.0:7090?textline=true&sync=true" log "received ${body}" to "seda:a"

    "seda:a" log "received ${body}" process(e => e.getOut.setBody("Some Result from seda route\n"))
  }.builder)

  m.enableHangupSupport()
  m.run()

}
