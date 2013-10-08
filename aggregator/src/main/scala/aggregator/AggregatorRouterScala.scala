package aggregator


import scala.beans.BeanProperty
import org.apache.camel.scala.dsl.builder.RouteBuilder
import grizzled.slf4j.Logging

/**
 * todo  
 */
class AggregatorRouterScala extends RouteBuilder with Logging {

  "file:./target/scala-2.10/classes/camel/in" ==> {
      convertBodyTo(classOf[String])

      aggregate(
        xpath("/item/@id"),
        new BodyAppenderAggregator()

      ).completionTimeout(1000) {
        to("file:./target/scala-2.10/classes/camel/out")
      }
  }


}
