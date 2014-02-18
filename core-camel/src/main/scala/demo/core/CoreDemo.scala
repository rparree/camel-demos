package demo.core

import org.apache.camel.impl.DefaultCamelContext

import java.nio.file.{FileSystems, Files}


/**
 * todo  
 */
object CoreDemo extends  App {


  if (!Files.exists(FileSystems.getDefault.getPath("target", "scala-2.10", "classes", "camel", "in","file1.xml")))
    sys.error("wrong working directory")

  // setup camel context
  val context = new DefaultCamelContext()
  context.setTracing(true)
  context.addRoutes(new CoreDemoRouteBuilder())

  // Start and then stop the context
  context.start()
  Thread.sleep(60000)
  context.stop()

}
