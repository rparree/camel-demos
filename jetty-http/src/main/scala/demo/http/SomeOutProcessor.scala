package demo.http

import org.apache.camel.{Exchange, Processor}
import grizzled.slf4j.Logging

/**
 * Created by rparree on 10/16/13.
 */
class SomeOutProcessor extends Processor with Logging {
  def process(exchange: Exchange) ={
    val body = exchange.getIn.getBody(classOf[String])
    info("processing: " + body)

    exchange.getOut.setBody("some data: "+body.toUpperCase)


  }
}
