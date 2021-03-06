package demo.basic

import org.apache.camel.impl.DefaultCamelContext

import java.nio.file.{FileSystems, Files}


/**
 * todo  
 */
object    FileDemo extends  App {




  // setup camel context
  val context = new DefaultCamelContext()
  context.addRoutes(new FileDemoRouteBuilder(context))

  // Start and then stop the context
  context.start()
  Thread.sleep(5000000)
  context.stop()

}
