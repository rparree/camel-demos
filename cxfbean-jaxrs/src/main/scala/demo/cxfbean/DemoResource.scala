package demo.cxfbean

import javax.ws.rs.{PathParam, Produces, GET, Path}


@Path("/example")   
class DemoResource {

  @GET
  @Produces(Array("text/plain"))
  @Path("calc/{value}")
  def test(@PathParam("value") i : Int) = i + i

}
