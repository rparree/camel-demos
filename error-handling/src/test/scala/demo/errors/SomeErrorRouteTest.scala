package demo.errors

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.test.junit4.CamelTestSupport
import org.junit.Test

/**
 * todo
 */
class SomeErrorRouteTest extends CamelTestSupport {


  override def createRouteBuilder(): RouteBuilder = new SomeRoute(context).builder

  @Test
  def testDefaultBehaviour : Unit = {
    template.requestBody("jetty:http://localhost:9091/service", "hello", classOf[String])
  }
  


}
