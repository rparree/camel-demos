package aggregator

import java.nio.file.{FileSystems, Files}

import org.apache.camel.spring.Main

/**
 * todo  
 */
object AggregatorApp extends App  {

  if (!Files.exists(FileSystems.getDefault.getPath("target", "classes", "camel", "in","file1.xml")))
    sys.error("wrong working directory")

  val m = new Main()
  m.setApplicationContextUri("spring-camel-context.xml")
  m.enableHangupSupport()
  m.setDuration(10000)
  m.run()

  
  
}
