package demo.multicast.java;

import org.apache.camel.builder.RouteBuilder;

/**
 * todo
 */
public class ParallelMultiCastJavaRoute extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("direct:foo").log("received message").
                multicast().parallelProcessing().
                   to(
                       "class:demo.multicast.parallel.SomeBean?method=baz",
                       "class:demo.multicast.parallel.SomeBean?method=bar"
                   ).
                   to("jms:C");
      from("jms:C").log("received on C");


    }
}
