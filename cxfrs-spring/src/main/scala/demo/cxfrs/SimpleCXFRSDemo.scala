package demo.cxfrs

import org.apache.camel.spring.Main

/**
 * todo
 */
object SimpleCXFRSDemo extends App{

  val m  = new Main()
  m.setApplicationContextUri("spring-camel-context.xml")
  m.enableHangupSupport()
  m.run()


}
