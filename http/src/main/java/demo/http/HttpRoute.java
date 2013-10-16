package demo.http;


import demo.http.SomeProcessor;
import org.apache.camel.builder.RouteBuilder;

/**
 * todo
 */
public class HttpRoute extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("jetty:http://localhost:9090/myapp/myservice").streamCaching().
                log("received message ${body}").
                process(new SomeProcessor()).process(new SomeProcessor());

    }
}
