package demo.jms

import org.apache.camel.impl.DefaultCamelContext

import org.apache.camel.component.jms.JmsComponent
import org.apache.activemq.ActiveMQConnectionFactory


/**
 * todo  
 */
object JMSDemo extends  App {

  // Setup ActiveMQ
  val connectionFactory = new ActiveMQConnectionFactory("vm://localhost")


  // setup camel context
  val context = new DefaultCamelContext()
  context.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory))
  context.addRoutes(new JmsDemoRouteBuilder())

  // Start and then stop the context
  context.start()
  Thread.sleep(10000)
  context.stop()

}
