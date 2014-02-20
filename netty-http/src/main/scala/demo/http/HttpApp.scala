package demo.http

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.component.jms.JmsComponent
import org.apache.camel.main.Main


/**
 * todo  
 */
object HttpApp extends App {

  val context = new DefaultCamelContext()
  context.setStreamCaching(true)
  context.addRoutes(new org.apache.camel.scala.dsl.builder.RouteBuilder(){
    "jetty:http://localhost:9090/myapp/myservice"  log "received ${body}"  process(e => e.getOut.setBody("wow thanks!"))
  })

  context.start()
  Thread sleep 60000
  context.stop()

}
