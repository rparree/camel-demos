package demo.core

import org.apache.camel.impl.DefaultCamelContext

import java.nio.file.{FileSystems, Files}


/**
 * todo  
 */
object RestDemo extends  App {



  // setup camel context
  val context = new DefaultCamelContext()
  context.setTracing(true)
  context.addRoutes(new RestDslRoutes())

  // Start and then stop the context
  context.start()
  Thread.sleep(60000)
  context.stop()

}
