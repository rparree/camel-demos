package demo.core

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.rest.RestBindingMode


/**
  * todo
  */
class RestDslRoutes extends RouteBuilder {
  override def configure(): Unit = {
    restConfiguration.component("jetty").bindingMode(RestBindingMode.auto).dataFormatProperty("mustBeJAXBElement", "false").dataFormatProperty("prettyPrint", "true").dataFormatProperty("json.out.disableFeatures", "WRITE_DATES_AS_TIMESTAMPS").contextPath("/").host("0.0.0.0").port("9898")

    rest("/demo").consumes("application/json").produces("application/json")
      .get("/status").description("Api status")
      .route()
      .to("direct:status")
      .endRest()
      .post("/update").description("Api status")
      .route()
      .to("sql:update foo set bar=10?dataSource=foo")
      .endRest()


    from("direct:status").transform.constant("Running")

  }
}
