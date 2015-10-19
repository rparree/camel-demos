package demo.core


import org.apache.camel.Exchange
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.test.junit4.CamelTestSupport
import org.junit.Test

/**
  * todo
  */
class CoreDemoTest extends CamelTestSupport {
  @throws(classOf[Exception])
  protected override def createRouteBuilder: RouteBuilder = {
    return new CoreDemoRouteBuilder().builder
  }

  @Test
  @throws(classOf[Exception])
  def smokeTest {
    template.sendBodyAndHeader("file:./target/classes/camel/in", "hi", Exchange.FILE_NAME, "hello-with-hi.txt")
    Thread.sleep(15000)
  }

}
