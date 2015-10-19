package demo.errors

import com.typesafe.scalalogging.LazyLogging
import org.apache.camel.scala.dsl.builder.ScalaRouteBuilder
import org.apache.camel.{CamelContext, Exchange}


/**
 * todo
 */
class SomeRoute(ctx : CamelContext) extends ScalaRouteBuilder(ctx) with LazyLogging {



  val simpleProcessor = (e : Exchange) => e.in = e.in[String].reverse
  
  val errorProcessor = (e: Exchange) => {
    logger.info(s"error processor's body=${e.in[String]}")
    e.in = "changed"
    if (e.header(Exchange.REDELIVERY_COUNTER)!="1")
      throw new MyException("Something went wrong")
    
  }



  errorHandler(deadLetterChannel("file:/tmp/camel/out/dlc").maximumRedeliveries(3).useOriginalMessage())

 /* handle[MyException]{
    log("handling exception")
  }.handled*/


  "jetty:http://localhost:9091/service" ==> {

    id ("some-error-route")
    log("processing request")
    process(simpleProcessor)
    process(errorProcessor)
    log("done")
    
  }
  

}
