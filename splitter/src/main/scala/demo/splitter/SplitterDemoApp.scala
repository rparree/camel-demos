package demo.splitter;

import org.apache.camel.impl.DefaultCamelContext

object SplitterDemoApp extends App{

    val camelContext = new DefaultCamelContext()

    camelContext.addRoutes(new SplitterRoute())

    camelContext.start
    Thread.sleep(5000)
    camelContext.stop



 


}

