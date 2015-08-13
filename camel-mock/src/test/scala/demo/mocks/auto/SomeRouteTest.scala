package demo.mocks.auto

import org.apache.camel.Exchange
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.mock.MockEndpoint
import org.apache.camel.test.junit4.{TestSupport, CamelTestSupport}
import org.apache.camel.test.spring.CamelSpringTestSupport
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext
import org.junit.{Before, Test}

/**
 * todo
 */
class SomeRouteTest extends CamelTestSupport {

  override def isMockEndpointsAndSkip = "file:/tmp/camel/mocks/out*"

  override def createRouteBuilder(): RouteBuilder = new SomeRoute(context).builder

  @Before
  def deleteDirectories(): Unit = TestSupport.deleteDirectory("/tmp/camel/mocks/in")


  @Test
  def testRoute = {
    val mockName = "mock:file:/tmp/camel/mocks/out" // params are stripped
  val endpoint: MockEndpoint = getMockEndpoint(mockName)
  endpoint.expectedMessageCount(1)
  endpoint.message(0).body().isEqualTo("olleh")
  endpoint.message(0).header(Exchange.FILE_NAME).contains("test.txt")
 
  endpoint.setResultWaitTime(1000)
 
  template.sendBodyAndHeader("file:/tmp/camel/mocks/in", "hello", Exchange.FILE_NAME,"test.txt")
  endpoint.assertIsSatisfied()
}
}
