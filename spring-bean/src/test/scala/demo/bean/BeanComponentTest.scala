package demo.bean

import org.apache.camel.Exchange
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext
import org.junit.Test

class BeanComponentTest extends  org.apache.camel.test.spring.CamelSpringTestSupport {

  override def createApplicationContext() = new ClassPathXmlApplicationContext("/spring-camel-context.xml ")

  @Test
  def smokeTest() {
    val body = """<?xml version="1.0" encoding="UTF-8"?>
                 |<order customer="12">
                 |        <item sku="122-121" amount="10"/>
                 |        <item sku="982-293" amount="4"/>
                 |</order>""".stripMargin('|')

    template.sendBodyAndHeader("file:/tmp/camel/in1", body, Exchange.FILE_NAME, "file1.xml")
    Thread.sleep(10000)
  }

}
