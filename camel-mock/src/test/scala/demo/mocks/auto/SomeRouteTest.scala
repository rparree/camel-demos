package demo.mocks.auto

import org.apache.camel.component.mock.MockEndpoint
import org.apache.camel.test.spring.CamelSpringTestSupport
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext
import org.junit.Test
import org.springframework.context.support.AbstractApplicationContext

/**
 * todo
 */
class SomeRouteTest extends CamelSpringTestSupport {
  override def createApplicationContext() = new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml")


  override def isMockEndpointsAndSkip: String = "file:/tmp/camel/mocks/out*"

  @Test
  def smokeTest ={
    val mockName = "mock:file:/tmp/camel/mocks/out" // params are stripped
    val endpoint: MockEndpoint = context.getEndpoint(mockName, classOf[MockEndpoint])
    endpoint.expectedBodiesReceived("olleh")
    endpoint.setAssertPeriod(1000)
    template.sendBody("file:/tmp/camel/mocks/in","hello")
    endpoint.assertIsSatisfied()
  }
}
