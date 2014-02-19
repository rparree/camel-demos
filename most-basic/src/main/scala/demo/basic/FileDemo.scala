package demo.basic

import org.apache.camel.impl.DefaultCamelContext

import java.nio.file.{FileSystems, Files}


/**
 * todo  
 */
object FileDemo extends  App {


  if (!Files.exists(FileSystems.getDefault.getPath("target", "scala-2.10", "classes", "camel", "in","file1.xml")))
    sys.error("wrong working directory")

  // setup camel context
  val context = new DefaultCamelContext()
  context.addRoutes(new FileDemoRouteBuilder())

  // Start and then stop the context
  context.start()
  Thread.sleep(5000)
  context.stop()

}