package demo.jms

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.test.junit4.CamelTestSupport
import org.hamcrest.core.Is
import org.junit.{Assert, Test}

/**
 * todo
 */
class EnrichDemoRouteTest extends CamelTestSupport  {
  override def createRouteBuilder(): RouteBuilder = new EnrichDemoRouteBuilder(context()).builder

  @Test
  def smokeTest(): Unit ={
    val body = template.requestBody("direct:a","hello",classOf[String])
    Assert.assertThat(body,Is.is("hello world"))
  }

}
