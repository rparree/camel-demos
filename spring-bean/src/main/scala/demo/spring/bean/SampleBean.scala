package demo.spring.bean

import org.springframework.stereotype.Component
import grizzled.slf4j.{Logger, Logging}
import org.apache.camel.{Body, Exchange}
import org.apache.camel.language.XPath
import org.w3c.dom.Document


@Component
class SampleBean {

  val logger = Logger[this.type]

  def processString(body : String ) {
     logger.info (s"processing string: $body")
  }

  def processExchange(exchange : Exchange) {
    logger.info ("processing exchange")
  }


  def processDocument(doc : Document) {
    logger.info ("processing document")
  }

  def processAmount(@XPath("/order/item/@amount") amount : String) {
    logger.info (s"processing amount: $amount")
  }
}
