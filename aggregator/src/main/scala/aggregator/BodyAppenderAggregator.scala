package aggregator

import org.apache.camel.processor.aggregate.AggregationStrategy
import org.apache.camel.Exchange
import grizzled.slf4j.Logging
import org.apache.camel.impl.DefaultExchange

/**
 * todo  
 */
class BodyAppenderAggregator extends AggregationStrategy with Logging {
  def aggregate(oldExchange: Exchange, newExchange: Exchange) = {
    info("Old: " + (if (oldExchange == null) "null" else oldExchange.getIn.getBody) + ", New: " + newExchange.getIn.getBody)
    if (oldExchange == null) {

      val e = new DefaultExchange(newExchange)
      e.getIn().setBody(newExchange.getIn.getBody())
      e
    }
    else {
      val oldBody = oldExchange.getIn.getBody(classOf[String])
      val newBody = newExchange.getIn.getBody(classOf[String])
      oldExchange.getIn.setBody(oldBody + newBody)
      oldExchange
    }
  }
}
