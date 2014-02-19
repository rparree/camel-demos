package org.demo;

import aggregator.BodyAppenderAggregator;
import org.apache.camel.builder.RouteBuilder;
import org.w3c.dom.Document;

/**
 * todo
 */
public class AggregatorRouterJava extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:./target/scala-2.10/classes/camel/in").convertBodyTo( Document.class ).
                aggregate(new BodyAppenderAggregator()).xpath("/item/@id").completionTimeout(1000).completionSize(0).
                to("file:./target/scala-2.10/classes/camel/out");
    }
}
