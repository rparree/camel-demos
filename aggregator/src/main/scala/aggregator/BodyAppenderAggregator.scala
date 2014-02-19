package aggregator

import org.apache.camel.processor.aggregate.AggregationStrategy
import org.apache.camel.Exchange
import grizzled.slf4j.Logging
import org.apache.camel.impl.DefaultExchange
import org.w3c.dom.{Document, DOMImplementation}
import org.w3c.dom.bootstrap.DOMImplementationRegistry

/**
 * todo  
 */
class BodyAppenderAggregator extends AggregationStrategy with Logging {
  def aggregate(oldExchange: Exchange, newExchange: Exchange) = {
    info("Old: " + (if (oldExchange == null) "null" else oldExchange.getIn.getBody(classOf[String])) + ", New: " + newExchange.getIn.getBody(classOf[String]))

    if (oldExchange == null) {
      // Initialise DOM 3
      val registry = DOMImplementationRegistry.newInstance
      val domImpl = registry.getDOMImplementation("XML 3.0")
      // Create a new Document
      val newDoc = domImpl.createDocument(null,"items",null)
      // Obtain the xml doc from the first exchange
      val exchange = new DefaultExchange(newExchange)
      val oldDoc = newExchange.getIn.getBody(classOf[org.w3c.dom.Document])
      // Copy the item to the new document
      val item = newDoc.importNode(oldDoc.getDocumentElement,true)
      newDoc.getDocumentElement.appendChild(item)
      // Set the new document as the body and return
      exchange.getIn.setBody(newDoc)
      exchange
    }
    else {
      // Get the aggregated Document from the previous exchange
      val doc = oldExchange.getIn.getBody(classOf[org.w3c.dom.Document])
      // Create a copy of the newly received item
      val newItem = doc.importNode(newExchange.getIn.getBody(classOf[Document]).getDocumentElement,true)
      //Append the item to the aggregated document
      doc.getDocumentElement.appendChild(newItem)
      oldExchange
    }
  }
}
