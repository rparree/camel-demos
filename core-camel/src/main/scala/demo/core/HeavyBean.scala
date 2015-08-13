package demo.core

import com.typesafe.scalalogging.LazyLogging
import org.apache.camel.Message

/**
 * todo  
 */
class HeavyBean extends LazyLogging {
   def workHard(m : Message ) = {
     logger.info("starting to work hard for 10s")
     Thread.sleep(10000)
     logger.info("done with work")
   }

  
}
