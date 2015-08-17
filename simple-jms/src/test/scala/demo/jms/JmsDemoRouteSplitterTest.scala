package demo.jms

import camel.util.scala.CamelTestHelper
import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.component.jms.JmsComponent
import org.apache.camel.component.mock.MockEndpoint
import org.apache.camel._
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.test.junit4.CamelTestSupport
import org.junit.{Before, Test}

/**
 * todo
 */
class JmsDemoRouteSplitterTest extends CamelTestSupport  {
  override def createRouteBuilder(): RouteBuilder = new JmsDemoRouteBuilder(context()).builder


  override def createCamelContext(): CamelContext = {
    val context = new DefaultCamelContext()
    val connectionFactory = new ActiveMQConnectionFactory("vm://localhost")
    context.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory))
    context
  }

  override def isMockEndpointsAndSkip: String = "jms:queue/(Xml|Csv|Bad)Orders"

  @Produce(uri = "file:./target/classes/camel/in")
  var producer : ProducerTemplate =_
  
  @EndpointInject(uri="mock:jms:queue/XmlOrders")
  var xmlOrdersMock : MockEndpoint = _

  @EndpointInject(uri="mock:jms:queue/CsvOrders")
  var csvOrdersMock : MockEndpoint = _

  @EndpointInject(uri="mock:jms:queue/BadOrders")
  var badOrdersMock : MockEndpoint = _



  @Test
  def testXml(): Unit ={

    xmlOrdersMock.setExpectedCount(1)
    csvOrdersMock.setExpectedCount(0)
    badOrdersMock.setExpectedCount(0)
    
    producer.sendBodyAndHeader("not important",Exchange.FILE_NAME,"testfile.xml")
    
    assertMockEndpointsSatisfied()
  }

  @Test
  def testCsv(): Unit ={

    xmlOrdersMock.setExpectedCount(0)
    csvOrdersMock.setExpectedCount(1)
    badOrdersMock.setExpectedCount(0)

    producer.sendBodyAndHeader("not important",Exchange.FILE_NAME,"testfile.csv")

    assertMockEndpointsSatisfied()
  }

  @Test
  def testBad(): Unit ={

    xmlOrdersMock.setExpectedCount(0)
    csvOrdersMock.setExpectedCount(0)
    badOrdersMock.setExpectedCount(1)

    producer.sendBodyAndHeader("not important",Exchange.FILE_NAME,"testfile.txt")

    assertMockEndpointsSatisfied()
  }
}
