package demo.http

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.jms.JmsComponent
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.test.junit4.CamelTestSupport
import org.hamcrest.core.Is
import org.junit.{Assert, Test}

/**
 * todo
 */
class HttpRouteTest extends CamelTestSupport {
  override def createRouteBuilder(): RouteBuilder = new HttpRoute(context).builder


  override def createCamelContext(): CamelContext = {
    val context = new DefaultCamelContext()
    context.setStreamCaching(true)
    context
  }

  @Test
  def exampleTest() : Unit = {
    val response= template.requestBody("jetty:http://localhost:9090/myapp/myservice",
      "hello", classOf[String])

    Assert.assertThat(response,Is.is("HELLO!"))
  }
}
