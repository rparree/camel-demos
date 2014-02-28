package demo.cxf

import java.nio.file.{FileSystems, Files}
import org.apache.camel.spring.Main

/**
 * todo
 */
object SimpleCXFDemo extends App{

  val m  = new Main()
  m.setApplicationContextUri("spring-camel-context.xml")
  m.enableHangupSupport()
  m.run()


}
