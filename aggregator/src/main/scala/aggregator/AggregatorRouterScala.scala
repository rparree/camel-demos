package aggregator


import scala.beans.BeanProperty
import org.apache.camel.scala.dsl.builder.RouteBuilder
import grizzled.slf4j.Logging

/**
 * todo  
 */
class AggregatorRouterScala extends RouteBuilder with Logging {

  @BeanProperty
  var inDir: String = _

  @BeanProperty
  var toDir: String = _
  info("Version")
  // "file:./target/test-classes/camel/in" to ("file:./target/test-classes/camel/out")
  "file:./target/test-classes/camel/in" ==> {
    convertBodyTo(classOf[String])

    aggregate(
      _.xpath("/item/@id"),
      new BodyAppenderAggregator()
    ).completionTimeout(1000) {
      to("file:./target/test-classes/camel/out")
    }

  }


}
