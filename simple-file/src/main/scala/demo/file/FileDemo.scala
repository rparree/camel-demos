package demo.file

import org.apache.camel.impl.DefaultCamelContext

import org.apache.camel.component.jms.JmsComponent
import org.apache.activemq.ActiveMQConnectionFactory


/**
 * todo  
 */
object FileDemo extends  App {

 
  // setup camel context
  val context = new DefaultCamelContext()
  //context.setTracing(true)
  context.addRoutes(new FileDemoRouteBuilder())

  // Start and then stop the context
  context.start()
  Thread.sleep(60000)
  context.stop()

}
