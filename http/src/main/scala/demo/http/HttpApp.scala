package demo.http

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.component.jms.JmsComponent
import org.apache.camel.impl.DefaultCamelContext


/**
 * todo  
 */
object HttpApp extends App {

  // Setup ActiveMQ
  val connectionFactory = new ActiveMQConnectionFactory("vm://mybroker")


  // setup camel context
  val context = new DefaultCamelContext()
  //context.setTracing(true)
  context.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory))

  context.setStreamCaching(true)
  context.addRoutes(new HttpRoute(context))

  context.start()
  Thread sleep 60000
  context.stop()

}
