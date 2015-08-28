package demo.http

import org.apache.camel.impl.DefaultCamelContext


/**
 * todo  
 */
object HttpApp extends App {

  val context = new DefaultCamelContext()
  context.setStreamCaching(true)
  context.addRoutes(new HttpRoute(context))

  context.start()
  println ("run the following command: curl -H \"Content-Type: text/plain\" --data \"hello\"  " +
    "http://localhost:9090/myapp/myservice")
  Thread sleep 60000
  context.stop()

}
