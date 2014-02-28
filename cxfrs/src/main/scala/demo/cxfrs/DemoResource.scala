package demo.cxfrs

import javax.ws.rs._
import javax.xml.bind.annotation.{XmlAccessType, XmlAccessorType, XmlRootElement}



@Path("/demo")
class DemoResource {


  @POST
  @Produces(Array("application/json","application/xml"))
  @Consumes(Array("application/xml","application/json"))
  @Path("register")
  def _register(r: Registration) : ActivationCode = null
}



@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
case class Registration(username : String, email : String){
  def this() = this("","")

  def genActivationCode = new ActivationCode(this.username.hashCode.abs)
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
case class ActivationCode(code : Int){
  def this() = this(0)  
}
