package demo.multicast.parallel

import com.typesafe.scalalogging.LazyLogging
import org.apache.camel.Message

/**
 * todo  
 */
class SomeBean extends LazyLogging {
   def bar(m : Message ) = {
     logger.info("bar invoked, sleeping 10s")
     Thread.sleep(10000)
     logger.info("done with bar")
   }

  def baz(m : Message ) = {
    logger.info("baz invoked, sleeping 30s")
    Thread.sleep(30000)
    logger.info("done with baz")
  }
}
