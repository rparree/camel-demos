package demo.spring.jms

import java.nio.file.{FileSystems, Files}
import org.apache.camel.spring.Main

/**
 * todo  
 */
object SimpleSpringDemo extends App{

  if (!Files.exists(FileSystems.getDefault.getPath("target", "classes", "camel", "in")))
    sys.error("wrong working directory")

  val m  = new Main()
  m.setApplicationContextUri("spring-camel-context.xml")
  m.enableHangupSupport()
  m.run()


}
