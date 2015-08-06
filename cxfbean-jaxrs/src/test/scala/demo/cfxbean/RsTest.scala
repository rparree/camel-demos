package demo.cfxbean

import java.util.concurrent.Future

import demo.cxfbean.{DemoResource, RsRoute}
import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.{SimpleRegistry, DefaultCamelContext}
import org.apache.camel.test.junit4.CamelTestSupport
import org.hamcrest.core.Is
import org.hamcrest.core.Is.is
import org.junit.Assert.assertThat
import org.junit.{Assert, Test}
import scala.collection.JavaConversions._


/**
 * todo
 */
class RsTest extends CamelTestSupport {
  override def createRouteBuilder(): RouteBuilder = new RsRoute().builder


  override def createCamelContext(): CamelContext = {
    val context = new DefaultCamelContext()
    
    val registry = new SimpleRegistry
    registry += ("demoResource"->new DemoResource)

    context.setRegistry(registry)
    context

  }

  @Test
  def testAddition()  {
    val i = 7
    val futureStr  = template.asyncRequestBody(s"http://localhost:9090/example/calc/$i",null, 
      classOf[String])
    val s = futureStr.get().toInt
    assertThat(s,is(14))
  }
}
