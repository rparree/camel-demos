package aggregator


import org.apache.camel.scala.dsl.builder.RouteBuilder


/**
 * todo  
 */
class AggregatorRouterScala extends RouteBuilder  {

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
