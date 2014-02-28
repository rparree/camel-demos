package demo.cxf

import javax.jws.{WebService, Oneway, WebParam}
import javax.jws.soap.SOAPBinding
import javax.jws.soap.SOAPBinding.ParameterStyle
import javax.xml.bind.annotation.{XmlType, XmlAccessorType, XmlAccessType}

@WebService
@SOAPBinding(parameterStyle = ParameterStyle.BARE)
trait DemoEndpoint {


  @Oneway
  def _register(@WebParam(name="register") r : DemoRequest) : Unit

}

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
case class DemoRequest(name : String, email: String){
  def this() = this("","")
}
