package aggregator

import org.apache.camel.spring.Main
import java.io.File
import grizzled.slf4j.Logging

/**
 * todo  
 */
object AggregatorApp extends App with Logging {




  checkFiles()
  val m = new Main()
  m.setApplicationContextUri("spring-camel-context.xml")
 // m.enableHangupSupport()
  m.setDuration(10000)
  m.start()

  Thread.sleep(10000)


  def checkFiles() = {
    val targetDir: File = new File("target")
    if (!targetDir.isDirectory) {
      error("Wrong working Directory" + new File(".").getCanonicalPath)
      sys.exit(0)
    } else {
      val scalaTargetDir = new File(targetDir, "scala-2.10")
      if (!scalaTargetDir.isDirectory) {
        error("this demo has the sbt output directory hard-coded in the endpoints, check the output directory")
        sys.exit(0)
      }

    }
  }
}
