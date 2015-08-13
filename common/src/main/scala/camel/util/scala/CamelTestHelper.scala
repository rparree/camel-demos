package camel.util.scala

import org.apache.camel.Exchange
import org.apache.camel.scala.RichExchange

/**
 * todo
 */
trait CamelTestHelper {

  implicit def exchangeWrapper(exchange: Exchange) = new RichExchange(exchange)

}
