package demo.multicast.parallel

import org.apache.camel.Message
import grizzled.slf4j.Logging

/**
 * todo  
 */
class SomeBean extends Logging {
   def bar(m : Message ) = {
     info("bar invoked, sleeping 10s")
     Thread.sleep(10000)
     info("done with bar")
   }

  def baz(m : Message ) = {
    info("baz invoked, sleeping 30s")
    Thread.sleep(30000)
    info("done with baz")
  }
}
