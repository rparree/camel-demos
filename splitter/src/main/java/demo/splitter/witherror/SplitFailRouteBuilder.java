package demo.splitter.witherror;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultExchange;

/**
 * todo
 */
public class SplitFailRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        errorHandler(deadLetterChannel("jms:myDLQ")
                .useOriginalMessage()
                .maximumRedeliveries(3)
                .redeliveryDelay(500).backOffMultiplier(2));

        from("jms:splitFailRoute")
                .split(body()).shareUnitOfWork()
                .parallelProcessing()
                .setHeader("correlation", simple("${id}"))
                .choice()
                    .when(e -> e.getIn().getBody(String.class).length() == 3)
                        .process(e -> {
                            Thread.sleep(500);
                            e.getIn().setBody(e.getIn().getBody(String.class).toUpperCase());
                        })
                    .otherwise()
                        .process(e -> {
                            throw new RuntimeException("explicit error for test");
                        })
                .end()
                .aggregate(header("correlation"), (a, m) -> {
                    if (a == null) {
                        DefaultExchange e = new DefaultExchange(getContext());
                        e.getIn().setBody(m.getIn().getBody());
                        return e;
                    }
                    a.getIn().setBody(a.getIn().getBody(String.class) + " " + m.getIn().getBody(String.class));
                    return a;
                }).completionSize(3)
                .log("done");

    }
}
