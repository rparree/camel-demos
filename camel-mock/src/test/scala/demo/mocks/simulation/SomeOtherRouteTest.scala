package demo.mocks.simulation

import camel.util.scala.CamelTestHelper
import demo.mocks.auto.SomeRoute
import org.apache.camel.scala.{RichExchange, ScalaExpression}
import org.apache.camel.{Processor, Exchange, Expression}
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.test.junit4.{TestSupport, CamelTestSupport}
import org.hamcrest.CoreMatchers
import org.hamcrest.core.Is
import org.hamcrest.core.Is._
import org.hamcrest.text.MatchesPattern
import org.junit.{Assert, Test}

/**
 * todo
 */
class SomeOtherRouteTest extends CamelTestSupport with CamelTestHelper{

  override def createRouteBuilder(): RouteBuilder = new SomeOtherRoute(context).builder

  override def isMockEndpointsAndSkip: String = "netty:*"

  @Test
  def smokeTest(): Unit = {

    val endpoint = getMockEndpoint("mock://tcp:tso92b:7777", false)
    endpoint expectedMessageCount 1
    
    endpoint.whenExchangeReceived(1, e => e.in = "<new-order><total>1089.75</total></new-order>")
    val msg = """{customer:"123", product:"sku-2819"}"""
    val reply = template.requestBody("http://localhost:17171/order", msg, classOf[String])
    assertMockEndpointsSatisfied()
    Assert.assertThat(reply, is( """{"new-order":{"total":"1089.75"}}"""))
  }
}
