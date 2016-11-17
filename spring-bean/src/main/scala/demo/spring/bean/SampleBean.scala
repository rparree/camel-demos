package demo.spring.bean

import com.typesafe.scalalogging.LazyLogging
import org.apache.camel.language.XPath
import org.apache.camel.{Body, Exchange, Header}
import org.springframework.stereotype.Component
import org.w3c.dom.Document


@Component
class SampleBean extends LazyLogging {

  def processString(@Body() body : String, @Header(Exchange.FILE_NAME) fileName : String ) : String = {
     logger.info (s"processing string: $body")
     "foo"
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
