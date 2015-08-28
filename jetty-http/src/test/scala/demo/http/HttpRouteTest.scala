package demo.http

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.test.junit4.CamelTestSupport
import org.hamcrest.core.Is
import org.junit.{Assert, Test}

/**
 * todo
 */
class HttpRouteTest extends CamelTestSupport {
  override def createRouteBuilder(): RouteBuilder = new HttpRoute(context).builder
  
  @Test
  def exampleTest() : Unit = {
    val response= template.requestBody("jetty:http://localhost:9090/myapp/myservice", "hello", classOf[String])
    response
    Assert.assertThat(response,Is.is("HELLO"))
  }
}
