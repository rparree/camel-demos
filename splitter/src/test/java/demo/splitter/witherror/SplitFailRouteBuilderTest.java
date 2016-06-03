package demo.splitter.witherror;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.ArrayList;

/**
 * todo
 */
public class SplitFailRouteBuilderTest  extends CamelTestSupport {
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new SplitFailRouteBuilder();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        return context;
    }

    @Test
    public void success() throws Exception {
        ArrayList<Object> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("one");
        String r = template.requestBody("jms:splitFailRoute", list, String.class);
        System.out.println(r);

    }
    @Test
    public void fails() throws Exception {
        ArrayList<Object> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        String r = template.requestBody("jms:splitFailRoute", list, String.class);
        System.out.println(r);

    }
}