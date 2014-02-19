package aggregator

import org.apache.camel.spring.Main
import java.io.File
import grizzled.slf4j.Logging
import java.nio.file.{FileSystems, Files}

/**
 * todo  
 */
object AggregatorApp extends App with Logging {

  if (!Files.exists(FileSystems.getDefault.getPath("target", "scala-2.10", "classes", "camel", "in","file1.xml")))
    sys.error("wrong working directory")

  val m = new Main()
  m.setApplicationContextUri("spring-camel-context.xml")
  m.enableHangupSupport()
  m.setDuration(10000)
  m.run()

  
  
}
