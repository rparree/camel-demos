package demo.core;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by rparree on 10/16/13.
 */
public class CoreDemoTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new CoreDemoRouteBuilder().builder();
    }

    @Test
    public void testName() throws Exception {
        template.sendBodyAndHeader("file:./target/scala-2.10/classes/camel/in", "hi", Exchange.FILE_NAME, "hello-with-hi.txt");
        Thread.sleep(15000);



    }
}
