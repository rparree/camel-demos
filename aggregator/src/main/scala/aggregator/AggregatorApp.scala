package aggregator

import org.apache.camel.spring.Main

/**
 * todo  
 */
object AggregatorApp extends App{
  val m  = new Main()
  m.setApplicationContextUri("spring-camel-context.xml")
  m.enableHangupSupport()
  m.start()
}
