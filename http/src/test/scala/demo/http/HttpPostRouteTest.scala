package demo.http

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.jms.JmsComponent
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.test.junit4.CamelTestSupport
import org.junit.Test

/**
 * todo
 */
class HttpPostRouteTest extends CamelTestSupport {
  override def createRouteBuilder(): RouteBuilder = new HttpPostRoute(context).builder




  @Test
  def exampleTest() : Unit = {
    val response= template.requestBody("direct:restRoute",
      "{ \"title\": \"json-server\", \"author\": \"typicode\" }", classOf[String])
    println(response)

  }
}
