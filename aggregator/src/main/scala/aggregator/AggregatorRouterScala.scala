package aggregator


import scala.beans.BeanProperty
import org.apache.camel.scala.dsl.builder.RouteBuilder
import grizzled.slf4j.Logging

/**
 * todo  
 */
class AggregatorRouterScala extends RouteBuilder with Logging {

  "file:./target/classes/camel/in" ==> {
      convertBodyTo(classOf[org.w3c.dom.Document])

      aggregate(
        xpath("/item/@id"),
        new BodyAppenderAggregator()

      ).completionTimeout(1000) {
        to("file:./target/classes/camel/out")
      }
  }


}
