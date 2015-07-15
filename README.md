# camel-demos


Organising my camel demos which are scattered over my hard drive, plus unifying them into a consistent set of demos.

You will need to install  [SBT 0.13](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html)

Example illustrating how to run a project (example uses **most-basic** project)

```bash
$ me@mylinux:camel-demos> sbt
Loading ...
[info] ...
...
> project most-basic
[info] Set current project to most-basic ...
> run
[info] Compiling 2 Scala sources to ...
[info] Running demo.basic.FileDemo
[info] ...
[info] 11:16:46,227 INFO  route1  - received file file1.xml
```

## Jolokia and Hawtio

When running from sbt the [jolokia JVM agent](https://jolokia.org/agent/jvm.html) is enabled. Use either a 
[standalone hawtio](http://hawt.io/getstarted/#standalone) or use the [Chrome plugin](https://chrome.google.com/webstore/detail/hawtio/aemcedanjggpkpeghpmlmioopekhhppl) to connect with the running process.

## List of demos

 1. **most-basic**: One of the most basic "Hello World"-type of demos.
 2. **simple-spring**: Similar "Hello World" using Spring
 3. **core-camel**: Similar to **most-basic**, but shows the default single-thread behaviour (tip surround the steps in `threads(5) { ... }`) or add `?concurrentConsumers=5` to teh URI and includes a unit test.  
 4. **splitter**: Simple demo using xpath based splitter
 5. **aggregator**: Aggregates an XML message. There is also a Java implementation of the same route, just change the Spring configuration to use the Java equivalent
 6. **simple-ftp**: Idempotent ftp consumer
 7. **spring-bean**: Shows the use of the bean component in conjunction with the Spring Registry (demo uses a unit test)
 8. **seda-inout**: Shows an netty:tcp endpoint connecting to a seda endpoint inout (for the client open a telnet session: `telnet localhost 7090`)
 9. **jetty-http**: Simple http endpoint, use for example curl to send data:
 ```bash
     me@my-linux> curl -H "Content-Type: text/plain" --data "hello"  http://localhost:9090/myapp/myservice
 ```
 10. **cxf-spring**: CXF example processing JAX-WS SOAP/HTTP message to JSON/FILE (wsdl available at http://localhost:9090/demo?wsdl)
 11. **cxfrs**: CXFRS demo. Consumes/Produces XML or JSON. CURL commands available in `src/test/bash` directory
 12. **cxfbean-jaxrs**; Simple cxfbean demo implementing a JAXRS services (test url http://localhost:9090/example/calc/10 )
 13. **pipeline-processors-inout**: Illustrates the behaviour of setting the `in` or `out` bodies on pipeline processors (only contains a "unit test")
 14. **simple-jms**: Uses invm JMS (also shows CBR to route xml and csv files)
 15. **spring-jms** Illustrates configuration of JMS in Spring
 16. **parallelMulticast**: Illustrated (parallel) multicast. A Java version is also included
 17. **osgi-spring**. This demo has its own  ([readme](osgi-spring/readme.md))
 18. **blueprint** Simple Camel blueprint demo with inlined route. Includes a test using mocks











