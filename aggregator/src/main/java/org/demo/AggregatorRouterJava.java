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
        from("file:./target/classes/camel/in").convertBodyTo( Document.class ).
                aggregate(new BodyAppenderAggregator())
                .xpath("/item/@id")
                .completionTimeout(1000).
                to("file:./target/classes/camel/out");
    }
}
