package demo.bean

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext
import org.apache.camel.Exchange
import org.junit.Test

class BeanComponentTest extends  org.apache.camel.test.spring.CamelSpringTestSupport {

  override def createApplicationContext() = new ClassPathXmlApplicationContext("/spring-camel-context.xml ")

  @Test
  def smokeTest() {
    val body = """<order customer="12">
                 |        <item sku="122-121" amount="10"/>
                 |        <item sku="982-293" amount="4"/>
                 |</order>"""

    template.sendBodyAndHeader("file:./target/scala-2.10/classes/camel/in", body, Exchange.FILE_NAME, "file1.xml")
    Thread.sleep(10000)
  }

}
