package demo.jms

import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
 * todo  
 */
class JmsDemoRouteBuilder extends RouteBuilder {

  "file:./target/classes/camel/in" ==> {
    log("received file ${file:name}")
    to ( "jms:queue/IncomingOrders" )
  }

  "jms:queue/IncomingOrders" ==>{
    choice{
      when ( _.getIn.getHeader("CamelFileName", classOf[String]).endsWith("xml"))  to "jms:queue/XmlOrders"
      when ( _.getIn.getHeader("CamelFileName", classOf[String]).endsWith("csv"))  to "jms:queue/CsvOrders"
      otherwise ( to("jms:queue/BadOrders") ) stop
    }
    log ("further processing")
  }

  "jms:queue/XmlOrders" when (xpath("not(/order/@test)")) log ("processing an XML order ${body}")

  "jms:queue/CsvOrders" log ("processing an CSV order")

  "jms:queue/BadOrders" log ("dealing with bad order")





}
