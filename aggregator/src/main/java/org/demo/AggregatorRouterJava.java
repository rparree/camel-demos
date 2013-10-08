package org.demo;

import aggregator.BodyAppenderAggregator;
import org.apache.camel.builder.RouteBuilder;

/**
 * todo
 */
public class AggregatorRouterJava extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:./target/test-classes/camel/in").convertBodyTo( String.class ).
                aggregate(new BodyAppenderAggregator()).xpath("/item/@id").completionTimeout(1000).completionSize(0).
                to("file:./target/test-classes/camel/out");
    }
}
