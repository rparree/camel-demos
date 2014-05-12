package demo.basic

import org.apache.camel.impl.DefaultCamelContext

import java.nio.file.{FileSystems, Files}


/**
 * todo  
 */
object FileDemo extends  App {


  if (!Files.exists(FileSystems.getDefault.getPath("target", "scala-2.10", "classes", "camel", "in")))
    sys.error("wrong working directory")

  // setup camel context
  val context = new DefaultCamelContext()
  context.addRoutes(new FileDemoRouteBuilder())

  // Start and then stop the context
  context.start()
  Thread.sleep(500000)
  context.stop()

}
