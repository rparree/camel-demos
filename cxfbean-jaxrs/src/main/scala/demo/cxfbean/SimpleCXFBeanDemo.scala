package demo.cxfbean

import org.apache.camel.impl.{SimpleRegistry, DefaultCamelContext}
import scala.collection.JavaConversions._

/**
 * todo
 */
object SimpleCXFBeanDemo extends App{

  val context = new DefaultCamelContext()
  val registry = new SimpleRegistry
  registry += ("demoResource"->new DemoResource)

  context.setRegistry(registry)

  context.addRoutes(new org.apache.camel.scala.dsl.builder.RouteBuilder(){
    "jetty:http://localhost:9090?matchOnUriPrefix=true" to "cxfbean:demoResource"
  })

  context.start()
  Thread.sleep(50000)
  context.stop()




}
