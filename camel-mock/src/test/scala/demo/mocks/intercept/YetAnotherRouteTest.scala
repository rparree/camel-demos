package demo.mocks.intercept

import camel.util.scala.CamelTestHelper
import demo.intercept.YetAnotherRoute
import org.apache.camel.Exchange
import org.apache.camel.builder.{AdviceWithRouteBuilder, RouteBuilder}
import org.apache.camel.model.TransformDefinition
import org.apache.camel.test.junit4.CamelTestSupport
import org.hamcrest.core.Is
import org.junit.{Assert, Test}

/**
 * todo
 */
class YetAnotherRouteTest extends CamelTestSupport with CamelTestHelper {

  override def createRouteBuilder(): RouteBuilder = new YetAnotherRoute(context).builder


  override def isUseAdviceWith = true

  @Test
  def runNormal(): Unit = {

    context.start()
    val reply = template.requestBody("direct:orderProcessor", "12", classOf[String])
    Assert.assertThat(reply, Is.is("24"))
  }

  @Test
  def interceptDemo(): Unit = {
    context.getRouteDefinition("camel-intercept-route").adviceWith(context, new AdviceWithRouteBuilder {
      override def configure() = {
        weaveAddFirst().log("received message {body}")
        weaveById("transform-to-request").before().process((e: Exchange) => e.in = e.in[Int] + 7)
        weaveByType(classOf[TransformDefinition]).selectLast().remove() // see https://issues.apache.org/jira/browse/CAMEL-9084
      }
    })
    context.start()
    val reply = template.requestBody("direct:orderProcessor", "12", classOf[String])
    Assert.assertThat(reply, Is.is("<response>38</response>"))
  }


}
