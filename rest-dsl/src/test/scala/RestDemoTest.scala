import java.io.PrintWriter
import java.sql.Connection
import java.util.logging.Logger
import javax.sql.DataSource

import demo.core.RestDslRoutes
import org.apache.camel.{CamelContext, Exchange}
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.mock.MockEndpoint
import org.apache.camel.impl.{DefaultCamelContext, SimpleRegistry}
import org.apache.camel.test.junit4.CamelTestSupport
import org.hamcrest.core.Is
import org.junit.Test
import org.springframework.jdbc.datasource.SimpleDriverDataSource

/**
  * todo
  */
class RestDemoTest extends CamelTestSupport {
  override def createRouteBuilder(): RouteBuilder = new RestDslRoutes()


  override def isMockEndpointsAndSkip: String = "sql:*"


  override def createCamelContext(): CamelContext = {
    val ctx = new DefaultCamelContext()
    val registry = new SimpleRegistry()
    registry.put("foo", new DataSource {override def getConnection: Connection = ???

      override def getConnection(s: String, s1: String): Connection = ???

      override def unwrap[T](aClass: Class[T]): T = ???

      override def isWrapperFor(aClass: Class[_]): Boolean = ???

      override def setLogWriter(printWriter: PrintWriter): Unit = ???

      override def getLoginTimeout: Int = ???

      override def setLoginTimeout(i: Int): Unit = ???

      override def getParentLogger: Logger = ???

      override def getLogWriter: PrintWriter = ???
    })
    ctx.setRegistry(registry)
    ctx
  }

  @Test
  def smokeTest(): Unit = {
    val body: String = template.requestBodyAndHeader("http://localhost:9898/demo/status", "", Exchange.HTTP_METHOD, "GET", classOf[String])
    org.junit.Assert.assertThat(body, Is.is("Running"))
  }

  @Test
  def mockDemo(): Unit = {
    val mockEndpoint: MockEndpoint = getMockEndpoint("mock:sql:update foo set bar=10",false)
    mockEndpoint.expectedMessageCount(1)
    val body: String = template.requestBody("http://localhost:9898/demo/update", "",  classOf[String])
    mockEndpoint.assertIsSatisfied()
  }

}
