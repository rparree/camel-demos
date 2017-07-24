package demo.jms

import org.apache.camel.CamelContext
import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder

/**
 * todo
 */
class JmsDemoRouteBuilder(camelContext: CamelContext) extends ScalaRouteBuilder(camelContext) {

  "file:./target/classes/camel/in" ==> {
    log("received file ${file:name}")
    to ( "jms:queue/IncomingOrders" )
  }

  "jms:queue/IncomingOrders?concurrentConsumers=5" ==>{
    choice{
      when ( header("CamelFileName").asInstanceOf[String].endsWith("xml"))  to "jms:queue/XmlOrders"
      when ( header("CamelFileName").asInstanceOf[String].endsWith("csv"))  to "jms:queue/CsvOrders"
      otherwise ( to("jms:queue/BadOrders") )
    }
    log ("further processing")
  }

  "jms:queue/XmlOrders" ==> {
    when( xpath("not(/order/@test)")) log "processing an XML order ${body}"
  }

  "jms:queue/CsvOrders" ==> {
    log("processing an CSV order")
  }

  "jms:queue/BadOrders" ==> {
    log("dealing with bad order")
  }





}
