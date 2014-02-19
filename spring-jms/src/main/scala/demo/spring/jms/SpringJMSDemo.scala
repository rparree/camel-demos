package demo.spring.jms

import org.apache.camel.spring.Main

/**
 * todo  
 */
object SpringJMSDemo extends App{

  val m  = new Main()
  m.setApplicationContextUri("spring-camel-context.xml")
  m.enableHangupSupport()
  m.run()


}
