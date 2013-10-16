package demo.file;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by rparree on 10/16/13.
 */
public class FileDemoTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new FileDemoRouteBuilder().builder();
    }

    @Test
    public void testName() throws Exception {
        template.sendBodyAndHeader("file:./target/scala-2.10/classes/camel/in", "hi", Exchange.FILE_NAME, "hello.txt");
        Thread.sleep(15000);



    }
}
