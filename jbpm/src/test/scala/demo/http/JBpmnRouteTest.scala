package demo.http

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.test.junit4.CamelTestSupport
import org.hamcrest.core.Is
import org.junit.Test

/**
  * todo
  */
class JBpmnRouteTest extends CamelTestSupport {
  override def createRouteBuilder(): RouteBuilder = new JBpmnRoute().builder


    @Test
    def exampleTest(): Unit = {
      val param: Map[String, AnyRef] = Map("name" -> "Mike Wheeler")
      val response = template.requestBody("direct:jbpmRoute",
        param, classOf[String])
      org.junit.Assert.assertThat(response, Is.is("Mike Wheeler"))

    }
}
