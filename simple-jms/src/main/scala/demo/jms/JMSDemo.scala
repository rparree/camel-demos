package demo.jms

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.component.jms.JmsComponent
import org.apache.camel.impl.DefaultCamelContext


/**
 * todo  
 */
object JMSDemo extends  App {

  // Setup ActiveMQ
  val connectionFactory = new ActiveMQConnectionFactory("vm://mybroker")


  // setup camel context
  val context = new DefaultCamelContext()
  //context.setTracing(true)
  context.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory))
  context.addRoutes(new JmsDemoRouteBuilder(context))

  // Start and then stop the context
  context.start()
  Thread.sleep(10000)
  context.stop()

}
