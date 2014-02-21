package demo.multicast.parallel

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.component.jms.JmsComponent
import demo.multicast.java.ParallelMultiCastJavaRoute


/**
 * todo  
 */
object ParallelMulticastApp extends App {

  // Setup ActiveMQ
  val connectionFactory = new ActiveMQConnectionFactory("vm://localhost")


  // setup camel context
  val context = new DefaultCamelContext()
  context.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory))
  context.addRoutes(new ParallelMulticastRoute())

  // Start and then stop the context
  context.start()

  context.createProducerTemplate().sendBody("direct:foo","hello")
  Thread.sleep(60000)
  context.stop()

}
