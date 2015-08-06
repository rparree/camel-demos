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

  context.addRoutes(new RsRoute())

  context.start()
  Thread.sleep(50000)
  context.stop()




}
