package demo.blueprint;

import org.apache.camel.CamelContext;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

import java.util.Dictionary;

/**
 * todo
 */
public class HttpAppTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "config.xml";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {
        props.put("systemA", "mock:a");
        return "camel-demo";
    }

    @Test
    public void testRoute() throws InterruptedException {
        getMockEndpoint("mock:a").expectedBodiesReceived("jennifer@work.com");

        template.sendBody("jetty:http://localhost:9090/myapp/myservice", "jennifer");

        assertMockEndpointsSatisfied();

    }

   
}
