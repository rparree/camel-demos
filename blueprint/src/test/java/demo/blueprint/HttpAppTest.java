package demo.blueprint;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

import java.util.Dictionary;

/**
 * todo
 */
public class HttpAppTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/config.xml";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {
        props.put("uriSystemA", "mock:a");
        return "camel-demo";
    }

    @Test
    public void testRoute() throws InterruptedException {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:a");
        mockEndpoint.expectedBodiesReceived("jennifer@work.com");
        mockEndpoint.expectedMessageCount(1);

        template.sendBody("jetty:http://localhost:9090/myservice", "jennifer");

        assertMockEndpointsSatisfied();

    }


    

   
}
