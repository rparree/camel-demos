package demo.file

import org.apache.camel.Message
import grizzled.slf4j.Logging

/**
 * todo  
 */
class HeavyBean extends Logging {
   def workHard(m : Message ) = {
     info("starting to work hard for 10s")
     Thread.sleep(10000)
     info("done with work")
   }

  
}
