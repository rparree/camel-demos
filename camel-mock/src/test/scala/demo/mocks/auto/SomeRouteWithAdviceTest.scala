package demo.mocks.auto

import org.apache.camel.builder.{AdviceWithRouteBuilder, RouteBuilder}
import org.apache.camel.component.mock.MockEndpoint
import org.apache.camel.test.junit4.CamelTestSupport
import org.junit.{Before, Test}


/**
 * todo
 */
class SomeRouteWithAdviceTest extends CamelTestSupport {

  override def createRouteBuilder(): RouteBuilder = new SomeRoute(context).builder

  @Before
  def init() : Unit = {
    context.getRouteDefinition("camel-mocks-route").adviceWith(context, new AdviceWithRouteBuilder() {
      override def configure(): Unit = mockEndpoints("file:/tmp/camel/mocks/out*")
    })
  }
  
  @Test
  def testRoute = {
    val mockName = "mock:file:/tmp/camel/mocks/out" // params are stripped
    
    val endpoint: MockEndpoint = getMockEndpoint(mockName)
    endpoint.expectedBodiesReceived("olleh")
    endpoint.setResultWaitTime(1000)
    template.sendBody("file:/tmp/camel/mocks/in", "hello")
    endpoint.assertIsSatisfied()
  }
}
