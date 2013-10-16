package foo;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by rparree on 10/15/13.
 */
public class MyRouteBuilder  extends RouteBuilder{


    private String src;
    private String dst;

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    @Override
    public void configure() throws Exception {
        from(src).to(dst);
    }
}
