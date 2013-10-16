package demo.http

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.component.jms.JmsComponent


/**
 * todo  
 */
object HttpApp extends App {


  // setup camel context
  val context = new DefaultCamelContext()
  context.addRoutes(new HttpRoute())
  context.setTracing(true);
  // Start and then stop the context
  context.start()

  Thread.sleep(60000)
  context.stop()

}
