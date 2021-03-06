package demo.pipeline

import camel.util.scala.CamelTestHelper
import com.typesafe.scalalogging.LazyLogging
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.model.ProcessorDefinition
import org.apache.camel.test.junit4.CamelTestSupport
import org.apache.camel.{Exchange, Processor}
import org.hamcrest.core.Is.is
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.junit.{Assert, Test}

import scala.collection.JavaConverters._

object PipeLineDemoTest {
  @Parameters(name = "expected = {0}")
  def data() = Seq("olleh", "OLLEH","0LLEH", "0lleh").map(Array(_)).asJava
}

@RunWith(classOf[Parameterized])
class PipeLineDemoTest(expected : String) extends CamelTestSupport with LazyLogging with CamelTestHelper {

  override def createCamelContext  = {
    val context = new DefaultCamelContext()
    context.setStreamCaching(true)
    context
  }


  override def isUseDebugger: Boolean = true


  override def debugBefore(exchange: Exchange, processor: Processor, definition: ProcessorDefinition[_], id: String, label: String): Unit = {
    logger.info(s"Before $id with in in.body ${exchange.in}")
  }

  val reverseIn2In = (e :Exchange) => e.in = e.in[String].reverse
  val upperCaseIn2Out = (e :Exchange) => e.out = e.in[String].toUpperCase
  val replaceInToOut = (e :Exchange) => e.out = e.in[String].replaceAll("[oO]", "0")

  override def createRouteBuilder(): RouteBuilder = new org.apache.camel.scala.dsl.builder.RouteBuilder(){
    "jetty:http://localhost:9090/myapp/myservice"  ==> {
      convertBodyTo(classOf[String])
      log ("received ${body}")
      process(reverseIn2In) id "reverse"
      process(upperCaseIn2Out) id "uppercase"
      process(replaceInToOut) id "replace"
    }
  }.builder

  @Test
  def testBehaviour(){
    val f = template.asyncRequestBody("jetty:http://localhost:9090/myapp/myservice","hello",classOf[String])
    val result = f.get()
    Assert.assertThat(result,is(expected))
  }

}




