package demo.pipeline

import org.apache.camel.test.junit4.CamelTestSupport
import org.junit.{Before, Test}
import org.apache.camel.builder.RouteBuilder
import java.util.concurrent.TimeUnit
import grizzled.slf4j.Logging
import org.apache.camel.{Exchange, Service}
import org.apache.camel.impl.DefaultCamelContext

/**
 * Created by rparree on 20/02/14.
 */
class PipeLineDemoTest extends CamelTestSupport with Logging{


  override def createCamelContext  = {
    val context = new DefaultCamelContext()
    context.setStreamCaching(true)
    context
  }

  val reverseIn2In = (e :Exchange) => e.getIn.setBody(e.getIn.getBody(classOf[String]).reverse)
  val upperCaseIn2Out = (e :Exchange) => e.getOut.setBody(e.getIn.getBody(classOf[String]).toUpperCase)
  val replaceInToOut = (e :Exchange) => e.getOut.setBody(e.getIn.getBody(classOf[String]).replaceAll("[oO]", "0"))

  override def createRouteBuilder(): RouteBuilder = new org.apache.camel.scala.dsl.builder.RouteBuilder(){
    "jetty:http://localhost:9090/myapp/myservice"  ==> {
      log ("received ${body}")
      process(reverseIn2In)
      process(upperCaseIn2Out)
      process(replaceInToOut)
    }
  }.builder

  @Test
  def testBehaviour(){
    val f = template.asyncRequestBody("jetty:http://localhost:9090/myapp/myservice","hello",classOf[String])
    val result = f.get()
    println(result)
  }

}
